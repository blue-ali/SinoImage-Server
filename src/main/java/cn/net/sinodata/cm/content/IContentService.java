package cn.net.sinodata.cm.content;

import javax.jcr.Node;

import cn.net.sinodata.cm.content.jcr.model.JcrContent;

public interface IContentService {

	public Node ensureFolder(final String path);
	
	public void addContent(final String path, final JcrContent jcrContent);
	
	public JcrContent getContent(final String path);
	
	public boolean isNodeExist(String path);
	/**
	 * JCR用
	 */
	public void regist();
}
