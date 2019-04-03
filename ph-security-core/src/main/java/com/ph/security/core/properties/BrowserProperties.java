package com.ph.security.core.properties;

import com.ph.security.core.constant.LoginTypeEnum;
import lombok.Data;

/**
 * @author penghui
 * @date 2019\4\1 0001   16:38
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
