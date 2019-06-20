package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Screen;

public class Button extends Entity {

	private static final int Y_TILE = 25;
	private static final int X_TILE = 0;

	public Button(EntityCollection entityCollection, int x, int y) {
		super(entityCollection);
		setX(x);
		setY(y);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Screen screen) {

		int scale = 2;
		int buttonColor = Colours.get(-1, 111, 145, 500);
		int tile1 = X_TILE + Y_TILE * 32;
		int tile2 = (X_TILE + 1) + Y_TILE * 32;
		int tile3 = X_TILE + (Y_TILE + 1) * 32;
		int tile4 = (X_TILE + 1) + (Y_TILE + 1) * 32;

		int offset = (8 * scale);

		screen.render(getX(), getY(), tile1, buttonColor, 0x00, scale);
		screen.render(getX() + offset, getY(), tile2, buttonColor, 0x00, scale);
		screen.render(getX(), getY() + offset, tile3, buttonColor, 0x00, scale);
		screen.render(getX() + offset, getY() + offset, tile4, buttonColor, 0x00, scale);
	}

}
