package cn.net.sinodata.framework.exception.message;

import java.text.MessageFormat;

public class ErrorMsgFormat {

	private ErrorMsgFormat() {
	}

	public static String format(String bundleName) {
		return format(bundleName, new Object[0]);
	}

	public static String format(String bundleName, long arg) {
		return format(bundleName, new Object[] { new Long(arg) });
	}

	public static String format(String bundleName, long arg1, long arg2) {
		return format(bundleName, new Object[] { new Long(arg1), new Long(arg2) });
	}

	public static String format(String bundleName, long arg1, String arg2) {
		return format(bundleName, new Object[] { new Long(arg1), arg2 });
	}

	public static String format(String bundleName, long arg1, String arg2, String arg3) {
		return format(bundleName, new Object[] { new Long(arg1), arg2, arg3 });
	}

	public static String format(String bundleName, long arg1, long arg2, long arg3) {
		return format(bundleName, new Object[] { new Long(arg1), new Long(arg2), new Long(arg3) });
	}

	public static String format(String bundleName, Object arg) {
		return format(bundleName, new Object[] { arg });
	}

	public static String format(String bundleName, Object arg1, Object arg2) {
		return format(bundleName, new Object[] { arg1, arg2 });
	}

	public static String format(String bundleName, Object[] args) {
		return MessageFormat.format(bundleName, args);
	}

}
