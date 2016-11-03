package com.independent.shadow.model;

/**
 * Created by iqianjin-shisong on 2016/10/18.
 */

public class HotfixModel extends BaseHotfixModel {
    /**
     * hotUrl : string,补丁包地址(如果appState != 1,有此字段，但是为空)
     * appVersion : string,app 版本
     * hotfixType : string,修复类型：1，全量；2，根据app版本
     * hashCode : string,唯一标示（用于验证此app是否修复过）
     * uploadDate : string,上传补丁包的时间
     */

    private String hotUrl;
    private String appVersion;
    private String hotfixType;
    private String hashCode;
    private String uploadDate;


    public String getHotUrl() {
        return hotUrl;
    }

    public void setHotUrl(String hotUrl) {
        this.hotUrl = hotUrl;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getHotfixType() {
        return hotfixType;
    }

    public void setHotfixType(String hotfixType) {
        this.hotfixType = hotfixType;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }


    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }


}
