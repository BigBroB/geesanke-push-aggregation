package com.geesanke.push.aggregation.mi;

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
import com.xiaomi.xmpush.server.Sender;

@ConfigurationProperties("miPush.config")
@Component("miPushAccounts")
//@RefreshScope
public class MiPushAccounts extends AbsPushAccounts<Sender> {
    private static Logger logger = LoggerFactory.getLogger(MiPushAccounts.class);

    // 帐号配置
    private Map<String, MiPushAccount> accounts = new HashMap<String, MiPushAccount>();

    @Override
    public void afterPropertiesSet() throws Exception {
        if (CommonUtils.isEmpty(accounts)) {
            logger.error("\r\n miPush推送帐号密码参数注入失败");
            throw new MessageException(ResponseCodeMsg.PROPER_INJECT_ERRO);
        }

        for (String appId : accounts.keySet()) {
            MiPushAccount account = accounts.get(appId);
            Sender client = new Sender(account.getAppSecret());
            pushClients.put(appId, client);
            logger.debug("\r\n miPush帐号添加应用： " + appId);
        }
    }

    @Override
    public Sender ifGetNull(String appId) throws MessageException {
        MiPushAccount account = this.accounts.get(appId);
        if (CommonUtils.isEmpty(account)) {
            logger.error("\r\n miPush客户端生成失败");
            throw new MessageException(ResponseCodeMsg.JPUSH_CLIENT_NOT_FOUND);
        }
        Sender client = new Sender(account.getAppSecret());
        return client;
    }

    public MiPushAccount getAccount(String appId) {
        return accounts.get(appId);
    }

    
    /** 
     * @Title: getAccounts 
     * @Description: TODO 注入参数 ，千万别忘记
     * @return Map<String,MiPushAccount>
     */
    public Map<String, MiPushAccount> getAccounts() {
        return accounts;
    }

    /** 
     * @Title: setAccounts 
     * @Description: TODO 注入参数 ，千万别忘记
     * @param accounts    
     * @return void
     */
    public void setAccounts(Map<String, MiPushAccount> accounts) {
        this.accounts = accounts;
    }
    
    
    
}
