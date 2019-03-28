package com.ph.web.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @author penghui
 * @date 2019\3\28 0028   14:17
 */

@Slf4j
@RestController
public class AsyncController {


    /**
     * 同步处理  Http请求----》主线程处理------》响应
     * @throws InterruptedException
     */
    @GetMapping("/syncDeal")
    public void syncDeal() throws InterruptedException {
        log.info("主线程开始");
        Thread.sleep(1000);
        log.info("主线程结束");
    }


    /**
     * 使用Callable进行异步处理
     * 缺点：副线程由主线程调起
     *
     * @return
     */
    @GetMapping("/asyncRunnable")
    public Callable<String> asyncRunnable(){
        log.info("主线程开始");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("副线程开始");
                Thread.sleep(1000);
                log.info("副线程结束");
                return null;
            }
        };
        log.info("主线程结束");
        return callable;
    }
}
