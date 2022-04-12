package com.example.demo;

import com.example.demo.config.AppV1Config;
import com.example.demo.config.AppV2Config;
import com.example.demo.config.v1_proxy.ConcreteProxyConfig;
import com.example.demo.config.v1_proxy.InterfaceProxyConfig;
import com.example.demo.trace.logtrace.LogTrace;
import com.example.demo.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(AppV1Config.class)
//@Import({AppV1Config.class, AppV2Config.class})
@Import(ConcreteProxyConfig.class)
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
