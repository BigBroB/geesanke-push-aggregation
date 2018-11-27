package com.geesanke.push.aggregation.jpush;

import java.util.Map;

import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;

/** 
 * @ClassName: HMNotificationFactory 
 * @Description: 平台 JPUSH 通知（Notification） 组装工厂 
 * @author bigbro_B
 * @date 2018年6月1日 下午3:57:21 
 *  
 */
public class HMNotificationBuilder {

    
    /** 
     * @Title: buildIosNotification 
     * @Description: 组装 ios 
     * @param @param alert
     * @param @param content
     * @param @param extras
     * @return IosNotification
     */
    public static IosNotification buildIosNotification(String alert, String content,Map<String, String> extras) {
        return IosNotification.newBuilder()
                    .setAlert( 
                            //设置alert
                            IosAlert.newBuilder()
                                    .setTitleAndBody(content, null, alert)
                                        .build())
                    .incrBadge(1)
                    .setSound("default")
                    .setContentAvailable(true)
                    .setMutableContent(true)
                    .addExtras(extras)
                .build();
    }
    
    
    /** 
     * @Title: buildAndroidNotification 
     * @Description: 组装 android 
     * @param alert
     * @param content
     * @param extras
     * @return AndroidNotification
     */
    public static AndroidNotification buildAndroidNotification(String alert, String content,Map<String, String> extras) {
        return AndroidNotification.newBuilder()
                            .setAlert(alert)
                            .setTitle(content)
                            .addExtras(extras)
                        .build();
    }
    
}
