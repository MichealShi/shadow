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
    private Map<String, Object> body;

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
        comm.setPid(AppData.getIMEI());
//        comm.setUs(AppData.getChanel());
        comm.setVersion(AppData.getVersionName());
    }

    public CommData getComm() {
        return comm;
    }

    public void setComm(CommData comm) {
        this.comm = comm;
    }

    public void put(String key, Object value) {
        if (null == body) {
            body = new HashMap<>();
        }
        body.put(key, value);
    }

    @Override
    public String toString() {
        return "ReqParam{" +
                "comm=" + comm +
                ", body=" + body +
                ", pid='" + pid + '\'' +
                '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}