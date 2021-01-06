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

    /**
     * 静默授权
     *
     * 静默授权：用户进入页面后自动授权并跳转回页面，这种授权对用户无感知。通过这种授权我们只能获取到用户的 openid，无法获得用户的其他信息。
     * 使用场景：只需要标识该用户，而不需要收集其他信息的场景都可以使用。比如投票、点赞等场景。
     * scope: snsapi_base
     *
     * 主动授权
     *
     * 主动授权：用户进入页面后会有授权弹窗，需要手动同意。该方试用来获取用户的基本信息
     * 注意：**对于已关注公众号的用户，**用户从公众号的会话或者自定义菜单进入本公众号的网页授权页，即使是scope: snsapi_userinfo，也是静默授权，用户无感知。
     * scope: snsapi_userinfo
     *

     */

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
