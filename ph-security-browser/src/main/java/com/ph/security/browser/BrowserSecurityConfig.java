package com.ph.security.browser;

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


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
           http.formLogin()           //表单登录
//           http.httpBasic()         //http basic 弹框登录
               .loginPage("login.html")
               .and()
               .authorizeRequests()   //对请求做授权
               .anyRequest()          //任何请求
               .authenticated();      //都需要身份认证
    }
}
