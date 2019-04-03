package com.ph.security.core.validate;


import lombok.Data;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author penghui
 * @date 2019\4\2 0002   13:16
 */
@Data
public class ImageCode {

    //图形验证码
    private BufferedImage image;

    //验证码
    private String code;

    //过期时间
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
