package cn.net.sinodata.framework.log;

import java.security.AccessControlException;

import cn.net.sinodata.framework.log.library.LoggerName;
import cn.net.sinodata.framework.log.library.SimpleLogger;

/**
 * @类名： SinoLogger
 * @描述： Sino日志的抽象类，声明的方法与log4j一致
 * @作者： 杨文胜
 * @生成时间： 2013-10-31 下午01:53:45
 * @修改人：
 * @修改时间：
 **/
public abstract class SinoLogger {
	/**
	 * @属性说明：单例sinoLogger
	 **/
	private static SinoLogger sinoLogger = null;

	/**
	 * @生成时间： 2013-10-31 下午01:57:43
	 * @方法说明： 工厂方法返回SinoLogger
	 * @参数：
	 * @返回值： SinoLogger
	 * @异常：
	 **/
	public static final SinoLogger getLogger(Class<?> cl) {
		if (sinoLogger == null) {
			initializeLogger();
		}
		return sinoLogger.getLoggerImpl(cl);
	}
	
	/**
	 * @生成时间： 2013-10-31 下午01:57:43
	 * @方法说明： 工厂方法返回SinoLogger,每个交易类根据类生成一个文件
	 * @参数：
	 * @返回值： SinoLogger
	 * @异常：
	 **/
	public static final SinoLogger getTransactionLogger(Class<?> cl) {
		if (sinoLogger == null) {
			initializeLogger();
		}
		return sinoLogger.getTransactionLoggerImpl(cl);
	}

	/**
	 * @生成时间： 2013-10-31 下午01:58:32
	 * @方法说明：线性安全初始化SinoLogger
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	private synchronized static void initializeLogger() {
		if (sinoLogger != null) {
			return;
		}
		String loggerName = LoggerName.NAME;
		try {
			// 运行时系统是否设置了其他日志
			loggerName = System.getProperty("logger");
			if (loggerName == null) {
				// 获取日志实现类名
				loggerName = LoggerName.NAME;
			}
			sinoLogger = (SinoLogger) Class.forName(loggerName).newInstance();
		} catch (IllegalAccessException e) {
			sinoLogger = new SimpleLogger();
			sinoLogger.warn("Could not instantiate logger " + loggerName
					+ " using default");
		} catch (InstantiationException e) {
			sinoLogger = new SimpleLogger();
			sinoLogger.warn("Could not instantiate logger " + loggerName
					+ " using default");
		} catch (AccessControlException e) {
			sinoLogger = new SimpleLogger();
			sinoLogger.warn("Could not instantiate logger " + loggerName
					+ " using default");
		} catch (ClassNotFoundException e) {
			sinoLogger = new SimpleLogger();
			sinoLogger.warn("Could not instantiate logger " + loggerName
					+ " using default");
		}
	}

	/**
	 * @生成时间： 2013-10-31 下午02:11:45
	 * @方法描述： 构造方法
	 * @param
	 **/
	protected SinoLogger() {
	}

	/**
	 * @生成时间： 2013-10-31 下午02:01:57
	 * @方法说明： debug message
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public abstract void debug(Object message);

	/**
	 * @生成时间： 2013-10-31 下午02:01:58
	 * @方法说明： debug message and exception
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public abstract void debug(Object message, Throwable t);

	/**
	 * @生成时间： 2013-10-31 下午02:01:59
	 * @方法说明： error message
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public abstract void error(Object message);

	/**
	 * Log an error message object and exception
	 */
	/**
	 * @生成时间： 2013-10-31 下午02:02:02
	 * @方法说明：error message object and exception
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public abstract void error(Object message, Throwable t);

	/**
	 * @生成时间： 2013-10-31 下午02:03:33
	 * @方法说明： fatal message
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public abstract void fatal(Object message);

	/**
	 * @生成时间： 2013-10-31 下午02:03:45
	 * @方法说明： fatal message and exception
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public abstract void fatal(Object message, Throwable t);

	/**
	 * @生成时间： 2013-10-31 下午02:03:55
	 * @方法说明： information message
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public abstract void info(Object message);

	/**
	 * @生成时间： 2013-10-31 下午02:04:05
	 * @方法说明： information message and exception
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public abstract void info(Object message, Throwable t);

	/**
	 * @生成时间： 2013-10-31 下午02:04:30
	 * @方法说明： warning message object
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public abstract void warn(Object message);

	/**
	 * @生成时间： 2013-10-31 下午02:04:37
	 * @方法说明： warning message with exception
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public abstract void warn(Object message, Throwable t);

	/**
	 * @生成时间： 2013-10-31 下午02:04:51
	 * @方法说明：获取SinoLogger的实现类
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	protected abstract SinoLogger getLoggerImpl(Class<?> cl);
	
	/**
	 * @生成时间： 2013-10-31 下午02:04:51
	 * @方法说明：通过交易类名获取SinoLogger的实现类
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	protected abstract SinoLogger getTransactionLoggerImpl(Class<?> cl);

	/**
	 * @生成时间： 2013-10-31 下午02:05:32
	 * @方法说明： 空实现，子类可以重载此方法
	 * @参数： w代表suppression标识
	 * @返回值：
	 * @异常：
	 **/
	public void setSuppressWarnings(boolean w) {
		// default implementation does nothing
	}
}
