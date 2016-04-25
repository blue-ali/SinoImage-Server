package cn.net.sinodata.framework.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.net.sinodata.framework.exception.message.ExceptionIDs;

/**
 *   @类名： SinoflowRuntimeException
 *   @描述： 平台异常处理类
 *   @作者： 梁乾公
 *   @生成时间： 2012-7-9 下午7:10:43
 *   @修改人： 
 *   @修改时间：  
 **/
@SuppressWarnings("serial")
public class SinoException extends Exception implements Serializable{
	
	/** 异常编号 */
	private int exCode;
	
	/** 异常详细内容 */
	private String exMsg;
	
	public SinoException(){}
	
	/** 未检查异常 */
	public SinoException(Throwable e){
		super(e);
	}
	
	/** 根据错误码，抛出指定错误码的错误信息 */
	public SinoException(int exCode){
		super(ExceptionMsgLoad.getErrCode(exCode) + ": " + ExceptionMsgLoad.getErrInfo(exCode));
		this.exCode = exCode;
	}
	
	/** 抛出指定的错误信息 */
	public SinoException(String errMsg){
		super(errMsg);
	}
	
	/** 抛出指定的错误信息 */
	public SinoException(String errMsg, Throwable e){
		super(errMsg, e);
	}
	
	/** 根据错误码，抛出指定错误码的错误信息 */
	public SinoException(int exCode, Throwable e){
		super(ExceptionMsgLoad.getErrCode(exCode) + ": " + ExceptionMsgLoad.getErrInfo(exCode), e);
		this.exCode = exCode;
	}
	
//	/** 根据错误码和错误码附加信息，抛出指定错误码的错误信息 */
//	public SinoException(String exCode, String msgType, Object... objects){
//		super(ExceptionMsgLoad.getErrCode(exCode) + ": " + ExceptionMsgLoad.getErrInfo(exCode) + "," + ExceptionMsgLoad.getErrMsg(msgType, objects));
//		this.exCode = exCode;
//	}
	
	/** 根据错误码和错误码附加信息，抛出指定错误码的错误信息 */
	public SinoException(int exCode, Throwable e, Object... objects){
		super(ExceptionMsgLoad.getErrCode(exCode) + ": " + ExceptionMsgLoad.getErrMsg(exCode, objects), e);
		this.exCode = exCode;
	}
	
	/**  */
	public SinoException(int exCode, Object... objects){
		super(ExceptionMsgLoad.getErrCode(exCode) + ": " + ExceptionMsgLoad.getErrMsg(exCode, objects));
		this.exCode = exCode;
	}
	
//	public SinoException(int exCode, String exMsg, Object... objects){
//		super(ExceptionMsgLoad.getErrCode(exCode) + ": " + exMsg + ": "+ ExceptionMsgLoad.getErrMsg(exCode, objects));
//		this.exCode = exCode;
//	}
	
	/** 抛出指定的错误码和错误信息 */
	public SinoException(int exCode, String exMsg, Throwable e){
		super(ExceptionMsgLoad.getErrCode(exCode) + ": " + exMsg, e);
		this.exCode = exCode;
		this.exMsg = exMsg;
	}
	
	public int getExCode() {
		return exCode;
	}

	public String getExMsg() {
		return exMsg;
	}
	
}
