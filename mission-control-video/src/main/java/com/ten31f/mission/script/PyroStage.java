package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import com.ten31f.mission.Panel;

public class PyroStage extends Stage {

	public static final String NAME = "Pyrotechics Stage";

	private static final String INSTRUCTIONS = "Its time to initiate the boosters";

	public PyroStage(Panel panel) {
		super(panel);
	}

	@Override
	public boolean isComplete() {

		return false;
	}

	@Override
	public void tick() {

	}

	@Override
	public void init() {
		int x = (int) (getPanel().getXCenter() + (getPanel().getWidth() / 4d * 0.5));
		int y = getPanel().getYCenter() - 300;

		getProfessor().moveToXY(x, y);

		getProfessor().setDialog(INSTRUCTIONS);

	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

	}

}
