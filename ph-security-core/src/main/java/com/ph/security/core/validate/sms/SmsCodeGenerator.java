package com.ph.security.core.validate.sms;

import com.ph.security.core.properties.SecurityProperties;
import com.ph.security.core.validate.ImageCode;
import com.ph.security.core.validate.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author penghui
 * @date 2019\4\3 0003   16:24
 *
 * 短信验证码生成规则实现
 */
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generateCode(HttpServletRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());

    }
}
