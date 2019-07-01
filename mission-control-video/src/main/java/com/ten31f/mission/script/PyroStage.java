package com.ten31f.mission.script;

import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import com.ten31f.mission.Panel;
import com.ten31f.mission.entities.Button;
import com.ten31f.mission.entities.Button.ButtonState;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Illuminated.LEDState;

public class PyroStage extends Stage {

	public static final String NAME = "Pyrotechics Stage";

	private static final String INSTRUCTIONS = "Its time to initiate the boosters";

	private static final String[] BUTTON_KEYS = { EntityNames.TOGGLE_PYRO_01, EntityNames.BUTTON_PYRO_01,
			EntityNames.TOGGLE_PYRO_02, EntityNames.BUTTON_PYRO_02, EntityNames.TOGGLE_PYRO_03,
			EntityNames.BUTTON_PYRO_03 };

	public PyroStage(Panel panel) {
		super(panel);
	}

	@Override
	public boolean isComplete() {
		for (Button button : getButtons().values()) {
			if (!LEDState.HIGH.equals(button.getLedState())) {
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

		for (Entry<String, Button> entry : getButtons().entrySet()) {
			if (entry.getValue().withIN(mouseEvent.getX(), mouseEvent.getY())) {

				switch (entry.getKey()) {
				case EntityNames.BUTTON_PYRO_01:
					if (isDepressed(EntityNames.TOGGLE_PYRO_01))
						entry.getValue().toggle();
					break;
				case EntityNames.BUTTON_PYRO_02:
					if (isDepressed(EntityNames.TOGGLE_PYRO_02))
						entry.getValue().toggle();
					break;
				case EntityNames.BUTTON_PYRO_03:
					if (isDepressed(EntityNames.TOGGLE_PYRO_03))
						entry.getValue().toggle();
					break;
				default:
				}

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

	private boolean isDepressed(String key) {
		return ButtonState.DEPRESSED.equals(getButtons().get(key).getButtonState());
	}
}
