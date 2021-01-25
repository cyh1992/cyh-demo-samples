package com.email.emaildemo.service;

/**
 * @author cyh
 * @date 2021/1/25 14:50
 */
public interface MailService {

    void sendHtmlMail(String from,String to,String subject,String content);
}
