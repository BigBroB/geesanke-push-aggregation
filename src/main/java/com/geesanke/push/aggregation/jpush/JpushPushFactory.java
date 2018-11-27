package com.geesanke.push.aggregation.jpush;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.geesanke.push.common.exception.MessageException;
import com.geesanke.push.common.request.BasePayload;
import com.geesanke.push.common.response.PushResult;
import com.geesanke.push.pusher.AbsPushFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.model.PushPayload;

@Component("jpushPushFactory")
public class JpushPushFactory extends AbsPushFactory<PushPayload> {
    @Autowired
    @Qualifier("jpushAccounts")
    private JpushAccounts jpushAccounts;
    private static Logger logger = LoggerFactory.getLogger(JpushPushFactory.class);
  
    @Override
    public PushPayload createMsg(BasePayload basePayload) {
        
        PushPayload payload = HMPushPayloadBuilder.createPushPayload(basePayload);
        return payload;
    }

    @Override
    public PushResult pushMsg(PushPayload msg) {
        try {
            cn.jpush.api.push.PushResult result = jpushAccounts.getPushClient(getAppId()).sendPush(msg);
            return PushResult.newInstance()
                                    .addIsOk(result.isResultOK())
                                    .addMsgId(String.valueOf(result.msg_id))
                                .build();
        } catch (MessageException | APIConnectionException | APIRequestException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
    
}
