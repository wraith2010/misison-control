package com.ten31f.mission;

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

public class PINControllerOnBoard {

	public static final String PIN_OUT_NAME_SIMON_GREEN01 = "SIMON_GREEN_01_OUT";
	public static final String PIN_OUT_NAME_SIMON_GREEN02 = "SIMON_GREEN_02_OUT";
	public static final String PIN_OUT_NAME_SIMON_WHITE = "SIMON_WHITE_OUT";
	public static final String PIN_OUT_NAME_SIMON_BLUE01 = "SIMON_BLUE_01_OUT";
	public static final String PIN_OUT_NAME_SIMON_BLUE02 = "SIMON_BLUE_02_OUT";
	public static final String PIN_OUT_NAME_SIMON_RED01 = "SIMON_RED_01_OUT";
	public static final String PIN_OUT_NAME_SIMON_RED02 = "SIMON_RED_02_OUT";
	public static final String PIN_OUT_NAME_SIMON_YELLOW01 = "SIMON_YELLOW_01_OUT";
	public static final String PIN_OUT_NAME_SIMON_YELLOW02 = "SIMON_YELLOW_02_OUT";

	public static final String PIN_OUT_NAME_SUB_SYSTEM_COMMS = "SUB_SYSTEM_COMMS_OUT";
	public static final String PIN_OUT_NAME_SUB_SYSTEM_LIFE_SUPPORT = "SUB_SYSTEM_LIFE_SUPPORT_OUT";
	public static final String PIN_OUT_NAME_SUB_SYSTEM_ENVIRONMENTAL_CONTROL = "SUB_ENVIRONMENTAL_CONTROL_OUT";
	public static final String PIN_OUT_NAME_SUB_SYSTEM_WATER = "SUB_WATER_OUT";
	public static final String PIN_OUT_NAME_SUB_SYSTEM_TWITCH_FEED = "SUB_TWITCH_FEED_OUT";
	public static final String PIN_OUT_NAME_SUB_SYSTEM_TANG = "SUB_SYSTEM_TANG_OUT";

	public static final String PIN_IN_NAME_SIMON_GREEN01 = "SIMON_GREEN_01_IN";
	public static final String PIN_IN_NAME_SIMON_GREEN02 = "SIMON_GREEN_02_IN";
	public static final String PIN_IN_NAME_SIMON_WHITE = "SIMON_WHITE_IN";
	public static final String PIN_IN_NAME_SIMON_BLUE01 = "SIMON_BLUE_01_IN";
	public static final String PIN_IN_NAME_SIMON_BLUE02 = "SIMON_BLUE_02_IN";
	public static final String PIN_IN_NAME_SIMON_RED01 = "SIMON_RED_01_IN";
	public static final String PIN_IN_NAME_SIMON_RED02 = "SIMON_RED_02_IN";
	public static final String PIN_IN_NAME_SIMON_YELLOW01 = "SIMON_YELLOW_01_IN";
	public static final String PIN_IN_NAME_SIMON_YELLOW02 = "SIMON_YELLOW_02_IN";

	public static final String PIN_IN_NAME_SUB_SYSTEM_COMMS = "SUB_SYSTEM_COMMS_IN";
	public static final String PIN_IN_NAME_SUB_SYSTEM_LIFE_SUPPORT = "SUB_SYSTEM_LIFE_SUPPORT_IN";
	public static final String PIN_IN_NAME_SUB_SYSTEM_ENVIRONMENTAL_CONTROL = "SUB_ENVIRONMENTAL_CONTROL_IN";
	public static final String PIN_IN_NAME_SUB_SYSTEM_WATER = "SUB_WATER_IN";
	public static final String PIN_IN_NAME_SUB_SYSTEM_TWITCH_FEED = "SUB_TWITCH_FEED_IN";
	public static final String PIN_IN_NAME_SUB_SYSTEM_TANG = "SUB_SYSTEM_TANG_IN";

