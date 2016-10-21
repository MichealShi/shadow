package com.independent.shadow.http;

/**
 * Created by iqianjin-shisong on 2016/10/21.
 */

public class HotPatchReq extends BaseRequestParam {
    private String step;

    public String getStep() {
        return step;
    }

    public HotPatchReq setStep(String step) {
        this.step = step;
        return this;
    }

    @Override
    public String toString() {
        return "HotPatchReq{" +
                "step='" + step + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", imei='" + imei + '\'' +
                ", channel='" + channel + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", osVersion='" + osVersion + '\'' +
                '}';
    }
}
