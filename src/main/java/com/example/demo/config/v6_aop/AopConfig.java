package com.example.demo.config.v6_aop;

import com.example.demo.config.AppV1Config;
import com.example.demo.config.AppV2Config;
import com.example.demo.config.v6_aop.aspect.LogTraceAspect;
import com.example.demo.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV2Config.class, AppV1Config.class})
public class AopConfig {

//    @Bean
    public LogTraceAspect logTraceAspect(LogTrace trace) {
        return new LogTraceAspect(trace);
    }
}
