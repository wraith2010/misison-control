package com.ten31f.mission.pi;

import java.util.Arrays;
import java.util.List;

import com.pi4j.io.gpio.PinState;

public interface IPINController {

	public PinState getStateForPin(String pinName);

	public void setStateForPin(String pinName, PinState pinState);

	public void pulse(String pinName, int duration);

}