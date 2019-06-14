package com.ten31f.mission.controller;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.ten31f.mission.pi.animation.AnimationThread;
import com.ten31f.mission.pi.examples.PINController;

@Controller
public class WelcomeController {

	final static Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@Autowired
	private PINController pingController = null;
	
	@Autowired
	private AnimationThread animationThread = null;

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
		
		if(getAnimationThread()!= null) {
			getAnimationThread().setLastInput(new Date());
		}
		
		
		GpioPinDigitalOutput[] simionPins = getPingController().getSimonOut();

		for (GpioPinDigitalOutput gpioPinDigitalOutput : simionPins) {

			if (gpioPinDigitalOutput.getName().equals(pinName)) {

				gpioPinDigitalOutput.toggle();
				logger.debug(gpioPinDigitalOutput.getName() + ":" + gpioPinDigitalOutput.getState());
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

	public AnimationThread getAnimationThread() {
		return animationThread;
	}
	
	public void setAnimationThread(AnimationThread animationThread) {
		this.animationThread = animationThread;
	}
	
}
