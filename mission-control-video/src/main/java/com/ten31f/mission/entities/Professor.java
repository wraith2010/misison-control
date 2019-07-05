package com.ten31f.mission.entities;

import org.apache.commons.text.WordUtils;

import com.ten31f.mission.audio.SoundEffect;
import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Font;
import com.ten31f.mission.gfx.Screen;

public class Professor extends Entity {

	public enum Animation {
		IDLE, WALKING_LEFT, WALKING_RIGHT;
	}

	private String dialog = null;
	private String visiableDialog = null;

	private Animation animation = Animation.IDLE;
	private int speak = 0;
	private int step = 0;
	private int tickCount = 0;

	private int targetX = 0;
	private int targetY = 0;

	public Professor(int x, int y) {
		super(x, y);
		setTargetX(x);
		setTargetY(y);
		setScale(3);
	}

	@Override
	public void tick() {
		tickCount++;

		// animate walking
		if (tickCount % 15 == 0) {
			setStep(getStep() + 1);

			if (getStep() > 2)
				setStep(0);

		}

		setSpeak(tickCount % 3);

		tickAdvanceDialog();
		tickMoveToTarget();

	}

	private void tickAdvanceDialog() {

		if (tickCount % 5 != 0)
			return;

		if (!isIdle())
			return;

		if (!isSpeaking())
			return;

		if (getVisiableDialog() == null) {
			setVisiableDialog(getDialog().substring(0, 1));
		} else if (getDialog().length() > getVisiableDialog().length()) {
			setVisiableDialog(getDialog().substring(0, getVisiableDialog().length() + 1));
		}

		if (tickCount % 15 == 0) {
			SoundEffect.BLIP.play();
		}

	}

	public boolean isSpeaking() {

		if (getDialog() == null)
			return false;

		if (getDialog() != null && getVisiableDialog() == null)
			return true;

		return getDialog().length() > getVisiableDialog().length();
	}

	public boolean isIdle() {
		return Animation.IDLE.equals(getAnimation());
	}

	private void tickMoveToTarget() {

		int speed = 10;

		if (getTargetX() == getX() && getTargetY() == getY()) {
			setAnimation(Animation.IDLE);

			return;
		}

		if (tickCount % 5 != 0)
			return;

		if (tickCount % 20 == 0)
			SoundEffect.FOOTSTEP.play();

		if (getX() - getTargetX() > 0) {
			setAnimation(Animation.WALKING_LEFT);
		} else {
			setAnimation(Animation.WALKING_RIGHT);
		}

		if (Math.abs(getX() - getTargetX()) < speed) {
			setX(getTargetX());
		}

		if (Math.abs(getY() - getTargetY()) < speed) {
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


		switch (getAnimation()) {
		case IDLE:
			renderTiles(screen, 3, 4, getSpeak() * 3, 14, color, 0x00);
			break;
		case WALKING_RIGHT:
			renderTiles(screen, 3, 4, getStep() * 3, 18, color, 0x00);
			break;
		case WALKING_LEFT:
			renderTiles(screen, 3, 4, getStep() * 3, 18, color, 0x01);
			break;
		default:
			break;

		}

		renderDialog(screen);

	}

	@Override
	public boolean withIN(int x, int y) {

		return false;
	}

	private void renderDialog(Screen screen) {

		if (getVisiableDialog() == null)
			return;

		if (!Animation.IDLE.equals(getAnimation()))
			return;

		int scale = 2;
		int fontColor = Colours.get(-1, -1, -1, 335);

		String[] lines = WordUtils.wrap(getVisiableDialog(), 15).split("\n");

		int yshift = -40;
		for (String line : lines) {
			Font.render(line, screen, getX() + 60, getY() + yshift, fontColor, scale);
			yshift += 20;
		}

	}

	private Animation getAnimation() {
		return animation;
	}

	private void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public void setDialog(String dialog) {
		this.dialog = dialog;
		setVisiableDialog(null);
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

	private int getSpeak() {
		return speak;
	}

	private void setSpeak(int speak) {
		this.speak = speak;
	}

	private int getStep() {
		return step;
	}

	private void setStep(int step) {
		this.step = step;
	}

}
