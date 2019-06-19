package com.ten31f.mission.pi;

import java.util.HashMap;
import java.util.Map;

import com.pi4j.io.gpio.PinState;

public class PINControllerOffBoard implements IPINController {

	private Map<String, PinState> pinIndex = null;

	public PINControllerOffBoard() {
		setPinIndex(new HashMap<>());
		IPINController.PIN_NAMES.forEach(pinName -> setStateForPin(pinName, PinState.LOW));
	}

	@Override
	public PinState getStateForPin(String pinName) {

		if (!getPinIndex().containsKey(pinName))
			return PinState.LOW;

		return getPinIndex().get(pinName);
	}

	@Override
	public void setStateForPin(String pinName, PinState pinState) {

		getPinIndex().put(pinName, pinState);
	}

	@Override
	public void pulse(String pinName, int duration) {

	}

	@Override
	public void toggle(String pinName) {

		switch (getStateForPin(pinName)) {
		case LOW:
			setStateForPin(pinName, PinState.HIGH);
			break;
		case HIGH:
			setStateForPin(pinName, PinState.LOW);
			break;

		default:
			break;
		}
	}

	private Map<String, PinState> getPinIndex() {
		return pinIndex;
	}

	private void setPinIndex(Map<String, PinState> pinIndex) {
		this.pinIndex = pinIndex;
	}

}
