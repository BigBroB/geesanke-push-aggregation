package com.geesanke.push.common.exception;

import com.geesanke.common.exception.BaseException;
import com.geesanke.push.common.ecode.ResponseCodeMsg;

public class MessageException extends BaseException {

    private static final long serialVersionUID = -1267974048788332396L;

    public MessageException(ResponseCodeMsg enums) {
        super(enums.getCode(), enums.getMsg());
    }

}
