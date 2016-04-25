package cn.net.sinodata.framework.log.loadproperties;

/**
 *   @类名： Log4jConfig
 *   @描述： log4j日志配置类,读取的是log4j.properties文件的transaction配置
 *   @作者： 杨文胜
 *   @生成时间： 2014年1月2日 下午2:57:54
 *   @修改人：
 *   @修改时间：  
 **/
public class Log4jConfig {
	/**
	 * @属性说明:日志级别
	 */
	public static final String TRANSACTION_LEVEL = LoadLog4jProperties.getValue("log4j.appender.transaction.level");
	/**
	 * @属性说明:交易日志存储目录
	 */
	public static final String TRANSACTION_FILEDIR = LoadLog4jProperties.getValue("log4j.appender.transaction.FileDir");
	/**
	 * @属性说明:是否附加
	 */
	public static final String TRANSACTION_APPEND = LoadLog4jProperties.getValue("log4j.appender.transaction.Append");
	/**
	 * @属性说明:最大文件大小
	 */
	public static final String TRANSACTION_MAXFILESIZE = LoadLog4jProperties.getValue("log4j.appender.transaction.MaxFileSize");
	/**
	 * @属性说明:编码
	 */
	public static final String TRANSACTION_ENCODING = LoadLog4jProperties.getValue("log4j.appender.transaction.encoding");
	/**
	 * @属性说明:最大回滚文件数目
	 */
	public static final String TRANSACTION_MAXBACKUPINDEX = LoadLog4jProperties.getValue("log4j.appender.transaction.MaxBackupIndex");
	/**
	 * @属性说明:输出格式
	 */
	public static final String TRANSACTION_CONVERSIONPATTERN = LoadLog4jProperties.getValue("log4j.appender.transaction.layout.ConversionPattern");
}
