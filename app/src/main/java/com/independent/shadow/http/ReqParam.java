package com.independent.shadow.http;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.independent.shadow.AppData;
import com.independent.shadow.util.XLog;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iqianjin-shisong on 2016/10/18.
 */

public class ReqParam {

    private CommData comm;
    private String token;
    private Map<String, Object> body;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    private String pid;

    public ReqParam(Context con) {
        if (null == comm) {
            comm = new CommData();
        }
        comm.setPid(getIMEI(con));
        comm.setType(3);
        comm.setUs(getChanel(con));
        comm.setVersion(getVersionName());
    }


    public CommData getComm() {
        return comm;
    }

    public void setComm(CommData comm) {
        this.comm = comm;
    }

    public void put(String key, Object value) {
        if (null == body) {
            body = new HashMap<String, Object>();
        }
        body.put(key, value);
    }

    public String toString() {
        return "token" + token + "  {comm" + this.comm.getPid() + "  " + this.comm.getType() + " " + this.comm.getUs() + "}";
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }


    /**
     * 获取渠道号
     * @param ctx 上下文
     * @return 渠道号
     */
    public int getChanel(Context ctx) {
        int channelId = 0;
        try {
            ApplicationInfo ai = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
            Object value = ai.metaData.get("UMENG_CHANNEL");
            if (value != null) {
                channelId = Integer.valueOf(value.toString());
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
     * @param con 上下文
     * @return 手机唯一设备号
     */
    public static String getIMEI(Context con) {
        String mDeviceId = AppData.imeiCash.get();
        if (TextUtils.isEmpty(mDeviceId)) {
            try {
                mDeviceId = ((TelephonyManager) con.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
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
}