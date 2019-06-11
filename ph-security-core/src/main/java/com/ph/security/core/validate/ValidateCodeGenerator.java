package com.ph.security.core.validate;

import com.ph.security.core.validate.sms.ValidateCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author penghui
 *
 * 验证码生成规则接口
 */
public interface ValidateCodeGenerator {

    ValidateCode generateCode(HttpServletRequest request);
}
