package com.email.emaildemo.service.impl;

import com.email.emaildemo.service.MailAuthenticator;
import com.email.emaildemo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * @author cyh
 * @date 2021/1/25 14:52
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String userName;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;
    @Value("${spring.mail.properties.mail.smtp.socketFactory.class}")
    private String socketFactory;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;


    @Override
    public void sendHtmlMail(String from, String to, String subject, String content) {
        try {
            // 配置发送邮件的环境属性
            final Properties props = new Properties();
            // 表示SMTP发送邮件，需要进行身份验证
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", host);
            // props.put("mail.smtp.port", ALIDM_SMTP_PORT);
            // 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
            props.put("mail.smtp.socketFactory.class", socketFactory);
            props.put("mail.smtp.port", port);
            // 发件人的账号，填写控制台配置的发信地址,比如xxx@xxx.com
            props.put("mail.user", userName);
            // 访问SMTP服务时需要提供的密码(在控制台选择发信地址进行设置)
            props.put("mail.password", password);
            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);

            MimeMessage message  = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message );
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            javaMailSender.send(message );
        } catch (MessagingException  e) {
            System.out.println("发送邮件失败");
        }

    }
}
