package com.ten31f.mission.entities;

import java.util.Random;

import com.ten31f.mission.PINControllerOnBoard;
import com.ten31f.mission.audio.SoundEffect;

public abstract class Button extends Illuminated {

	public enum ButtonState {
		DEPRESSED, NOTDEPRESSED;
	}

	public static Random random = new Random(System.currentTimeMillis());

	private ButtonState buttonState = ButtonState.NOTDEPRESSED;
	private SoundEffect soundEffect = null;

	public Button(int x, int y, int ledON, int ledOFF, SoundEffect soundEffect, String outputPinName,
			PINControllerOnBoard pinControllerOnBoard) {
		super(x, y, ledON, ledOFF, outputPinName, pinControllerOnBoard);
		setSoundEffect(soundEffect);
	}

	public ButtonState getButtonState() {
		return buttonState;
	}

	public void setButtonState(ButtonState buttonState) {
		this.buttonState = buttonState;
	}

	public SoundEffect getSoundEffect() {
		return soundEffect;
	}

	private void setSoundEffect(SoundEffect soundEffect) {
		this.soundEffect = soundEffect;
	}
}
