package com.ten31f.mission.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Screen;

public class Starfield extends Entity {

	private static final Random random = new Random(System.nanoTime());

	private int screenWidth = 0;
	private int screenHeight = 0;
	private List<Star> stars = new ArrayList<>();

	public Starfield(int x, int y, int screenWidth, int screenHeight) {
		super(x, y);
		setScreenWidth(screenWidth);
		setScreenHeight(screenHeight);

		for (int index = 0; index < 40; index++) {

			int starX = random.nextInt(getScreenWidth());
			int starY = random.nextInt(getScreenHeight() / 2);

			getStars().add(new Star(starX, starY, 1));
		}

		for (int index = 0; index < 15; index++) {

			int starX = random.nextInt(getScreenWidth());
			int starY = random.nextInt(getScreenHeight() / 2);

			getStars().add(new Star(starX, starY, 2));
		}

		for (int index = 0; index < 5; index++) {

			int starX = random.nextInt(getScreenWidth());
			int starY = random.nextInt(getScreenHeight() / 2);

			getStars().add(new Star(starX, starY, 4));
		}

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Screen screen) {
		int color = Colours.get(-1, -1, 555, 540);

		for (Star star : getStars()) {
			screen.render(star.getX(), star.getY(), 28, color, 0x00, star.getScale());
		}
	}

	@Override
	public boolean withIN(int x, int y) {
		return false;
	}

	private int getScreenHeight() {
		return screenHeight;
	}

	private void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	private int getScreenWidth() {
		return screenWidth;
	}

	private void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	private List<Star> getStars() {
		return stars;
	}

	public class Star {

		private int x = 0;
		private int y = 0;
		private int scale = 1;

		public Star(int x, int y, int scale) {
			setX(x);
			setY(y);
			setScale(scale);
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getScale() {
			return scale;
		}

		public void setScale(int scale) {
			this.scale = scale;
		}

	}
}
