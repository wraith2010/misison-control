package com.ten31f.mission.entities;

public abstract class Button extends Illuminated {	
	
	public Button(int x, int y, int ledON, int ledOFF) {
		super(x, y, ledON, ledOFF);
	}

	public static enum ButtonState {
		DEPRESSED, NOTDEPRESSED;
	}

	private ButtonState buttonState = ButtonState.NOTDEPRESSED;

	public ButtonState getButtonState() {
		return buttonState;
	}

	public void setButtonState(ButtonState buttonState) {
		this.buttonState = buttonState;
	}
}
