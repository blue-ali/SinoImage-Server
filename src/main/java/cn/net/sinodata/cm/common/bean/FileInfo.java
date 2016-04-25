package cn.net.sinodata.cm.common.bean;

public class FileInfo {

	/** 影像ID */
    private String fileId;
    /** 本地路径，用于上传，只能set */
    private String localPath;
	/** 提交时间 */
    private String createTime;
    /** 创建者 */
    private String creator;
    /** 最后修改时间*/
    private String lastModTime;
    /** 版本信息， 用于下载，只能get */
    private int version;
    /** 影像名称 */
    private String fileName;
    /** 文件类型 */
    private String mimeType;
    /** 影像大小 */
    private String fileSize;
    /** MD5码 */
    private String fileMd5;
    
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getLastModTime() {
		return lastModTime;
	}
	public void setLastModTime(String lastModTime) {
		this.lastModTime = lastModTime;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileMd5() {
		return fileMd5;
	}
	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}
    
//	@Override
//    public int hashCode() {
//	int h = 17;
//	if (imgName != null && imgNo != null) {
//	    return imgName.hashCode() + imgNo.hashCode() + 31 * h;
//	} else {
//	    return this.getClass().getName().hashCode()+ 31 * h;
//	}
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//	if (obj instanceof ImageInfo) {
//	    return this.hashCode() == obj.hashCode();
//	} else {
//	    return false;
//	}
//    }

}
