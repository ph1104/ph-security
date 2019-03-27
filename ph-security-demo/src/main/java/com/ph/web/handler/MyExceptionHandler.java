package com.ph.web.handler;

import com.ph.exception.UserNotExistExcption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  penghui
 *
 * @date 2019\3\26    15:34
 *
 *  全局异常捕捉处理
 */

@Slf4j
@ControllerAdvice
public class MyExceptionHandler {


    /**
     * 处理用户自定义异常
     * @param request
     * @param e
     */
    @ResponseBody
    @ExceptionHandler(UserNotExistExcption.class)
    public Map handlerBussExp(HttpServletRequest request,UserNotExistExcption e){
        log.info(e.getMsg());
        Map map = new HashMap();
        map.put("code",e.getCode());
        map.put("msg",e.getMsg());
        map.put("dataResult",e.getDataResult());
        return map;
    }

    /**
     * 处理运行时异常
     * @param request
     * @param e
     */
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public void handlerRuntimeExp(HttpServletRequest request,RuntimeException e){
        log.info(e.getMessage());
    }

}
