package com.ph.security.core.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @author penghui
 *
 * 图形验证码异常处理类
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
