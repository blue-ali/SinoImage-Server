/**
 * 
 */
package cn.net.sinodata.cm.response;

import java.util.HashMap;
import java.util.Map;

import cn.net.sinodata.framework.exception.message.ExceptionInfo;

/**
 * @author manan
 *
 */
public class ResponseCode {
	
	protected static Map<String, String> codeMap = new HashMap<String, String>();
	/** INFO **/
	public static final String INFO_SUCCESS = "BPCI0000";	//操作成功
	public static final String INFO_NO_TASK = "BPCI0001";	//当前无任务
	public static final String INFO_NO_JOURNALNO = "BPCI0002";	//无该笔流水号
	/** WARN **/
	public static final String WARN_NO_PERMISSION_TO_SUSPEND = "BPCW0001";	//没有权限执行挂起操作
	/** ERROR **/
	public static final String ERROR_START_PROCESS = "BPCE0001";	//发起流程失败
	public static final String ERROR_COMPLETE_TASK = "BPCE0002";	//提交任务失败
	public static final String ERROR_AUTO_TAKE_TASK = "BPCE0003";	//自动获取任务失败
	public static final String ERROR_TAKE_TASK = "BPCE0004";	//获取任务失败
	
	protected static String[][] respCodes = new String[][]{{INFO_SUCCESS, "操作成功"},
		{INFO_NO_TASK, "当前无任务"},
		{INFO_NO_JOURNALNO, "无该笔流水号"},
		{WARN_NO_PERMISSION_TO_SUSPEND, "没有权限执行挂起操作"},
		{ERROR_START_PROCESS, "发起流程失败"},
		{ERROR_COMPLETE_TASK, "提交任务失败"},
		{ERROR_AUTO_TAKE_TASK, "自动获取任务失败"},
		{ERROR_TAKE_TASK, "获取任务失败"}
	};
	
	protected static void initMap(){
		for (int i = 0; i < respCodes.length; i++) {
			String respCode = (String) respCodes[i][0];
			codeMap.put(respCode, respCodes[i][1]);
		}
		System.out.println(codeMap);
	}

}
