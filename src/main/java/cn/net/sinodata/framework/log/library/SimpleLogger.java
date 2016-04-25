package cn.net.sinodata.framework.log.library;

import cn.net.sinodata.framework.log.SinoLogger;

/**
 * @类名： SimpleLogger
 * @描述： 简单日志类，默认打印所有的输入信息
 * @作者： 杨文胜
 * @生成时间： 2013-10-31 下午02:18:20
 * @修改人：
 * @修改时间：
 **/
public class SimpleLogger extends SinoLogger {
	/**
	 * @属性说明：是否停止打印日志的标识，true是停止打印，false是打印
	 **/
	private boolean suppressWarnings;

	/**
	 * @生成时间： 2013-10-31 下午02:21:41
	 * @方法描述： 构造方法
	 * @param
	 **/
	public SimpleLogger() {
		suppressWarnings = false;
	}

	/**
	 * @生成时间： 2013-10-31 下午02:22:05
	 * @方法说明： debug message
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public void debug(Object message) {
		if (!suppressWarnings) {
			System.out.print("Debug: ");
			System.out.println(message);
		}
	}

	/**
	 * @生成时间： 2013-10-31 下午02:22:19
	 * @方法说明： debug message and exception
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public void debug(Object message, Throwable t) {
		if (!suppressWarnings) {
			System.out.print("Debug: ");
			System.out.println(message);
			t.printStackTrace();
		}
	}

	/**
	 * @生成时间： 2013-10-31 下午02:22:40
	 * @方法说明： error message
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public void error(Object message) {
		System.err.print("Error: ");
		System.err.println(message);
	}

	/**
	 * @生成时间： 2013-10-31 下午02:22:48
	 * @方法说明： error message object and exception
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public void error(Object message, Throwable t) {
		System.err.print("Error: ");
		System.err.println(message);
		t.printStackTrace();
	}

	/**
	 * @生成时间： 2013-10-31 下午02:23:02
	 * @方法说明： fatal message
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public void fatal(Object message) {
		System.err.print("Fatal: ");
		System.err.println(message);
	}

	/**
	 * @生成时间： 2013-10-31 下午02:23:30
	 * @方法说明： fatal message and exception
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public void fatal(Object message, Throwable t) {
		System.err.print("Fatal:  ");
		System.err.println(message);
		t.printStackTrace();
	}

	/**
	 * @生成时间： 2013-10-31 下午02:23:38
	 * @方法说明： information message
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public void info(Object message) {
		if (!suppressWarnings) {
			System.out.println(message);
		}
	}

	/**
	 * @生成时间： 2013-10-31 下午02:23:46
	 * @方法说明： information message and exception
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public void info(Object message, Throwable t) {
		if (!suppressWarnings) {
			System.out.println(message);
			t.printStackTrace();
		}
	}

	/**
	 * @生成时间： 2013-10-31 下午02:23:58
	 * @方法说明： warning message object
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public void warn(Object message) {
		if (!suppressWarnings) {
			System.err.print("Warning:  ");
			System.err.println(message);
		}
	}

	/**
	 * @生成时间： 2013-10-31 下午02:24:07
	 * @方法说明： warning message with exception
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	public void warn(Object message, Throwable t) {
		if (!suppressWarnings) {
			System.err.print("Warning:  ");
			System.err.println(message);
			t.printStackTrace();
		}
	}

	/**
	 * @生成时间： 2013-10-31 下午02:24:16
	 * @方法说明： 获取日志实现类
	 * @参数：
	 * @返回值：
	 * @异常：
	 **/
	protected SinoLogger getLoggerImpl(Class<?> c) {
		return this;
	}

	/**
	 * @生成时间： 2013-10-31 下午02:24:50
	 * @方法说明： 重载此方法，根据传入参数w判断是否停止打印信息
	 * @参数：
	 * @返回值： w 代表suppression标识
	 * @异常：
	 **/
	public void setSuppressWarnings(boolean w) {
		suppressWarnings = w;
	}

	/* (non-Javadoc)
	 * @see cn.net.sinodata.business.log.SinoLogger#getTransactionLoggerImpl(java.lang.Class)
	 */
	@Override
	protected SinoLogger getTransactionLoggerImpl(Class<?> cl) {
		return this;
	}
}
