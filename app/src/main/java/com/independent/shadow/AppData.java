package com.independent.shadow;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.independent.shadow.prefer.PreferPropertyString;
import com.independent.shadow.prefer.PreferenceUtil;
import com.independent.shadow.util.XLog;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Created by iqianjin-shisong on 2016/10/18.
 */

public class AppData {
    private static final String SP_NAME = "shadow";
    private static PreferenceUtil sp = new PreferenceUtil();
    private static Context context = null;

    /**
     * 初始化, 在Application中调用
     * @param c
     */
    public static void init(Context c) {
        context = c;
        sp.init(c, SP_NAME);
    }

    public static Context getContext() {
        return context;
    }

    public static String getNativePhoneNumber() {
        return ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
    }

    /**
     * 获取渠道号
     * @return 渠道号
     */
    public static String getChanel() {
        String channelId = "";
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Object value = ai.metaData.get("UMENG_CHANNEL");
            if (value != null) {
                channelId = value.toString();
            }
        } catch (Exception e) {
            XLog.e(e);
        }
        return channelId;
    }

    /**
     * 获取当前版本号
     * @return 版本号
     */
    public static String getVersionName() {
        // 获取packageManager的实例
        PackageManager packageManager = AppData.getContext().getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(AppData.getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            XLog.e(e);
        }
        if (null != packInfo)
            return packInfo.versionName;
        return "";
    }

    /**
     * 获取deviceId
     * @return 手机唯一设备号
     */
    public static String getIMEI() {
        String mDeviceId = AppData.imeiCash.get();
        if (TextUtils.isEmpty(mDeviceId)) {
            try {
                mDeviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
            } catch (Exception e) {
                XLog.e(e);
                mDeviceId = getMac();
            }
            if (TextUtils.isEmpty(mDeviceId)) {
                mDeviceId = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.DEVICE.length() % 10
                        + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10
                        + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10 + System.currentTimeMillis();
            }
            AppData.imeiCash.set(mDeviceId);
        }
        return mDeviceId;
    }

    private static String getMac() {
        String macSerial = null;
        String str = "";

        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            while (null != str) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            XLog.e(ex);
            // 赋予默认值
            macSerial = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.DEVICE.length() % 10
                    + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10
                    + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10 + System.currentTimeMillis();
        }
        return macSerial;
    }

    public static final PreferPropertyString imeiCash = new PreferPropertyString(sp, "imeiCash", ""); // imei
}
