package com.ph.security.core.validate;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author penghui
 * @date 2019\4\2 0002   16:04
 *
 * 图形验证码过滤器
 *
 */
public class ValidateCodeFilter extends OncePerRequestFilter {


    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;


    /**
     *  验证前端输入的验证码和sesion中保存的是否一致
     *
     *  将ValidateCodeFilter加入到SpringSecurity的过滤器链中，
     *
     *  在UsernamePasswordAuthenticationFilter之前进行校验:
     *     1》匹配成功则接着调用后续的过滤器
     *     2》匹配失败则抛出异常信息
     *
     */


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        if(StringUtils.equals("/valicateCode/createImageCode",httpServletRequest.getRequestURI())
                && StringUtils.endsWithIgnoreCase(httpServletRequest.getMethod(),"post")){

            //进行校验
            try{
                validate();
            }catch(ValidateCodeException e){
                //校验失败，抛出异常信息,调用失败处理
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
            }

            //校验成功 调用后续的过滤器
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }


    public void validate(){

    }


}
