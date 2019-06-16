package com.ten31f.mission;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.ten31f.mission.animation.AnimationThread;
import com.ten31f.mission.pi.IPINController;
import com.ten31f.mission.pi.PINControllerOffBoard;
import com.ten31f.mission.pi.PINControllerOnBoard;
import com.ten31f.mission.profiles.NotOffBoardCondition;
import com.ten31f.mission.profiles.OffBoardCondition;

@Configuration
public class AppConfig {

	@Bean
	@Conditional(NotOffBoardCondition.class)
	public IPINController pinControllerOnBoard() throws IOException, UnsupportedBusNumberException {
		return new PINControllerOnBoard();
	}

	@Bean
	@Conditional(OffBoardCondition.class)
	public IPINController pinControllerOffBoard() {
		return new PINControllerOffBoard();
	}

	@Bean
	public AnimationThread animationThreadn(IPINController pinController) {
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
