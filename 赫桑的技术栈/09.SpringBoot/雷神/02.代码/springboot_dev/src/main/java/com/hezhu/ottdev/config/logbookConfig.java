package com.hezhu.ottdev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.*;
import static org.zalando.logbook.json.JsonPathBodyFilters.jsonPath;
import static java.util.regex.Pattern.compile;

import static org.zalando.logbook.Conditions.contentType;

@Configuration
public class logbookConfig {
    @Bean
    public HttpLogWriter writer() {
        return new MyLogWriter();
        // return new DefaultHttpLogWriter();

    }
}
