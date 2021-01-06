package com.cyhdemo.springsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.PrintWriter;

/**
 * @author cyh
 * @date 2020/12/5 13:28
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("cyh")
                .password("123").roles("admin");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin")
//                .usernameParameter("")
//                .passwordParameter("")  //表单的字段可以自定义
                .successForwardUrl("/success") //服务端跳转
//                .defaultSuccessUrl("/success") //重定向
//                .failureForwardUrl("/fail")
//                //登录成功回调
//                .successHandler((request, response, authentication) -> {
//                    Object principal = authentication.getPrincipal();
//                    response.setContentType("application/json:charset=utf-8");
//                    PrintWriter writer = response.getWriter();
//                    writer.write(new ObjectMapper().writeValueAsString(principal));
//                    writer.flush();
//                    writer.close();  //前后端分离
//                })
//               // .failureHandler("")
//               // .successHandler("")
//
//                //登录失败回调
//                .failureHandler((req, resp, e) -> {
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    out.write(e.getMessage());
//                    out.flush();
//                    out.close();
//                })
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("logout")//退出登录  默认get请求
//               // .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST")) 和 logoutUrl 任意设置一个即可
//                .logoutSuccessUrl("/login.html")//退出登录成功后 跳转登录页面
//                .deleteCookies()//用来清除 cookie
//                .invalidateHttpSession(true)//注销登录 H使 HttpSession 失效  默认为true   可不配
//                .clearAuthentication(true)//清除认证信息   可不配
//                .and()
                .permitAll()
                .and()
                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> {
//                    response.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = response.getWriter();
//                    out.write("尚未登录，请先登录");
//                    out.flush();
//                    out.close();
//                });
        ;
    }

}
