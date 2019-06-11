package com.ph.security.core.properties;

import lombok.Data;

/**
 * @author penghui
 *
 * 短信验证码属性配置类
 */
@Data
public class SmsCodeProperties {

    //随机数位数
    private Integer length = 6;

    //过期时间
    private Integer expireIn = 60;
}
