package com.yjxxt.config;

import com.yjxxt.config.handler.SpringSecurityAuthenticationFailureHandler;
import com.yjxxt.config.handler.SpringSecurityAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * SpringSecurity 配置类
 *    内置访问控制方法
 *       permitAll
 *       authenticated
 */
//@Configuration
public class SecurityConfig3 extends WebSecurityConfigurerAdapter {
    @Resource
    private SpringSecurityAuthenticationSuccessHandler authenticationSuccessHandler;

    @Resource
    private SpringSecurityAuthenticationFailureHandler authenticationFailureHandler;



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf 配置
        http.csrf().disable();

        // 配置表单处理信息  登录页&后端登录请求地址&登录成功页面
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                // 登录成功Handler 处理
                .successHandler(authenticationSuccessHandler)
                // 登录失败Handler 处理
                .failureHandler(authenticationFailureHandler)
                // 设置登录表单参数名称
                .usernameParameter("userName")
                .passwordParameter("userPwd");


        // 配置SpringSecurity 权限
        http.authorizeRequests()
                // 配置放行资源
                .antMatchers("/login.html","/login","/error.html").permitAll()
                // 放行所有js 文件(js目录包含子目录) images||css 与 js 类似
                .antMatchers("/js/**").permitAll()
                // 资源不被任何人访问
                .antMatchers("/test.html").denyAll()
                // 记住我时 才能访问资源
                //.antMatchers("/qq","/www").rememberMe()
                // 没有记住我时 访问的资源
                //.antMatchers("/ee","/dd").fullyAuthenticated()
                // 通过正则表达式匹配资源
                //.regexMatchers( ".+[.]js").permitAll()
                // 其他资源必须登录才能访问

                //不配置这个相当于任何用户的任何请求都能访问各个页面
                .anyRequest().authenticated();

    }

}
