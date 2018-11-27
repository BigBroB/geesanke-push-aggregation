package com.geesanke.push.aggregation.oppo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.geesanke.push.common.exception.MessageException;
import com.geesanke.push.common.request.BasePayload;
import com.geesanke.push.common.response.PushResult;
import com.geesanke.push.pusher.AbsPushFactory;
import com.oppo.push.server.Result;
import com.oppo.push.server.Sender;

@Component("oppoPushFactory")
public class OPushFactory extends AbsPushFactory<OpushPayload>{
    
    private static Logger logger = LoggerFactory.getLogger(OPushFactory.class);
    
    @Autowired
    OPushAccounts oPushAccounts;
    
    @Override
    public OpushPayload createMsg(BasePayload payload) {
        
        return HMOPushPayloadBuilder.createPushPayload(payload);
    }

    @Override
    public PushResult pushMsg(OpushPayload msg) {
        Sender sender = null;
        try {
            sender = oPushAccounts.getPushClient(this.getAppId());
            Result oppoResult = sender.unicastNotification(msg.getNotification(), msg.getTarget());
            PushResult result = PushResult.newInstance()
                    .addMsgId(oppoResult.getMessageId())
                    .addIsOk("0".equals(String.valueOf(oppoResult.getReturnCode().getCode())))
                    .addStatusMsg(oppoResult.getReturnCode().getMessage()).build();
            return result;
        } catch (MessageException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return PushResult.newInstance().build();
    }

}