	public static final String PIN_OUT_NAME_PYRO_FUEL_PUMP = "PYRO_FUEL_PUMP_OUT";
	public static final String PIN_OUT_NAME_PYRO_SOLID_BOOSTER = "PYRO_SOLID_BOOSTER_OUT";
	public static final String PIN_OUT_NAME_PYRO_MAIN_ENGINE = "PYRO_MAIN_ENGINE_OUT";
	public static final String PIN_OUT_NAME_PYRO_FUEL_PUMP_SWITCH = "PYRO_FUEL_PUMP_SWITCH_OUT";
	public static final String PIN_OUT_NAME_PYRO_SOLID_BOOSTER_SWITCH = "PYRO_SOLID_BOOSTER_SWITCH_OUT";
	public static final String PIN_OUT_NAME_PYRO_MAIN_ENGINE_SWITCH = "PYRO_MAIN_ENGINE_SWITCH_OUT";

	public static final String PIN_IN_NAME_PYRO_FUEL_PUMP = "PYRO_FUEL_PUMP_IN";
	public static final String PIN_IN_NAME_PYRO_SOLID_BOOSTER = "PYRO_SOLID_BOOSTER_IN";
	public static final String PIN_IN_NAME_PYRO_MAIN_ENGINE = "PYRO_MAIN_ENGINE_IN";
	public static final String PIN_IN_NAME_PYRO_FUEL_PUMP_SWITCH = "PYRO_FUEL_PUMP_SWITCH_IN";
	public static final String PIN_IN_NAME_PYRO_SOLID_BOOSTER_SWITCH = "PYRO_SOLID_BOOSTER_SWITCH_IN";
	public static final String PIN_IN_NAME_PYRO_MAIN_ENGINE_SWITCH = "PYRO_MAIN_ENGINE_SWITCH_IN";

	public static final String PIN_IN_NAME_LAUNCH = "LAUNCH_IN";
	public static final String PIN_OUT_NAME_LAUNCH = "LAUNCH_OUT";

	public static final String[] SIMON_OUT_PINS = { PIN_OUT_NAME_SIMON_GREEN01, PIN_OUT_NAME_SIMON_GREEN02,
			PIN_OUT_NAME_SIMON_WHITE, PIN_OUT_NAME_SIMON_BLUE01, PIN_OUT_NAME_SIMON_BLUE02, PIN_OUT_NAME_SIMON_RED01,
			PIN_OUT_NAME_SIMON_RED02, PIN_OUT_NAME_SIMON_YELLOW01, PIN_OUT_NAME_SIMON_YELLOW02 };

	public static final String[] SUBSYTEM_OUT_PINS = { PIN_OUT_NAME_SUB_SYSTEM_COMMS,
			PIN_OUT_NAME_SUB_SYSTEM_LIFE_SUPPORT, PIN_OUT_NAME_SUB_SYSTEM_ENVIRONMENTAL_CONTROL,
			PIN_OUT_NAME_SUB_SYSTEM_WATER, PIN_OUT_NAME_SUB_SYSTEM_TWITCH_FEED, PIN_OUT_NAME_SUB_SYSTEM_TANG };

	public static final String[] PYRO_OUT_PINS = { PIN_OUT_NAME_PYRO_FUEL_PUMP, PIN_OUT_NAME_PYRO_SOLID_BOOSTER,
			PIN_OUT_NAME_PYRO_MAIN_ENGINE, PIN_OUT_NAME_PYRO_FUEL_PUMP_SWITCH, PIN_OUT_NAME_PYRO_SOLID_BOOSTER_SWITCH,
			PIN_OUT_NAME_PYRO_MAIN_ENGINE_SWITCH };

	private GpioController gpioController = null;
	private MCP23017GpioProvider mco23017GpioProvider01 = null;
	private MCP23017GpioProvider mco23017GpioProvider02 = null;
	private MCP23017GpioProvider mco23017GpioProvider03 = null;

