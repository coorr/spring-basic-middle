package com.example.demo.config.v4_postprocessor.postprocessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@RequiredArgsConstructor
@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {
    private final String basePackage;
    private final Advisor advisor;



    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        String packageName = bean.getClass().getPackageName();
        // 프록시 적용 대상이 아니면 원본을 그대로
        if (!packageName.startsWith(basePackage)) {
            return bean;
        }
        // 프록시 대상이면 프록시를 만들어서 반환
        ProxyFactory factory = new ProxyFactory(bean);
        factory.addAdvisor(advisor);
        Object proxy = factory.getProxy();
        return proxy;
    }
}
