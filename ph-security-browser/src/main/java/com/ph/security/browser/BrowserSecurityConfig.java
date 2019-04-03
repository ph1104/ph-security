package com.ph.security.browser;

import com.ph.security.browser.authentication.MyAuthenticationFailureHandler;
import com.ph.security.browser.authentication.MyAuthenticationSuccessHandler;
import com.ph.security.core.properties.SecurityProperties;
import com.ph.security.core.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


/**
 * @author penghui
 * @date 2019\4\1 0001   9:31
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private MyAuthenticationSuccessHandler successHandler;
    @Autowired
    private MyAuthenticationFailureHandler failureHandler;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * 记住我功能配置
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return  jdbcTokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        validateCodeFilter.setAuthenticationFailureHandler(failureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

       // http.httpBasic()                     //httpBasic弹框登录
        http.addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)  //将图片验证码过滤器加入到UsernamePassword过滤器之前
            .formLogin()                       //表单登录,默认进行表单登录处理的过滤器是UsernamePasswordAuthenticationFilter
            .loginPage("/auth/require")          //自定义登录请求
//            .loginPage("/login.html")          //自定义登录页面
            .successHandler(successHandler)     //自定义登录成功后的处理
            .failureHandler(failureHandler)    //自定义登录失败后的处理
            .loginProcessingUrl("/userlogin")  //表单登录时处理请求的url ,UsernamePasswordAuthenticationFilter默认处理的表单url是/login
            .and()
                .rememberMe()                  //记住我相关配置
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())   //token失效时间
                .userDetailsService(userDetailsService)
            .and()
                .authorizeRequests()               //对请求做授权
                .antMatchers("/auth/require",
                        "/valicateCode/createImageCode",
                        securityProperties.getBrowser().getLoginPage())
                .permitAll()                       //匹配到这个url的时候，不需要身份认证
                .anyRequest()                      //任何请求
                .authenticated()                   //都需要身份认证
            .and()
                .csrf().disable();             //跨站请求防护功能关闭
    }
}
