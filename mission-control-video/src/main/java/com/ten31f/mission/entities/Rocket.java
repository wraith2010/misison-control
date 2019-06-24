package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Screen;

public class Rocket extends Entity {

	private int SPRITE_WIDTH = 24;
	private int SPRITE_HEIGHT = 21;

	public Rocket(int x, int y) {
		super(x, y);

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Screen screen) {
		int scale = 4;
		int color = Colours.get(-1, 555, 500, 530);

		int xShift = 20;
		int yShift = 7;

		for (int xtile = 0; xtile < 3; xtile++) {
			for (int ytile = 0; ytile < 4; ytile++) {
				int tile = (xtile + xShift) + ((ytile + yShift) * 32);

				int tileSize = 8 * scale;

				int xpositon = getX() + (tileSize * xtile) - (getWidth(scale) / 2);
				int yposition = getY() + (tileSize * ytile) - (getHeight(scale) / 2);

				screen.render(xpositon, yposition, tile, color, 0x00, scale);
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
