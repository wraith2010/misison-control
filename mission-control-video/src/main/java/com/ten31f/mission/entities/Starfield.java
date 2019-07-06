package com.ten31f.mission.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Screen;

public class Starfield extends Entity {

	public enum Animation {
		TWINKLE, FLY;
	}

	private Animation animation = null;

	private static final Random random = new Random(System.nanoTime());

	private int screenWidth = 0;
	private int screenHeight = 0;
	private List<Star> stars = new ArrayList<>();

	public Starfield(int x, int y, int screenWidth, int screenHeight) {
		super(x, y);
		setScreenWidth(screenWidth);
		setScreenHeight(screenHeight);
		setAnimation(Animation.TWINKLE);

		generateStars();
	}
	
	public void generateStars() {
		
		generateStars(15, 1);
		generateStars(10, 2);
		generateStars(4, 4);
	}

	private void generateStars(int count, int scale) {
		
		getStars().clear();
		
		for (int index = 0; index < count; index++) {

			int starX = random.nextInt(getScreenWidth());
			int starY = random.nextInt(getScreenHeight()) - (getScreenHeight() / 2);

			getStars().add(new Star(starX, starY, scale));
		}
	}

	@Override
	public void tick() {
		switch (getAnimation()) {
		case FLY:
			animateFly();
			break;
		case TWINKLE:
			break;
		default:
			break;

		}
	}

	private void animateFly() {
		for (Star star : getStars()) {
			star.setY(star.getY() + star.getScale());
			if (star.getY() > getScreenHeight()) {
				star.setY(-star.getScale());
				star.setX(random.nextInt(getScreenWidth()));
			}
		}
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

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
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
