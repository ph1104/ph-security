package com.ph.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author penghui
 * @date 2019\3\27 0027   17:12
 */
@Slf4j
@Aspect //创建一个切面类
@Component
public class ControllerAop {

    //指定切点  匹配com.ph.web.controller包及其子包下的所有类的 返回值为任意的 所有方法
    @Pointcut("execution(* com.ph.web.controller..*.*(..))")
    public void executeService(){

    }


    /**
     * 前置通知，方法调用前被调用
     *
     */
    @Before("executeService()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        log.info("我是前置通知");
        log.info("AOP代理类的信息{}",joinPoint.getThis());
        log.info("AOP代理的目标对象{}",joinPoint.getTarget());
        log.info("通知的签名是{}",joinPoint.getSignature());
        log.info("AOP代理的方法名{}",joinPoint.getSignature().getName());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //获取request的信息
        HttpServletRequest httpServletRequest = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //获取session信息
        HttpSession httpSession = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
    }


    /**
     * 环绕通知：
     *      环绕通知，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *      环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     *
     */

    @Around("executeService()")
    public void doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        log.info("我是环绕通知");
        log.info("环绕通知的目标方法名：{}",proceedingJoinPoint.getSignature().getName());
    }

}
