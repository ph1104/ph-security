package com.ph.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author penghui
 */

@Data
@ConfigurationProperties(prefix = "ph.security")  //当前这个类会读取所有配置文件里以 ph.security 开头的配置项
public class SecurityProperties {

    //浏览器相关的配置读取到 browser
    private BrowserProperties browser = new BrowserProperties();

    //验证码相关配置读取到 code
    private ValidateCodeProperties code = new ValidateCodeProperties();
}
