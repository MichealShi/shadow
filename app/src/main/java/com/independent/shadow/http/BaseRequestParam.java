package com.independent.shadow.http;

import com.independent.shadow.AppData;

/**
 * Created by iqianjin-shisong on 2016/10/21.
 */

public class BaseRequestParam {
    public String appVersion = AppData.getVersionName();
    public String imei = AppData.getIMEI();
    public String channel = AppData.getChanel();
    public String userPhone = AppData.getNativePhoneNumber();
    public String osVersion = android.os.Build.VERSION.RELEASE;

    public String getAppVersion() {
        return appVersion;
    }

    public BaseRequestParam setAppVersion(String appVersion) {
        this.appVersion = appVersion;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public BaseRequestParam setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public BaseRequestParam setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public BaseRequestParam setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    @Override
    public String toString() {
        return "BaseRequestParam{" +
                "appVersion='" + appVersion + '\'' +
                ", imei='" + imei + '\'' +
                ", channel='" + channel + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", osVersion='" + osVersion + '\'' +
                '}';
    }
}
