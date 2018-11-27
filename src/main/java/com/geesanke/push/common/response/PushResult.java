package com.geesanke.push.common.response;

import java.io.Serializable;

public class PushResult implements Serializable {

    private static final long serialVersionUID = -2865222334323811732L;
    // 消息ID
    private String msgId;
    // 消息发送
    private String sendno;
    // 发送状态
    private String statusCode;
    // 是否成功
    private boolean isOk = false;
    // 状态信息
    private String statusMsg;
    // 推送渠道
    private String pushType;
    // 推送appId
    private String appId;

    private PushResult(PushResultBuilder pushResultBuilder) {
        this.msgId = pushResultBuilder.msgId;
        this.sendno = pushResultBuilder.sendno;
        this.statusCode = pushResultBuilder.statusCode;
        this.isOk = pushResultBuilder.isOk;
        this.statusMsg = pushResultBuilder.statusMsg;
        this.pushType = pushResultBuilder.pushType;
        this.appId = pushResultBuilder.appId;
    }

    public PushResult() {

    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getSendno() {
        return sendno;
    }

    public void setSendno(String sendno) {
        this.sendno = sendno;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean isOk) {
        this.isOk = isOk;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public static PushResultBuilder newInstance() {
        return new PushResultBuilder();
    }

    public static final class PushResultBuilder {
        private String msgId;
        private String sendno;
        private String statusCode;
        private boolean isOk;
        private String statusMsg;
        private String pushType;
        private String appId;

        private PushResultBuilder() {
        }

        public PushResultBuilder addMsgId(String msgId) {
            this.msgId = msgId;
            return this;
        }

        public PushResultBuilder addSendno(String sendno) {
            this.sendno = sendno;
            return this;
        }

        public PushResultBuilder addStatusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public PushResultBuilder addIsOk(boolean isOk) {
            this.isOk = isOk;
            return this;
        }

        public PushResultBuilder addStatusMsg(String statusMsg) {
            this.statusMsg = statusMsg;
            return this;
        }

        public PushResultBuilder addPushType(String pushType) {
            this.pushType = pushType;
            return this;
        }

        public PushResultBuilder addAppId(String appId) {
            this.appId = appId;
            return this;
        }

        public PushResult build() {
            return new PushResult(this);
        }
    }

}
