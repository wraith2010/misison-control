package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;

import com.ten31f.mission.Panel;
import com.ten31f.mission.audio.SoundEffect;
import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Professor;
import com.ten31f.mission.entities.Starfield;
import com.ten31f.mission.entities.Starfield.Animation;

public class AnimateLaunch extends Stage {

	private static final String[] VISABLE_ENTITIES = { EntityNames.STARFIELD, EntityNames.ROCKET,
			EntityNames.PROFESSOR };

	private int countDown = 10;

	private int tickCount = 0;

	private Clip music = null;

	public AnimateLaunch(Panel panel, EntityCollection visibleEntityCollection,
			EntityCollection hiddenEntityCollection) {
		super(panel, visibleEntityCollection, hiddenEntityCollection);
	}

	@Override
	public boolean isComplete() {
		if (getMusic() == null)
			return false;

		if (getMusic().isRunning())
			return false;

		setMusic(null);

		return true;
	}

	@Override
	public void tick() {

		Professor professor = (Professor) getVisibleEntityCollection().getEntity(EntityNames.PROFESSOR);

		setTickCount(getTickCount() + 1);

		if (getTickCount() % 60 == 0 && getCountDown() > 0 && professor.isIdle()) {
			setCountDown(getCountDown() - 1);
			professor.setDialog(Integer.toString(getCountDown()));
		} else if (getCountDown() <= 0) {
			Starfield starfield = (Starfield) getVisibleEntityCollection().getEntity(EntityNames.STARFIELD);
			starfield.setAnimation(Animation.FLY);
			getVisibleEntityCollection().addEntity(EntityNames.FLAME,
					getHiddenEntityCollection().getEntity(EntityNames.FLAME));
			getVisibleEntityCollection().removeEntity(EntityNames.PROFESSOR);
		}

	}

	@Override
	public void init() {
		pack(VISABLE_ENTITIES);
		setTickCount(0);
		setCountDown(10);
		setMusic(SoundEffect.MOONTHEME.play());
	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

	}

	private int getTickCount() {
		return tickCount;
	}

	private void setTickCount(int tickCount) {
		this.tickCount = tickCount;
	}

	private void setCountDown(int countDown) {
		this.countDown = countDown;
	}

	private int getCountDown() {
		return countDown;
	}

	private void setMusic(Clip music) {
		this.music = music;
	}

	private Clip getMusic() {
		return music;
	}

}
