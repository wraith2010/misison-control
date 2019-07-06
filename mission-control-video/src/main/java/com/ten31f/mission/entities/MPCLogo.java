package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Screen;

public class MPCLogo extends Entity {

	private static int WIDTH = 10;
	private static int HEIGHT = 11;

	private int color = 0;

	public MPCLogo(int x, int y) {
		super(x, y);
		setScale(6);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Screen screen) {
		renderTiles(screen, WIDTH, HEIGHT, 9, 14, getColor(), 0x00);
	}

	@Override
	public boolean withIN(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	private int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
