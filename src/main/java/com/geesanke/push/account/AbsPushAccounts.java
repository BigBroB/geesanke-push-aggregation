package com.geesanke.push.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.geesanke.common.core.CommonUtils;
import com.geesanke.common.exception.BaseException;

public abstract class AbsPushAccounts<T> implements InitializingBean{

    protected Map<String, T> pushClients = new HashMap<String, T>();

    public abstract T ifGetNull(String appId) throws BaseException;

    public T getPushClient(String appId) throws BaseException {
        T client = null;
        client = this.pushClients.get(appId);
        if (CommonUtils.isEmpty(client)) {
            client = ifGetNull(appId);
            pushClients.put(appId, client);
        }
        return client;
    }

    public AbsPushAccounts() {

    }

    public Map<String, T> getPushClients() {
        return pushClients;
    }

    public void setPushClients(Map<String, T> pushClients) {
        this.pushClients = pushClients;
    }

}
