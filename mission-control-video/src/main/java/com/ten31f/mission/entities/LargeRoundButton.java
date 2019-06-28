package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Screen;

public class LargeRoundButton extends Button {

	public LargeRoundButton(int x, int y, int ledON, int ledOFF) {
		super(x, y, ledON, ledOFF);
	}

	int tickCount = 0;

	@Override
	public void tick() {

		tickCount++;

		if (tickCount > 100) {
			tickCount = 0;
			setLedState((Button.random.nextBoolean()) ? LEDState.HIGH : LEDState.LOW);
			setButtonState((Button.random.nextBoolean()) ? ButtonState.NOTDEPRESSED : ButtonState.DEPRESSED);
		}

	}

	@Override
	public void render(Screen screen) {

		int buttonColor = LEDState.HIGH.equals(getLedState()) ? getLedON() : getLedOFF();
		int xshift = ButtonState.NOTDEPRESSED.equals(getButtonState()) ? 0 : 10;

		renderTiles(screen, 10, 7, xshift, 0, buttonColor, 0x00, 3);

	}

}
