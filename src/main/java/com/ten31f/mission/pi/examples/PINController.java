package com.ten31f.mission.pi.examples;

import java.io.IOException;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

public class PINController {

	private GpioController gpioController = null;
	private MCP23017GpioProvider mco23017GpioProvider01 = null;

	private GpioPinDigitalOutput[] simonOut = null;

	public PINController() throws IOException, UnsupportedBusNumberException {

		setGpioController(GpioFactory.getInstance());
		setMco23017GpioProvider01(new MCP23017GpioProvider(I2CBus.BUS_1, 0x20));

		establishPins();

	}

	private void establishPins() {

		setSimonOut(new GpioPinDigitalOutput[9]);

		// button 0
		final GpioPinDigitalOutput pin00 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_A4, "pin00", PinState.LOW);
		pin00.setShutdownOptions(true, PinState.LOW);
		getSimonOut()[0] = pin00;

		// button 2
		final GpioPinDigitalOutput pin02 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_A3, "pin02", PinState.LOW);
		pin02.setShutdownOptions(true, PinState.LOW);
		getSimonOut()[2] = pin02;

		// button 1
		final GpioPinDigitalOutput pin01 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_B0, "pin01", PinState.LOW);
		pin01.setShutdownOptions(true, PinState.LOW);
		getSimonOut()[1] = pin01;

		// button 3
		final GpioPinDigitalOutput pin10 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_B3, "pin10", PinState.LOW);
		pin10.setShutdownOptions(true, PinState.LOW);
		getSimonOut()[3] = pin10;

		// button 4
		final GpioPinDigitalOutput pin11 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_B1, "pin11", PinState.LOW);
		pin11.setShutdownOptions(true, PinState.LOW);
		getSimonOut()[4] = pin11;

		// button 5
		final GpioPinDigitalOutput pin12 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_A6, "pin12", PinState.LOW);
		pin12.setShutdownOptions(true, PinState.LOW);
		getSimonOut()[5] = pin12;

		// button 6
		final GpioPinDigitalOutput pin20 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_A7, "pin20", PinState.LOW);
		pin20.setShutdownOptions(true, PinState.LOW);
		getSimonOut()[6] = pin20;

		final GpioPinDigitalOutput pin21 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_A5, "pin21", PinState.LOW);
		pin21.setShutdownOptions(true, PinState.LOW);
		getSimonOut()[7] = pin21;

		// button 8
		final GpioPinDigitalOutput pin22 = getGpioController().provisionDigitalOutputPin(getMco23017GpioProvider01(),
				MCP23017Pin.GPIO_B2, "pin22", PinState.LOW);
		pin22.setShutdownOptions(true, PinState.LOW);
		getSimonOut()[8] = pin22;
	}

	public void setSimonOut(GpioPinDigitalOutput[] simonOut) {
		this.simonOut = simonOut;
	}

	public GpioPinDigitalOutput[] getSimonOut() {
		return simonOut;
	}

	public GpioController getGpioController() {
		return gpioController;
	}

	public void setGpioController(GpioController gpioController) {
		this.gpioController = gpioController;
	}

	public MCP23017GpioProvider getMco23017GpioProvider01() {
		return mco23017GpioProvider01;
	}

	public void setMco23017GpioProvider01(MCP23017GpioProvider mco23017GpioProvider01) {
		this.mco23017GpioProvider01 = mco23017GpioProvider01;
	}

}
