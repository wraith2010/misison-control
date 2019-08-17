package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import com.ten31f.mission.PixelPanel;
import com.ten31f.mission.audio.SoundEffect;
import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.MPCLogo;
import com.ten31f.mission.entities.MTMLogo;
import com.ten31f.mission.entities.Starfield;
import com.ten31f.mission.entities.Starfield.Animation;
import com.ten31f.mission.gfx.Colours;

public class IntroStage extends Stage {

	private static final int FADE_LIMIT = 200;

	private enum Phase {
		FADEINMPC, FADEOUTMPC, FADEINMTM, FADEOUTMTM;
	}

	private static final int TARGET_MPC_COLOR_2 = 555;
	private static final int TARGET_MPC_COLOR_3 = 500;
	private static final int TARGET_MPC_COLOR_4 = 530;

	private static final int TARGET_MTM_COLOR_2 = 3;
	private static final int TARGET_MTM_COLOR_3 = 531;
	private static final int TARGET_MTM_COLOR_4 = 500;

	private static final String[] VISABLE_ENTITIES = { EntityNames.STARFIELD, EntityNames.MPC_LOGO };

	private int fade = 0;
	private Phase currentPhase = null;
	private boolean complete;

	public IntroStage(PixelPanel panel, EntityCollection visibleEntityCollection, EntityCollection hiddenEntityCollection) {
		super(panel, visibleEntityCollection, hiddenEntityCollection);
	}

	@Override
	public boolean isComplete() {
		return complete;
	}

	@Override
	public void tick() {

		switch (getCurrentPhase()) {
		case FADEINMPC:
			fadeINMPC();
			break;
		case FADEINMTM:
			fadeINMTM();
			break;
		case FADEOUTMPC:
			fadeOUTMPC();
			break;
		case FADEOUTMTM:
			fadeOUTMTM();
			break;
		default:
			break;

		}
	}

	@Override
	public void establishPins() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wipePins() {
		// TODO Auto-generated method stub

	}

	private double getPercentage() {
		return (getFade() / ((double) FADE_LIMIT));
	}

	private void fadeINMPC() {

		if (getFade() <= FADE_LIMIT) {
			setFade(getFade() + 1);

			MPCLogo mpcLogo = (MPCLogo) getVisibleEntityCollection().getEntity(EntityNames.MPC_LOGO);

			double percentage = getPercentage();

			int color1 = -1;
			int color2 = dimColor(percentage, TARGET_MPC_COLOR_2);
			int color3 = dimColor(percentage, TARGET_MPC_COLOR_3);
			int color4 = dimColor(percentage, TARGET_MPC_COLOR_4);

			mpcLogo.setColor(Colours.get(color1, color2, color3, color4));
		} else {
			setCurrentPhase(Phase.FADEOUTMPC);
		}
	}

	private void fadeOUTMPC() {

		if (getFade() > 0) {
			setFade(getFade() - 2);

			MPCLogo mpcLogo = (MPCLogo) getVisibleEntityCollection().getEntity(EntityNames.MPC_LOGO);

			double percentage = getPercentage();

			int color1 = -1;
			int color2 = dimColor(percentage, TARGET_MPC_COLOR_2);
			int color3 = dimColor(percentage, TARGET_MPC_COLOR_3);
			int color4 = dimColor(percentage, TARGET_MPC_COLOR_4);

			mpcLogo.setColor(Colours.get(color1, color2, color3, color4));
		} else {
			hide(EntityNames.MPC_LOGO);
			reveal(EntityNames.MTM_LOGO);
			setCurrentPhase(Phase.FADEINMTM);
		}
	}

	private void fadeINMTM() {

		if (getFade() <= FADE_LIMIT) {
			setFade(getFade() + 1);

			MTMLogo mtmLogo = (MTMLogo) getVisibleEntityCollection().getEntity(EntityNames.MTM_LOGO);

			double percentage = getPercentage();

			int color1 = -1;
			int color2 = dimColor(percentage, TARGET_MTM_COLOR_2);
			int color3 = dimColor(percentage, TARGET_MTM_COLOR_3);
			int color4 = dimColor(percentage, TARGET_MTM_COLOR_4);

			mtmLogo.setColor(Colours.get(color1, color2, color3, color4));
		} else {
			setCurrentPhase(Phase.FADEOUTMTM);
		}
	}

	private void fadeOUTMTM() {

		if (getFade() > 0) {
			setFade(getFade() - 2);

			MTMLogo mtmLogo = (MTMLogo) getVisibleEntityCollection().getEntity(EntityNames.MTM_LOGO);

			double percentage = getPercentage();

			int color1 = -1;
			int color2 = dimColor(percentage, TARGET_MTM_COLOR_2);
			int color3 = dimColor(percentage, TARGET_MTM_COLOR_3);
			int color4 = dimColor(percentage, TARGET_MTM_COLOR_4);

			mtmLogo.setColor(Colours.get(color1, color2, color3, color4));
		} else {
			setComplete(true);
		}
	}

	private int dimColor(double percentage, int targetColor) {
		int rr = (int) ((targetColor / 100) * percentage);
		int gg = (int) (((targetColor % 100) / 10) * percentage);
		int bb = (int) ((targetColor % 10) * percentage);

		rr = (rr > 216) ? 216 : rr;
		gg = (gg > 216) ? 216 : gg;
		bb = (bb > 216) ? 216 : bb;

		return (rr * 100) + (gg * 10) + bb;
	}

	@Override
	public void init() {
		getPanel().setMainLoop(SoundEffect.MAINLOOP.play());
		pack(VISABLE_ENTITIES);
		setComplete(false);
		setCurrentPhase(Phase.FADEINMPC);

		Starfield starfield = (Starfield) getVisibleEntityCollection().getEntity(EntityNames.STARFIELD);
		starfield.generateStars();
		starfield.setAnimation(Animation.TWINKLE);
	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub

	}

	private int getFade() {
		return fade;
	}

	private void setFade(int fade) {
		this.fade = fade;
	}

	private Phase getCurrentPhase() {
		return currentPhase;
	}

	private void setCurrentPhase(Phase currentPhase) {
		this.currentPhase = currentPhase;
	}

	private void setComplete(boolean complete) {
		this.complete = complete;
	}
}
