package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import com.ten31f.mission.Panel;
import com.ten31f.mission.entities.Illuminated.LEDState;

public class LaunchStage extends Stage {

	public static final String BUTTON = "LAUNCH";

	private static final String INSTRUCTIONS = "Mission is GO!";

	public LaunchStage(Panel panel) {
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
		int x = (int) (getPanel().getXCenter() + (getPanel().getWidth() / 4d * 1.5));
		int y = getPanel().getYCenter() - 300;

		getProfessor().moveToXY(x, y);

		getProfessor().setDialog(INSTRUCTIONS);
		getButtons().get(BUTTON).setLedState(LEDState.PROMPT);
	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

	}
}
