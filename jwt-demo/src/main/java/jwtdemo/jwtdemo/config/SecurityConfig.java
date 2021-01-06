package jwtdemo.jwtdemo.config;

import jwtdemo.jwtdemo.filter.JwtFilter;
import jwtdemo.jwtdemo.filter.JwtLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author cyh
 * @date 2020/12/12 18:10
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("chenyuhu")
               .password("$2a$10$tOrUxV8r0Hli07UlQt04lOmi6EqxO3Y9HpbCLf1ZPaaQoyrrxitDG")
                .roles("user")
                .and()
                .withUser("admin")
                .password("$2a$10$ajZQjJ4/LhSnVibFvz1SLekuejf6aAcC70Ioj/L/eUOB6LpMgF3fm")
                .roles("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello")
                .hasRole("user")
                .antMatchers("/admin")
                .hasRole("admin")
                .antMatchers(HttpMethod.POST,"/login")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtLoginFilter("/login",authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(),UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
}
