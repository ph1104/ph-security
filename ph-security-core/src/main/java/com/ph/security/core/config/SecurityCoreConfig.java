package com.ph.security.core.config;

import com.ph.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author penghui
 * @date 2019\4\1 0001   16:41
 *
 *
 * 当前配置类的作用是 让SecurityProperties这个配置读取器生效
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
