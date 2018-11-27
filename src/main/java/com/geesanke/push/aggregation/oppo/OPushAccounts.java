package com.geesanke.push.aggregation.oppo;

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
import com.oppo.push.server.Sender;

@ConfigurationProperties("oppoPush.config")
@Component("oPushAccounts")
//@RefreshScope
public class OPushAccounts extends AbsPushAccounts<Sender> {
    private static Logger logger = LoggerFactory.getLogger(OPushAccounts.class);

    // 帐号配置
    private Map<String, OPushAccount> accounts = new HashMap<String, OPushAccount>();

    @Override
    public void afterPropertiesSet() throws Exception {
        if (CommonUtils.isEmpty(accounts)) {
            logger.error("\r\n OPush推送帐号密码参数注入失败");
            throw new MessageException(ResponseCodeMsg.PROPER_INJECT_ERRO);
        }

        for (String appId : accounts.keySet()) {
            OPushAccount account = accounts.get(appId);
            Sender client = new Sender(account.getAppKey(),account.getAppSecret());
            pushClients.put(appId, client);
            logger.debug("\r\n OPush帐号添加应用： " + appId);
        }

    }

    @Override
    public Sender ifGetNull(String appId) throws MessageException {
        OPushAccount account = this.accounts.get(appId);
        if (CommonUtils.isEmpty(account)) {
            logger.error("\r\n miPush客户端生成失败");
            throw new MessageException(ResponseCodeMsg.PUSH_CLIENT_NOT_FOUND);
        }
        Sender client;
        try {
            client = new Sender(account.getAppKey(),account.getAppSecret());
            return client;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new MessageException(ResponseCodeMsg.PUSH_CLIENT_NOT_FOUND);
        }
    }

    public Map<String, OPushAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, OPushAccount> accounts) {
        this.accounts = accounts;
    }

    
}
