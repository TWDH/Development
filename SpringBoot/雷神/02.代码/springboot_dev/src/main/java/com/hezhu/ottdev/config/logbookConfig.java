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
    public RequestFilter requestFilter() {
        System.out.println(" === This is requestFilter === ");
        return RequestFilters.replaceBody(new BodyReplacer<HttpRequest>() {
            @org.jetbrains.annotations.Nullable
            @Override
            public String replace(HttpRequest message) {

                String host = message.getHost();
                System.out.println(message);
                return host;
            }
        });
    }

    @Bean
    public BodyFilter bodyFilter() {
        BodyFilter jsonFilter = jsonPath("$.active").replace("unknown");

        BodyFilter merge = BodyFilter.merge(BodyFilters.defaultValue(), BodyFilters.truncate(1));
        return jsonFilter;
    }
}
