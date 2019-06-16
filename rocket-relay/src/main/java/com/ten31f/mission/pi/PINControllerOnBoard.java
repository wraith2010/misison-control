package com.ten31f.mission.pi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

public class PINControllerOnBoard implements IPINController {

	private GpioController gpioController = null;
	private MCP23017GpioProvider mco23017GpioProvider01 = null;

	private GpioPinDigitalOutput[] simonOut = null;

	private Map<String, GpioPinDigitalOutput> pinIndex = null;

	public PINControllerOnBoard() throws IOException, UnsupportedBusNumberException {

		setGpioController(GpioFactory.getInstance());
		setMco23017GpioProvider01(new MCP23017GpioProvider(I2CBus.BUS_1, 0x20));

		setPinIndex(new HashMap<>());

		establishPins();

	}

	private void establishPins() {

		setSimonOut(new GpioPinDigitalOutput[9]);

		// button 0
		final GpioPinDigitalOutput pin00 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_A4, PIN_NAME_PIN00, PinState.LOW);
		pin00.setShutdownOptions(true, PinState.LOW);
		getPinIndex().put(pin00.getName(), pin00);
		getSimonOut()[0] = pin00;

		// button 1
		final GpioPinDigitalOutput pin01 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_B0, PIN_NAME_PIN01, PinState.LOW);
		pin01.setShutdownOptions(true, PinState.LOW);
		getPinIndex().put(pin01.getName(), pin01);
		getSimonOut()[1] = pin01;

		// button 2
		final GpioPinDigitalOutput pin02 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_A3, PIN_NAME_PIN02, PinState.LOW);
		pin02.setShutdownOptions(true, PinState.LOW);
		getPinIndex().put(pin02.getName(), pin02);
		getSimonOut()[2] = pin02;

		// button 3
		final GpioPinDigitalOutput pin10 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_B3, PIN_NAME_PIN10, PinState.LOW);
		pin10.setShutdownOptions(true, PinState.LOW);
		getPinIndex().put(pin10.getName(), pin10);
		getSimonOut()[3] = pin10;

		// button 4
		final GpioPinDigitalOutput pin11 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_B1, PIN_NAME_PIN11, PinState.LOW);
		pin11.setShutdownOptions(true, PinState.LOW);
		getPinIndex().put(pin11.getName(), pin11);
		getSimonOut()[4] = pin11;

		// button 5
		final GpioPinDigitalOutput pin12 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_A6, PIN_NAME_PIN12, PinState.LOW);
		pin12.setShutdownOptions(true, PinState.LOW);
		getPinIndex().put(pin12.getName(), pin12);
		getSimonOut()[5] = pin12;

		// button 6
		final GpioPinDigitalOutput pin20 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_A7, PIN_NAME_PIN20, PinState.LOW);
		pin20.setShutdownOptions(true, PinState.LOW);
		getPinIndex().put(pin20.getName(), pin20);
		getSimonOut()[6] = pin20;

		// button 7
		final GpioPinDigitalOutput pin21 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_A5, PIN_NAME_PIN21, PinState.LOW);
		pin21.setShutdownOptions(true, PinState.LOW);
		getPinIndex().put(pin21.getName(), pin21);
		getSimonOut()[7] = pin21;

		// button 8
		final GpioPinDigitalOutput pin22 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_B2, PIN_NAME_PIN22, PinState.LOW);
		pin22.setShutdownOptions(true, PinState.LOW);
		getPinIndex().put(pin22.getName(), pin22);
		getSimonOut()[8] = pin22;
	}

	/* (non-Javadoc)
	 * @see com.ten31f.mission.pi.IPINController#getStateForPin(java.lang.String)
	 */
	@Override
	public PinState getStateForPin(String pinName) {

		GpioPinDigitalOutput gpioPinDigitalOutput = getPinIndex().get(pinName);
		if (gpioPinDigitalOutput == null)
			return PinState.LOW;

		return gpioPinDigitalOutput.getState();
	}

	/* (non-Javadoc)
	 * @see com.ten31f.mission.pi.IPINController#setStateForPin(java.lang.String, com.pi4j.io.gpio.PinState)
	 */
	@Override
	public void setStateForPin(String pinName, PinState pinState) {

		GpioPinDigitalOutput gpioPinDigitalOutput = getPinIndex().get(pinName);

		if (gpioPinDigitalOutput == null)
			return;

		gpioPinDigitalOutput.setState(pinState);
	}

	/* (non-Javadoc)
	 * @see com.ten31f.mission.pi.IPINController#test(java.lang.String, int)
	 */
	@Override
	public void pulse(String pinName, int duration) {
		GpioPinDigitalOutput gpioPinDigitalOutput = getPinIndex().get(pinName);

		gpioPinDigitalOutput.pulse(duration);
	}

	private Map<String, GpioPinDigitalOutput> getPinIndex() {
		return pinIndex;
	}

	private void setPinIndex(Map<String, GpioPinDigitalOutput> pinIndex) {
		this.pinIndex = pinIndex;
	}

	private void setSimonOut(GpioPinDigitalOutput[] simonOut) {
		this.simonOut = simonOut;
	}

	private GpioPinDigitalOutput[] getSimonOut() {
		return simonOut;
	}

	private GpioController getGpioController() {
		return gpioController;
	}

	private void setGpioController(GpioController gpioController) {
		this.gpioController = gpioController;
	}

	private MCP23017GpioProvider getMco23017GpioProvider01() {
		return mco23017GpioProvider01;
	}

	private void setMco23017GpioProvider01(MCP23017GpioProvider mco23017GpioProvider01) {
		this.mco23017GpioProvider01 = mco23017GpioProvider01;
	}

}
