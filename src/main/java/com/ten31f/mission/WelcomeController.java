package com.ten31f.mission;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.ten31f.mission.pi.examples.PINController;

@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@Autowired
	private PINController pingController = null;

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {

		GpioPinDigitalOutput[] simionPins = getPingController().getSimonOut();

		for (GpioPinDigitalOutput gpioPinDigitalOutput : simionPins) {

			model.put(gpioPinDigitalOutput.getName(), gpioPinDigitalOutput.getState());

		}

		model.put("message", this.message);
		return "welcome";
	}

	public void setPingController(PINController pingController) {
		this.pingController = pingController;
	}

	public PINController getPingController() {
		return pingController;
	}

}
