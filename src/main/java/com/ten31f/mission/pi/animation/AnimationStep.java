package com.ten31f.mission.pi.animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class AnimationStep {

	private List<GpioPinDigitalOutput> pins = new ArrayList<>();

	private PinState pinState = null;

	private int duration = 0;

	public AnimationStep(GpioPinDigitalOutput[] pins, PinState pinState, int duration) {
		setPins(new ArrayList<GpioPinDigitalOutput>(Arrays.asList(pins)));
		setPinState(pinState);
		setDuration(duration);
	}

	public List<GpioPinDigitalOutput> getPins() {
		return pins;
	}

	public void setPins(List<GpioPinDigitalOutput> pins) {
		this.pins = pins;
	}

	public PinState getPinState() {
		return pinState;
	}

	public void setPinState(PinState pinState) {
		this.pinState = pinState;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
