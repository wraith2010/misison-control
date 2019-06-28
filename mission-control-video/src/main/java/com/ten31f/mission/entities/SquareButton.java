package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Font;
import com.ten31f.mission.gfx.Screen;

public class SquareButton extends Button {

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

		int buttonColor = getLedState().equals(LEDState.HIGH) ? getLedON() : getLedOFF();
		int xShift = getButtonState().equals(ButtonState.NOTDEPRESSED) ? 0 : 10;

		renderTiles(screen, 10, 7, xShift, 7, buttonColor, 0x00, 1);

		renderText(screen);
	}

	private void renderText(Screen screen) {

		int fontScale = 1;
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

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
}
