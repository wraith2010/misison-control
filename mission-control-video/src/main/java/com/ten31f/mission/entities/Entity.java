package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Screen;

public abstract class Entity {

	private int x, y;

	public Entity(int x, int y) {

		setX(x);
		setY(y);
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public abstract void tick();

	public abstract void render(Screen screen);
}
