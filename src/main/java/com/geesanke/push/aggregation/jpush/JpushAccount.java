package com.geesanke.push.aggregation.jpush;

import java.io.Serializable;

public class JpushAccount implements Serializable{
 
    private static final long serialVersionUID = 8817214397921720126L;
    //应用
    private String appId;
    //钥匙 
    private String appKey;
    //密码
    private String masterSecret;
    public JpushAccount() {
    }
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getAppKey() {
        return appKey;
    }
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
    public String getMasterSecret() {
        return masterSecret;
    }
    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }
    

}
