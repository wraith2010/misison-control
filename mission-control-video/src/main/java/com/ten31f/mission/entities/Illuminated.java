package com.ten31f.mission.entities;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.ten31f.mission.PINControllerOnBoard;
import com.ten31f.mission.gfx.Colours;

public abstract class Illuminated extends Entity {

	public static final int RED_ON = Colours.get(-1, 111, 200, 500);
	public static final int RED_OFF = Colours.get(-1, 111, 200, 200);
	public static final int BLUE_ON = Colours.get(-1, 111, 2, 5);
	public static final int BLUE_OFF = Colours.get(-1, 111, 2, 2);
	public static final int YELLOW_ON = Colours.get(-1, 111, 220, 550);
	public static final int YELLOW_OFF = Colours.get(-1, 111, 220, 220);
	public static final int GREEN_ON = Colours.get(-1, 111, 20, 50);
	public static final int GREEN_OFF = Colours.get(-1, 111, 20, 20);
	public static final int WHITE_ON = Colours.get(-1, 111, 222, 555);
	public static final int WHITE_OFF = Colours.get(-1, 111, 222, 333);

	public static final int WHITE_SQUARE_OFF = Colours.get(-1, 111, 111, 2222);
	public static final int WHITE_SQUARE_ON = Colours.get(-1, 111, 111, 555);

	public static final int TOGLE_ON = Colours.get(-1, 111, 222, 500);
	public static final int TOGLE_OFF = Colours.get(-1, 111, 222, 200);

	public static final int LARGE_RED_ON = Colours.get(-1, 111, 000, 500);
	public static final int LARGE_RED_OFF = Colours.get(-1, 111, 000, 200);

	public enum LEDState {
		HIGH, LOW, PROMPT;
	}

	private int ledON = -1;
	private int ledOFF = -1;

	public Illuminated(int x, int y, int ledON, int ledOFF, String outputPinName,
			PINControllerOnBoard pinControllerOnBoard) {
		super(x, y);
		setLedON(ledON);
		setLedOFF(ledOFF);
		setOutputPinName(outputPinName);
		setPinControllerOnBoard(pinControllerOnBoard);
	}

	private LEDState ledState = LEDState.LOW;
	private int promptTickCout = 0;
	private boolean prompt = false;
	private String outputPinName = null;
	private PINControllerOnBoard pinControllerOnBoard = null;

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

	public GpioPinDigitalOutput getOuputPin() {
		return getPinControllerOnBoard().getOutputPin(getOutputPinName());		
	}

	public void setLedState(LEDState ledState) {
		this.ledState = ledState;

		GpioPinDigitalOutput gpioPinDigitalOutput = getOuputPin();
		if (gpioPinDigitalOutput != null) {
			switch (ledState) {
			case HIGH:
				gpioPinDigitalOutput.setState(PinState.HIGH);
				break;
			case LOW:
				gpioPinDigitalOutput.setState(PinState.LOW);
				break;
			case PROMPT:
				break;
			default:
				break;
			}
		}
	}

	public void toggle() {
		setLedState(getLedState().equals(LEDState.HIGH) ? LEDState.LOW : LEDState.HIGH);
	}

	protected boolean isPrompt() {
		return prompt;
	}

	protected void setPrompt(boolean prompt) {
		this.prompt = prompt;
		if (prompt && LEDState.PROMPT.equals(getLedState())) {

			GpioPinDigitalOutput gpioPinDigitalOutput = getOuputPin();
			if (gpioPinDigitalOutput != null) {
				gpioPinDigitalOutput.pulse(500);
			}
		}
	}

	protected int getPromptTickCout() {
		return promptTickCout;
	}

	protected void setPromptTickCout(int promptTickCout) {
		this.promptTickCout = promptTickCout;
	}

	public String getOutputPinName() {
		return outputPinName;
	}

	public void setOutputPinName(String outputPinName) {
		this.outputPinName = outputPinName;
	}

	public PINControllerOnBoard getPinControllerOnBoard() {
		return pinControllerOnBoard;
	}

	public void setPinControllerOnBoard(PINControllerOnBoard pinControllerOnBoard) {
		this.pinControllerOnBoard = pinControllerOnBoard;
	}
}
