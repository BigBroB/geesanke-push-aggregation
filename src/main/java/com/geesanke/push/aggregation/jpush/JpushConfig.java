package com.geesanke.push.aggregation.jpush;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpushConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public static boolean development = true;

    public static boolean isDevelopment() {
        return development;
    }

    @Value("${jpush.config.development}")
    public void setDevelopment(boolean development) {
        logger.info("\r\n jpush的推送环境是："+development);
        JpushConfig.development = development;
    }

}
