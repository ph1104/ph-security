package com.ph.security.core.validate;


import com.ph.security.core.validate.sms.ValidateCode;
import lombok.Data;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author penghui
 *
 * 图形验证码实体类
 */
@Data
public class ImageCode extends ValidateCode {

    //图形验证码
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn){
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
        super(code, expireTime);
        this.image = image;
    }





}
