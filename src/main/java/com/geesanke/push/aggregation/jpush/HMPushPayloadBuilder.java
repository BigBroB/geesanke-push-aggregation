package com.geesanke.push.aggregation.jpush;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geesanke.common.core.CommonUtils;
import com.geesanke.push.common.ecode.PushTypeEnum;
import com.geesanke.push.common.request.BasePayload;

import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.Notification;

public class HMPushPayloadBuilder {

    private static Logger logger = LoggerFactory.getLogger(HMPushPayloadBuilder.class);
    
    /** 
     * @Title: buildPlatformAllAlert 
     * @Description: TODO android 或者 ios alert推送 
     * @param basePayload
     * @return PushPayload
     */
    public static PushPayload buildPlatformAllAlert(BasePayload basePayload) {
        String[] regists = basePayload.getRegists();
        String alert = basePayload.getTitle();
        String content = basePayload.getDescription();
        Map<String,String> extras = basePayload.getExtras();
        
        return PushPayload.newBuilder()
                    .setPlatform(Platform.all())
                    .setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.registrationId(regists)).build())
                    // 通知
                    .setNotification(Notification.newBuilder()
                                .addPlatformNotification(HMNotificationBuilder.buildIosNotification(alert, content, extras))
                                .addPlatformNotification(HMNotificationBuilder.buildAndroidNotification(alert, content, extras))
                            .build())
                    .setMessage(Message.newBuilder()
                                        .setMsgContent(alert)
                                        .setTitle(content)
                                        .addExtras(extras)
                                    .build())
                    .setOptions(Options.newBuilder()
                                    .setApnsProduction(!JpushConfig.development)
                                    .setTimeToLive(86400)
                                    .build())
                .build();
    }
    /** 
     * @Title: buildPlatformAllNotAlert 
     * @Description: TODO android 或者 ios 静默消息
     * @param basePayload
     * @return PushPayload
     */
    public static PushPayload buildPlatformAllNotAlert(BasePayload basePayload) {
        String[] regists = basePayload.getRegists();
        String alert = basePayload.getTitle();
        String content = basePayload.getDescription();
        Map<String,String> extras = basePayload.getExtras();
        return PushPayload.newBuilder()
                    .setPlatform(Platform.android_ios())
                    .setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.registrationId(regists)).build())
                    .setMessage(
                            Message.newBuilder()
                                        .setMsgContent(alert)
                                        .setTitle(content)
                                        .addExtras(extras)
                                    .build())
                    .setOptions(
                            Options.newBuilder()
                                        .setApnsProduction(!JpushConfig.development)
                                        .setTimeToLive(86400)
                                    .build())
                .build();
    }
    
    /** 
     * @Title: buildPlatformAllAllAlert 
     * @Description: TODO 给所有用户推送alert消息 
     * @param basePayload
     * @return PushPayload
     */
    public static PushPayload buildPlatformAllAllAlert(BasePayload basePayload) {
        String alert = basePayload.getTitle();
        String content = basePayload.getDescription();
        Map<String,String> extras = basePayload.getExtras();
        return PushPayload.newBuilder()
                    .setPlatform(Platform.all())
                    .setAudience(Audience.all())
                    .setNotification(Notification.newBuilder()
                                .addPlatformNotification(HMNotificationBuilder.buildIosNotification(alert, content, extras))
                                .addPlatformNotification(HMNotificationBuilder.buildAndroidNotification(alert, content, extras))
                            .build())
                    .setMessage(
                            Message.newBuilder()
                                        .setTitle(content)
                                        .setMsgContent(alert)
                                        .addExtras(extras)
                                    .build())
                    .setOptions(
                            Options.newBuilder()
                                        .setApnsProduction(!JpushConfig.development)
                                        .setTimeToLive(86400)
                                    .build())
                .build();
    }
    
 
    /** 
     * @Title: buildPlatformAllAllNotAlert 
     * @Description: TODO 给所有用户推送静默消息 
     * @param basePayload
     * @return PushPayload
     */
    public static PushPayload buildPlatformAllAllNotAlert(BasePayload basePayload) {
        String alert = basePayload.getTitle();
        String content = basePayload.getDescription();
        Map<String,String> extras = basePayload.getExtras();
        return PushPayload.newBuilder()
                    .setPlatform(Platform.all())
                    .setAudience(Audience.all())
                    .setMessage(
                            Message.newBuilder()
                                        .setTitle(content)
                                        .setMsgContent(alert)
                                        .addExtras(extras)
                                    .build())
                    .setOptions(
                            Options.newBuilder()
                                        .setApnsProduction(!JpushConfig.development)
                                        .setTimeToLive(86400)
                                    .build())
                .build();
    }
  
    /** 
     * @Title: buildAndroidAllAlert 
     * @Description: TODO 给所有 android 用户推送 alert 消息 
     * @param basePayload
     * @return PushPayload
     */
    public static PushPayload buildAndroidAllAlert(BasePayload basePayload) {
        String alert = basePayload.getTitle();
        String content = basePayload.getDescription();
        Map<String,String> extras = basePayload.getExtras();
        return PushPayload.newBuilder()
                    .setPlatform(Platform.android())
                    .setAudience(Audience.all())
                    // 通知
                    .setNotification(Notification.newBuilder()
                                .addPlatformNotification(HMNotificationBuilder.buildAndroidNotification(alert, content, extras))
                            .build())
                    .setMessage(Message.newBuilder()
                                        .setMsgContent(alert)
                                        .setTitle(content)
                                        .addExtras(extras)
                                    .build())
                    .setOptions(Options.newBuilder()
                                    .setApnsProduction(!JpushConfig.development)
                                    .setTimeToLive(86400)
                                    .build())
                .build();
    }
    /** 
     * @Title: buildAndroidAllNotAlert 
     * @Description: TODO 给所有 android 用户静默推送 
     * @param basePayload
     * @return PushPayload
     */
    public static PushPayload buildAndroidAllNotAlert(BasePayload basePayload) {
        String alert = basePayload.getTitle();
        String content = basePayload.getDescription();
        Map<String,String> extras = basePayload.getExtras();
        return PushPayload.newBuilder()
                    .setPlatform(Platform.android())
                    .setAudience(Audience.all())
                    .setMessage(
                            Message.newBuilder()
                                        .setMsgContent(alert)
                                        .setTitle(content)
                                        .addExtras(extras)
                                    .build())
                    .setOptions(
                            Options.newBuilder()
                                        .setApnsProduction(!JpushConfig.development)
                                        .setTimeToLive(86400)
                                    .build())
                .build();
    }
    
    /** 
     * @Title: buildIosAllAlert 
     * @Description: TODO 给所有 ios 用户推送 alert 消息 
     * @param basePayload
     * @return PushPayload
     */
    public static PushPayload buildIosAllAlert(BasePayload basePayload) {
        String alert = basePayload.getTitle();
        String content = basePayload.getDescription();
        Map<String,String> extras = basePayload.getExtras();
        
        return PushPayload.newBuilder()
                    .setPlatform(Platform.ios())
                    .setAudience(Audience.all())
                    // 通知
                    .setNotification(Notification.newBuilder()
                                            .addPlatformNotification(HMNotificationBuilder.buildIosNotification(alert, content, extras))
                                        .build())
                    .setMessage(Message.newBuilder()
                                        .setMsgContent(alert)
                                        .setTitle(content)
                                        .addExtras(extras)
                                    .build())
                    .setOptions(Options.newBuilder()
                                    .setApnsProduction(!JpushConfig.development)
                                    .setTimeToLive(86400)
                                    .build())
                .build();
    }
  
    /** 
     * @Title: buildIosAllNotAlert 
     * @Description: TODO 给所有 ios 用户静默推
     * @param basePayload
     * @return PushPayload
     */
    public static PushPayload buildIosAllNotAlert(BasePayload basePayload) {
        String alert = basePayload.getTitle();
        String content = basePayload.getDescription();
        Map<String,String> extras = basePayload.getExtras();
        
        return PushPayload.newBuilder()
                    .setPlatform(Platform.ios())
                    .setAudience(Audience.all())
                    .setMessage(
                            Message.newBuilder()
                                        .setMsgContent(alert)
                                        .setTitle(content)
                                        .addExtras(extras)
                                    .build())
                    .setOptions(
                            Options.newBuilder()
                                        .setApnsProduction(!JpushConfig.development)
                                        .setTimeToLive(86400)
                                    .build())
                .build();
    }

    public static PushPayload createPushPayload(BasePayload basePayload) {
        PushTypeEnum sign = basePayload.getSign();
        PushPayload pushPayload = null;
        switch (sign) {
        case IOS_OR_ANDROID_ALERT:
            pushPayload = buildPlatformAllAlert(basePayload);
            break;
        case IOS_OR_ANDROID_NOT_ALERT:
            pushPayload = buildPlatformAllNotAlert(basePayload);        
            break;
        case IOS_ALL_ALERT:
            pushPayload = buildIosAllAlert(basePayload);
            break;
        case IOS_ALL_NOT_ALERT:
            pushPayload = buildIosAllNotAlert(basePayload);
            break;
        case ANDROID_ALL_ALERT:
            pushPayload = buildAndroidAllAlert(basePayload);     
            break;
        case ANDROID_ALL_NOT_ALERT:
            pushPayload = buildAndroidAllNotAlert(basePayload);
            break;
        case ALL_ALERT:
            pushPayload = buildPlatformAllAllAlert(basePayload);
            break;
        case ALL_NOT_ALERT:
            pushPayload = buildPlatformAllAllNotAlert(basePayload);
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
