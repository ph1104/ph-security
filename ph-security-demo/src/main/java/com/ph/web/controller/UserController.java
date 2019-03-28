package com.ph.web.controller;

import com.ph.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/user")
@RestController  //标记这个controlller是用来处理Rest风格的请求
public class UserController {



    @GetMapping("/hello")
    public String sayHello(){
        log.info("hello controller");
        return "hello spring boot";
    }

   @GetMapping("/page")
   public List<User> getUser(@RequestParam String username,
                             @PageableDefault(size = 10,page = 1,sort = "username,asc") Pageable pageable){  //Pageable 是SpringData中的一个对象

       System.out.println(username);
       System.out.println(pageable.getPageSize());
       System.out.println(pageable.getPageNumber());
       System.out.println(pageable.getSort());
       List<User> users = new ArrayList<>();
       users.add(new User());
       users.add(new User());
       users.add(new User());
       return users;
    }



    @GetMapping("/{id}")
    public User getUserDetail(@PathVariable Integer id){
       User user = new User(1,"ph","123456");
       return user;
    }


}
