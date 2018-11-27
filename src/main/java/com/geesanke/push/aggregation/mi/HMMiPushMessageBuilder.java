package com.geesanke.push.aggregation.mi;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geesanke.common.core.CommonUtils;
import com.geesanke.push.common.ecode.PushTypeEnum;
import com.geesanke.push.common.request.BasePayload;
import com.xiaomi.xmpush.server.Message;
@SuppressWarnings("unused")
public class HMMiPushMessageBuilder {

    private static Logger logger = LoggerFactory.getLogger(HMMiPushMessageBuilder.class);

    private static final int  NOTIFY_TYPE_DEFAULT_DING = 1;
    private static final int  NOTIFY_TYPE_DEFAULT_VIBRATION = 2;
    private static final int  NOTIFY_TYPE_DEFAULT_LED = 3;
    private static final int  PASSTHROUGH = 1;
    private static final int  PASSTHROUGH_NO = 0;
    
    
    
    /**
     * @Title: buildPlatformAllAlert
     * @Description: android 或者 ios alert推送
     * @param regists
     * @param alert
     * @param content
     * @param extras
     * @return Message
     */
    public static MiPushPayload buildPlatformAllAlert(String packageName, BasePayload payload) {

        Message.Builder builder = new Message.Builder()
                                                    .restrictedPackageName(packageName)
                                                    .notifyType(NOTIFY_TYPE_DEFAULT_LED)
                                                    .title(payload.getTitle())
                                                    .passThrough(PASSTHROUGH_NO)
                                                    .payload(payload.getPayload())
                                                    .description(payload.getDescription());
        builder = wrapExtras(payload, builder);
        return wrapToPayload(payload, builder);
    }


   

    /**
     * @Title: buildPlatformAllNotAlert
     * @Description: android 或者 ios 静默消息
     * @param regists
     * @param alert
     * @param content
     * @param extras
     * @return Message
     */
    public static MiPushPayload buildPlatformAllNotAlert(String packageName, BasePayload payload) {
        Message.Builder builder = new Message.Builder()
                                            .restrictedPackageName(packageName)
                                            .notifyType(NOTIFY_TYPE_DEFAULT_LED)
                                            .passThrough(PASSTHROUGH)
                                            .title(payload.getTitle())
                                            .payload(payload.getPayload())
                                            .description(payload.getDescription());
        builder = wrapExtras(payload, builder);
        return wrapToPayload(payload, builder);
    }

    /**
     * @Title: buildPlatformAllAllAlert
     * @Description: 给所有用户推送alert消息
     * @param regists
     * @param alert
     * @param content
     * @param extras
     * @return Message
     */
    public static MiPushPayload buildPlatformAllAllAlert(String packageName, BasePayload payload) {
        
        Message.Builder builder = new Message.Builder()
                .restrictedPackageName(packageName)
                .notifyType(NOTIFY_TYPE_DEFAULT_LED)
                .title(payload.getTitle())
                .payload(payload.getPayload())
                .description(payload.getDescription());
        
        builder = wrapExtras(payload, builder);
        MiPushPayload miPushPayload = wrapToPayload(payload, builder);
        miPushPayload.setAll(true);
        return miPushPayload;
    }

    /**
     * @Title: buildPlatformAllAllNotAlert
     * @Description: 给所有用户推送静默消息
     * @param regists
     * @param alert
     * @param content
     * @param extras
     * @return Message
     */
    public static MiPushPayload buildPlatformAllAllNotAlert(String packageName, BasePayload payload) {
        Message.Builder builder = new Message.Builder()
                .restrictedPackageName(packageName)
                .notifyType(NOTIFY_TYPE_DEFAULT_LED)
                .passThrough(PASSTHROUGH)
                .title(payload.getTitle())
                .payload(payload.getPayload())
                .description(payload.getDescription());
        builder = wrapExtras(payload, builder);
        MiPushPayload miPushPayload = wrapToPayload(payload, builder);
        miPushPayload.setAll(true);
        return miPushPayload;
    }

