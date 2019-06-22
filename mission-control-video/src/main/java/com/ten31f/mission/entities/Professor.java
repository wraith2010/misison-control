package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Screen;

public class Professor extends Entity {

	public static enum Animation {
		STANDING, WALKING_LEFT, WALING_RIGHT;
	}

	private Animation animation = Animation.WALKING_LEFT;
	private int step = 0;
	private int tickCount = 0;

	public Professor(int x, int y) {
		super(x, y);
	}

	@Override
	public void tick() {
		tickCount++;

		if (tickCount % 1800 == 0) {
			switch (getAnimation()) {
			case STANDING:
				setAnimation(Animation.WALING_RIGHT);
				break;
			case WALING_RIGHT:
				setAnimation(Animation.WALKING_LEFT);
				break;
			case WALKING_LEFT:
				setAnimation(Animation.STANDING);
				break;
			default:
				break;
			}
		}

		if (tickCount % 15 == 0) {
			step++;

			if (step > 2)
				step = 0;
		}

	}

	@Override
	public void render(Screen screen) {

		int color = Colours.get(-1, 112, 145, 543);
		int scale = 3;

		switch (getAnimation()) {
		case STANDING:
			for (int xtile = 0; xtile < 3; xtile++) {
				for (int ytile = 14; ytile < 18; ytile++) {
					int tile = xtile + (ytile * 32);

					int xpositon = getX() + tileOffset(xtile - 1, scale);
					int yposition = getY() + tileOffset(ytile - 14, scale);

					screen.render(xpositon, yposition, tile, color, 0x00, scale);
				}
			}

			break;
		case WALING_RIGHT:

			for (int xtile = 3; xtile < 6; xtile++) {
				for (int ytile = 14; ytile < 18; ytile++) {
					int tile = xtile + (step * 3) + (ytile * 32);

					int xpositon = getX() + tileOffset(xtile - 4, scale);
					int yposition = getY() + tileOffset(ytile - 14, scale);

					screen.render(xpositon, yposition, tile, color, 0x00, scale);
				}
			}

			break;
		case WALKING_LEFT:

			int xflip = -2;
			for (int xtile = 5; xtile > 2; xtile--) {

				for (int ytile = 14; ytile < 18; ytile++) {
					int tile = xtile + (step * 3) + (ytile * 32);

					int xoffset = tileOffset((xtile - 4) + xflip, scale);
					int xpositon = getX() + xoffset;
					int yposition = getY() + tileOffset(ytile - 14, scale);

					screen.render(xpositon, yposition, tile, color, 0x01, scale);

				}
				xflip += 2;
			}

			break;
		default:
			break;

		}

	}

	private Animation getAnimation() {
		return animation;
	}

	private void setAnimation(Animation animation) {
		this.animation = animation;
	}

}
