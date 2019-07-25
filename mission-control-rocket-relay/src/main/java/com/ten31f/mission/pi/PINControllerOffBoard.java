package com.ten31f.mission.pi;

import java.util.HashMap;
import java.util.Map;

import com.pi4j.io.gpio.PinState;

public class PINControllerOffBoard implements IPINController {

	private Map<String, PinState> pinIndex = null;

	public PINControllerOffBoard() {
		setPinIndex(new HashMap<>());
		establishPins();
	}

	private void establishPins() {
		//IPINController.PIN_NAMES.forEach(pinName -> getPinIndex().put(pinName, PinState.LOW));
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

		PinState pinState = getPinIndex().get(pinName);
		if (pinState == null)
			System.out.println("Not found");
		else
			System.out.println(pinName + " : " + pinState);

	}

	private void setPinIndex(Map<String, PinState> pinIndex) {
		this.pinIndex = pinIndex;
	}

	private Map<String, PinState> getPinIndex() {
		return pinIndex;
	}
}
