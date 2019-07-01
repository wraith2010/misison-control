package com.ten31f.mission.script;

import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import com.ten31f.mission.Panel;
import com.ten31f.mission.entities.Button;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Illuminated.LEDState;

public class LaunchStage extends Stage {

	private static final String INSTRUCTIONS = "Mission is GO!";

	public LaunchStage(Panel panel) {
		super(panel);
	}

	@Override
	public boolean isComplete() {

		return LEDState.HIGH.equals(getButtons().get(EntityNames.LAUNCH_BUTTON).getLedState());
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
		getButtons().get(EntityNames.LAUNCH_BUTTON).setLedState(LEDState.PROMPT);
	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {
		for (Entry<String, Button> entry : getButtons().entrySet()) {
			if (entry.getValue().withIN(mouseEvent.getX(), mouseEvent.getY())) {
				entry.getValue().toggle();
			}
		}
	}
}
