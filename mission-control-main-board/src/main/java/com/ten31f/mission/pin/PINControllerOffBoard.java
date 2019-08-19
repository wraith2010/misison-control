package com.ten31f.mission.pin;

import java.util.Map;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class PINControllerOffBoard implements IPINController {

	@Override
	public GpioPinDigitalOutput establishOuputPin(Pin pin, String name, MCP23017GpioProvider mcp23017GpioProvider) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void establishInputPin(Pin pin, String name, MCP23017GpioProvider mcp23017GpioProvider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePin(String pinName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void establishInputPin(Pin pin, String name, MCP23017GpioProvider mcp23017GpioProvider,
			GpioPinDigitalOutput gpioPinDigitalOutput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GpioController getGpioController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGpioController(GpioController gpioController) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MCP23017GpioProvider getMco23017GpioProvider01() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMco23017GpioProvider01(MCP23017GpioProvider mco23017GpioProvider01) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MCP23017GpioProvider getMco23017GpioProvider02() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMco23017GpioProvider02(MCP23017GpioProvider mco23017GpioProvider02) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MCP23017GpioProvider getMco23017GpioProvider03() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMco23017GpioProvider03(MCP23017GpioProvider mco23017GpioProvider03) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOutputPins(Map<String, GpioPinDigitalOutput> outputPins) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, GpioPinDigitalOutput> getOutputPins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GpioPinDigitalOutput getOutputPin(String pinName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, GpioPinDigitalInput> getInputPins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInputPins(Map<String, GpioPinDigitalInput> inputPins) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addGpioPinListener(GpioPinListenerDigital gpioPinListenerDigital) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeGpioPinListener(GpioPinListenerDigital gpioPinListenerDigital) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