    /**
     * @Title: buildAndroidAlert
     * @Description: 给所有 android 用户推送 alert 消息
     * @param @param
     *            regists
     * @param alert
     * @param content
     * @param extras
     * @return Message
     */
    public static MiPushPayload buildAndroidAllAlert(String packageName, BasePayload payload) {
        Message.Builder builder = new Message.Builder()
                .restrictedPackageName(packageName)
                .notifyType(NOTIFY_TYPE_DEFAULT_LED)
                .title(payload.getTitle())
                .payload(payload.getPayload())
                .description(payload.getDescription());
        
        builder = wrapExtras(payload, builder);
        MiPushPayload miPushPayload = wrapToPayload(payload, builder);
        miPushPayload.setAll(true);
        return miPushPayload;
    }

    /**
     * @Title: buildAndroidNotAlert
     * @Description: 给所有 android 用户静默推送
     * @param regists
     * @param alert
     * @param content
     * @param extras
     * @return Message
     */
    public static MiPushPayload buildAndroidAllNotAlert(String packageName, BasePayload payload) {
        Message.Builder builder = new Message.Builder()
                .restrictedPackageName(packageName)
                .notifyType(NOTIFY_TYPE_DEFAULT_LED)
                .passThrough(PASSTHROUGH)
                .title(payload.getTitle())
                .payload(payload.getPayload())
                .description(payload.getDescription());
        builder = wrapExtras(payload, builder);
        MiPushPayload miPushPayload = wrapToPayload(payload, builder);
        miPushPayload.setAll(true);
        return miPushPayload;
    }

    /**
     * @Title: buildIosAlert
     * @Description: 给所有 ios 用户推送 alert 消息
     * @param @param
     *            regists
     * @param alert
     * @param content
     * @param extras
     * @return Message
     */
    public static MiPushPayload buildIosAllAlert(String packageName, BasePayload payload) {
        return null;
    }

    /**
     * @Title: buildIosNotAlert
     * @Description: 给所有 ios 用户静默推送
     * @param regists
     * @param alert
     * @param content
     * @param extras
     * @return Message
     */
    public static MiPushPayload buildIosAllNotAlert(String packageName, BasePayload payload) {

        return null;
    }
    
    private static Message.Builder wrapExtras(BasePayload payload, Message.Builder builder) {
        Map<String, String> extras = payload.getExtras();
        for (String key : extras.keySet()) {
            String value = extras.get(key);
            if(CommonUtils.isEmpty(value)) {
                continue;
            }
            builder.extra(key, value);
        }
        return builder;
    }
    private static MiPushPayload wrapToPayload(BasePayload payload, Message.Builder builder) {
        return MiPushPayload.newInstance().addRegids(payload.getRegists()).addMessage(builder.build()).build();
    }

    
    public static MiPushPayload createPushPayload(String packageName, BasePayload payload) {
        MiPushPayload pushPayload = null;
        PushTypeEnum sign = payload.getSign();
        if(CommonUtils.isEmpty(sign)) {
            logger.error("推送载体标识为空");
            return null;
        }
        switch (sign) {
        case IOS_OR_ANDROID_ALERT:
            pushPayload = buildPlatformAllAlert(packageName,payload);
            break;
        case IOS_OR_ANDROID_NOT_ALERT:
            pushPayload = buildPlatformAllNotAlert(packageName,payload);        
            break;
//        case IOS_ALL_ALERT:
//            pushPayload = buildIosAllAlert(packageName,payload);
//            break;
//        case IOS_ALL_NOT_ALERT:
//            pushPayload = buildIosAllNotAlert(regists,alert,content,extras);
//            break;
        case ANDROID_ALL_ALERT:
            pushPayload = buildAndroidAllAlert(packageName,payload);     
            break;
        case ANDROID_ALL_NOT_ALERT:
            pushPayload = buildAndroidAllNotAlert(packageName,payload);
            break;
        case ALL_ALERT:
            pushPayload = buildPlatformAllAllAlert(packageName,payload);
            break;
        case ALL_NOT_ALERT:
            pushPayload = buildPlatformAllAllNotAlert(packageName,payload);
            break;
        default:
            break;
        }
        
        if(CommonUtils.isEmpty(pushPayload)) {
            logger.error("推送载体:"+sign.getCode()+"未找到相应的方法");
        }
        return pushPayload;
    }
    
    
    
    
}
