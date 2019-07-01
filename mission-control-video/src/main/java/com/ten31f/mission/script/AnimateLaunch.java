package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import com.ten31f.mission.Panel;

public class AnimateLaunch extends Stage {

	private boolean complete = false;

	public AnimateLaunch(Panel panel) {
		super(panel);

	}

	@Override
	public boolean isComplete() {
		return complete;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {
		

	}

	private void setComplete(boolean complete) {
		this.complete = complete;
	}

}
