package com.ten31f.mission.pi;

import java.util.Arrays;
import java.util.List;

import com.pi4j.io.gpio.PinState;

public interface IPINController {

	// Panel
	// Simon out
	public static String PIN_NAME_PIN00_OUT = "pin00_out";
	public static String PIN_NAME_PIN01_OUT = "pin01_out";
	public static String PIN_NAME_PIN02_OUT = "pin02_out";
	public static String PIN_NAME_PIN10_OUT = "pin10_out";
	public static String PIN_NAME_PIN11_OUT = "pin11_out";
	public static String PIN_NAME_PIN12_OUT = "pin12_out";
	public static String PIN_NAME_PIN20_OUT = "pin20_out";
	public static String PIN_NAME_PIN21_OUT = "pin21_out";
	public static String PIN_NAME_PIN22_OUT = "pin22_out";

	// Simon int
	public static String PIN_NAME_PIN00_IN = "pin00_in";
	public static String PIN_NAME_PIN01_IN = "pin01_in";
	public static String PIN_NAME_PIN02_IN = "pin02_in";
	public static String PIN_NAME_PIN10_IN = "pin10_in";
	public static String PIN_NAME_PIN11_IN = "pin11_in";
	public static String PIN_NAME_PIN12_IN = "pin12_in";
	public static String PIN_NAME_PIN20_IN = "pin20_in";
	public static String PIN_NAME_PIN21_IN = "pin21_in";
	public static String PIN_NAME_PIN22_IN = "pin22_in";

	// panel 2
	public static String PIN_NAME_BUTTON_01_OUT = "button01_out";
	public static String PIN_NAME_BUTTON_02_OUT = "button02_out";
	public static String PIN_NAME_BUTTON_03_OUT = "button03_out";
	public static String PIN_NAME_BUTTON_04_OUT = "button04_out";
	public static String PIN_NAME_BUTTON_05_OUT = "button05_out";
	public static String PIN_NAME_BUTTON_06_OUT = "button06_out";

	public static String PIN_NAME_BUTTON_01_IN = "button01_in";
	public static String PIN_NAME_BUTTON_02_IN = "button02_in";
	public static String PIN_NAME_BUTTON_03_IN = "button03_in";
	public static String PIN_NAME_BUTTON_04_IN = "button04_in";
	public static String PIN_NAME_BUTTON_05_IN = "button05_in";
	public static String PIN_NAME_BUTTON_06_IN = "button06_in";

	// panel 3
	public static String PIN_NAME_SWITCH01_OUT = "switch01_out";
	public static String PIN_NAME_SWITCH02_OUT = "switch02_out";
	public static String PIN_NAME_SWITCH03_OUT = "switch03_out";

	public static String PIN_NAME_SWITCH01_IN = "switch01_in";
	public static String PIN_NAME_SWITCH02_IN = "switch02_in";
	public static String PIN_NAME_SWITCH03_IN = "switch03_in";

	public static String PIN_NAME_BUTTON07_OUT = "button07_out";
	public static String PIN_NAME_BUTTON08_OUT = "button08_out";
	public static String PIN_NAME_BUTTON09_OUT = "button09_out";

	public static String PIN_NAME_BUTTON07_IN = "button07_in";
	public static String PIN_NAME_BUTTON08_IN = "button08_in";
	public static String PIN_NAME_BUTTON09_IN = "button09_in";

	// panel 4
	public static String PIN_NAME_MEGA_BUTTON01_OUT = "mega_button01_out";

	public static String PIN_NAME_MEGA_BUTTON01_IN = "mega_button01_in";

	public static List<String> PIN_NAMES = Arrays.asList(PIN_NAME_PIN00_OUT, PIN_NAME_PIN01_OUT, PIN_NAME_PIN02_OUT,
			PIN_NAME_PIN10_OUT, PIN_NAME_PIN11_OUT, PIN_NAME_PIN12_OUT, PIN_NAME_PIN20_OUT, PIN_NAME_PIN21_OUT,
			PIN_NAME_PIN22_OUT, PIN_NAME_PIN00_IN, PIN_NAME_PIN01_IN, PIN_NAME_PIN02_IN, PIN_NAME_PIN10_IN,
			PIN_NAME_PIN11_IN, PIN_NAME_PIN12_IN, PIN_NAME_PIN20_IN, PIN_NAME_PIN21_IN, PIN_NAME_PIN22_IN,
			PIN_NAME_BUTTON_01_OUT, PIN_NAME_BUTTON_02_OUT, PIN_NAME_BUTTON_03_OUT, PIN_NAME_BUTTON_04_OUT,
			PIN_NAME_BUTTON_05_OUT, PIN_NAME_BUTTON_06_OUT, PIN_NAME_BUTTON_01_IN, PIN_NAME_BUTTON_02_IN,
			PIN_NAME_BUTTON_03_IN, PIN_NAME_BUTTON_04_IN, PIN_NAME_BUTTON_05_IN, PIN_NAME_BUTTON_06_IN,
			PIN_NAME_SWITCH01_OUT, PIN_NAME_SWITCH02_OUT, PIN_NAME_SWITCH03_OUT, PIN_NAME_SWITCH01_IN,
			PIN_NAME_SWITCH02_IN, PIN_NAME_SWITCH03_IN, PIN_NAME_BUTTON07_OUT, PIN_NAME_BUTTON08_OUT,
			PIN_NAME_BUTTON09_OUT, PIN_NAME_BUTTON07_IN, PIN_NAME_BUTTON08_IN, PIN_NAME_BUTTON09_IN,
			PIN_NAME_MEGA_BUTTON01_OUT, PIN_NAME_MEGA_BUTTON01_IN);

	public PinState getStateForPin(String pinName);

	public void setStateForPin(String pinName, PinState pinState);

	public void pulse(String pinName, int duration);

	public void toggle(String pinName);

}
