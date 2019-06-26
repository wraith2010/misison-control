package com.ten31f.mission.entities;

import java.util.Random;

public abstract class Button extends Illuminated {	
	
	public enum ButtonState {
		DEPRESSED, NOTDEPRESSED;
	}
	
	public static Random random = new Random(System.currentTimeMillis());
	
	public Button(int x, int y, int ledON, int ledOFF) {
		super(x, y, ledON, ledOFF);
	}	

	private ButtonState buttonState = ButtonState.NOTDEPRESSED;

	public ButtonState getButtonState() {
		return buttonState;
	}

	public void setButtonState(ButtonState buttonState) {
		this.buttonState = buttonState;
	}
}
