package com.geesanke.push.aggregation.oppo;

public class OPushAccount {

    // 本地应用名
    private String appId;
    // 应用包名
    private String packageName;
    //
    private String appKey;

    private String appSecret;

    public OPushAccount() { }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
    
    
}
