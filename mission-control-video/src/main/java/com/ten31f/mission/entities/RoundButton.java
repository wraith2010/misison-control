package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Screen;

public class RoundButton extends Button {

	private static final int SPRITE_WIDTH = 80;
	private static final int SPRITE_HEIGHT = 56;

	public RoundButton(int x, int y, int ledON, int ledOFF) {
		super(x, y, ledON, ledOFF);
	}

	@Override
	public void tick() {

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

		switch (getButtonState()) {
		case NOTDEPRESSED:
			renderButton(0, buttonColor, scale, screen);
			break;
		case DEPRESSED:
			renderButton(10, buttonColor, scale, screen);
			break;
		}

	}

	private void renderButton(int xTileOff, int buttonColor, int scale, Screen screen) {
		for (int xtile = 0; xtile < 10; xtile++) {
			for (int ytile = 0; ytile < 7; ytile++) {
				int tile = xtile + xTileOff + (ytile * 32);

				int tilesize = 8 * scale;

				int xpositon = getX() + (tilesize * (xtile)) - (getWidth(scale) / 2);
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
