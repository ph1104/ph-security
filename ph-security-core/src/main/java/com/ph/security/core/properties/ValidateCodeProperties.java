package com.ph.security.core.properties;

import lombok.Data;

/**
 * @author penghui
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();
}
