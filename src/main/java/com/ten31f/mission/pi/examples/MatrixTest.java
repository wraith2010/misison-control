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

public class MatrixTest {

	private static int DELAY = 500;

	public static void main(String[] args) throws InterruptedException, IOException, UnsupportedBusNumberException {

		System.out.println("<--Pi4J--> GPIO Control Example ... started.");

		// create gpio controller
		final GpioController gpio = GpioFactory.getInstance();

		// create custom MCP23017 GPIO provider
		final MCP23017GpioProvider provider = new MCP23017GpioProvider(I2CBus.BUS_1, 0x20);

		// provision gpio pin #01 as an output pin and turn on

		GpioPinDigitalOutput[] pins = new GpioPinDigitalOutput[9];

		// button 0
		final GpioPinDigitalOutput pin00 = gpio.provisionDigitalOutputPin(provider, MCP23017Pin.GPIO_A4, "pin00",
				PinState.LOW);
		pin00.setShutdownOptions(true, PinState.LOW);
		pins[0] = pin00;

		// button 2
		final GpioPinDigitalOutput pin02 = gpio.provisionDigitalOutputPin(provider, MCP23017Pin.GPIO_A3, "pin02",
				PinState.LOW);
		pin02.setShutdownOptions(true, PinState.LOW);
		pins[2] = pin02;

		// button 1
		final GpioPinDigitalOutput pin01 = gpio.provisionDigitalOutputPin(provider, MCP23017Pin.GPIO_B0, "pin01",
				PinState.LOW);
		pin01.setShutdownOptions(true, PinState.LOW);
		pins[1] = pin01;

		// button 3
		final GpioPinDigitalOutput pin10 = gpio.provisionDigitalOutputPin(provider, MCP23017Pin.GPIO_B3, "pin10",
				PinState.LOW);
		pin10.setShutdownOptions(true, PinState.LOW);
		pins[3] = pin10;

		// button 4
		final GpioPinDigitalOutput pin11 = gpio.provisionDigitalOutputPin(provider, MCP23017Pin.GPIO_B1, "pin11",
				PinState.LOW);
		pin11.setShutdownOptions(true, PinState.LOW);
		pins[4] = pin11;

		// button 5
		final GpioPinDigitalOutput pin12 = gpio.provisionDigitalOutputPin(provider, MCP23017Pin.GPIO_A6, "pin12",
				PinState.LOW);
		pin12.setShutdownOptions(true, PinState.LOW);
		pins[5] = pin12;

		// button 6
		final GpioPinDigitalOutput pin20 = gpio.provisionDigitalOutputPin(provider, MCP23017Pin.GPIO_A7, "pin20",
				PinState.LOW);
		pin20.setShutdownOptions(true, PinState.LOW);
		pins[6] = pin20;

		final GpioPinDigitalOutput pin21 = gpio.provisionDigitalOutputPin(provider, MCP23017Pin.GPIO_A5, "pin21",
				PinState.LOW);
		pin21.setShutdownOptions(true, PinState.LOW);
		pins[7] = pin21;

		// button 8
		final GpioPinDigitalOutput pin22 = gpio.provisionDigitalOutputPin(provider, MCP23017Pin.GPIO_B2, "pin22",
				PinState.LOW);
		pin22.setShutdownOptions(true, PinState.LOW);
		pins[8] = pin22;

		for (int x = 0; x < 5; x++) {

			for (int y = 0; y < 2; y++) {

				for (GpioPinDigitalOutput pin : pins) {

					System.out.println("Flipping: " + pin.getName());

					pin.toggle();

					Thread.sleep(DELAY);
				}

			}

		}

		gpio.shutdown();

		System.out.println("Exiting ControlGpioExample");

	}

}
