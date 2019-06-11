package com.ph.security.core.validate.sms;

/**
 * @author penghui
 *
 * 发送短信验证码接口，可自定义实现规则
 */
public interface SmsSend {
    void send(String mobile, String code);
}
