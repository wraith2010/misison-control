package com.ten31f.mission.pi;

import java.util.Arrays;
import java.util.List;

import com.pi4j.io.gpio.PinState;

public interface IPINController {

	String PIN_NAME_PIN00 = "pin00";
	String PIN_NAME_PIN01 = "pin01";
	String PIN_NAME_PIN02 = "pin02";
	String PIN_NAME_PIN10 = "pin10";
	String PIN_NAME_PIN11 = "pin11";
	String PIN_NAME_PIN12 = "pin12";
	String PIN_NAME_PIN20 = "pin20";
	String PIN_NAME_PIN21 = "pin21";
	String PIN_NAME_PIN22 = "pin22";

	public static List<String> PIN_NAMES = Arrays.asList(PIN_NAME_PIN00, PIN_NAME_PIN01, PIN_NAME_PIN02, PIN_NAME_PIN10,
			PIN_NAME_PIN11, PIN_NAME_PIN12, PIN_NAME_PIN20, PIN_NAME_PIN21, PIN_NAME_PIN22);

	public PinState getStateForPin(String pinName);

	public void setStateForPin(String pinName, PinState pinState);

	public void pulse(String pinName, int duration);

}