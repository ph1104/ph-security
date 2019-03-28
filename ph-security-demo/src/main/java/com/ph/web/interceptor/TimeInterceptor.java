package com.ph.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author penghui
 * @date 2019\3\27 0027   14:56
 *
 * 拦截器可以获取到
 *      1、请求头里面的信息
 *      2、请求的具体的类名和方法
 *
 *
 * SpringBoot自定义拦截器
 *      1、编写拦截器实现类，此类必须实现接口   HandlerInterceptor，然后重写里面需要的三个比较常用的方法，实现自己的业务逻辑代码
 *      2、编写拦截器配置文件主类 WebMvcConfig  此类必须继承 WebMvcConfigurerAdapter 类，并重写其中的方法  addInterceptors   并且在主类上加上注解  @Configuration
 *
 *
 */
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {


    /**
     * 方法执行时间：该方法将在Controller处理之前进行调用
     *
     * SpringMvc中的Interceptor是链式的，可以同时存在多个Interceptor，然后按照顺序一个接一个执行
     *
     * 所有的Interceptor的preHandler方法都会在Controller方法调用之前调用。
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        log.info("preHandle");
        Long startTime = new Date().getTime();
        //只有返回true才会继续向下执行，返回false取消当前请求
        log.info("请求的类名：{}", ((HandlerMethod)handler).getBean().getClass().getName());
        log.info("请求的方法名：{}", ((HandlerMethod)handler).getMethod().getName());
        httpServletRequest.setAttribute("startTime",startTime);
        log.info("preHandle 耗时：{}",new Date().getTime() - startTime);
        return true;
    }


    /**
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行
     *
     * 方法执行时间：处理器执行之后，也就是在Controller的方法调用之后执行
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
        Long startTime = (Long) httpServletRequest.getAttribute("startTime");
        log.info("postHandle 耗时：{}",new Date().getTime() - startTime);
    }


    /**
     *该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。
     *
     * 该方法是在整个请求之后，主要作用是用于清理资源
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
        log.info("afterCompletion");
        Long startTime = (Long) httpServletRequest.getAttribute("startTime");
        log.info("afterCompletion 耗时：{}",new Date().getTime() - startTime);
    }
}
