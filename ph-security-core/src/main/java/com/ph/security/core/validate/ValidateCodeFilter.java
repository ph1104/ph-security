package com.ph.security.core.validate;


import com.ph.security.core.constant.CommonConstant;
import com.ph.security.core.properties.SecurityProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author penghui
 * @date 2019\4\2 0002   16:04
 *
 * 图形验证码过滤器
 *
 */
@Data
@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private SecurityProperties securityProperties;

    private Set<String> urls = new HashSet<>();


    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] urlsInConfig = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(),",");
        for (String url: urlsInConfig) {
            urls.add(url);
        }
    }

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

        boolean flag = false;
        for(String url: urls) {
            if(antPathMatcher.match(url,httpServletRequest.getRequestURI())){
                flag = true;
            }
        }

        if(flag){
            //进行校验
            try{
                validate(new ServletWebRequest(httpServletRequest));
            }catch(ValidateCodeException e){
                //校验失败，抛出异常信息,调用失败处理
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }
        //校验成功 调用后续的过滤器
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }




    public void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        //存在session中的验证码
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest,CommonConstant.SESSION_KEY);
        //请求中的验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");
        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("请输入验证码");
        }
        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(servletWebRequest,CommonConstant.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if(!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(servletWebRequest,CommonConstant.SESSION_KEY);
    }


}
