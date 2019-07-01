package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Screen;

public class LargeRoundButton extends Button {

	public LargeRoundButton(int x, int y, int ledON, int ledOFF) {
		super(x, y, ledON, ledOFF);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Screen screen) {

		int buttonColor = LEDState.HIGH.equals(getLedState()) ? getLedON() : getLedOFF();
		int xshift = ButtonState.NOTDEPRESSED.equals(getButtonState()) ? 0 : 10;

		renderTiles(screen, 10, 7, xshift, 0, buttonColor, 0x00, 3);

	}

	@Override
	public boolean withIN(int x, int y) {

		return false;
	}
}
