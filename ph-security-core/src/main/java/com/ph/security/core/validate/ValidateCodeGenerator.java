package com.ph.security.core.validate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author penghui
 * @date 2019\4\3 0003   14:23
 *
 * 验证码生成规则接口
 */
public interface ValidateCodeGenerator {

    ImageCode generateImage(HttpServletRequest request);
}
