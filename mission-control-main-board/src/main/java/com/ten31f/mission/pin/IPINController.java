package com.ten31f.mission.pin;

import java.util.Map;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public interface IPINController {

	public static final String PIN_OUT_NAME_SIMON_GREEN01 = "SIMON_GREEN_01_OUT";
	public static final String PIN_OUT_NAME_SIMON_GREEN02 = "SIMON_GREEN_02_OUT";
	public static final String PIN_OUT_NAME_SIMON_WHITE = "SIMON_WHITE_OUT";
	public static final String PIN_OUT_NAME_SIMON_BLUE01 = "SIMON_BLUE_01_OUT";
	public static final String PIN_OUT_NAME_SIMON_BLUE02 = "SIMON_BLUE_02_OUT";
	public static final String PIN_OUT_NAME_SIMON_RED01 = "SIMON_RED_01_OUT";
	public static final String PIN_OUT_NAME_SIMON_RED02 = "SIMON_RED_02_OUT";
	public static final String PIN_OUT_NAME_SIMON_YELLOW01 = "SIMON_YELLOW_01_OUT";
	public static final String PIN_OUT_NAME_SIMON_YELLOW02 = "SIMON_YELLOW_02_OUT";

	public static final String[] SIMON_OUT_PINS = { PIN_OUT_NAME_SIMON_GREEN01, PIN_OUT_NAME_SIMON_GREEN02,
			PIN_OUT_NAME_SIMON_WHITE, PIN_OUT_NAME_SIMON_BLUE01, PIN_OUT_NAME_SIMON_BLUE02, PIN_OUT_NAME_SIMON_RED01,
			PIN_OUT_NAME_SIMON_RED02, PIN_OUT_NAME_SIMON_YELLOW01, PIN_OUT_NAME_SIMON_YELLOW02 };

	public static final String PIN_IN_NAME_SIMON_GREEN01 = "SIMON_GREEN_01_IN";
	public static final String PIN_IN_NAME_SIMON_GREEN02 = "SIMON_GREEN_02_IN";
	public static final String PIN_IN_NAME_SIMON_WHITE = "SIMON_WHITE_IN";
	public static final String PIN_IN_NAME_SIMON_BLUE01 = "SIMON_BLUE_01_IN";
	public static final String PIN_IN_NAME_SIMON_BLUE02 = "SIMON_BLUE_02_IN";
	public static final String PIN_IN_NAME_SIMON_RED01 = "SIMON_RED_01_IN";
	public static final String PIN_IN_NAME_SIMON_RED02 = "SIMON_RED_02_IN";
	public static final String PIN_IN_NAME_SIMON_YELLOW01 = "SIMON_YELLOW_01_IN";
	public static final String PIN_IN_NAME_SIMON_YELLOW02 = "SIMON_YELLOW_02_IN";

	public static final String PIN_OUT_NAME_SUB_SYSTEM_COMMS = "SUB_SYSTEM_COMMS_OUT";
	public static final String PIN_OUT_NAME_SUB_SYSTEM_LIFE_SUPPORT = "SUB_SYSTEM_LIFE_SUPPORT_OUT";
	public static final String PIN_OUT_NAME_SUB_SYSTEM_ENVIRONMENTAL_CONTROL = "SUB_ENVIRONMENTAL_CONTROL_OUT";
	public static final String PIN_OUT_NAME_SUB_SYSTEM_WATER = "SUB_WATER_OUT";
	public static final String PIN_OUT_NAME_SUB_SYSTEM_TWITCH_FEED = "SUB_TWITCH_FEED_OUT";
	public static final String PIN_OUT_NAME_SUB_SYSTEM_TANG = "SUB_SYSTEM_TANG_OUT";

	public static final String[] SUBSYTEM_OUT_PINS = { PIN_OUT_NAME_SUB_SYSTEM_COMMS,
			PIN_OUT_NAME_SUB_SYSTEM_LIFE_SUPPORT, PIN_OUT_NAME_SUB_SYSTEM_ENVIRONMENTAL_CONTROL,
			PIN_OUT_NAME_SUB_SYSTEM_WATER, PIN_OUT_NAME_SUB_SYSTEM_TWITCH_FEED, PIN_OUT_NAME_SUB_SYSTEM_TANG };
	
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

	public static final String[] PYRO_OUT_PINS = { PIN_OUT_NAME_PYRO_FUEL_PUMP, PIN_OUT_NAME_PYRO_SOLID_BOOSTER,
			PIN_OUT_NAME_PYRO_MAIN_ENGINE, PIN_OUT_NAME_PYRO_FUEL_PUMP_SWITCH, PIN_OUT_NAME_PYRO_SOLID_BOOSTER_SWITCH,
			PIN_OUT_NAME_PYRO_MAIN_ENGINE_SWITCH };

	public static final String PIN_IN_NAME_PYRO_FUEL_PUMP = "PYRO_FUEL_PUMP_IN";
	public static final String PIN_IN_NAME_PYRO_SOLID_BOOSTER = "PYRO_SOLID_BOOSTER_IN";
	public static final String PIN_IN_NAME_PYRO_MAIN_ENGINE = "PYRO_MAIN_ENGINE_IN";
	public static final String PIN_IN_NAME_PYRO_FUEL_PUMP_SWITCH = "PYRO_FUEL_PUMP_SWITCH_IN";
	public static final String PIN_IN_NAME_PYRO_SOLID_BOOSTER_SWITCH = "PYRO_SOLID_BOOSTER_SWITCH_IN";
	public static final String PIN_IN_NAME_PYRO_MAIN_ENGINE_SWITCH = "PYRO_MAIN_ENGINE_SWITCH_IN";

	public static final String PIN_OUT_NAME_LAUNCH = "LAUNCH_OUT";
	public static final String PIN_IN_NAME_LAUNCH = "LAUNCH_IN";

	public GpioPinDigitalOutput establishOuputPin(Pin pin, String name, MCP23017GpioProvider mcp23017GpioProvider);

	public void establishInputPin(Pin pin, String name, MCP23017GpioProvider mcp23017GpioProvider);

	public void removePin(String pinName);

	public void establishInputPin(Pin pin, String name, MCP23017GpioProvider mcp23017GpioProvider,
			GpioPinDigitalOutput gpioPinDigitalOutput);

	public GpioController getGpioController();

	public void setGpioController(GpioController gpioController);

	public MCP23017GpioProvider getMco23017GpioProvider01();

	public void setMco23017GpioProvider01(MCP23017GpioProvider mco23017GpioProvider01);

	public MCP23017GpioProvider getMco23017GpioProvider02();

	public void setMco23017GpioProvider02(MCP23017GpioProvider mco23017GpioProvider02);

	public MCP23017GpioProvider getMco23017GpioProvider03();

	public void setMco23017GpioProvider03(MCP23017GpioProvider mco23017GpioProvider03);

	public void setOutputPins(Map<String, GpioPinDigitalOutput> outputPins);

	public Map<String, GpioPinDigitalOutput> getOutputPins();

	public GpioPinDigitalOutput getOutputPin(String pinName);

	public Map<String, GpioPinDigitalInput> getInputPins();

	public void setInputPins(Map<String, GpioPinDigitalInput> inputPins);

	public void addGpioPinListener(GpioPinListenerDigital gpioPinListenerDigital);

	public void removeGpioPinListener(GpioPinListenerDigital gpioPinListenerDigital);

	public void reset();

}