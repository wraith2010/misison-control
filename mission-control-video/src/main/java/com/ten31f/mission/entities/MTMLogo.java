package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Screen;

public class MTMLogo extends Entity {

	private static int WIDTH = 13;
	private static int HEIGHT = 14;

	private int color = 0;

	public MTMLogo(int x, int y) {
		super(x, y);
		setScale(6);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Screen screen) {
		renderTiles(screen, WIDTH, HEIGHT, 19, 14, getColor(), 0x00);
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
