package com.example.demo.config.v4_postprocessor;

import com.example.demo.config.AppV1Config;
import com.example.demo.config.AppV2Config;
import com.example.demo.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.example.demo.config.v4_postprocessor.postprocessor.PackageLogTracePostProcessor;
import com.example.demo.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {

    @Bean
    public PackageLogTracePostProcessor logTracePostProcessor(LogTrace trace) {
        return new PackageLogTracePostProcessor("com.example.demo.app",getAdvisor(trace));
    }

    private Advisor getAdvisor(LogTrace trace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        LogTraceAdvice advice = new LogTraceAdvice(trace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }

}
