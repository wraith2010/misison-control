package com.ten31f.mission.level.tiles;

import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.gfx.Screen;

public class BasicTile extends Tile {

	protected int tileId;
	protected int tileColour;

	public BasicTile(int id, int x, int y, int tileColour, int levelColour) {
		super(id, false, false, levelColour);
		this.tileId = x + y * 32;
		this.tileColour = tileColour;
	}

	public void tick() {
	}

	@Override
	public void render(Screen screen, EntityCollection entityCollection, int x, int y) {
		screen.render(x, y, tileId, tileColour, 0x00, 1);
	}

}
