package com.securitydemo2.securitydemo2.web;

import com.google.code.kaptcha.Producer;
import com.securitydemo2.securitydemo2.config.VerifyCodeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**  生成验证码
 *
 * @author cyh
 * @date 2020/12/15 16:36
 */
@RestController
public class VerifyCodeController {

    @Autowired
    private Producer producer;

    @GetMapping("/verifyCode")
    public void  getVerifyCode(HttpServletResponse response, HttpSession httpSession) throws IOException {
        response.setContentType("image/jpeg");
        String text = producer.createText();
        httpSession.setAttribute("verify_code",text);
        BufferedImage image = producer.createImage(text);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"jpg",outputStream);
    }


}
