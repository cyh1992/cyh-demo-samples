package com.securitydemo2.securitydemo2.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author cyh
 * @date 2020/12/15 16:32
 */
@Configuration
public class VerifyCodeConfig {

        @Bean
       Producer verifyCode() throws IOException {
        Properties properties = new Properties();
        properties.load(new ClassPathResource("kaptch.properties").getInputStream());
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
