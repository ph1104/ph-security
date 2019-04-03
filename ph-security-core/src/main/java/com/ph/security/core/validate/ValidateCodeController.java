package com.ph.security.core.validate;

import com.ph.security.core.constant.CommonConstant;
import com.ph.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
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
 * @date 2019\4\2 0002   15:23
 *
 * 图形验证码controller
 */


@RestController
@RequestMapping("/valicateCode")
public class ValidateCodeController {


    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

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
        ImageCode imageCode = imageCodeGenerator.generateImage(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),CommonConstant.SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }


    /**
     * 短信验证码生成
     * @param request
     * @param response
     * @throws IOException
     */

    @GetMapping("/createSmsCode")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = imageCodeGenerator.generateImage(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),CommonConstant.SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }







}
