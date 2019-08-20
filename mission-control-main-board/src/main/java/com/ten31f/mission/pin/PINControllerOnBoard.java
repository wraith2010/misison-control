package com.ten31f.mission.pin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.trigger.GpioPulseStateTrigger;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

public class PINControllerOnBoard implements IPINController {

	private GpioController gpioController = null;
	private MCP23017GpioProvider mco23017GpioProvider01 = null;
	private MCP23017GpioProvider mco23017GpioProvider02 = null;
	private MCP23017GpioProvider mco23017GpioProvider03 = null;

	private Map<String, GpioPinDigitalOutput> outputPins = null;
	private Map<String, GpioPinDigitalInput> inputPins = null;

	public PINControllerOnBoard() {

		setGpioController(GpioFactory.getInstance());

		try {
			setMco23017GpioProvider01(new MCP23017GpioProvider(I2CBus.BUS_1, 0x20));
		} catch (UnsupportedBusNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			setMco23017GpioProvider02(new MCP23017GpioProvider(I2CBus.BUS_1, 0x21));
		} catch (UnsupportedBusNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			setMco23017GpioProvider03(new MCP23017GpioProvider(I2CBus.BUS_1, 0x23));
		} catch (UnsupportedBusNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// establishPins();
	}

	private void establishPins() {

		setOutputPins(new HashMap<String, GpioPinDigitalOutput>());

		GpioPinDigitalOutput pinGreen01 = establishOuputPin(MCP23017Pin.GPIO_A0, PIN_OUT_NAME_SIMON_GREEN01,
				getMco23017GpioProvider01());
		GpioPinDigitalOutput pinBlue01 = establishOuputPin(MCP23017Pin.GPIO_A5, PIN_OUT_NAME_SIMON_BLUE01,
				getMco23017GpioProvider01());
		GpioPinDigitalOutput pinYellow01 = establishOuputPin(MCP23017Pin.GPIO_B0, PIN_OUT_NAME_SIMON_YELLOW01,
				getMco23017GpioProvider01());
		GpioPinDigitalOutput pinGreen02 = establishOuputPin(MCP23017Pin.GPIO_B1, PIN_OUT_NAME_SIMON_GREEN02,
				getMco23017GpioProvider01());
		GpioPinDigitalOutput pinWhite = establishOuputPin(MCP23017Pin.GPIO_B2, PIN_OUT_NAME_SIMON_WHITE,
				getMco23017GpioProvider01());
		GpioPinDigitalOutput pinRed02 = establishOuputPin(MCP23017Pin.GPIO_B3, PIN_OUT_NAME_SIMON_RED02,
				getMco23017GpioProvider01());
		GpioPinDigitalOutput pinRed01 = establishOuputPin(MCP23017Pin.GPIO_B4, PIN_OUT_NAME_SIMON_RED01,
				getMco23017GpioProvider01());
		GpioPinDigitalOutput pinYellow02 = establishOuputPin(MCP23017Pin.GPIO_B5, PIN_OUT_NAME_SIMON_YELLOW02,
				getMco23017GpioProvider01());
		GpioPinDigitalOutput pinBlue02 = establishOuputPin(MCP23017Pin.GPIO_B6, PIN_OUT_NAME_SIMON_BLUE02,
				getMco23017GpioProvider01());

		establishInputPin(MCP23017Pin.GPIO_A0, PIN_IN_NAME_SIMON_GREEN01, getMco23017GpioProvider02(), pinGreen01);
		establishInputPin(MCP23017Pin.GPIO_A1, PIN_IN_NAME_SIMON_GREEN02, getMco23017GpioProvider02(), pinGreen02);
		establishInputPin(MCP23017Pin.GPIO_A2, PIN_IN_NAME_SIMON_WHITE, getMco23017GpioProvider02(), pinWhite);
		establishInputPin(MCP23017Pin.GPIO_A3, PIN_IN_NAME_SIMON_YELLOW02, getMco23017GpioProvider02(), pinYellow02);
		establishInputPin(MCP23017Pin.GPIO_A4, PIN_IN_NAME_SIMON_RED02, getMco23017GpioProvider02(), pinRed02);
		establishInputPin(MCP23017Pin.GPIO_A5, PIN_IN_NAME_SIMON_YELLOW01, getMco23017GpioProvider02(), pinYellow01);
		establishInputPin(MCP23017Pin.GPIO_A6, PIN_IN_NAME_SIMON_BLUE02, getMco23017GpioProvider02(), pinBlue02);
		establishInputPin(MCP23017Pin.GPIO_A7, PIN_IN_NAME_SIMON_BLUE01, getMco23017GpioProvider02(), pinBlue01);
		establishInputPin(MCP23017Pin.GPIO_B0, PIN_IN_NAME_SIMON_RED01, getMco23017GpioProvider02(), pinRed01);

		establishOuputPin(MCP23017Pin.GPIO_A1, PIN_OUT_NAME_SUB_SYSTEM_ENVIRONMENTAL_CONTROL,
				getMco23017GpioProvider01());
		establishOuputPin(MCP23017Pin.GPIO_A2, PIN_OUT_NAME_SUB_SYSTEM_TANG, getMco23017GpioProvider01());
		establishOuputPin(MCP23017Pin.GPIO_A3, PIN_OUT_NAME_SUB_SYSTEM_LIFE_SUPPORT, getMco23017GpioProvider01());
		establishOuputPin(MCP23017Pin.GPIO_A4, PIN_OUT_NAME_SUB_SYSTEM_WATER, getMco23017GpioProvider01());
		establishOuputPin(MCP23017Pin.GPIO_A6, PIN_OUT_NAME_SUB_SYSTEM_COMMS, getMco23017GpioProvider01());
		establishOuputPin(MCP23017Pin.GPIO_A7, PIN_OUT_NAME_SUB_SYSTEM_TWITCH_FEED, getMco23017GpioProvider01());

		establishOuputPin(MCP23017Pin.GPIO_B5, PIN_OUT_NAME_PYRO_FUEL_PUMP, getMco23017GpioProvider03());
		establishOuputPin(MCP23017Pin.GPIO_B6, PIN_OUT_NAME_PYRO_SOLID_BOOSTER, getMco23017GpioProvider03());
		establishOuputPin(MCP23017Pin.GPIO_B7, PIN_OUT_NAME_PYRO_MAIN_ENGINE, getMco23017GpioProvider03());

		establishInputPin(MCP23017Pin.GPIO_B1, PIN_IN_NAME_SUB_SYSTEM_TWITCH_FEED, getMco23017GpioProvider02());
		establishInputPin(MCP23017Pin.GPIO_B2, PIN_IN_NAME_SUB_SYSTEM_COMMS, getMco23017GpioProvider02());
		establishInputPin(MCP23017Pin.GPIO_B3, PIN_IN_NAME_SUB_SYSTEM_WATER, getMco23017GpioProvider02());
		establishInputPin(MCP23017Pin.GPIO_B4, PIN_IN_NAME_SUB_SYSTEM_LIFE_SUPPORT, getMco23017GpioProvider02());
		establishInputPin(MCP23017Pin.GPIO_B5, PIN_IN_NAME_SUB_SYSTEM_TANG, getMco23017GpioProvider02());
		establishInputPin(MCP23017Pin.GPIO_B6, PIN_IN_NAME_SUB_SYSTEM_ENVIRONMENTAL_CONTROL,
				getMco23017GpioProvider02());

		establishOuputPin(MCP23017Pin.GPIO_A0, PIN_OUT_NAME_PYRO_FUEL_PUMP_SWITCH, getMco23017GpioProvider03());
		establishOuputPin(MCP23017Pin.GPIO_A1, PIN_OUT_NAME_PYRO_SOLID_BOOSTER_SWITCH, getMco23017GpioProvider03());
		establishOuputPin(MCP23017Pin.GPIO_A2, PIN_OUT_NAME_PYRO_MAIN_ENGINE_SWITCH, getMco23017GpioProvider03());

		establishInputPin(MCP23017Pin.GPIO_A5, PIN_IN_NAME_PYRO_MAIN_ENGINE, getMco23017GpioProvider03());
		establishInputSwitchPin(MCP23017Pin.GPIO_A6, PIN_IN_NAME_PYRO_SOLID_BOOSTER_SWITCH,
				getMco23017GpioProvider03());
		establishInputPin(MCP23017Pin.GPIO_A7, PIN_IN_NAME_PYRO_FUEL_PUMP, getMco23017GpioProvider03());

		establishInputSwitchPin(MCP23017Pin.GPIO_B0, PIN_IN_NAME_PYRO_FUEL_PUMP_SWITCH, getMco23017GpioProvider03());
		establishInputPin(MCP23017Pin.GPIO_B1, PIN_IN_NAME_PYRO_SOLID_BOOSTER, getMco23017GpioProvider03());
		establishInputSwitchPin(MCP23017Pin.GPIO_B2, PIN_IN_NAME_PYRO_MAIN_ENGINE_SWITCH, getMco23017GpioProvider03());

		establishOuputPin(MCP23017Pin.GPIO_B4, PIN_OUT_NAME_LAUNCH, getMco23017GpioProvider03());
		establishInputPin(MCP23017Pin.GPIO_B3, PIN_IN_NAME_LAUNCH, getMco23017GpioProvider03());

	}

	@Override
	public GpioPinDigitalOutput establishOuputPin(Pin pin, String name, MCP23017GpioProvider mcp23017GpioProvider) {

		if (getOutputPins() == null) {
			setOutputPins(new HashMap<>());
		}

		GpioPinDigitalOutput gpioPinDigitalOutput = getGpioController().provisionDigitalOutputPin(mcp23017GpioProvider,
				pin, name, PinState.LOW);
		gpioPinDigitalOutput.setShutdownOptions(true, PinState.LOW);
		getOutputPins().put(gpioPinDigitalOutput.getName(), gpioPinDigitalOutput);

		return gpioPinDigitalOutput;
	}

	@Override
	public void establishInputPin(Pin pin, String name, MCP23017GpioProvider mcp23017GpioProvider) {

		if (getInputPins() == null) {
			setInputPins(new HashMap<>());
		}

		GpioPinDigitalInput gpioPinDigitalInput = getGpioController().provisionDigitalInputPin(mcp23017GpioProvider,
				pin, name, PinPullResistance.PULL_UP);
		gpioPinDigitalInput.setShutdownOptions(true);

		getInputPins().put(gpioPinDigitalInput.getName(), gpioPinDigitalInput);

	}

	@Override
	public void removePin(String pinName) {

		GpioPinDigitalOutput gpioPinDigitalOutput = getOutputPin(pinName);
		if (gpioPinDigitalOutput != null) {
			getGpioController().unprovisionPin(gpioPinDigitalOutput);
		}

		GpioPinDigitalInput gpioPinDigitalInput = getInputPins().get(pinName);
		if (gpioPinDigitalInput != null) {
			getGpioController().unprovisionPin(gpioPinDigitalInput);
		}

	}

	@Override
	public void establishInputPin(Pin pin, String name, MCP23017GpioProvider mcp23017GpioProvider,
			GpioPinDigitalOutput gpioPinDigitalOutput) {

		if (getInputPins() == null) {
			setInputPins(new HashMap<>());
		}

		GpioPinDigitalInput gpioPinDigitalInput = getGpioController().provisionDigitalInputPin(mcp23017GpioProvider,
				pin, name, PinPullResistance.PULL_UP);
		gpioPinDigitalInput.setShutdownOptions(true);
		gpioPinDigitalInput.addTrigger(new GpioPulseStateTrigger(PinState.LOW, gpioPinDigitalOutput, 700));

		getInputPins().put(gpioPinDigitalInput.getName(), gpioPinDigitalInput);
	}

	public void establishInputSwitchPin(Pin pin, String name, MCP23017GpioProvider mcp23017GpioProvider) {

		if (getInputPins() == null) {
			setInputPins(new HashMap<>());
		}

		GpioPinDigitalInput gpioPinDigitalInput = getGpioController().provisionDigitalInputPin(mcp23017GpioProvider,
				pin, name, PinPullResistance.PULL_UP);
		gpioPinDigitalInput.setDebounce(1000);
		gpioPinDigitalInput.setShutdownOptions(true);

		getInputPins().put(gpioPinDigitalInput.getName(), gpioPinDigitalInput);
	}

	@Override
	public GpioController getGpioController() {
		return gpioController;
	}

	@Override
	public void setGpioController(GpioController gpioController) {
		this.gpioController = gpioController;
	}

	@Override
	public MCP23017GpioProvider getMco23017GpioProvider01() {
		return mco23017GpioProvider01;
	}

	@Override
	public void setMco23017GpioProvider01(MCP23017GpioProvider mco23017GpioProvider01) {
		this.mco23017GpioProvider01 = mco23017GpioProvider01;
	}

	@Override
	public MCP23017GpioProvider getMco23017GpioProvider02() {
		return mco23017GpioProvider02;
	}

	@Override
	public void setMco23017GpioProvider02(MCP23017GpioProvider mco23017GpioProvider02) {
		this.mco23017GpioProvider02 = mco23017GpioProvider02;
	}

	@Override
	public MCP23017GpioProvider getMco23017GpioProvider03() {
		return mco23017GpioProvider03;
	}

	@Override
	public void setMco23017GpioProvider03(MCP23017GpioProvider mco23017GpioProvider03) {
		this.mco23017GpioProvider03 = mco23017GpioProvider03;
	}

	@Override
	public void setOutputPins(Map<String, GpioPinDigitalOutput> outputPins) {
		this.outputPins = outputPins;
	}

	@Override
	public Map<String, GpioPinDigitalOutput> getOutputPins() {
		return outputPins;
	}

	@Override
	public GpioPinDigitalOutput getOutputPin(String pinName) {
		if (getOutputPins() == null)
			return null;
		return getOutputPins().get(pinName);
	}

	@Override
	public Map<String, GpioPinDigitalInput> getInputPins() {
		return inputPins;
	}

	@Override
	public void setInputPins(Map<String, GpioPinDigitalInput> inputPins) {
		this.inputPins = inputPins;
	}

	@Override
	public void addGpioPinListener(GpioPinListenerDigital gpioPinListenerDigital) {
		getInputPins().values().forEach(pin -> pin.addListener(gpioPinListenerDigital));
	}

	@Override
	public void removeGpioPinListener(GpioPinListenerDigital gpioPinListenerDigital) {
		getInputPins().values().forEach(pin -> pin.removeListener(gpioPinListenerDigital));
	}

	@Override
	public void reset() {
		if (getOutputPins() == null)
			return;
		getOutputPins().values().forEach(pin -> pin.setState(PinState.LOW));
	}

}
