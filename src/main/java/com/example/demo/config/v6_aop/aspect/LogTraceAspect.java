package com.example.demo.config.v6_aop.aspect;

import com.example.demo.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogTraceAspect {

    private final LogTrace trace;

    @Around("execution(* com.example.demo.app..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature().toShortString();
            status = trace.begin(message);
            // 비즈니스 로직 호출
            Object result = joinPoint.proceed();
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
