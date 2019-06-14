package com.ten31f.mission;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.ten31f.mission.pi.animation.AnimationThread;
import com.ten31f.mission.pi.examples.PINController;

@Configuration
public class AppConfig {

	@Bean
	public PINController pinController() throws IOException, UnsupportedBusNumberException {
		return new PINController();
	}

	@Bean
	public AnimationThread animationThreadn(PINController pinController) {
		return new AnimationThread(pinController);
	}

	@Bean
	public TaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setThreadNamePrefix("default_task_executor_thread");
		executor.initialize();
		return executor;
	}

}
