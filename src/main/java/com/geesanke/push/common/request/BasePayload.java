
package com.geesanke.push.common.request;

import java.util.Map;

import com.geesanke.push.common.ecode.PushTypeEnum;

public class BasePayload {
    // 推送目标
    private String[] regists;
    // 透传消息
    private String payload;
    // 通知栏展示的通知的标题
    private String title;
    // 副标题
    private String subTitle;
    // 通知栏展示的通知的描述
    private String description;
    // 对 app 提供一些扩展的功能
    private Map<String, String> extras;
    // app打开对应 Activity
    private String activity;
    // Activity 的 intent uri
    private String intentUri;
    // 网页地址
    private String webUri;
    // 推送标识
    private PushTypeEnum sign;

    private BasePayload(BasePayloadBuilder basePayloadBuilder) {
        this.regists = basePayloadBuilder.regists;
        this.payload = basePayloadBuilder.payload;
        this.title = basePayloadBuilder.title;
        this.subTitle = basePayloadBuilder.subTitle;
        this.description = basePayloadBuilder.description;
        this.extras = basePayloadBuilder.extras;
        this.activity = basePayloadBuilder.activity;
        this.intentUri = basePayloadBuilder.intentUri;
        this.webUri = basePayloadBuilder.webUri;
        this.sign = basePayloadBuilder.sign;
    }
    
    public BasePayload() {}

    public String[] getRegists() {
        return regists;
    }

    public void setRegists(String[] regists) {
        this.regists = regists;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getIntentUri() {
        return intentUri;
    }

    public void setIntentUri(String intentUri) {
        this.intentUri = intentUri;
    }

    public String getWebUri() {
        return webUri;
    }

    public void setWebUri(String webUri) {
        this.webUri = webUri;
    }

    public PushTypeEnum getSign() {
        return sign;
    }

    public void setSign(PushTypeEnum sign) {
        this.sign = sign;
    }

    public static BasePayloadBuilder newInstance() {
        return new BasePayloadBuilder();
    }

    public static final class BasePayloadBuilder {
        private String[] regists;
        private String payload;
        private String title;
        private String subTitle;
        private String description;
        private Map<String, String> extras;
        private String activity;
        private String intentUri;
        private String webUri;
        private PushTypeEnum sign;

        private BasePayloadBuilder() {
        }

        public BasePayloadBuilder addRegists(String[] regists) {
            this.regists = regists;
            return this;
        }

        public BasePayloadBuilder addPayload(String payload) {
            this.payload = payload;
            return this;
        }

        public BasePayloadBuilder addTitle(String title) {
            this.title = title;
            return this;
        }

        public BasePayloadBuilder addSubTitle(String subTitle) {
            this.subTitle = subTitle;
            return this;
        }

        public BasePayloadBuilder addDescription(String description) {
            this.description = description;
            return this;
        }

        public BasePayloadBuilder addExtras(Map<String, String> extras) {
            this.extras = extras;
            return this;
        }

        public BasePayloadBuilder addActivity(String activity) {
            this.activity = activity;
            return this;
        }

        public BasePayloadBuilder addIntentUri(String intentUri) {
            this.intentUri = intentUri;
            return this;
        }

        public BasePayloadBuilder addWebUri(String webUri) {
            this.webUri = webUri;
            return this;
        }

        public BasePayloadBuilder addSign(PushTypeEnum sign) {
            this.sign = sign;
            return this;
        }

        public BasePayload build() {
            return new BasePayload(this);
        }
    }
    
    
    
    
}
