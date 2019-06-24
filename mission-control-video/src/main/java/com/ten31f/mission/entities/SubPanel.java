package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Font;
import com.ten31f.mission.gfx.Screen;

public class SubPanel extends Entity {

	private String title = null;

	public SubPanel(int x, int y, String title) {
		super(x, y);
		setTitle(title);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Screen screen) {

		int scale = 3;
		int fontColor = Colours.get(-1, -1, -1, 555);

		int yPosition = getY() - 180;
		int xPosition = getX() - (((getTitle().length()) / 2) * (8 * scale));

		Font.render(getTitle(), screen, xPosition, yPosition, fontColor, scale);

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int getHeight(int scale) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getWidth(int scale) {

		throw new UnsupportedOperationException();

	}
}
