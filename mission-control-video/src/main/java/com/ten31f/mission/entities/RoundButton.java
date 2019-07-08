package com.ten31f.mission.entities;

import com.ten31f.mission.audio.SoundEffect;
import com.ten31f.mission.gfx.Screen;

public class RoundButton extends Button {

	private static int WIDTH = 10;
	private static int HEIGHT = 7;

	private int buttonPressTick = 0;
	

	public RoundButton(int x, int y, int ledON, int ledOFF, SoundEffect soundEffect) {
		super(x, y, ledON, ledOFF, soundEffect);
		setScale(1);
	}

	@Override
	public void tick() {

		if (getButtonPressTick() == 0) {
			setButtonState(ButtonState.NOTDEPRESSED);
		} else {
			setButtonPressTick(getButtonPressTick() - 1);
		}

		setPromptTickCout(getPromptTickCout() + 1);

		if (getPromptTickCout() >= 20) {
			setPrompt(!isPrompt());
			setPromptTickCout(0);
		}

	}

	@Override
	public void render(Screen screen) {

		int buttonColor = getButtonState().equals(ButtonState.DEPRESSED) ? getLedON() : getButtonColor();
		int xShift = getButtonState().equals(ButtonState.NOTDEPRESSED) ? 0 : 10;

		renderTiles(screen, WIDTH, HEIGHT, xShift, 0, buttonColor, 0x00);

	}

	private int getButtonColor() {

		switch (getLedState()) {
		case HIGH:
			return getLedON();
		case LOW:
			return getLedOFF();
		case PROMPT:
			return isPrompt() ? getLedON() : getLedOFF();
		default:
			return getLedOFF();
		}
	}

	@Override
	public boolean withIN(int x, int y) {

		if (Math.abs(getX() - x) < ((WIDTH * 8 * getScale()) / 2)
				&& Math.abs(getY() - y) < ((HEIGHT * 8 * getScale()) / 2)) {
			setButtonState(ButtonState.DEPRESSED);
			setButtonPressTick(20);
			return true;
		}

		return false;
	}

	
	
	private int getButtonPressTick() {
		return buttonPressTick;
	}

	private void setButtonPressTick(int buttonPressTick) {
		this.buttonPressTick = buttonPressTick;
	}
}
