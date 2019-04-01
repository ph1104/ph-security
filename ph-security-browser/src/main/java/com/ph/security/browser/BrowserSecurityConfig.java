package com.ph.security.browser;

import com.ph.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author penghui
 * @date 2019\4\1 0001   9:31
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // http.httpBasic()                     //httpBasic弹框登录
        http.formLogin()                       //表单登录,默认进行表单登录处理的过滤器是UsernamePasswordAuthenticationFilter
            .loginPage("/auth/require")          //自定义登录页面
            .loginProcessingUrl("/userlogin")  //表单登录时处理请求的url ,UsernamePasswordAuthenticationFilter默认处理的表单url是/login
            .and()
            .authorizeRequests()               //对请求做授权
            .antMatchers("/auth/require",
                    securityProperties.getBrowserProperties().getLoginPage())
            .permitAll()                       //匹配到这个url的时候，不需要身份认证
            .anyRequest()                      //任何请求
            .authenticated()                   //都需要身份认证
            .and()
                .csrf().disable();             //跨站请求防护功能关闭
    }
}
