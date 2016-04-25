package cn.net.sinodata.framework.log.loadproperties;

import java.io.File;
import java.util.ResourceBundle;

/**
 *   @类名： LoadLog4jProperties
 *   @描述： log4j日志配置类加载类
 *   @作者： 杨文胜
 *   @生成时间： 2014年1月2日 下午2:58:20
 *   @修改人：
 *   @修改时间：  
 **/
public class LoadLog4jProperties {
	/**
	 * @属性说明: log4j的路径
	 */
	private static final String LOG4JPATH = "resources" + File.separator + "log4j" + File.separator + "log4j";
	/**
	 * @属性说明：单例模式加载log4j配置文件 
	 **/
	private static ResourceBundle resouceBundle = null;
	/**
	 *   @生成时间： 2014年1月2日 下午3:01:53
	 *   @方法说明： 加载log4j文件
	 *   @参数：
	 *   @返回值： 
	 *   @异常：
	 **/
	public static ResourceBundle getResourceBundle(){
		if(resouceBundle == null){
			resouceBundle = ResourceBundle.getBundle(LOG4JPATH);
		}
		return resouceBundle;
	}
	/**
	 *   @生成时间： 2014年1月2日 下午3:02:52
	 *   @方法说明： 根据配置项获取值
	 *   @参数：
	 *   @返回值： 
	 *   @异常：
	 **/
	public static String getValue(String key){
		String value = null;
		try{
			value = getResourceBundle().getString(key);
		}catch (Exception e) {
			System.err.println("获取配置文件值失败!");
		}
		return value;
	}
}
