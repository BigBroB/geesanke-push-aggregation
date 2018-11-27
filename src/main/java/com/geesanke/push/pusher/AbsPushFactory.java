package com.geesanke.push.pusher;

import com.geesanke.push.common.request.BasePayload;
import com.geesanke.push.common.response.PushResult;

public abstract class AbsPushFactory<T> implements PushFactory {

    ThreadLocal<String> appId = new ThreadLocal<String>();

    @Override
    public PushResult push(BasePayload payload) {
        try {
            T msg = createMsg(payload);
            return pushMsg(msg);
        } finally {
            removeAppId();
        }

    }

    public abstract T createMsg(BasePayload payload);

    public abstract PushResult pushMsg(T msg);

    /**
     * @Title: setAppIdBeforeInit
     * @Description: TODO 推送前确认推送应用通道
     * @param appId
     * @return PushFactory
     */
    public PushFactory setAppIdBeforeInit(String appId) {
        this.appId.set(appId);
        return this;
    }

    
    
    public String getAppId() {
        return appId.get();
    }

    public void setAppId(ThreadLocal<String> appId) {
        this.appId = appId;
    }

    private void removeAppId() {
        this.appId.remove();
    }

}
