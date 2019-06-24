package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Font;
import com.ten31f.mission.gfx.Screen;

public class Toggle extends Button {

	private static final int SPRITE_WIDTH = 32;
	private static final int SPRITE_HEIGHT = 56;

	private static final int spriteScale = 2;
	private static final int fontScale = 1;
	
	private String name = null;
	
	public Toggle(String name, int x, int y, int ledON, int ledOFF) {
		super(x, y, ledON, ledOFF);
		setName(name);
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
		int xshift = (getButtonState().equals(ButtonState.NOTDEPRESSED)) ? 20 : 24;

		for (int xtile = 0; xtile < 4; xtile++) {
			for (int ytile = 0; ytile < 7; ytile++) {
				int tile = xtile + xshift + (ytile * 32);

				int tileWidth = 8 * spriteScale;

				int xPositon = getX() + (tileWidth * xtile) - (getWidth(spriteScale) / 2);
				int yPosition = getY() + (tileWidth * ytile) - (getHeight(spriteScale) / 2);

				screen.render(xPositon, yPosition, tile, buttonColor, 0x00, spriteScale);
			}
		}
		
		renderText(screen);

	}
	
	private void renderText(Screen screen) {

		int fontColor = Colours.get(-1, -1, -1, 500);

		int yPosition = getY() + 50;

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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
