package com.ten31f.mission.script;

import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import com.ten31f.mission.Panel;
import com.ten31f.mission.entities.Button;
import com.ten31f.mission.entities.Illuminated.LEDState;

public class SubSystemStage extends Stage {

	private static final String INSTRUCTIONS = "Now we need to boot the various systems on the Ship";

	public static final String BUTTON_SUBSYSTEM_01 = "SECUIRITY01";
	public static final String BUTTON_SUBSYSTEM_02 = "SECUIRITY02";
	public static final String BUTTON_SUBSYSTEM_03 = "SECUIRITY03";
	public static final String BUTTON_SUBSYSTEM_04 = "SECUIRITY04";
	public static final String BUTTON_SUBSYSTEM_05 = "SECUIRITY05";
	public static final String BUTTON_SUBSYSTEM_06 = "SECUIRITY06";

	private static final String[] BUTTON_KEYS = { BUTTON_SUBSYSTEM_01, BUTTON_SUBSYSTEM_02, BUTTON_SUBSYSTEM_03,
			BUTTON_SUBSYSTEM_04, BUTTON_SUBSYSTEM_05, BUTTON_SUBSYSTEM_06 };

	private int buttonIndex = 0;

	public SubSystemStage(Panel panel) {
		super(panel);
	}

	@Override
	public boolean isComplete() {
		for (Button button : getButtons().values()) {
			if (LEDState.LOW.equals(button.getLedState()))
				return false;
		}

		return true;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		int x = (int) (getPanel().getXCenter() - (getPanel().getWidth() / 4d * 0.5));
		int y = getPanel().getYCenter() - 300;

		getProfessor().moveToXY(x, y);

		getProfessor().setDialog(INSTRUCTIONS);

	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

		for (Entry<String, Button> entry : getButtons().entrySet()) {
			if (entry.getValue().withIN(mouseEvent.getX(), mouseEvent.getY())) {

				entry.getValue().toggle();
			}
		}
	}

	private int getButtonIndex() {
		return buttonIndex;
	}

	private void setButtonIndex(int buttonIndex) {
		this.buttonIndex = buttonIndex;
	}

}
