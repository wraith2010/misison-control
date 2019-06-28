package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Screen;

public class RoundButton extends Button {

	public RoundButton(int x, int y, int ledON, int ledOFF) {
		super(x, y, ledON, ledOFF);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Screen screen) {

		int buttonColor = getLedState().equals(LEDState.HIGH) ? getLedON() : getLedOFF();
		int xShift = getButtonState().equals(ButtonState.NOTDEPRESSED) ? 0 : 10;
		
		renderTiles(screen, 10, 7, xShift, 0, buttonColor, 0x00, 1);

	}
}
