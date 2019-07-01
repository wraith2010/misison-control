package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Screen;

public abstract class Entity {

	private int x, y, scale;

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

	public int tileOffset(int index) {
		return index * 8 * getScale();
	}

	protected void renderTiles(Screen screen, int xWidth, int yHeight, int xShift, int yShift, int color, int mirror) {

		if (mirror == 0x00) {

			for (int xTile = 0; xTile < xWidth; xTile++) {
				for (int yTile = 0; yTile < yHeight; yTile++) {
					int tile = (xTile + xShift) + ((yTile + yShift) * 32);

					int xpositon = getX() + tileOffset(xTile) - ((xWidth * 8 * getScale()) / 2);
					int yposition = getY() + tileOffset(yTile) - ((yHeight * 8 * getScale()) / 2);

					screen.render(xpositon, yposition, tile, color, mirror, getScale());
				}
			}
		} else if (mirror == 0x01) {

			int initialX = xWidth - 1;
			for (int xTile = initialX; xTile >= 0; xTile--) {
				for (int yTile = 0; yTile < 4; yTile++) {
					int tile = xTile + xShift + ((yTile + yShift) * 32);

					int xpositon = getX() - tileOffset(xTile) - ((initialX * 8 * getScale()) / 2);
					int yposition = getY() + tileOffset(yTile) - ((yHeight * 8 * getScale()) / 2);

					screen.render(xpositon, yposition, tile, color, mirror, getScale());

				}
			}
		}
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public abstract void tick();

	public abstract void render(Screen screen);

	public abstract boolean withIN(int x, int y);
}
