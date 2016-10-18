package com.independent.shadow.util;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author shisong 日志 Logcat日志级别: 0:禁用日志, 1:启用(�??��日志), 2:verbose, 3: debug,
 *         4:info, 5:warn, 6:error, 7:assert 文件日志输出: 0:禁用日志, 1:启用(�??��日志),
 *         2:verbose, 3: debug, 4:info, 5:warn, 6:error, 7:assert
 */
public class XLog {

	private static String TAG = "xlog";

	private static int LOGCAT_PRIORITY = 1;// 0禁用

	public static void setTag(String tag) {
		TAG = tag;
	}

	/**
	 * 设置logcat的log级别, 见android.util.Log.VERBOSE�??
	 * 
	 * @param level
	 *          值是android.util.Log.VERBOSE/DEBUG/INFO/WARN�?? 0表示全部禁用, 1表示全部启用
	 */
	public static void setLevel(int level) {
		LOGCAT_PRIORITY = level;
	}

	private static boolean isEnable(int level) {
		return LOGCAT_PRIORITY != 0 && level >= LOGCAT_PRIORITY;
	}

	public static void d(Object... args) {
		println(Log.DEBUG, TAG, args);
	}

	public static void dTag(String tag, Object... args) {
		println(Log.DEBUG, tag, args);
	}

	public static void w(Object... args) {
		println(Log.WARN, TAG, args);
	}

	public static void wTag(String tag, Object... args) {
		println(Log.WARN, tag, args);
	}

	public static void e(Object... args) {
		println(Log.ERROR, TAG, args);
	}

	public static void eTag(String tag, Object... args) {
		println(Log.ERROR, tag, args);
	}

	public static void i(Object... args) {
		println(Log.INFO, TAG, args);
	}

	public static void iTag(String tag, Object... args) {
		println(Log.INFO, tag, args);
	}

	public static void v(Object... args) {
		println(Log.VERBOSE, TAG, args);
	}

	public static void vTag(String tag, Object... args) {
		println(Log.VERBOSE, tag, args);
	}

	private static String getString(Object obj) {
		if (obj == null) {
			return "null";
		}
		// else
		if (obj instanceof Throwable) {
			Throwable tr = (Throwable) obj;
			StringWriter sw = new StringWriter(128);
			PrintWriter pw = new PrintWriter(sw);
			tr.printStackTrace(pw);
			return sw.toString();
		}
		// else
		if (obj instanceof String) {
			return (String) obj;
		}
		// else
		return obj.toString();
	}

	/**
	 * @param priority
	 *          见Log.WARN/ERROR�??
	 * @param tag
	 * @param args
	 */
	public static void println(int priority, String tag, Object... args) {
		if (!isEnable(priority)) {
			return;
		}
		String msg = null;
		if (args.length == 0) {
			msg = "";
		}
		else {
			StringBuffer sb = new StringBuffer(args.length * 8 + 8);
			boolean first = true;
			for (Object obj : args) {
				if (first) {
					first = false;
				}
				else {
					sb.append(" ");
				}
				sb.append(getString(obj));
			}
			msg = sb.toString();
		}
		Log.println(priority, tag, msg);
	}
}
