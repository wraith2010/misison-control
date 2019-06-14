package com.ten31f.mission;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.ten31f.mission.pi.examples.PINController;

@Configuration
public class AppConfig {

	@Bean
	public PINController pinController() throws IOException, UnsupportedBusNumberException {
		return new PINController();
	}

}
