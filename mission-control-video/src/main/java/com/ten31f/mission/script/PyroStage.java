package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import com.ten31f.mission.Panel;
import com.ten31f.mission.entities.Button;
import com.ten31f.mission.entities.Button.ButtonState;
import com.ten31f.mission.entities.Entity;
import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Illuminated;
import com.ten31f.mission.entities.Illuminated.LEDState;

public class PyroStage extends Stage {

	public static final String NAME = "Pyrotechics Stage";

	private static final String INSTRUCTIONS = "Its time to initiate the boosters";

	private static final String[] BUTTON_KEYS = { EntityNames.TOGGLE_PYRO_01, EntityNames.BUTTON_PYRO_01,
			EntityNames.TOGGLE_PYRO_02, EntityNames.BUTTON_PYRO_02, EntityNames.TOGGLE_PYRO_03,
			EntityNames.BUTTON_PYRO_03 };

	public PyroStage(Panel panel, EntityCollection visibleEntityCollection, EntityCollection hiddenEntityCollection) {
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
		int x = (int) (getPanel().getXCenter() + (getPanel().getWidth() / 4d * 0.5));
		int y = getPanel().getYCenter() - 300;

		getProfessor().moveToXY(x, y);

		getProfessor().setDialog(INSTRUCTIONS);
		promptNextButton();
	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

		for (String key : BUTTON_KEYS) {

			Entity entity = getVisibleEntityCollection().getEntity(key);

			if (entity.withIN(mouseEvent.getX(), mouseEvent.getY())) {

				switch (key) {
				case EntityNames.BUTTON_PYRO_01:
					if (isDepressed(EntityNames.TOGGLE_PYRO_01))
						((Illuminated) entity).toggle();
					break;
				case EntityNames.BUTTON_PYRO_02:
					if (isDepressed(EntityNames.TOGGLE_PYRO_02))
						((Illuminated) entity).toggle();
					break;
				case EntityNames.BUTTON_PYRO_03:
					if (isDepressed(EntityNames.TOGGLE_PYRO_03))
						((Illuminated) entity).toggle();
					break;
				default:
				}

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

	private boolean isDepressed(String key) {
		return ButtonState.DEPRESSED.equals(((Button) getVisibleEntityCollection().getEntity(key)).getButtonState());
	}
}
