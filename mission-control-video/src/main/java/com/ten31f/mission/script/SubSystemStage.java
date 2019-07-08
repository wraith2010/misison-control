package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import com.ten31f.mission.Panel;
import com.ten31f.mission.entities.Button;
import com.ten31f.mission.entities.Entity;
import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Illuminated;
import com.ten31f.mission.entities.Illuminated.LEDState;

public class SubSystemStage extends Stage {

	private static final String INSTRUCTIONS = "Now we need to boot the various systems on the Ship";

	private static final String[] BUTTON_KEYS = { EntityNames.BUTTON_SUBSYSTEM_01, EntityNames.BUTTON_SUBSYSTEM_02,
			EntityNames.BUTTON_SUBSYSTEM_03, EntityNames.BUTTON_SUBSYSTEM_04, EntityNames.BUTTON_SUBSYSTEM_05,
			EntityNames.BUTTON_SUBSYSTEM_06 };

	public SubSystemStage(Panel panel, EntityCollection visibleEntityCollection,
			EntityCollection hiddenEntityCollection) {
		super(panel, visibleEntityCollection, hiddenEntityCollection);
	}

	@Override
	public boolean isComplete() {

		for (String key : BUTTON_KEYS) {
			if (!LEDState.HIGH.equals(((Button) getVisibleEntityCollection().getEntity(key)).getLedState())) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void tick() {

	}

	@Override
	public void init() {
		pack(SecurityStage.VISABLE_ENTITIES);

		int x = (int) (getPanel().getXCenter() - (getPanel().getWidth() / 4d * 0.5));
		getProfessor().moveToXY(x, getProfessor().getY());
		getProfessor().setDialog(INSTRUCTIONS);

		promptNextButton();
	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {
		for (String key : BUTTON_KEYS) {
			Entity entity = getVisibleEntityCollection().getEntity(key);
			if (entity.withIN(mouseEvent.getX(), mouseEvent.getY())) {
				((Illuminated) entity).toggle();
				promptNextButton();
			}
		}
	}

	private void promptNextButton() {

		for (String key : BUTTON_KEYS) {
			Button button = (Button) getVisibleEntityCollection().getEntity(key);
			if (!LEDState.HIGH.equals(button.getLedState())) {
				button.setLedState(LEDState.PROMPT);
				return;
			}
		}

	}
}
