package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Screen;

public class LargeRoundButton extends Button {

	private static final int SPRITE_WIDTH = 80;
	private static final int SPRITE_HEIGHT = 56;

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

		int scale = 3;

		int buttonColor = LEDState.HIGH.equals(getLedState()) ? getLedON() : getLedOFF();
		int xshift = ButtonState.NOTDEPRESSED.equals(getButtonState()) ? 0 : 10;

		for (int xtile = 0; xtile < 10; xtile++) {
			for (int ytile = 0; ytile < 7; ytile++) {
				int tile = xtile + xshift + (ytile * 32);

				int tilesize = 8 * scale;
				int xpositon = getX() + (tilesize * xtile) - (getWidth(scale) / 2);
				int yposition = getY() + (tilesize * ytile) - (getHeight(scale) / 2);

				screen.render(xpositon, yposition, tile, buttonColor, 0x00, scale);
			}
		}

	}

	@Override
	public int getWidth(int scale) {
		return SPRITE_WIDTH * scale;
	}

	@Override
	public int getHeight(int scale) {
		return SPRITE_HEIGHT * scale;
	}

}
