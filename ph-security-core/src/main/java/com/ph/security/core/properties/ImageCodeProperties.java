package com.ph.security.core.properties;

import lombok.Data;

/**
 * @author penghui
 * @date 2019\4\3 0003   11:48
 *
 * 图形验证码属性配置类
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties{

    public ImageCodeProperties(){
        setLength(4);
    }

    //图片宽度
    private Integer width = 67;

    //图片高度
    private Integer height = 23;

    //验证码拦截的url
    private String url;

}
