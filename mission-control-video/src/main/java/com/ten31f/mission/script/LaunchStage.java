package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import com.ten31f.mission.PixelPanel;
import com.ten31f.mission.entities.Entity;
import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Illuminated;
import com.ten31f.mission.entities.Illuminated.LEDState;

public class LaunchStage extends Stage {

	private static final String INSTRUCTIONS = "Mission is GO!";

	public LaunchStage(PixelPanel panel, EntityCollection visibleEntityCollection, EntityCollection hiddenEntityCollection) {
		super(panel, visibleEntityCollection, hiddenEntityCollection);
	}

	@Override
	public boolean isComplete() {
		return LEDState.HIGH.equals(
				((Illuminated) getVisibleEntityCollection().getEntity(EntityNames.LAUNCH_BUTTON)).getLedState());
	}

	@Override
	public void tick() {

	}

	@Override
	public void init() {
		pack(SecurityStage.VISABLE_ENTITIES);
		int x = (int) (getPanel().getXCenter() + (getPanel().getWidth() / 4d * 1.5));
		getProfessor().moveToXY(x, getProfessor().getY());

		getProfessor().setDialog(INSTRUCTIONS);

		((Illuminated) getVisibleEntityCollection().getEntity(EntityNames.LAUNCH_BUTTON)).setLedState(LEDState.PROMPT);
	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

		Entity entity = getVisibleEntityCollection().getEntity(EntityNames.LAUNCH_BUTTON);

		if (entity.withIN(mouseEvent.getX(), mouseEvent.getY())) {
			((Illuminated) entity).toggle();
		}

	}

	@Override
	public void establishPins() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wipePins() {
		// TODO Auto-generated method stub

	}

}
