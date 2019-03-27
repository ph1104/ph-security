package com.ph.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Date;

/**
 * @author penghui
 * @date 2019\3\27 0027   14:23
 *
 *
 *  Filter 可以获取到请求头里面的信息，但是获取不到请求的具体路径和方法
 *
 *
 *  通过注解的方式进行注册Filter
 *
 *  方法一：
 *        在Filter类上加入 @Component 注解，Spring会将该类加入到spring的容器中 ，也就是让Filter起作用
 *
 *  方法二：
 *     （1）、创建一个Filter，并使用 @WebFilter 注解进行修饰，表示该类是一个Filter
 *     （2）、在SpringBoot的启动类中同样额外添加一个注解用于自动扫描指定包下（默认是与启动类同包下）的WebFilter/WebServlet/WebListener等特殊类
 */
@Slf4j
@Component   //将filter加入到spring的容器中 ，也就是让Filter起作用
//@WebFilter
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("filter start");
        Long startTime = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("filter 耗时: {}",new Date().getTime()-startTime);
        log.info("filter finish");
    }

    @Override
    public void destroy() {
        log.info("filter destroy");
    }
}
