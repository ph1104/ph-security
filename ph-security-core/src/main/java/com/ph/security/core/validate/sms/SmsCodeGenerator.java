package com.ph.security.core.validate.sms;

import com.ph.security.core.validate.ImageCode;
import com.ph.security.core.validate.ValidateCodeGenerator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author penghui
 * @date 2019\4\3 0003   16:24
 */
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generateImage(HttpServletRequest request) {
        return null;
    }
}
