package com.ph.security.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest  //标记这是SpringBoot的一个测试程序
@RunWith(SpringRunner.class)  //如何运行这个测试用例
public class UserControllerTest {

    //伪造一个WebMvc的环境  伪造的环境不会启动tomcat

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before  //在测试用例执行之前执行
    public void setUp(){
        //构建MockMvc
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //测试用例

    @Test
    public void whenSelectSuccess() throws Exception{

        //模拟发送HTTP请求
        //  MockMvcRequestBuilders.get("/user")  执行get请求，请求路径是  /user
        //  andExpect()  请求成功后的期望，期望返回的Http状态吗是不是200
        //  andExpect()  期望返回的结果是个集合  集合的长度是三
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username","ph")
//                .param("size","15")
//                .param("page","3")
//                .param("sort","age,desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

}
