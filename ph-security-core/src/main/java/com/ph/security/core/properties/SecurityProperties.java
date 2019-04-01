package com.ph.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author penghui
 * @date 2019\4\1 0001   16:38
 */

@Data
@ConfigurationProperties(prefix = "ph.security")  //当前这个类会读取所有配置文件里以 ph.security 开头的配置项
public class SecurityProperties {

    //浏览器相关的配置读取到 browserProperties
    private BrowserProperties browserProperties = new BrowserProperties();
}