	private Map<String, GpioPinDigitalOutput> outputPins = null;
	private Map<String, GpioPinDigitalInput> inputPins = null;

	public PINControllerOnBoard() {
		System.out.println("New Board");
		
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

		establishPins();
	}

	private void establishPins() {

		setOutputPins(new HashMap<String, GpioPinDigitalOutput>());

		establishOuputPin(MCP23017Pin.GPIO_A0, PIN_OUT_NAME_SIMON_GREEN01, getMco23017GpioProvider01());
		establishOuputPin(MCP23017Pin.GPIO_A5, PIN_OUT_NAME_SIMON_BLUE01, getMco23017GpioProvider01());
		establishOuputPin(MCP23017Pin.GPIO_B0, PIN_OUT_NAME_SIMON_YELLOW01, getMco23017GpioProvider01());
		establishOuputPin(MCP23017Pin.GPIO_B1, PIN_OUT_NAME_SIMON_GREEN02, getMco23017GpioProvider01());
		establishOuputPin(MCP23017Pin.GPIO_B2, PIN_OUT_NAME_SIMON_WHITE, getMco23017GpioProvider01());
		establishOuputPin(MCP23017Pin.GPIO_B3, PIN_OUT_NAME_SIMON_RED02, getMco23017GpioProvider01());
		establishOuputPin(MCP23017Pin.GPIO_B4, PIN_OUT_NAME_SIMON_RED01, getMco23017GpioProvider01());
		establishOuputPin(MCP23017Pin.GPIO_B5, PIN_OUT_NAME_SIMON_YELLOW02, getMco23017GpioProvider01());
		establishOuputPin(MCP23017Pin.GPIO_B6, PIN_OUT_NAME_SIMON_BLUE02, getMco23017GpioProvider01());

		establishInputPin(MCP23017Pin.GPIO_A0, PIN_IN_NAME_SIMON_GREEN01, getMco23017GpioProvider02(),
				getOutputPin(PIN_OUT_NAME_SIMON_GREEN01));
		establishInputPin(MCP23017Pin.GPIO_A1, PIN_IN_NAME_SIMON_GREEN02, getMco23017GpioProvider02(),
				getOutputPin(PIN_OUT_NAME_SIMON_GREEN02));
		establishInputPin(MCP23017Pin.GPIO_A2, PIN_IN_NAME_SIMON_WHITE, getMco23017GpioProvider02(),
				getOutputPin(PIN_OUT_NAME_SIMON_WHITE));
		establishInputPin(MCP23017Pin.GPIO_A3, PIN_IN_NAME_SIMON_YELLOW02, getMco23017GpioProvider02(),
				getOutputPin(PIN_OUT_NAME_SIMON_YELLOW02));
		establishInputPin(MCP23017Pin.GPIO_A4, PIN_IN_NAME_SIMON_RED02, getMco23017GpioProvider02(),
				getOutputPin(PIN_OUT_NAME_SIMON_RED02));
		establishInputPin(MCP23017Pin.GPIO_A5, PIN_IN_NAME_SIMON_YELLOW01, getMco23017GpioProvider02(),
				getOutputPin(PIN_OUT_NAME_SIMON_YELLOW01));
		establishInputPin(MCP23017Pin.GPIO_A6, PIN_IN_NAME_SIMON_BLUE02, getMco23017GpioProvider02(),
				getOutputPin(PIN_OUT_NAME_SIMON_BLUE02));
		establishInputPin(MCP23017Pin.GPIO_A7, PIN_IN_NAME_SIMON_BLUE01, getMco23017GpioProvider02(),
				getOutputPin(PIN_OUT_NAME_SIMON_BLUE01));
		establishInputPin(MCP23017Pin.GPIO_B0, PIN_IN_NAME_SIMON_RED01, getMco23017GpioProvider02());

//		establishOuputPin(MCP23017Pin.GPIO_B4, PIN_OUT_NAME_LAUNCH, getMco23017GpioProvider03());
//
//		establishOuputPin(MCP23017Pin.GPIO_A1, PIN_OUT_NAME_SUB_SYSTEM_ENVIRONMENTAL_CONTROL,
//				getMco23017GpioProvider01());
//		establishOuputPin(MCP23017Pin.GPIO_A2, PIN_OUT_NAME_SUB_SYSTEM_TANG, getMco23017GpioProvider01());
//		establishOuputPin(MCP23017Pin.GPIO_A3, PIN_OUT_NAME_SUB_SYSTEM_LIFE_SUPPORT, getMco23017GpioProvider01());
//		establishOuputPin(MCP23017Pin.GPIO_A4, PIN_OUT_NAME_SUB_SYSTEM_WATER, getMco23017GpioProvider01());
//		establishOuputPin(MCP23017Pin.GPIO_A6, PIN_OUT_NAME_SUB_SYSTEM_COMMS, getMco23017GpioProvider01());
//		establishOuputPin(MCP23017Pin.GPIO_A7, PIN_OUT_NAME_SUB_SYSTEM_TWITCH_FEED, getMco23017GpioProvider01());
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		establishOuputPin(MCP23017Pin.GPIO_B5, PIN_OUT_NAME_PYRO_FUEL_PUMP, getMco23017GpioProvider03());
//		establishOuputPin(MCP23017Pin.GPIO_B6, PIN_OUT_NAME_PYRO_SOLID_BOOSTER, getMco23017GpioProvider03());
//		establishOuputPin(MCP23017Pin.GPIO_B7, PIN_OUT_NAME_PYRO_MAIN_ENGINE, getMco23017GpioProvider03());
//
//
//		establishInputPin(MCP23017Pin.GPIO_B1, PIN_IN_NAME_SUB_SYSTEM_TWITCH_FEED, getMco23017GpioProvider02());
//		establishInputPin(MCP23017Pin.GPIO_B2, PIN_IN_NAME_SUB_SYSTEM_COMMS, getMco23017GpioProvider02());
//		establishInputPin(MCP23017Pin.GPIO_B3, PIN_IN_NAME_SUB_SYSTEM_WATER, getMco23017GpioProvider02());
//		establishInputPin(MCP23017Pin.GPIO_B4, PIN_IN_NAME_SUB_SYSTEM_LIFE_SUPPORT, getMco23017GpioProvider02());
//		establishInputPin(MCP23017Pin.GPIO_B5, PIN_IN_NAME_SUB_SYSTEM_TANG, getMco23017GpioProvider02());
//		establishInputPin(MCP23017Pin.GPIO_B6, PIN_IN_NAME_SUB_SYSTEM_ENVIRONMENTAL_CONTROL,
//				getMco23017GpioProvider02());
//
		// establishOuputPin(MCP23017Pin.GPIO_A0, PIN_OUT_NAME_PYRO_FUEL_PUMP_SWITCH,
		// getMco23017GpioProvider03());
		// establishOuputPin(MCP23017Pin.GPIO_A1,
		// PIN_OUT_NAME_PYRO_SOLID_BOOSTER_SWITCH, getMco23017GpioProvider03());
		// establishOuputPin(MCP23017Pin.GPIO_A2, PIN_OUT_NAME_PYRO_MAIN_ENGINE_SWITCH,
		// getMco23017GpioProvider03());
//
//		establishInputPin(MCP23017Pin.GPIO_A5, PIN_IN_NAME_PYRO_MAIN_ENGINE, getMco23017GpioProvider03());
//		establishInputSwitchPin(MCP23017Pin.GPIO_A6, PIN_IN_NAME_PYRO_SOLID_BOOSTER_SWITCH,
//				getMco23017GpioProvider03());
//		establishInputPin(MCP23017Pin.GPIO_A7, PIN_IN_NAME_PYRO_FUEL_PUMP, getMco23017GpioProvider03());
//
//		establishInputSwitchPin(MCP23017Pin.GPIO_B0, PIN_IN_NAME_PYRO_FUEL_PUMP_SWITCH, getMco23017GpioProvider03());
//		establishInputPin(MCP23017Pin.GPIO_B1, PIN_IN_NAME_PYRO_SOLID_BOOSTER, getMco23017GpioProvider03());
//		establishInputSwitchPin(MCP23017Pin.GPIO_B2, PIN_IN_NAME_PYRO_MAIN_ENGINE_SWITCH, getMco23017GpioProvider03());
//
//		establishInputPin(MCP23017Pin.GPIO_B3, PIN_IN_NAME_LAUNCH, getMco23017GpioProvider03());
	}

