package com.hezhu.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * ctrl + o 覆盖方法
 */

@Configuration // 配置类
@EnableWebSecurity // spring security 配置类
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // 指定加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 随机加盐
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
        // 1. 采用 httpBasic 认证方式
        /*http.httpBasic() // 采用 httpBasic 认证方式
                .and()
                .authorizeRequests() // 认证请求
                .anyRequest().authenticated(); //所有访问该应用的 http 请求，都需要身份认证才可以访问*/
        // 2. 采用 formLogin 认证方式
        http.formLogin()
                .loginPage("/login/page") // 指定登录页
                .loginProcessingUrl("/login/form") // 登录表单提交处理url，默认是 "/login"
                .usernameParameter("name") // 表单提交的用户名name，默认是username
                .passwordParameter("pwd")  // 表单提交的密码name，默认是password
                .and()
                .authorizeRequests() // 认证请求
                .antMatchers("/login/page").permitAll() // 放行该请求, 不需要认证
                .anyRequest().authenticated(); //所有访问该应用的 http 请求，都需要身份认证才可以访问

    }

    /**
     * 针对静态资源放行
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) {
        super.configure(web);
        web.ignoring().antMatchers("/dist/**", "/modules/**", "/plugins/**");
    }
}












































