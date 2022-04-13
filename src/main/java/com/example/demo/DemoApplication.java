package com.example.demo;

import com.example.demo.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import com.example.demo.config.v3_proxyfactory.ProxyFactoryConfig1;
import com.example.demo.config.v3_proxyfactory.ProxyFactoryConfig2;
import com.example.demo.config.v5_autoproxy.AutoProxyConfig;
import com.example.demo.config.v6_aop.AopConfig;
import com.example.demo.trace.logtrace.LogTrace;
import com.example.demo.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(AppV1Config.class)
//@Import({AppV1Config.class, AppV2Config.class})
//@Import(DynamicProxyBasicConfig.class)
@Import(AopConfig.class)
@SpringBootApplication(scanBasePackages = "com.example.demo.app")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}
}
