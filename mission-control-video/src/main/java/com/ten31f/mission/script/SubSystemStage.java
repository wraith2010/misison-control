package com.ten31f.mission.script;

import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import com.ten31f.mission.Panel;
import com.ten31f.mission.entities.Button;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Illuminated.LEDState;

public class SubSystemStage extends Stage {

	private static final String INSTRUCTIONS = "Now we need to boot the various systems on the Ship";

	private static final String[] BUTTON_KEYS = { EntityNames.BUTTON_SUBSYSTEM_01, EntityNames.BUTTON_SUBSYSTEM_02,
			EntityNames.BUTTON_SUBSYSTEM_03, EntityNames.BUTTON_SUBSYSTEM_04, EntityNames.BUTTON_SUBSYSTEM_05,
			EntityNames.BUTTON_SUBSYSTEM_06 };

	private int buttonIndex = 0;

	public SubSystemStage(Panel panel) {
		super(panel);
	}

	@Override
	public boolean isComplete() {
		for (Button button : getButtons().values()) {
			if (!LEDState.HIGH.equals(button.getLedState()))
				return false;
		}

		return true;
	}

	@Override
	public void tick() {

	}

	@Override
	public void init() {
		int x = (int) (getPanel().getXCenter() - (getPanel().getWidth() / 4d * 0.5));
		int y = getPanel().getYCenter() - 300;

		getProfessor().moveToXY(x, y);
		getProfessor().setDialog(INSTRUCTIONS);

		promptNextButton();
	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

		for (Entry<String, Button> entry : getButtons().entrySet()) {
			if (entry.getValue().withIN(mouseEvent.getX(), mouseEvent.getY())) {
				entry.getValue().toggle();
				promptNextButton();
			}
		}
	}

	private void promptNextButton() {
		for (int x = 0; x < BUTTON_KEYS.length; x++) {
			Button button = getButtons().get(BUTTON_KEYS[x]);
			if (!LEDState.HIGH.equals(button.getLedState())) {
				button.setLedState(LEDState.PROMPT);
				return;
			}
		}
	}
}
