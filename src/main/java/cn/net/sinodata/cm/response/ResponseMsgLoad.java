/**
 * 
 */
package cn.net.sinodata.cm.response;

/**
 * @author manan
 *
 */
public class ResponseMsgLoad {

	public static String getResponseDesc(String respCode){
		return ResponseCode.codeMap.get(respCode);
	}
	
	public static void initMap(){
		ResponseCode.initMap();
	}
}
