package com.ten31f.mission.script;

import com.ten31f.mission.Panel;

public class SecurityStage extends Stage {

	public static final String NAME = "Security Stage";

	public static final String BUTTON_SECURITY_01 = "SECUIRITY01";
	public static final String BUTTON_SECURITY_02 = "SECUIRITY02";
	public static final String BUTTON_SECURITY_03 = "SECUIRITY03";
	public static final String BUTTON_SECURITY_04 = "SECUIRITY04";
	public static final String BUTTON_SECURITY_05 = "SECUIRITY05";
	public static final String BUTTON_SECURITY_06 = "SECUIRITY06";
	public static final String BUTTON_SECURITY_07 = "SECUIRITY07";
	public static final String BUTTON_SECURITY_08 = "SECUIRITY08";
	public static final String BUTTON_SECURITY_09 = "SECUIRITY09";

	private static final String INSTRUCTION_SECURITY = "Repeat the secuirty sequences to unlock the console";

	public SecurityStage(Panel panel) {
		super(NAME, panel);
	}

	@Override
	public boolean isComplete() {

		return false;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		System.out.println("Init Security Stage");
		int x = (int) (getPanel().getXCenter() - (getPanel().getWidth() / 4 * 1.5));
		int y = getPanel().getYCenter() - 300;

		getProfessor().moveToXY(x, y);

		getProfessor().setDialog(INSTRUCTION_SECURITY);
	}

}
