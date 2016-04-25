package cn.net.sinodata.framework.exception;

import java.io.Serializable;

public class SinoRuntimeException extends RuntimeException implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int exCode;
	private String exMsg;
	
	public SinoRuntimeException(Throwable e){
		super(e);
	}
	
	public SinoRuntimeException(int exCode){
		super(ExceptionMsgLoad.getErrCode(exCode) + ": " + ExceptionMsgLoad.getErrInfo(exCode));
		this.exCode = exCode;
	}
	
	/** 工作流运行时异常 */
	public SinoRuntimeException(int exCode, Throwable e){
		super(ExceptionMsgLoad.getErrCode(exCode) + ": " + ExceptionMsgLoad.getErrInfo(exCode), e);
	}
	
	public SinoRuntimeException(int exCode, String exMsg){
		super(ExceptionMsgLoad.getErrCode(exCode) + ": " + ExceptionMsgLoad.getErrInfo(exCode));
		this.exCode = exCode;
		this.exMsg = exMsg;
	}
	
	public SinoRuntimeException(int exCode, String exMsg, Throwable e){
		super(ExceptionMsgLoad.getErrCode(exCode) + ": " + exMsg, e);
		this.exCode = exCode;
		this.exMsg = exMsg;
	}
	
	public String getExMsg(){
		return this.exMsg;
	}
	
	public int getExCode(){
		return this.exCode;
	}

}
