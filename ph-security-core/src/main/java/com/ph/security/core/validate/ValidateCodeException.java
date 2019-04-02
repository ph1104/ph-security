package com.ph.security.core.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @author penghui
 * @date 2019\4\2 0002   16:54
 *
 * 图形验证码异常处理类
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
