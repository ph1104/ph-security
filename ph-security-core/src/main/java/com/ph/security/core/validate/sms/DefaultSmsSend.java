package com.ph.security.core.validate.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @author penghui
 *
 * 发送短信验证码的默认实现
 */
@Slf4j
public class DefaultSmsSend implements SmsSend{

    @Override
    public void send(String mobile, String code) {
        log.info("发送验证码{}给{}",code,mobile);
    }
}
