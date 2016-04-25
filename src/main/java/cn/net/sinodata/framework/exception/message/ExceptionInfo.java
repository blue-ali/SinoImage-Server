package cn.net.sinodata.framework.exception.message;

public class ExceptionInfo {

	private static final String STANDARD_ERROR = "SINO-";
	
	private int errorCode;

	private String name;

	private String exceptionInfo;

	public ExceptionInfo() {
	}

	public ExceptionInfo(int errorCode, String name, String info) {
		this.errorCode = errorCode;
		this.name = name;
		this.exceptionInfo = info;
	}

	public String getErrorCode() {
		return STANDARD_ERROR + errorCode;
	}

	public long getIntErrorCode() {
		try {
//			return Integer.parseInt(errorCode);
			return errorCode;
		} catch (Exception e) {
			return -1;
		}
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExceptionInfo() {
		return exceptionInfo;
	}

	public void setExceptionInfo(String info) {
		this.exceptionInfo = info;
	}


	

}
