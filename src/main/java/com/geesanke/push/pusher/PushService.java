package com.geesanke.push.pusher;

import com.geesanke.common.exception.BaseException;
import com.geesanke.push.common.request.BasePayload;
import com.geesanke.push.common.response.PushResult;

public interface PushService {

    
    
    PushResult push(String channel,String appId,BasePayload payload) throws BaseException;
    
    
    
}
