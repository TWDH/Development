package com.hezhu.ottdev.config;

import com.hezhu.ottdev.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 过滤条件
        String[] addPathPatterns = {
                "/user/**"
        };
        String[] excludePathPatterns = {
                "/user/withoutLogin",
                "/user/error",
                "/user/login"
        };

        registry.addInterceptor(new MyInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}
