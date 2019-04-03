package com.ph.code;

import com.ph.security.core.validate.ImageCode;
import com.ph.security.core.validate.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author penghui
 * @date 2019\4\3 0003   14:51
 */
@Slf4j
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generateImage(HttpServletRequest request) {
        log.info("demo调用验证码生成逻辑，返回空");
        return null;
    }
}
