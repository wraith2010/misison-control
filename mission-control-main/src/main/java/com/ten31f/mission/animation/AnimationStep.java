package com.ten31f.mission.animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pi4j.io.gpio.PinState;

public class AnimationStep {

	private List<String> pinNames = null;

	private PinState pinState = null;

	private int duration = 0;

	public AnimationStep(String[] pins, PinState pinState, int duration) {
		setPinNames(new ArrayList<String>(Arrays.asList(pins)));
		setPinState(pinState);
		setDuration(duration);
	}

	public List<String> getPinNames() {
		return pinNames;
	}

	public void setPinNames(List<String> pinNames) {
		this.pinNames = pinNames;
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
