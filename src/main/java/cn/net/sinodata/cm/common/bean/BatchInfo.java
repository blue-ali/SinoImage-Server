package cn.net.sinodata.cm.common.bean;

import java.util.ArrayList;
import java.util.List;

public class BatchInfo {
	/** 批次号 */
	private String batchId;
    /** 柜员机构号*/
	private String orgId;
    /** 业务系统编号*/
	private String sysId;
    /** 创建人 */
    private String tellerNo;
    /** 创建时间 */
    private String createTime;
    /** 最后创建时间 */
    private String lastModTime;
    /** 来源IP */
    private String sourceIp;
    /** 批次状态 */
    private String state;
    /** 同步状态 */
    private String syncState;
    /** 同步类型 */
    private String syncType;
    /** 同步时间 **/
    private String syncTime;
    /** 同步所用时间 **/
    private String syncTotalTime;
    /** 业务流水号 */
    private String serialNumber;
    /** 备用字段 */
    private String spareOne;
    /** 影像信息集合 */
    private List<FileInfo> files = new ArrayList<FileInfo>();
    
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getTellerNo() {
		return tellerNo;
	}
	public void setTellerNo(String tellerNo) {
		this.tellerNo = tellerNo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastModTime() {
		return lastModTime;
	}
	public void setLastModTime(String lastModTime) {
		this.lastModTime = lastModTime;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSyncState() {
		return syncState;
	}
	public void setSyncState(String syncState) {
		this.syncState = syncState;
	}
	public String getSyncType() {
		return syncType;
	}
	public void setSyncType(String syncType) {
		this.syncType = syncType;
	}
	public String getSyncTime() {
		return syncTime;
	}
	public void setSyncTime(String syncTime) {
		this.syncTime = syncTime;
	}
	public String getSyncTotalTime() {
		return syncTotalTime;
	}
	public void setSyncTotalTime(String syncTotalTime) {
		this.syncTotalTime = syncTotalTime;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getSpareOne() {
		return spareOne;
	}
	public void setSpareOne(String spareOne) {
		this.spareOne = spareOne;
	}
	public List<FileInfo> getFiles() {
		return files;
	}
	public void setFiles(List<FileInfo> files){
		this.files = files;
	}
	public void addFile(FileInfo fileInfo){
		files.add(fileInfo);
	}
	
}
