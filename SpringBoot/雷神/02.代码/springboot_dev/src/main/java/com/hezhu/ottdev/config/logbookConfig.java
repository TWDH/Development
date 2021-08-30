package com.hezhu.ottdev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.*;

import static org.zalando.logbook.Conditions.contentType;

@Configuration
public class logbookConfig {
    @Bean
    public RequestFilter requestFilter() {
        System.out.println(" === This is requestFilter === ");
        return RequestFilters.replaceBody(new BodyReplacer<HttpRequest>() {
            @org.jetbrains.annotations.Nullable
            @Override
            public String replace(HttpRequest message) {

                System.out.println(message);
                return "This is Replace";
            }
        });
    }

    /*@Bean
    public BodyFilter bodyFilter() {

    }*/
}
