package com.ten31f.mission.entities;

import org.apache.commons.lang3.text.WordUtils;

import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Font;
import com.ten31f.mission.gfx.Screen;

public class Professor extends Entity {

	public static final String INSTRUCTION_SECURITY = "Repeat the secuirty sequences to unlock the console";

	private int SPRITE_WIDTH = 24;
	private int SPRITE_HEIGHT = 21;

	public static enum Animation {
		IDLE, WALKING_LEFT, WALKING_RIGHT;
	}

	private String dialog = INSTRUCTION_SECURITY;
	private String visiableDialog = null;

	private Animation animation = Animation.IDLE;
	private int step = 0;
	private int tickCount = 0;
	private int textTickCount = 0;

	private int targetX = 0;
	private int targetY = 0;

	public Professor(int x, int y) {
		super(x, y);
		setTargetX(x);
		setTargetY(y);
	}

	@Override
	public void tick() {
		tickCount++;

		// animate walking
		if (tickCount % 15 == 0) {
			step++;

			if (step > 2)
				step = 0;
		}

		tickAdvanceDialog();
		tickMoveToTarget();

	}

	private void tickAdvanceDialog() {

		// animate text
		if (tickCount % 5 == 0 && getDialog() != null) {

			if (getVisiableDialog() == null) {
				setVisiableDialog(getDialog().substring(0, 1));
			} else if (getDialog().length() > getVisiableDialog().length()) {

				setVisiableDialog(getDialog().substring(0, getVisiableDialog().length() + 1));
			} else {
				textTickCount++;
				if (textTickCount > 50) {
					textTickCount = 0;
					setVisiableDialog(null);
				}
			}

		}

	}

	private void tickMoveToTarget() {

		int speed = 5;

		if (getTargetX() == getX() && getTargetY() == getY()) {
			setAnimation(Animation.IDLE);
			return;
		}

		if (tickCount % 5 != 0)
			return;

		if (getX() - getTargetX() > 0) {
			setAnimation(Animation.WALKING_LEFT);
		} else {
			setAnimation(Animation.WALKING_RIGHT);
		}

		if (Math.abs(getX() - getTargetX()) < speed) {
			System.out.println("Arrived X!");
			setX(getTargetX());
		}

		if (Math.abs(getY() - getTargetY()) < speed) {
			System.out.println("Arrived Y!");
			setY(getTargetY());
		}

		if (getX() > getTargetX()) {
			setX(getX() - speed);
		} else if (getX() < getTargetX()) {
			setX(getX() + speed);
		}

		if (getY() > getTargetY()) {
			setY(getY() - speed);
		} else if (getY() < getTargetY()) {
			setY(getY() + speed);
		}

	}

	@Override
	public void render(Screen screen) {

		int color = Colours.get(-1, 112, 145, 543);
		int scale = 3;

		switch (getAnimation()) {
		case IDLE:
			for (int xtile = 0; xtile < 3; xtile++) {
				for (int ytile = 14; ytile < 18; ytile++) {
					int tile = xtile + (ytile * 32);

					int xpositon = getX() + tileOffset(xtile - 1, scale);
					int yposition = getY() + tileOffset(ytile - 14, scale);

					screen.render(xpositon, yposition, tile, color, 0x00, scale);
				}
			}

			break;
		case WALKING_RIGHT:

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

		renderDialog(screen);

	}

	private void renderDialog(Screen screen) {

		if (getVisiableDialog() == null)
			return;

		int scale = 2;
		int fontColor = Colours.get(-1, -1, -1, 335);

		String[] lines = WordUtils.wrap(getVisiableDialog(), 15).split("\n");

		int yshift = 0;
		for (String line : lines) {
			Font.render(line, screen, getX() + 50, getY() + yshift, fontColor, scale);
			yshift += 20;
		}

	}

	private Animation getAnimation() {
		return animation;
	}

	private void setAnimation(Animation animation) {
		this.animation = animation;
	}

	@Override
	public int getWidth(int scale) {
		return SPRITE_WIDTH * scale;
	}

	@Override
	public int getHeight(int scale) {
		return SPRITE_HEIGHT * scale;
	}

	public void setDialog(String dialog) {
		this.dialog = dialog;
	}

	public String getDialog() {
		return dialog;
	}

	private String getVisiableDialog() {
		return visiableDialog;
	}

	private void setVisiableDialog(String visiableDialog) {
		this.visiableDialog = visiableDialog;
	}

	public void moveToXY(int x, int y) {
		setTargetX(x);
		setTargetY(y);
	}

	private int getTargetX() {
		return targetX;
	}

	private void setTargetX(int targetX) {
		this.targetX = targetX;
	}

	private int getTargetY() {
		return targetY;
	}

	private void setTargetY(int targetY) {
		this.targetY = targetY;
	}

}
