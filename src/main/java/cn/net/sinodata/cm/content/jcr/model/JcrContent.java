package cn.net.sinodata.cm.content.jcr.model;

import java.io.Serializable;
import java.util.Calendar;

public class JcrContent extends JcrNode implements Serializable{
	
	private static final long serialVersionUID = -5712762771410466392L;
	public static final String PROP_DATA = "jcr:data";	//file inputstream
	public static final String PROP_MICO_DATA = "jcr:thumbnail";
	public static final String PROP_MIMETYPE = "jcr:mimeType";
	public static final String PROP_LAST_MODIFIED = "jcr:lastModified";
	public static final String PROP_FILE_ID = "jcr:fileId";
	public static final String PROP_FILE_SUFFIX="cm:suffix";
	public static final String PROP_FILE_NAME="cm:fileName";
	
	public static final String CONTENT_NODE_NAME = "jcr:content";
	
	
	private Calendar lastModified;
	private byte[] data;
	private String type = JcrNodeType.TYPE_CONTENT;
	private String mimeType;
	private byte[] thumbnailDate;
	private String fileId;
	private String suffix;
	private String fileName;
	
	public Calendar getLastModified() {
		return lastModified;
	}

	public void setLastModified(Calendar lastModified) {
		this.lastModified = lastModified;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getThumbnailDate() {
		return thumbnailDate;
	}

	public void setThumbnailDate(byte[] thumbnailDate) {
		this.thumbnailDate = thumbnailDate;
	}
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("}");
//		sb.append("id: " + id + " | ");
		sb.append("fileId: " + fileId + "." + suffix + " | ");
		sb.append("jcr:mimeType: " + mimeType + " | ");
		sb.append("jcr:lastModified: " + lastModified + " | ");
		sb.append("bytes: " + data );
		return sb.toString();
	}
}
