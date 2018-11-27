package com.geesanke.push.aggregation.mi;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.geesanke.push.common.exception.MessageException;
import com.geesanke.push.common.request.BasePayload;
import com.geesanke.push.common.response.PushResult;
import com.geesanke.push.pusher.AbsPushFactory;
import com.xiaomi.push.sdk.ErrorCode;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

@Component("miPushFactory")
public class MiPushFactory extends AbsPushFactory<MiPushPayload> {

    @Autowired
    private MiPushAccounts miPushAccounts;

    @Override
    public MiPushPayload createMsg(BasePayload payload) {
        String packageName = miPushAccounts.getAccount(this.getAppId()).getPackageName();
        return HMMiPushMessageBuilder.createPushPayload(packageName, payload);
    }

    @Override
    public PushResult pushMsg(MiPushPayload payload) {
        Sender sender;
        try {
            sender = miPushAccounts.getPushClient(this.getAppId());
            Result miResult = null;
            if (payload.isAll()) {
                miResult = sender.broadcastAll(payload.getMessage(), 1);
            }
            if (payload.getRegids().size() > 1) {
                miResult = sender.send(payload.getMessage(), payload.getRegids(), 1);
            } else {
                miResult = sender.send(payload.getMessage(), payload.getRegids().get(0), 1);
            }

            PushResult result = PushResult.newInstance()
                                .addMsgId(miResult.getMessageId())
                                .addIsOk(ErrorCode.Success.equals(miResult.getErrorCode()))
                                .addStatusMsg(miResult.getReason()).build();
            return result;
        } catch (IOException e) {

        } catch (ParseException e) {

        } catch (MessageException e) {

        }
        return PushResult.newInstance().build();
    }

    // public static void main(String[] args) throws IOException, ParseException {
    // Sender sender;
    // Result miResult = null;
    // sender = new Sender("9KlHHp2jiIu1FcSI2mnfJw==");
    // Map<String, String> extras = new HashMap<String, String>();
    // extras.put("asd", "asd");
    // extras.put("asd2", "asd");
    // extras.put("asd3", "asd");
    // extras.put("asd4", "asd");
    //
    // BasePayload payload = BasePayload.newInstance().addRegists(new String[] {
    // "9KlHHp2jiIu1FcSI2mnfJw==" })
    // .addTitle("notification
    // title").addExtras(extras).addSign(PushTypeEnum.IOS_OR_ANDROID_ALERT)
    // .addDescription("notification description").build();
    // MiPushPayload miPushPayload =
    // HMMiPushMessageBuilder.createPushPayload("com.inlee.push.test", payload);
    //
    // miResult = sender.send(miPushPayload.getMessage(),
    // "nil4YcigfOBxEictFlfbSd2DpkzwFQI1cMZev4lvuxxavwaTjPPK7qCKJr//Xd9/", 3);
    // System.out.println(JsonUtils.to(miResult));
    // }

}
