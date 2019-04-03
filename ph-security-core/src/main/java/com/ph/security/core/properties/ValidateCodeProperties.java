package com.ph.security.core.properties;

import lombok.Data;

/**
 * @author penghui
 * @date 2019\4\3 0003   11:49
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();
}
