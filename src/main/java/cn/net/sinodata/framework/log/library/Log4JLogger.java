package cn.net.sinodata.framework.log.library;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import cn.net.sinodata.framework.log.SinoLogger;
import cn.net.sinodata.framework.log.loadproperties.Log4jConfig;

/**
 *   @类名： Log4JLogger
 *   @描述： Log4J日志封装类
 *   @作者： 杨文胜
 *   @生成时间： 2013-10-31 下午02:29:35
 *   @修改人：
 *   @修改时间：  
 **/
public class Log4JLogger extends SinoLogger {
	/**
	 * @属性说明：log4j 日志类
	 **/
	private Logger log4jLogger;

	/**
	 *   @生成时间： 2013-10-31 下午02:33:45
	 *   @方法描述： 默认构造方法
	 *   @param
	 **/
	public Log4JLogger() {
		super();
	}

	/**
	 * Constructor invoked by the getLoggerImpl method to return a logger for a
	 * particular class
	 */
	/**
	 *   @生成时间： 2013-10-31 下午02:34:09
	 *   @方法描述： 私有构造方法
	 *   @param
	 **/
	private Log4JLogger(Logger l) {
		super();
		log4jLogger = l;
	}

	/**
	 *   @生成时间： 2013-10-31 下午02:36:30
	 *   @方法说明： debug message
	 *   @参数：
	 *   @返回值： 
	 *   @异常：
	 **/
	public void debug(Object message) {
		log4jLogger.debug(message);
	}

	/**
	 *   @生成时间： 2013-10-31 下午02:36:40
	 *   @方法说明： debug message and exception
	 *   @参数：
	 *   @返回值： 
	 *   @异常：
	 **/
	public void debug(Object message, Throwable t) {
		log4jLogger.debug(message, t);
	}

	/**
	 *   @生成时间： 2013-10-31 下午02:36:48
	 *   @方法说明： an error message
	 *   @参数：
	 *   @返回值： 
	 *   @异常：
	 **/
	public void error(Object obj) {
		if(obj instanceof Throwable)
			log4jLogger.error("", (Throwable) obj);
		else
			log4jLogger.error(obj);
	}

	/**
	 *   @生成时间： 2013-10-31 下午02:36:56
	 *   @方法说明： error message object and exception
	 *   @参数：
	 *   @返回值： 
	 *   @异常：
	 **/
	public void error(Object message, Throwable t) {
		log4jLogger.error(message, t);
	}

	/**
	 *   @生成时间： 2013-10-31 下午02:37:04
	 *   @方法说明： fatal message
	 *   @参数：
	 *   @返回值： 
	 *   @异常：
	 **/
	public void fatal(Object message) {
		log4jLogger.fatal(message);
	}

	/**
	 *   @生成时间： 2013-10-31 下午02:37:11
	 *   @方法说明： fatal message and exception
	 *   @参数：
	 *   @返回值： 
	 *   @异常：
	 **/
	public void fatal(Object message, Throwable t) {
		log4jLogger.fatal(message, t);
	}

	/**
	 *   @生成时间： 2013-10-31 下午02:37:20
	 *   @方法说明： an information message
	 *   @参数：
	 *   @返回值： 
	 *   @异常：
	 **/
	public void info(Object message) {
		log4jLogger.info(message);
	}

	/**
	 * Logs an information message and an exception
	 */

	/**
	 *   @生成时间： 2013-10-31 下午02:37:33
	 *   @方法说明： information message and exception
	 *   @参数：
	 *   @返回值： 
	 *   @异常：
	 **/
	public void info(Object message, Throwable t) {
		log4jLogger.info(message, t);
	}

	/**
	 *   @生成时间： 2013-10-31 下午02:37:43
	 *   @方法说明： warning message object
	 *   @参数：
	 *   @返回值： 
	 *   @异常：
	 **/
	public void warn(Object message) {
		log4jLogger.warn(message);
	}

	/**
	 *   @生成时间： 2013-10-31 下午02:37:54
	 *   @方法说明： warning message with exception
	 *   @参数：
	 *   @返回值： 
	 *   @异常：
	 **/
	public void warn(Object message, Throwable t) {
		log4jLogger.warn(message, t);
	}

	/**
	 *   @生成时间： 2013-10-31 下午02:38:03
	 *   @方法说明： 获取日志实现类
	 *   @参数：           cl代表类的类型
	 *   @返回值： 
	 *   @异常：
	 **/
	protected SinoLogger getLoggerImpl(Class<?> cl) {
		Logger l = Logger.getLogger(cl);
		return new Log4JLogger(l);
	}
	/**
	 *   @生成时间： 2013-10-31 下午02:38:03
	 *   @方法说明： 获取交易日志实现类
	 *   @参数：           cl代表类的类型
	 *   @返回值： 
	 *   @异常：
	 **/
	protected SinoLogger getTransactionLoggerImpl(Class<?> cl) {
		try {
			Logger rootLooger = Logger.getRootLogger();
			Logger logger = Logger.getLogger(cl + "");
			// 清空Appender。特別是不想使用现存实例时一定要初期化
			logger.removeAllAppenders();
			// 设定Logger级别。
			logger.setLevel(rootLooger.getLevel());
			// 设定是否继承父Logger。
			// 默认true。继承root输出。
			// 设定false后将不输出root。
			//logger.setAdditivity(true);
			// 生成新的Appender
			RollingFileAppender appender = new RollingFileAppender();
			//设置单个文件大小
			appender.setMaxFileSize(Log4jConfig.TRANSACTION_MAXFILESIZE);
			//设置保存文件数目
			appender.setMaxBackupIndex(Integer.parseInt(Log4jConfig.TRANSACTION_MAXBACKUPINDEX));
			PatternLayout layout = new PatternLayout();
			// log的输出形式
			layout.setConversionPattern(Log4jConfig.TRANSACTION_CONVERSIONPATTERN);
			appender.setLayout(layout);
			String logFilePath = Log4jConfig.TRANSACTION_FILEDIR + File.separator + cl.getSimpleName();
			// log输出路径
			appender.setFile(logFilePath);
			// log的文字码
			appender.setEncoding(Log4jConfig.TRANSACTION_ENCODING);
			// true:在已存在log文件后面追加 false:新log覆盖以前的log
			appender.setAppend(Boolean.parseBoolean(Log4jConfig.TRANSACTION_APPEND));
			// 适用当前配置
			appender.activateOptions();
			// 将新的Appender加到Logger中
			logger.addAppender(appender);
			return new Log4JLogger(logger);
		} catch (Exception e) {
			System.err.println("交易日志打印错误,请检查日志配置文件log4j.properties");
		}
		return null;
	}
}
