package com.geesanke.push.pusher;

import com.geesanke.push.common.request.BasePayload;
import com.geesanke.push.common.response.PushResult;

public interface PushFactory {
    
    
    public PushResult push(BasePayload payload) ;

}
