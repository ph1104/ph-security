package com.ph.security.browser;

import com.ph.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author penghui
 * @date 2019\4\1 0001   16:02
 */

@Slf4j
@RestController
public class BrowerSecurityController {


    //请求缓存类
    private RequestCache requestCache = new HttpSessionRequestCache();

    //用来做请求的跳转
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Autowired
    private SecurityProperties securityProperties;


    /**
     * 当需要身份认证的时候，跳转到这
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/auth/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)  //返回401 状态码
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //拿到 缓存在requestCache 中的引发跳转的请求
        SavedRequest savedRequest  = requestCache.getRequest(request,response);
        if(savedRequest != null){
            String url = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是：{}",url);
            if(StringUtils.endsWithIgnoreCase(url,".html")){
                //如果是html页面，跳转到登录页面
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowserProperties().getLoginPage());
            }
        }
        return "访问的服务需要身份认证,请先登录";
    }

}
