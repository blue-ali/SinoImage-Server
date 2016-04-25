package cn.net.sinodata.framework.exception;

import cn.net.sinodata.framework.exception.message.ErrorMsgFormat;
import cn.net.sinodata.framework.exception.message.ExceptionInfo;
import cn.net.sinodata.framework.exception.message.ExceptionMap;

public class ExceptionMsgLoad {
	
	/** 根据异常码获取异常信息 */
	public static String getErrInfo(int exCode){
		ExceptionInfo exInfo = ExceptionMap.getInstance().getMap().get(exCode);
		return exInfo.getExceptionInfo();
	}
	
	/** 获取异常码 */
	public static String getErrCode(int exCode){
		ExceptionInfo exInfo = ExceptionMap.getInstance().getMap().get(exCode);
		return exInfo.getErrorCode();
	}
	
	/** 获取ExceptionInfo信息 */
	public static String getErrInfoString(ExceptionInfo info){
		return info.getErrorCode() + ": " + info.getExceptionInfo();
	}
	
	/** 获取标准异常信息 */
	public static String getErrMsg(ExceptionInfo info, int errorCode, Object...objs){
		return ExceptionMsgLoad.getErrInfoString(info)+ ": " + ExceptionMsgLoad.getErrMsg(errorCode, objs);
	}
	
	
	/** 根据异常码，以及异常所对应的数据拼装并返回异常信息 */
	public static String getErrMsg(int exCode, Object... objs){
//		return ErrorMsgFormat.format(msgType, objs);
		return ErrorMsgFormat.format(ExceptionMsgLoad.getErrInfo(exCode), objs);
	}

}
