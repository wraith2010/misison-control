package com.ten31f.mission.pi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

public class PINControllerOnBoard implements IPINController {

	private GpioController gpioController = null;

	private Map<String, GpioPinDigitalOutput> pinIndex = null;

	public PINControllerOnBoard() throws IOException, UnsupportedBusNumberException {

		setGpioController(GpioFactory.getInstance());

		setPinIndex(new HashMap<>());

		establishPins();

	}

	private void establishPins() {

		GpioPinDigitalOutput pin = getGpioController().provisionDigitalOutputPin(RaspiPin.GPIO_24, "SMOKE",
				PinState.LOW);
		pin.setShutdownOptions(true, PinState.LOW);
		getPinIndex().put(pin.getName(), pin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ten31f.mission.pi.IPINController#getStateForPin(java.lang.String)
	 */
	@Override
	public PinState getStateForPin(String pinName) {

		GpioPinDigitalOutput gpioPinDigitalOutput = getPinIndex().get(pinName);
		if (gpioPinDigitalOutput == null)
			return PinState.LOW;

		return gpioPinDigitalOutput.getState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ten31f.mission.pi.IPINController#setStateForPin(java.lang.String,
	 * com.pi4j.io.gpio.PinState)
	 */
	@Override
	public void setStateForPin(String pinName, PinState pinState) {

		GpioPinDigitalOutput gpioPinDigitalOutput = getPinIndex().get(pinName);

		if (gpioPinDigitalOutput == null)
			return;

		gpioPinDigitalOutput.setState(pinState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ten31f.mission.pi.IPINController#test(java.lang.String, int)
	 */
	@Override
	public void pulse(String pinName, int duration) {

		GpioPinDigitalOutput pin = getPinIndex().get(pinName);

		System.out.println(String.format("pulsing pin(%s): %s ms", pinName, duration));
		
		if(pin != null) {
				pin.pulse(duration);
		}
		else {
			System.out.println("pin not found!");	
		}

	}

	private Map<String, GpioPinDigitalOutput> getPinIndex() {
		return pinIndex;
	}

	private void setPinIndex(Map<String, GpioPinDigitalOutput> pinIndex) {
		this.pinIndex = pinIndex;
	}

	private GpioController getGpioController() {
		return gpioController;
	}

	private void setGpioController(GpioController gpioController) {
		this.gpioController = gpioController;
	}

}
