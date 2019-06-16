package com.ten31f.mission.controller;

import java.util.Date;
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
import com.ten31f.mission.animation.AnimationThread;
import com.ten31f.mission.pi.IPINController;

@Controller
public class WelcomeController {

	final static Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@Autowired
	private IPINController pinController = null;

	@Autowired
	private AnimationThread animationThread = null;

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {

		IPINController.PIN_NAMES.forEach(pinindex -> model.put(pinindex, getPinController().getStateForPin(pinindex)));

		return "welcome";
	}

	@PostMapping("/{pinName}")
	public ResponseEntity<?> flipPin(@PathVariable String pinName) {

		Map<String, PinState> pinstates = new HashMap<>();

		getAnimationThread().setLastInput(new Date());

		getPinController().toggle(pinName);

		IPINController.PIN_NAMES
				.forEach(pinindex -> pinstates.put(pinindex, getPinController().getStateForPin(pinindex)));

		return ResponseEntity.ok(pinstates);
	}

	@GetMapping("/{pinName}")
	public String flipPin(@PathVariable String pinName, Map<String, Object> model) {

		getAnimationThread().setLastInput(new Date());

		getPinController().toggle(pinName);

		IPINController.PIN_NAMES.forEach(pinindex -> model.put(pinindex, getPinController().getStateForPin(pinindex)));

		return "welcome";
	}

	public IPINController getPinController() {
		return pinController;
	}

	public void setPinController(IPINController pinController) {
		this.pinController = pinController;
	}

	public AnimationThread getAnimationThread() {
		return animationThread;
	}

	public void setAnimationThread(AnimationThread animationThread) {
		this.animationThread = animationThread;
	}

}
