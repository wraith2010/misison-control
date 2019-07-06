package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Screen;

public class Flame extends Entity {

	private int flame = 0;
	private int tickCount = 0;

	public Flame(int x, int y) {
		super(x, y);
		setScale(5);
	}

	@Override
	public void tick() {

		tickCount++;

		if (tickCount % 5 == 0) {
			setFlame(getFlame() + 1);
			if (getFlame() > 2) {
				setFlame(0);
			}
		}

	}

	@Override
	public void render(Screen screen) {

		int color = Colours.get(-1, 500, 531, 555);

		renderTiles(screen, 1, 2, 20 + getFlame(), 11, color, 0x00);
	}

	@Override
	public boolean withIN(int x, int y) {
		return false;
	}

	private int getFlame() {
		return flame;
	}

	private void setFlame(int flame) {
		this.flame = flame;
	}

}
