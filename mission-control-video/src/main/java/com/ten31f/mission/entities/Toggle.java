package com.ten31f.mission.entities;

import com.ten31f.mission.PINControllerOnBoard;
import com.ten31f.mission.audio.SoundEffect;
import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Font;
import com.ten31f.mission.gfx.Screen;

public class Toggle extends Button {

	private static int WIDTH = 4;
	private static int HEIGHT = 7;

	private String name = null;

	public Toggle(String name, int x, int y, int ledON, int ledOFF, SoundEffect soundEffect,
			PINControllerOnBoard pinControllerOnBoard) {
		super(x, y, ledON, ledOFF, soundEffect, null, pinControllerOnBoard);
		setName(name);
		setScale(2);
	}

	@Override
	public void tick() {
		setPromptTickCout(getPromptTickCout() + 1);

		if (getPromptTickCout() >= 20) {
			setPrompt(!isPrompt());
			setPromptTickCout(0);
		}
	}

	@Override
	public void render(Screen screen) {

		int xShift = (getButtonState().equals(ButtonState.NOTDEPRESSED)) ? 20 : 24;

		renderTiles(screen, WIDTH, HEIGHT, xShift, 0, getButtonColor(), 0x00);
		renderText(screen);
	}

	private int getButtonColor() {

		switch (getLedState()) {
		case HIGH:
			return getLedON();
		case LOW:
			return getLedOFF();
		case PROMPT:
			return isPrompt() ? getLedON() : getLedOFF();
		default:
			return getLedOFF();
		}
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

		if (Math.abs(getX() - x) < (WIDTH * 8 / 2) && Math.abs(getY() - y) < (HEIGHT * 8 / 2)) {
			switch (getButtonState()) {
			case DEPRESSED:
				setButtonState(ButtonState.NOTDEPRESSED);
				setLedState(LEDState.LOW);
				break;
			case NOTDEPRESSED:
				setButtonState(ButtonState.DEPRESSED);
				setLedState(LEDState.HIGH);
				getSoundEffect().play();
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
