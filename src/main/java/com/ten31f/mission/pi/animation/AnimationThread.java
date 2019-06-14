package com.ten31f.mission.pi.animation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.ten31f.mission.pi.examples.PINController;

public class AnimationThread implements Runnable {

	public static Logger logger = LoggerFactory.getLogger(AnimationThread.class);

	private static final int FIVE_MINUTES = 5 * 60 * 500;

	private Date lastInput = null;

	private PINController pinController = null;

	private List<AnimationStep> currentAnimation = null;

	private boolean repeat = false;

	private List<AnimationStep> spinAnimation = null;

	public AnimationThread(PINController pinController) {

		setPinController(pinController);
		initilizeSpinAnimation();

	}

	private void initilizeSpinAnimation() {

		GpioPinDigitalOutput[] simonOut = getPinController().getSimonOut();

		List<AnimationStep> spinAnimation = new ArrayList<>();

		spinAnimation
				.add(new AnimationStep(new GpioPinDigitalOutput[] { simonOut[0], simonOut[8] }, PinState.HIGH, 500));
		spinAnimation
				.add(new AnimationStep(new GpioPinDigitalOutput[] { simonOut[0], simonOut[8] }, PinState.LOW, 500));
		spinAnimation
				.add(new AnimationStep(new GpioPinDigitalOutput[] { simonOut[1], simonOut[7] }, PinState.HIGH, 500));
		spinAnimation
				.add(new AnimationStep(new GpioPinDigitalOutput[] { simonOut[1], simonOut[7] }, PinState.LOW, 500));
		spinAnimation
				.add(new AnimationStep(new GpioPinDigitalOutput[] { simonOut[2], simonOut[6] }, PinState.HIGH, 500));
		spinAnimation
				.add(new AnimationStep(new GpioPinDigitalOutput[] { simonOut[2], simonOut[6] }, PinState.LOW, 500));
		spinAnimation
				.add(new AnimationStep(new GpioPinDigitalOutput[] { simonOut[3], simonOut[5] }, PinState.HIGH, 500));
		spinAnimation
				.add(new AnimationStep(new GpioPinDigitalOutput[] { simonOut[3], simonOut[5] }, PinState.LOW, 500));
		spinAnimation.add(new AnimationStep(new GpioPinDigitalOutput[] { simonOut[4] }, PinState.HIGH, 500));
		spinAnimation.add(new AnimationStep(new GpioPinDigitalOutput[] { simonOut[4] }, PinState.LOW, 500));

		setSpinAnimation(spinAnimation);

	}

	private boolean goToScreenSaver() {
		if (getCurrentAnimation() != null)
			return false;

		if (getLastInput() == null)
			return false;

		if (getTimeTillScreenSaver() > 0)
			return false;

		setCurrentAnimation(getSpinAnimation());
		setRepeat(true);

		return true;
	}

	@Override
	public void run() {

		while (true) {

			goToScreenSaver();

			if (getCurrentAnimation() != null) {

				AnimationStep animationStep = getCurrentAnimation().get(0);

				for (GpioPinDigitalOutput pinDigitalOutput : animationStep.getPins()) {
					pinDigitalOutput.setState(animationStep.getPinState());
				}

				if (isRepeat())
					getCurrentAnimation().add(animationStep);

				try {
					Thread.sleep(animationStep.getDuration());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	public long getTimeTillScreenSaver() {
		return getLastInput().getTime() - (System.currentTimeMillis() - FIVE_MINUTES);
	}

	public Date getLastInput() {
		return lastInput;
	}

	public void setLastInput(Date lastInput) {
		this.lastInput = lastInput;
		setCurrentAnimation(null);
		setRepeat(false);
	}

	public PINController getPinController() {
		return pinController;
	}

	public void setPinController(PINController pinController) {
		this.pinController = pinController;
	}

	public List<AnimationStep> getCurrentAnimation() {
		return currentAnimation;
	}

	public void setCurrentAnimation(List<AnimationStep> currentAnimation) {
		this.currentAnimation = currentAnimation;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	private List<AnimationStep> getSpinAnimation() {
		return spinAnimation;
	}

	private void setSpinAnimation(List<AnimationStep> spinAnimation) {
		this.spinAnimation = spinAnimation;
	}
}
