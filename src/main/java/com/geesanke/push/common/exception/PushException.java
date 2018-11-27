package com.geesanke.push.common.exception;

import com.geesanke.common.exception.BaseException;
import com.geesanke.push.common.ecode.ResponseCodeMsg;

public class PushException extends BaseException {

    private static final long serialVersionUID = -8953714064501576437L;

    public PushException(ResponseCodeMsg enums) {
        super(enums.getCode(), enums.getMsg());
    }

}
