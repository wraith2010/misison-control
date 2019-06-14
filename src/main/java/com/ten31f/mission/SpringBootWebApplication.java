package com.ten31f.mission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import com.ten31f.mission.pi.animation.AsynchronousService;

@SpringBootApplication
@EnableAsync
public class SpringBootWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWebApplication.class);
	}

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootWebApplication.class, args);

		AsynchronousService asynchronousService = applicationContext.getBean(AsynchronousService.class);
		asynchronousService.executeAsynchronously();
	}

}
