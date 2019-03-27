package com.ph.web.config;

import com.ph.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author penghui
 * @date 2019\3\27 0027   16:13
 */

@Configuration //springboot 配置类
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;


    /**
     * 重写父类的添加拦截器方法，将我们自己的拦截器添加进去
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则    excludePathPatterns 用户排除拦截
        registry.addInterceptor(timeInterceptor).addPathPatterns("/**").excludePathPatterns("/hello");
    }
}
