/**
 * 
 */
package cn.net.sinodata.cm.util;

import java.util.HashMap;
import java.util.Map;

import cn.net.sinodata.cm.common.bean.BatchInfo;
import cn.net.sinodata.cm.common.bean.FileInfo;
import net.sf.json.JSONObject;

/**
 * @author manan
 *
 */
public class ImageJsonUtil {

	public static BatchInfo jsonStr2BatchInfo(String json){
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("files", FileInfo.class);
		return (BatchInfo) JSONObject.toBean(JSONObject.fromObject(json), BatchInfo.class, classMap);
	}
}
