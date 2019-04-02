package com.ph.security.core.properties;

import com.ph.security.core.constant.LoginTypeEnum;
import lombok.Data;

/**
 * @author penghui
 * @date 2019\4\1 0001   16:38
 */

@Data
public class BrowserProperties {

    private String loginPage = "/login.html";

    private LoginTypeEnum loginType = LoginTypeEnum.JSON;
}
