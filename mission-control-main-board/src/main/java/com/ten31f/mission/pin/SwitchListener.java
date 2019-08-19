package com.ten31f.mission.pin;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class SwitchListener implements GpioPinListenerDigital {

	private IPINController pinControllerOnBoard = null;

	public SwitchListener(IPINController pinControllerOnBoard) {
		setPinControllerOnBoard(pinControllerOnBoard);
	}

	@Override
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		String inputPinName = event.getPin().getName();
		System.out.println(String.format(" --> GPIO PIN STATE CHANGE(%s): %s = %s", event.getPin().getPin(),
				inputPinName, event.getState()));

		String outputPinName = inputPinName.replace("_IN", "_OUT");

		GpioPinDigitalOutput gpioPinDigitalOutput = getPinControllerOnBoard().getOutputPin(outputPinName);

		if (gpioPinDigitalOutput != null) {
			gpioPinDigitalOutput.setState(!PinState.LOW.equals(event.getState()));
			System.out.println(String.format("%s -> %s = %s", gpioPinDigitalOutput.getPin(),
					gpioPinDigitalOutput.getName(), gpioPinDigitalOutput.getState()));
		} else {
			System.out.println(String.format("Couldn't find pin: %s", outputPinName));
		}

	}

	private IPINController getPinControllerOnBoard() {
		return pinControllerOnBoard;
	}

	private void setPinControllerOnBoard(IPINController pinControllerOnBoard) {
		this.pinControllerOnBoard = pinControllerOnBoard;
	}

}
