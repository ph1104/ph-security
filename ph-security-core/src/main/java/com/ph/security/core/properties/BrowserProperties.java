package com.ph.security.core.properties;

import com.ph.security.core.constant.LoginTypeEnum;
import lombok.Data;

/**
 * @author penghui
 */

@Data
public class BrowserProperties {

    //默认登录页面
    private String loginPage = "/login.html";

    //登录成功后返回json
    private LoginTypeEnum loginType = LoginTypeEnum.JSON;

    //记住我的时间
    private Integer rememberMeSeconds = 3600;
}
