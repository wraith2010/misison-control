package com.ten31f.mission.entities;

import java.util.Random;

import com.ten31f.mission.gfx.Screen;

public class SquareButton extends Button {

	private static final int BUTTON_WIDTH = 80;
	private static final int BUTTON_HEIGHT = 70;

	private static Random random = new Random(System.currentTimeMillis());

	public SquareButton(int x, int y, int ledON, int ledOFF) {
		super(x, y, ledON, ledOFF);
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
				for (int ytile = 7; ytile < 14; ytile++) {
					int tile = xtile + (ytile * 32);

					int xpositon = getX() + (8 * xtile) - (BUTTON_WIDTH / 2);
					int yposition = getY() + (8 * (ytile - 7)) - (BUTTON_HEIGHT / 2);

					screen.render(xpositon, yposition, tile, buttonColor, 0x00, 1);
				}
			}
			break;
		case DEPRESSED:
			for (int xtile = 0; xtile < 10; xtile++) {
				for (int ytile = 0; ytile < 7; ytile++) {
					int tile = xtile + 10 + ((ytile + 7) * 32);

					int xpositon = getX() + (8 * xtile) - (BUTTON_WIDTH / 2);
					int yposition = getY() + (8 * ytile) - (BUTTON_HEIGHT / 2);

					screen.render(xpositon, yposition, tile, buttonColor, 0x00, 1);
				}
			}
			break;
		}

	}

	public static Random getRandom() {
		return random;
	}

	public static void setRandom(Random random) {
		SquareButton.random = random;
	}

}
