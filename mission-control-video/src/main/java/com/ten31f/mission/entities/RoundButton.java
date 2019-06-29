package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Screen;

public class RoundButton extends Button {

	private static int WIDTH = 10;
	private static int HEIGHT = 7;

	private int buttonPressTick = 0;

	public RoundButton(int x, int y, int ledON, int ledOFF) {
		super(x, y, ledON, ledOFF);
	}

	@Override
	public void tick() {

		if (getButtonPressTick() == 0) {
			setButtonState(ButtonState.NOTDEPRESSED);
		} else {
			setButtonPressTick(getButtonPressTick() - 1);
		}

	}

	@Override
	public void render(Screen screen) {

		int buttonColor = getLedState().equals(LEDState.HIGH) ? getLedON() : getLedOFF();
		buttonColor = getButtonState().equals(ButtonState.DEPRESSED) ? getLedON() : buttonColor;
		int xShift = getButtonState().equals(ButtonState.NOTDEPRESSED) ? 0 : 10;
		

		renderTiles(screen, WIDTH, HEIGHT, xShift, 0, buttonColor, 0x00, 1);

	}

	@Override
	public boolean withIN(int x, int y) {

		if (Math.abs(getX() - x) < (WIDTH * 8 / 2) && Math.abs(getY() - y) < (HEIGHT * 8 / 2)) {
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
