/**
 * 
 */
package cn.net.sinodata.cm.util.bean;

/**
 * @author Administrator
 *
 */
public class ColumStructure {
	private String columName;
	private String columType;
	private boolean isPk;
	/**
	 * @return the columName
	 */
	public String getColumName() {
		return columName;
	}
	/**
	 * @param columName the columName to set
	 */
	public void setColumName(String columName) {
		this.columName = columName;
	}
	/**
	 * @return the columType
	 */
	public String getColumType() {
		return columType;
	}
	/**
	 * @param columType the columType to set
	 */
	public void setColumType(String columType) {
		this.columType = columType;
	}
	/**
	 * @return the isPk
	 */
	public boolean isPk() {
		return isPk;
	}
	/**
	 * @param isPk the isPk to set
	 */
	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}
	
}
