package com.cyh.weixin.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author cyh
 * @date 2021/1/6 10:42
 */
@Component
@Configuration
public class WechatMpConfig {

    @Autowired
    private WechatMpPropertires wechatMpPropertires;

    /**
     * 配置appID和appsecret
     * @return
     */
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        // 使用这个实现类则表示将配置信息存储在内存中
        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId(wechatMpPropertires.getAppId());
        wxMpDefaultConfig.setSecret(wechatMpPropertires.getAppSecret());
        return wxMpDefaultConfig;
    }

    /**
     * 配置WxMpService所需信息
     * @return
     */
    @Bean // 此注解指定在Spring容器启动时，就执行该方法并将该方法返回的对象交由Spring容器管理
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

}
