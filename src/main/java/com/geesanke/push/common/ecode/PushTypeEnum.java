package com.geesanke.push.common.ecode;

public enum PushTypeEnum {
    // ios or android
    IOS_OR_ANDROID_ALERT("ios_or_android_alert","ios 或 android 推送通知","101"),
    IOS_OR_ANDROID_NOT_ALERT("ios_or_android_not_alert","ios 或 android 静默通知","100"),
    // ios
    IOS_ALL_ALERT("ios_all_alert","ios通知所有","111"),
    IOS_ALL_NOT_ALERT("ios_all_not_alert","ios静默所有","110"),
    // android
    ANDROID_ALL_ALERT("android_all_alert","android通知所有","211"),
    ANDROID_ALL_NOT_ALERT("android_all_not_alert","android静默所有","210"),
    // all
    ALL_ALERT("all_alert","所有通知","311"),
    ALL_NOT_ALERT("all_not_alert","所有通知","310"),
    ;
    private String code;
    private String detail;
    // * - 1 ios 2 android 3 all
    // * - 0 单或批量 1 所有
    // * - 1 alert 0 静默 
    private String sign;
    
    private PushTypeEnum(String code, String detail, String sign) {
        this.code = code;
        this.detail = detail;
        this.sign = sign;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    
    
    
    
}
