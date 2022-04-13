package com.example.demo.config.v3_proxyfactory.advice;

import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public class LogTraceAdvice implements MethodInterceptor {
    private final LogTrace trace;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;
        try {
            Method method = invocation.getMethod();
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = trace.begin(message);
            // 비즈니스 로직 호출
            Object result = invocation.proceed();
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
