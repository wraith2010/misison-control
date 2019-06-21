package com.ten31f.mission.entities;

import java.util.Random;

import com.ten31f.mission.gfx.Screen;

public class Button extends Illuminated {

	private static Random random = new Random(System.currentTimeMillis());

	public Button(EntityCollection entityCollection, int x, int y, int ledON, int ledOFF) {
		super(entityCollection, x, y, ledON, ledOFF);
	}

	int tickCount = 0;

	@Override
	public void tick() {

		tickCount++;

		if (tickCount > 100) {
			tickCount = 0;
			setLedState((getRandom().nextBoolean()) ? LEDState.HIGH : LEDState.LOW);
		}

	}

	@Override
	public void render(Screen screen) {

		int scale = 1;
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

		int offset = (8 * scale);

		for (int xtile = 0; xtile < 10; xtile++) {
			for (int ytile = 0; ytile < 7; ytile++) {
				int tile = xtile + (ytile * 32);

				screen.render(getX() + (offset * xtile), getY() + (offset * ytile), tile, buttonColor, 0x00, scale);
			}
		}

	}

	private Random getRandom() {
		return random;
	}

}
