package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Screen;

public class Rocket extends Entity {

	public Rocket(int x, int y) {
		super(x, y);

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Screen screen) {

		int color = Colours.get(-1, 555, 500, 530);

		renderTiles(screen, 3, 4, 20, 7, color, 0x00, 4);
	}
}
