package com.hezhu.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // 指定加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器：
     *  1. 认证信息（用户名、密码）
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置用户名、密码（必须加密）、权限
        auth.inMemoryAuthentication()
                .withUser("hezhu") // 用户名
                .password(passwordEncoder().encode("123456")) // 密码
                .authorities("ADMIN"); // 权限
    }

    /**
     * 资源权限配置
     *  1. 被拦截的资源
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 采用 httpBasic 认证方式
        /*http.httpBasic() // 采用 httpBasic 认证方式
                .and()
                .authorizeRequests() // 认证请求
                .anyRequest().authenticated(); //所有访问该应用的 http 请求，都需要身份认证才可以访问*/
        // 采用 formLogin 认证方式
        http.formLogin()
                .and()
                .authorizeRequests() // 认证请求
                .anyRequest().authenticated(); //所有访问该应用的 http 请求，都需要身份认证才可以访问

    }
}
