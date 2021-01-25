package com.email.emaildemo.service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author cyh
 * @date 2021/1/25 15:34
 */
public class MailAuthenticator extends Authenticator {

    private String strUser;
    private String strPwd;

    public MailAuthenticator() {
        super();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = this.strUser;
        String password = this.strPwd;
        if ((username != null) && (username.length() > 0) && (password != null) && (password.length() > 0)) {

            return new PasswordAuthentication(username, password);
        }

        return null;
    }

    public MailAuthenticator(String user, String password) {
        this.strUser = user;
        this.strPwd = password;
    }
}
