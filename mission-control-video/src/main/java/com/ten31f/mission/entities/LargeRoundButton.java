package com.ten31f.mission.entities;

import java.util.Random;

import com.ten31f.mission.gfx.Screen;

public class LargeRoundButton extends Button {

	private static final int BUTTON_WIDTH = 240;
	private static final int BUTTON_HEIGHT = 210;

	private static Random random = new Random(System.currentTimeMillis());

	public LargeRoundButton(EntityCollection entityCollection, int x, int y, int ledON, int ledOFF) {
		super(entityCollection, x, y, ledON, ledOFF);
	}

	int tickCount = 0;

	@Override
	public void tick() {

		tickCount++;

		if (tickCount > 100) {
			tickCount = 0;
			setLedState((getRandom().nextBoolean()) ? LEDState.HIGH : LEDState.LOW);
			setButtonState((getRandom().nextBoolean()) ? ButtonState.NOTDEPRESSED : ButtonState.DEPRESSED);
		}

	}

	@Override
	public void render(Screen screen) {

		int scale = 3;

		int buttonColor = -1;
		switch (getLedState()) {
		case HIGH:
			buttonColor = getLedON();
			break;
		case LOW:
			buttonColor = getLedOFF();
			break;
		default:
			break;
		}

		switch (getButtonState()) {
		case NOTDEPRESSED:
			for (int xtile = 0; xtile < 10; xtile++) {
				for (int ytile = 0; ytile < 7; ytile++) {
					int tile = xtile + (ytile * 32);

					int xpositon = getX() + (8 * scale * xtile) - (BUTTON_WIDTH / 2);
					int yposition = getY() + (8 * scale * ytile) - (BUTTON_HEIGHT / 2);

					screen.render(xpositon, yposition, tile, buttonColor, 0x00, 3);
				}
			}
			break;
		case DEPRESSED:
			for (int xtile = 10; xtile < 20; xtile++) {
				for (int ytile = 0; ytile < 7; ytile++) {
					int tile = xtile + (ytile * 32);

					int xpositon = getX() + (8 * scale * (xtile - 10)) - (BUTTON_WIDTH / 2);
					int yposition = getY() + (8 * scale * ytile) - (BUTTON_HEIGHT / 2);

					screen.render(xpositon, yposition, tile, buttonColor, 0x00, 3);
				}
			}
			break;
		}

	}

	private Random getRandom() {
		return random;
	}

}
