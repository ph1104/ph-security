package com.ph.web.controller;

import com.ph.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController  //标记这个controlller是用来处理Rest风格的请求
public class UserController {


   @RequestMapping(value = "/user",method = RequestMethod.GET)
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
}
