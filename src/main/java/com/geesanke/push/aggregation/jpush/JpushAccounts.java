package com.geesanke.push.aggregation.jpush;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.geesanke.common.core.CommonUtils;
import com.geesanke.push.account.AbsPushAccounts;
import com.geesanke.push.common.ecode.ResponseCodeMsg;
import com.geesanke.push.common.exception.MessageException;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;

@ConfigurationProperties("jpush.config")
@Component("jpushAccounts")
//@RefreshScope
public class JpushAccounts extends AbsPushAccounts<JPushClient>  {

    private static Logger logger = LoggerFactory.getLogger(JpushAccounts.class);

    // 帐号配置
    private Map<String, JpushAccount> accounts = new HashMap<String, JpushAccount>();
   
    @Override
    public void afterPropertiesSet() throws Exception {

        if (CommonUtils.isEmpty(accounts)) {
            logger.error("\r\n 极光推送帐号密码参数注入失败");
            throw new MessageException(ResponseCodeMsg.PROPER_INJECT_ERRO);
        }

        for (String appId : accounts.keySet()) {
            JpushAccount account = accounts.get(appId);
            JPushClient client = new JPushClient(account.getMasterSecret(), account.getAppKey(), null,
                    ClientConfig.getInstance());
            pushClients.put(appId, client);
            logger.debug("\r\n 极光推送帐号添加应用： " + appId);
        }

    }

    @Override
    public JPushClient ifGetNull(String appId) throws MessageException {
        JpushAccount account = this.accounts.get(appId);
        if (CommonUtils.isEmpty(account)) {
            logger.error("\r\n 极光推送客户端生成失败");
            throw new MessageException(ResponseCodeMsg.JPUSH_CLIENT_NOT_FOUND);
        }
        JPushClient client = new JPushClient(account.getMasterSecret(), account.getAppKey(), null,
                ClientConfig.getInstance());
        return client;
    }

    public JpushAccounts() {

    }

    public Map<String, JpushAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, JpushAccount> accounts) {
        this.accounts = accounts;
    }

  
}
