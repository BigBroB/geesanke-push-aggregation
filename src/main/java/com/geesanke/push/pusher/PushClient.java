package com.geesanke.push.pusher;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.geesanke.common.core.CommonUtils;
import com.geesanke.common.exception.BaseException;
import com.geesanke.push.common.ecode.ResponseCodeMsg;
import com.geesanke.push.common.exception.PushException;
import com.geesanke.push.common.request.BasePayload;
import com.geesanke.push.common.response.PushResult;

@SuppressWarnings("rawtypes")
@Component("pushClient")
public class PushClient implements PushService {

    private final String DEFAULT_CHANNEL = "jpush";

    @Autowired
    private Map<String, AbsPushFactory> factorys = new HashMap<String, AbsPushFactory>();

    @Override
    public PushResult push(String channel, String appId, BasePayload payload) throws BaseException {

        AbsPushFactory factory = null;

        for (String key : factorys.keySet()) {
            if (key.startsWith(channel)) {
                factory = factorys.get(key);
                break;
            }
        }

        if (CommonUtils.isEmpty(factory)) {
            for (String key : factorys.keySet()) {
                if (key.startsWith(DEFAULT_CHANNEL)) {
                    factory = factorys.get(key);
                    channel = DEFAULT_CHANNEL;
                    break;
                }
            }
        }
        if (CommonUtils.isEmpty(factory)) {
            throw new PushException(ResponseCodeMsg.PUSH_CLIENT_NOT_FOUND);
        }
        
        PushResult result = factory.setAppIdBeforeInit(appId).push(payload);
        
        result.setAppId(appId); 
        result.setPushType(channel);
        return  result;
    }

}
