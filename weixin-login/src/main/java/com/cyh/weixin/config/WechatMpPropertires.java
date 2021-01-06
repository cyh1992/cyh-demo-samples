package com.cyh.weixin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cyh
 * @date 2021/1/6 10:35
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WechatMpPropertires {

    private String appId;

    private String appSecret;
}
