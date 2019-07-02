package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import com.ten31f.mission.Panel;
import com.ten31f.mission.entities.EntityCollection;

public class AnimateLaunch extends Stage {

	private boolean complete = false;

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

	}

	@Override
	public void init() {

	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

	}

	private void setComplete(boolean complete) {
		this.complete = complete;
	}

}
