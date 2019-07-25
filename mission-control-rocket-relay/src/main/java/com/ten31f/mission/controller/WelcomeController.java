package com.ten31f.mission.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pi4j.io.gpio.PinState;
import com.ten31f.mission.pi.IPINController;

@Controller
public class WelcomeController {

	final static Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@Autowired
	private IPINController pinController = null;

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {

		return "welcome";
	}

	@PostMapping("/{pinName}")
	public ResponseEntity<?> flipPin(@PathVariable String pinName) {

		Map<String, PinState> pinstates = new HashMap<>();

		getPinController().pulse(pinName, 5000);

		return ResponseEntity.ok(pinstates);
	}

	@GetMapping("/{pinName}")
	public ResponseEntity<?> pulsePin(@PathVariable String pinName) {

		Map<String, PinState> pinstates = new HashMap<>();

		getPinController().pulse(pinName, 3000);

		return ResponseEntity.ok(pinstates);
	}

	public void setPinController(IPINController pinController) {
		this.pinController = pinController;
	}

	public IPINController getPinController() {
		return pinController;
	}

}
