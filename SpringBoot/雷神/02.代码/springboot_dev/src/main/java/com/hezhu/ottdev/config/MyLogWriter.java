package com.hezhu.ottdev.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpLogWriter;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.Precorrelation;

import java.io.IOException;

// @Slf4j
public class MyLogWriter implements HttpLogWriter {
    private final Logger log = LoggerFactory.getLogger(Logbook.class);

    private final String BEGIN_TIME_MILLIS = "BEGIN_TIME_MILLIS";

    @Override
    public boolean isActive() {
        return this.log.isTraceEnabled();
    }

    @Override
    public void write(Precorrelation precorrelation, String request) throws IOException {
        MDC.put(BEGIN_TIME_MILLIS, String.valueOf(System.currentTimeMillis()));
        this.log.trace(request);
    }

    @Override
    public void write(Correlation correlation, String response) throws IOException {
        String timeInterval = String.valueOf(System.currentTimeMillis() - Long.parseLong(MDC.get(BEGIN_TIME_MILLIS)));
        JSONObject jsonObject = JSON.parseObject(response);
        jsonObject.put("time", timeInterval + "ms");
        String jsonResponse = jsonObject.toJSONString();
        System.out.println(jsonResponse);
        this.log.trace(jsonResponse);
    }
}
