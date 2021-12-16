package com.yjxxt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf 配置
        http.csrf().disable();


        // 配置表单处理信息  登录页&后端登录请求地址&登录成功页面
        http.formLogin()
                //自定义登录的界面
                .loginPage("/login.html")
                //跟表单提交的地址一样
                .loginProcessingUrl("/login")
                // 登录成功页面跳转
                .successForwardUrl("/toMain")
                // 登录失败页面跳转
                .failureForwardUrl("/toError")
                // 设置登录表单参数名称
                .usernameParameter("userName")
                .passwordParameter("userPwd");


        // 配置SpringSecurity 权限
        http.authorizeRequests()
                // 配置放行资源
                .antMatchers("/login.html","/login","/error.html").permitAll()
                // 其他资源必须登录才能访问
                .anyRequest().authenticated();

    }


}
