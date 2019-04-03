package com.ph.security.core.properties;

import lombok.Data;

/**
 * @author penghui
 * @date 2019\4\3 0003   11:48
 */
@Data
public class ImageCodeProperties {

    //图片宽度
    private Integer width = 67;

    //图片高度
    private Integer height = 23;

    //随机数位数
    private Integer length = 4;

    //过期时间
    private Integer expireIn = 60;

    //验证码拦截的url
    private String url;

}
