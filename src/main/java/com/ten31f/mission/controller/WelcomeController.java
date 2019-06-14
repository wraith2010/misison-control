package com.ten31f.mission.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.ten31f.mission.pi.examples.PINController;

@Controller
public class WelcomeController {

	@Autowired
	private PINController pingController = null;

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {

		GpioPinDigitalOutput[] simionPins = getPingController().getSimonOut();

		for (GpioPinDigitalOutput gpioPinDigitalOutput : simionPins) {

			model.put(gpioPinDigitalOutput.getName(), gpioPinDigitalOutput.getState());

		}

		return "welcome";
	}

	@GetMapping("/{pinName}")
	public String flipPin(@PathVariable String pinName, Map<String, Object> model) {
		GpioPinDigitalOutput[] simionPins = getPingController().getSimonOut();

		for (GpioPinDigitalOutput gpioPinDigitalOutput : simionPins) {

			if (gpioPinDigitalOutput.getName().equals(pinName)) {
				gpioPinDigitalOutput.toggle();
			}

			model.put(gpioPinDigitalOutput.getName(), gpioPinDigitalOutput.getState());

		}

		return "welcome";
	}

	public void setPingController(PINController pingController) {
		this.pingController = pingController;
	}

	public PINController getPingController() {
		return pingController;
	}

}