	public void establishOuputPin(Pin pin, String name, MCP23017GpioProvider mcp23017GpioProvider) {

		if (getOutputPins() == null) {
			setOutputPins(new HashMap<>());
		}

		GpioPinDigitalOutput gpioPinDigitalOutput = getGpioController().provisionDigitalOutputPin(mcp23017GpioProvider,
				pin, name, PinState.LOW);
		gpioPinDigitalOutput.setShutdownOptions(true, PinState.LOW);
		getOutputPins().put(gpioPinDigitalOutput.getName(), gpioPinDigitalOutput);

	}

	public void establishInputPin(Pin pin, String name, MCP23017GpioProvider mcp23017GpioProvider) {

		if (getInputPins() == null) {
			setInputPins(new HashMap<>());
		}

		GpioPinDigitalInput gpioPinDigitalInput = getGpioController().provisionDigitalInputPin(mcp23017GpioProvider,
				pin, name, PinPullResistance.PULL_UP);
		gpioPinDigitalInput.setShutdownOptions(true);

		getInputPins().put(gpioPinDigitalInput.getName(), gpioPinDigitalInput);

	}

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

	private void establishInputSwitchPin(Pin pin, String name, MCP23017GpioProvider mcp23017GpioProvider) {

		if (getInputPins() == null) {
			setInputPins(new HashMap<>());
		}

		GpioPinDigitalInput gpioPinDigitalInput = getGpioController().provisionDigitalInputPin(mcp23017GpioProvider,
				pin, name, PinPullResistance.PULL_UP);
		gpioPinDigitalInput.setDebounce(1000);
		gpioPinDigitalInput.setShutdownOptions(true);

		getInputPins().put(gpioPinDigitalInput.getName(), gpioPinDigitalInput);
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

	public MCP23017GpioProvider getMco23017GpioProvider02() {
		return mco23017GpioProvider02;
	}

	public void setMco23017GpioProvider02(MCP23017GpioProvider mco23017GpioProvider02) {
		this.mco23017GpioProvider02 = mco23017GpioProvider02;
	}

	public MCP23017GpioProvider getMco23017GpioProvider03() {
		return mco23017GpioProvider03;
	}

	public void setMco23017GpioProvider03(MCP23017GpioProvider mco23017GpioProvider03) {
		this.mco23017GpioProvider03 = mco23017GpioProvider03;
	}

	public void setOutputPins(Map<String, GpioPinDigitalOutput> outputPins) {
		this.outputPins = outputPins;
	}

	public Map<String, GpioPinDigitalOutput> getOutputPins() {
		return outputPins;
	}

	public GpioPinDigitalOutput getOutputPin(String pinName) {
		if (getOutputPins() == null)
			return null;
		return getOutputPins().get(pinName);
	}

	public Map<String, GpioPinDigitalInput> getInputPins() {
		return inputPins;
	}

	public void setInputPins(Map<String, GpioPinDigitalInput> inputPins) {
		this.inputPins = inputPins;
	}

	public void addGpioPinListener(GpioPinListenerDigital gpioPinListenerDigital) {
		getInputPins().values().forEach(pin -> pin.addListener(gpioPinListenerDigital));
	}

	public void removeGpioPinListener(GpioPinListenerDigital gpioPinListenerDigital) {
		getInputPins().values().forEach(pin -> pin.removeListener(gpioPinListenerDigital));
	}

}
