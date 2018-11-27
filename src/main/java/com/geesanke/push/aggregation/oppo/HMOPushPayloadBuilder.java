package com.geesanke.push.aggregation.oppo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geesanke.common.core.CommonUtils;
import com.geesanke.common.json.JsonUtils;
import com.geesanke.push.common.ecode.PushTypeEnum;
import com.geesanke.push.common.request.BasePayload;
import com.oppo.push.server.Notification;
import com.oppo.push.server.Target;

public class HMOPushPayloadBuilder {

    private static Logger logger = LoggerFactory.getLogger(HMOPushPayloadBuilder.class);
    
    public static OpushPayload buildPlatformAllAlert(BasePayload payload) {
        Notification notification = new Notification();
        
        notification.setTitle(payload.getTitle());
        notification.setSubTitle(payload.getPayload());
        notification.setContent(payload.getDescription());
        notification.setActionParameters(JsonUtils.to(payload.getExtras()));
     
        if(CommonUtils.isNotEmpty(payload.getActivity())) {
            notification.setClickActionType(4);
            notification.setClickActionActivity(payload.getActivity());
        }
        if(CommonUtils.isNotEmpty(payload.getIntentUri())) {
            notification.setClickActionType(1);
            notification.setClickActionActivity(payload.getIntentUri());
        }
        if(CommonUtils.isNotEmpty(payload.getWebUri())) {
            notification.setClickActionType(2);
            notification.setClickActionUrl(payload.getWebUri());
        }
        Target target =  new Target();
        target.setTargetValue(payload.getRegists()[0]);
        
        return OpushPayload.newInstance().addNotification(notification).addTarget(target).build();
    }
    
    
    
    
    
    public static OpushPayload createPushPayload(BasePayload payload) {
        OpushPayload pushPayload = null;
        PushTypeEnum sign = payload.getSign();
        if(CommonUtils.isEmpty(sign)) {
            logger.error("推送载体标识为空");
            return null;
        }
        switch (sign) {
        case IOS_OR_ANDROID_ALERT:
            pushPayload = buildPlatformAllAlert(payload);
            break;
        case IOS_OR_ANDROID_NOT_ALERT:
//            pushPayload = buildPlatformAllNotAlert(payload);        
            break;
        case ANDROID_ALL_ALERT:
//            pushPayload = buildAndroidAllAlert(payload);     
            break;
        case ANDROID_ALL_NOT_ALERT:
//            pushPayload = buildAndroidAllNotAlert(payload);
            break;
        case ALL_ALERT:
//            pushPayload = buildPlatformAllAllAlert(payload);
            break;
        case ALL_NOT_ALERT:
//            pushPayload = buildPlatformAllAllNotAlert(payload);
            break;
        default:
            break;
        }
        return pushPayload;
    }
    
}
