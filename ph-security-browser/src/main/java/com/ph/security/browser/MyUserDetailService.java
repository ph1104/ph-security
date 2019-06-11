package com.ph.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author penghui
 */
@Slf4j
@Component
public class MyUserDetailService implements UserDetailsService {



   @Autowired
   private PasswordEncoder passwordEncoder;


    /**
     * 自定义用户认证一：
     *
     *     处理用户信息获取逻辑
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("登录用户名：{}",username);
//       /*
//
//           此处的User是spring security 的User，实现了UserDetails
//
//           commaSeparatedStringToAuthorityList()方法是将字符串转换为权限列表
//
//           此处的密码应该是从数据库取出来的密码
//
//        */
//        return new User(username,"123456",AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//    }



    /**
     * 自定义用户认证二：
     *
     *    处理用户校验逻辑（账户是否可用、是否过期。。。）
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("登录用户名：{}",username);
//        return new User(username,"123456",
//                true,   //账户是否可用
//                true,  //账号是否过期
//                true,//授权是否过期
//                false,  //账户是否锁定
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//    }





    /**
     * 自定义用户认证三：
     *
     *    处理密码加密解密
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户名：{}",username);
        /*

        注册的时候将密码进行加密passwordEncoder.encode() 存入到数据库
           注：此方法每次生成的密码都不一致，会进行加盐

        登录的时候根据用户名将密码取出来 默认会根据  passwordEncoder.matches()方法进行判断密码是否一致

        */
        String password = passwordEncoder.encode("123456");
        log.info("数据库取出来的密码是:{}",password);
        return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
