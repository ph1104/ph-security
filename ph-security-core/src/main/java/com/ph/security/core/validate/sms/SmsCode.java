package com.ph.security.core.validate.sms;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author penghui
 * @date 2019\4\3 0003   16:18
 *
 * 验证码实体类
 */
@Data
public class SmsCode {

    //验证码
    private String code;

    //过期时间
    private LocalDateTime expireTime;

    public SmsCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public SmsCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
