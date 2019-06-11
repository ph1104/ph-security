package com.ph.security.core.validate;

import com.ph.security.core.constant.CommonConstant;
import com.ph.security.core.properties.SecurityProperties;
import com.ph.security.core.validate.sms.SmsCodeGenerator;
import com.ph.security.core.validate.sms.SmsSend;
import com.ph.security.core.validate.sms.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author penghui
 *
 * 图形验证码controller
 */


@RestController
@RequestMapping("/valicateCode")
public class ValidateCodeController {


    @Autowired
    private ImageCodeGenerator imageCodeGenerator;

    @Autowired
    private SmsCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsSend smsSend;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();





    /**
     * 图形验证码生成步骤：
     *
     *   1、根据随机数生成图片
     *
     *   2、将随机数存入到session中
     *
     *   3、将生成的图片写入到响应
     */

    @GetMapping("/createImageCode")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = imageCodeGenerator.generateCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),CommonConstant.SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }


    /**
     * 短信验证码生成
     *
     *
     *   1、根据规则生成验证码
     *
     *   2、将验证码存入到session中
     *
     *   3、将验证码发送给用户
     */

    @GetMapping("/createSmsCode")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {

        ValidateCode validateCode = smsCodeGenerator.generateCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),CommonConstant.SESSION_KEY,validateCode);
        String mobile = ServletRequestUtils.getStringParameter(request,"mobile");
        smsSend.send(mobile,validateCode.getCode());
    }







}
