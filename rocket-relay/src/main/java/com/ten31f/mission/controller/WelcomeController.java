package com.ten31f.mission.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ten31f.mission.pi.IPINController;

@Controller
public class WelcomeController {

	final static Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@Autowired
	private IPINController pinController = null;

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
//
//		GpioPinDigitalOutput[] simionPins = getPinController().getSimonOut();
//
//		for (GpioPinDigitalOutput gpioPinDigitalOutput : simionPins) {
//
//			model.put(gpioPinDigitalOutput.getName(), gpioPinDigitalOutput.getState());
//
//		}

		return "welcome";
	}

	public void setPinController(IPINController pinController) {
		this.pinController = pinController;
	}

	public IPINController getPinController() {
		return pinController;
	}

}
