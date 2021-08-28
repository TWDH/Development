package com.hezhu.ottdev.config;

import com.hezhu.ottdev.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean myFilterRegistrationBean() {
        // 注册过滤器
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>(new MyFilter());

        // 添加过滤路径
        filterFilterRegistrationBean.addUrlPatterns("/user/*");

        return filterFilterRegistrationBean;
    }
}
