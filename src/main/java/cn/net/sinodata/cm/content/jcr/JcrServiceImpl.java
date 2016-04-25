package cn.net.sinodata.cm.content.jcr;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.ValueFactory;

import org.springframework.stereotype.Service;
import org.springmodules.jcr.JcrCallback;
import org.springmodules.jcr.JcrTemplate;

import cn.net.sinodata.cm.content.IContentService;
import cn.net.sinodata.cm.content.jcr.model.JcrContent;
import cn.net.sinodata.cm.content.jcr.model.JcrNodeType;
import cn.net.sinodata.framework.log.SinoLogger;
import cn.net.sinodata.framework.util.FileUtil;

@Service("jcrService")
public class JcrServiceImpl implements IContentService {
	
	private final SinoLogger logger = SinoLogger.getLogger(this.getClass());

	@Resource
	private JcrTemplate jcrTemplate;

	/**
	 * 注册自定义属性
	 */
	public void regist(){
		jcrTemplate.execute(new JcrCallback() {
			
			public Object doInJcr(Session session) throws IOException, RepositoryException {
				try {
					NodeTypeRegister.RegisterCustomNodeTypes(session,"/resources/jcr/custom-attribute.cnd");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
	
	/**
	 *  /SysId/OrgId/BatchId
	 * 
	 */
	public Node ensureFolder(final String folderPath){
		return (Node) jcrTemplate.execute(new JcrCallback() {

			public Object doInJcr(Session session) throws IOException, RepositoryException {
				return ensureFolder(session, folderPath);
			}
		});
	}
	
	/**
	 * FileId(nt:file)/FileContent(nt:resource)
	 * @param parentNode
	 * @param jcrContent
	 */
	public void addContent(final String path, final JcrContent jcrContent){
		jcrTemplate.execute(new JcrCallback() {

			public Object doInJcr(Session session) throws IOException, RepositoryException {
				Node parentNode = ensureFolder(session, path);
				String fileId = jcrContent.getFileId();
				Node fileNode = parentNode.addNode(fileId, JcrNodeType.TYPE_FILE);
				fileNode.addMixin("cm:suffixable");
				fileNode.setProperty(JcrContent.PROP_FILE_SUFFIX, jcrContent.getSuffix());
				fileNode.setProperty(JcrContent.PROP_FILE_NAME, jcrContent.getFileName());
				Node contentNode = fileNode.addNode(JcrContent.CONTENT_NODE_NAME, JcrNodeType.TYPE_CONTENT);
				jcrContent2Node(session, jcrContent, contentNode);
				session.save();
				return null;
			}
		});
	}
	
	public JcrContent getContent(final String path){
		return (JcrContent) jcrTemplate.execute(new JcrCallback() {
			
			public Object doInJcr(Session session) throws IOException, RepositoryException {
				Node fileNode = ensureFolder(session, path);
				return node2JcrContent(fileNode);
			}
		});
	}
	
	private Node ensureFolder(Session session, String folderPath) throws RepositoryException {
		Node root = session.getRootNode();
        if(folderPath == null || folderPath.equals("")){
        	return root;
        }
        Node folder = null;
        try {
        	System.out.println("path: " + folderPath);
            folder = session.getNode(folderPath);
        } catch (PathNotFoundException e) {
        	logger.debug(folderPath + " not exists, create folder...");
            folder = createFolder(session, root, folderPath);
        }
        return folder;
	}
	
	private synchronized Node createFolder(Session session, Node root, String folderPath) throws RepositoryException{
        String[] splits = folderPath.substring(1).split("/");
        Node temp = root;
        for(String split : splits){
            try{
                temp = temp.getNode(split);
            }catch (PathNotFoundException e) {
                temp = temp.addNode(split, JcrNodeType.TYPE_FOLDER);
            }
        }
        session.save();
        return temp;
    }

	private void jcrContent2Node(Session session, JcrContent jcrContent, Node node) throws RepositoryException {

		node.setProperty(JcrContent.PROP_LAST_MODIFIED, jcrContent.getLastModified());
		node.setProperty(JcrContent.PROP_MIMETYPE, jcrContent.getMimeType());

		byte[] bytes = jcrContent.getData();
		InputStream is = new ByteArrayInputStream(bytes);
		ValueFactory valueFactory = session.getValueFactory();
		Binary binary = valueFactory.createBinary(is);
		node.setProperty(JcrContent.PROP_DATA, binary);
	}
	
	protected JcrContent node2JcrContent(Node fileNode) throws RepositoryException, IOException {
		Node contentNode = fileNode.getNode(JcrContent.CONTENT_NODE_NAME);
		PropertyIterator pi = fileNode.getProperties();
		while (pi.hasNext()) {
			Property prop = (Property) pi.next();
			if(prop.getName().equals("jcr:mixinTypes")){
				Value[] values = prop.getValues();
				for (Value value : values) {
					System.out.println(prop.getName() + ": " + value.getString());
				}
			}else{
			System.out.println(prop.getName() + ": " + prop.getString());
			}
		}

		JcrContent jcrContent = new JcrContent();
		jcrContent.setFileId(fileNode.getName());
		jcrContent.setFileName(fileNode.getProperty(JcrContent.PROP_FILE_NAME).getString());
//		jcrContent.setSuffix(fileNode.getProperty(JcrContent.PROP_FILE_SUFFIX).getString());
		Binary binary = contentNode.getProperty(JcrContent.PROP_DATA).getBinary();
		InputStream is = binary.getStream();
		byte[] bytes = FileUtil.input2byte(is);
		jcrContent.setData(bytes);
		jcrContent.setLastModified(contentNode.getProperty(JcrContent.PROP_LAST_MODIFIED).getDate());
		jcrContent.setMimeType(contentNode.getProperty(JcrContent.PROP_MIMETYPE).getString());
        return jcrContent;
    }

	public boolean isNodeExist(final String path) {
		return (Boolean) jcrTemplate.execute(new JcrCallback() {
			
			public Object doInJcr(Session session) throws IOException, RepositoryException {
				return session.nodeExists(path);
			}
		});
	}
	
}
