package com.ph.web.controller;

import com.ph.exception.UserNotExistExcption;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/exception")
public class ExceptionTestController {

    @GetMapping("/test1")
    public void testException(){
        throw new UserNotExistExcption();
    }


    /**
     *
     * 单个异常处理:
     *
     * 在controller中加入被@ExceptionHandler修饰的类即可
     * 该异常处理方法只在当前的controller中起作用
     *
     * @return
     */
    //@ExceptionHandler(UserNotExistExcption.class)  //指定该方法需要处理的异常类
    public Map<String,Object> exceptionHandler() {
        Map<String,Object> map = new HashMap<>();
        map.put("code",10001);
        map.put("message","user not exists");
        return map;
    }



    @GetMapping("/test2")
    public void exceptionHandlerAll() throws UserNotExistExcption{
        throw new UserNotExistExcption(888,"用户不存在",null);
    }


}
