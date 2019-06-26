package com.ten31f.mission.script;

import com.ten31f.mission.Panel;

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

	public SubSystemStage(Panel panel) {
		super(panel);
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		int x = (int) (getPanel().getXCenter() - (getPanel().getWidth() / 4 * 0.5));
		int y = getPanel().getYCenter() - 300;

		getProfessor().moveToXY(x, y);

		getProfessor().setDialog(INSTRUCTIONS);

	}

}
