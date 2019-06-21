package com.ten31f.mission.entities;

public abstract class Illuminated extends Entity {

	public static enum LEDState {
		HIGH, LOW;
	}

	private int ledON = -1;
	private int ledOFF = -1;

	public Illuminated(int x, int y, int ledON, int ledOFF) {
		super(x, y);
		setLedON(ledON);
		setLedOFF(ledOFF);
	}

	private LEDState ledState = LEDState.LOW;

	public int getLedON() {
		return ledON;
	}

	public void setLedON(int ledON) {
		this.ledON = ledON;
	}

	public int getLedOFF() {
		return ledOFF;
	}

	public void setLedOFF(int ledOFF) {
		this.ledOFF = ledOFF;
	}

	public LEDState getLedState() {
		return ledState;
	}

	public void setLedState(LEDState ledState) {
		this.ledState = ledState;
	}

}
