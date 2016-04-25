package cn.net.sinodata.cm.content.jcr;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.jcr.Session;
import javax.jcr.nodetype.NodeType;
import javax.jcr.nodetype.NodeTypeIterator;
import javax.jcr.nodetype.NodeTypeManager;

import org.apache.jackrabbit.commons.cnd.CndImporter;

public class NodeTypeRegister {

	public static void RegisterCustomNodeTypes(Session session, String cndFileName) throws Exception {
		// Register the custom node types defined in the CND file, using JCR
		// Commons CndImporter
		Reader in=new InputStreamReader(NodeTypeRegister.class.getClass().getResourceAsStream(cndFileName),"UTF-8");
		NodeType[] nodeTypes = CndImporter.registerNodeTypes(in,session, true);
		
		for (NodeType nt : nodeTypes) {
			System.out.println("Registered: " + nt.getName());
		}
		
		// You can also use JCR NodeTypeManager from the Workspace.
		NodeTypeManager manager = session.getWorkspace().getNodeTypeManager();
		NodeTypeIterator itor = manager.getMixinNodeTypes();
		while (itor.hasNext()) {
			NodeType type = (NodeType) itor.next();
			System.out.println(type.getName());
		}
	}
	
	public static void unregist(Session session) throws Exception{
		NodeTypeManager manager = session.getWorkspace().getNodeTypeManager();
		manager.unregisterNodeType("file:suffixable");
	}
}
