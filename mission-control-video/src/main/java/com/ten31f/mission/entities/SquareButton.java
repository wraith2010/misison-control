package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Font;
import com.ten31f.mission.gfx.Screen;

public class SquareButton extends Button {

	private static final int SPRITE_WIDTH = 80;
	private static final int SPRITE_HEIGHT = 56;

	private static final int spriteScale = 1;
	private static final int fontScale = 1;

	public SquareButton(String name, int x, int y, int ledON, int ledOFF) {
		super(x, y, ledON, ledOFF);
		setName(name);
	}

	private String name = null;

	@Override
	public void tick() {
	}

	@Override
	public void render(Screen screen) {

		int buttonColor = LEDState.HIGH.equals(getLedState()) ? getLedON() : getLedOFF();
		int xshift = (ButtonState.NOTDEPRESSED.equals(getButtonState())) ? 0 : 10;
		int yshift = 7;

		for (int xtile = 0; xtile < 10; xtile++) {
			for (int ytile = 0; ytile < 7; ytile++) {
				int tile = xtile + xshift + ((ytile + yshift) * 32);

				int tileWidth = 8 * spriteScale;
				int xPosition = getX() + (tileWidth * xtile) - (getWidth(spriteScale) / 2);
				int yPosition = getY() + (tileWidth * ytile) - (getHeight(spriteScale) / 2);

				screen.render(xPosition, yPosition, tile, buttonColor, 0x00, spriteScale);
			}
		}

		renderText(screen);

	}

	private void renderText(Screen screen) {

		int fontColor = Colours.get(-1, -1, -1, 500);

		int yPosition = getY() - 40;

		String[] lines = getName().split("\n");

		int yshift = 0;
		for (String line : lines) {
			int xPosition = getX() - (((line.length()) / 2) * (8 * fontScale));
			Font.render(line, screen, xPosition, yPosition + yshift, fontColor, fontScale);
			yshift += 10;
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

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
}
