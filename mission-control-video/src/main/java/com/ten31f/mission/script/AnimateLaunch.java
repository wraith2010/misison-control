package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import com.ten31f.mission.Panel;
import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Starfield;
import com.ten31f.mission.entities.Starfield.Animation;

public class AnimateLaunch extends Stage {

	private static final String[] VISABLE_ENTITIES = { EntityNames.STARFIELD, EntityNames.ROCKET };

	private boolean complete = false;
	private int tickCount = 0;

	public AnimateLaunch(Panel panel, EntityCollection visibleEntityCollection,
			EntityCollection hiddenEntityCollection) {
		super(panel, visibleEntityCollection, hiddenEntityCollection);
	}

	@Override
	public boolean isComplete() {
		return complete;
	}

	@Override
	public void tick() {
		setTickCount(getTickCount() + 1);
		if (getTickCount() > 30) {
			Starfield starfield = (Starfield) getVisibleEntityCollection().getEntity(EntityNames.STARFIELD);
			starfield.setAnimation(Animation.FLY);
			getVisibleEntityCollection().addEntity(EntityNames.FLAME,
					getHiddenEntityCollection().getEntity(EntityNames.FLAME));
		} else if (getTickCount() > 180) {
			setComplete(true);
		}
	}

	@Override
	public void init() {
		pack(VISABLE_ENTITIES);
		setComplete(false);
		setTickCount(0);
	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

	}

	private void setComplete(boolean complete) {
		this.complete = complete;
	}

	private int getTickCount() {
		return tickCount;
	}

	private void setTickCount(int tickCount) {
		this.tickCount = tickCount;
	}

}
