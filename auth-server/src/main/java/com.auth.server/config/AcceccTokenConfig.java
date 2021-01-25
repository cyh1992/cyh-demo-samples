package com.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import java.util.Date;

/**
 * @author cyh
 * @date 2021/1/7 11:01
 */
@Configuration
public class AcceccTokenConfig {

    @Bean
    TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Configuration
    @EnableAuthorizationServer
    class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

        @Autowired
        ClientDetailsService clientDetailsService;

        @Bean
        AuthorizationServerTokenServices tokenServices() {
            DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
            defaultTokenServices.setClientDetailsService(clientDetailsService);
            defaultTokenServices.setTokenStore(new InMemoryTokenStore());
            defaultTokenServices.setSupportRefreshToken(true);
            defaultTokenServices.setAccessTokenValiditySeconds(60 * 60 * 2);
            defaultTokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 3);
            return defaultTokenServices;
        }

        /**
         * AuthorizationServerSecurityConfigurer 配置令牌端点的安全约束
         * @param security
         * @throws Exception
         */
        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security.checkTokenAccess("permitAll()")
                    .allowFormAuthenticationForClients();
        }

        /**
         * ClientDetailsServiceConfigurer 用来配置客户端的详细信息
         * @param clients
         * @throws Exception
         */
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
           clients.inMemory()
                   .withClient("chenyuhu")
                   .secret(new BCryptPasswordEncoder().encode("123"))
                   .resourceIds("res1")
                   .authorizedGrantTypes("authorization_code","refresh_token")
                   .scopes("all")
                   .redirectUris("http://localhost:8082/index.html");
        }

        /**
         * AuthorizationServerEndpointsConfigurer 这里用来配置令牌的访问端点和令牌服务
         * authorizationCodeServices用来配置授权码的存储
         * @param endpoints
         * @throws Exception
         */
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authorizationCodeServices(authorizationCodeServices())
                    .tokenServices(tokenServices());
        }

        @Bean
        AuthorizationCodeServices authorizationCodeServices() {
            return new InMemoryAuthorizationCodeServices();
        }
    }
}


