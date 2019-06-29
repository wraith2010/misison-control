package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Font;
import com.ten31f.mission.gfx.Screen;

public class Toggle extends Button {

	private static int WIDTH_IN_TILES = 4;
	private static int HEIGHT_IN_TILES = 7;

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
		int xShift = (getButtonState().equals(ButtonState.NOTDEPRESSED)) ? 20 : 24;

		renderTiles(screen, WIDTH_IN_TILES, 7, xShift, 0, buttonColor, 0x00, 2);

		renderText(screen);

	}

	private void renderText(Screen screen) {

		int fontScale = 1;
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
	public boolean withIN(int x, int y) {

		if (Math.abs(getX() - x) < (WIDTH_IN_TILES * 8 / 2) && Math.abs(getY() - y) < (HEIGHT_IN_TILES * 8 / 2)) {
			switch (getButtonState()) {
			case DEPRESSED:
				setButtonState(ButtonState.NOTDEPRESSED);
				break;
			case NOTDEPRESSED:
				setButtonState(ButtonState.DEPRESSED);
				break;
			default:
				break;

			}
			return true;
		}

		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
