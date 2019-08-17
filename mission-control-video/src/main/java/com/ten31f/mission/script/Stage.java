package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import com.ten31f.mission.PixelPanel;
import com.ten31f.mission.entities.Entity;
import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Professor;

public abstract class Stage {

	private Stage nextStage;
	private PixelPanel panel = null;

	private EntityCollection visibleEntityCollection = null;
	private EntityCollection hiddenEntityCollection = null;

	public Stage(PixelPanel panel, EntityCollection visibleEntityCollection, EntityCollection hiddenEntityCollection) {
		setPanel(panel);
		setVisibleEntityCollection(visibleEntityCollection);
		setHiddenEntityCollection(hiddenEntityCollection);
	}

	public Stage getNextStage() {
		return nextStage;
	}

	public void setNextStage(Stage nextStage) {
		this.nextStage = nextStage;
	}

	protected Professor getProfessor() {
		return (Professor) getVisibleEntityCollection().getEntity(EntityNames.PROFESSOR);
	}

	protected void setPanel(PixelPanel panel) {
		this.panel = panel;
	}

	protected PixelPanel getPanel() {
		return panel;
	}

	protected EntityCollection getVisibleEntityCollection() {
		return visibleEntityCollection;
	}

	protected void setVisibleEntityCollection(EntityCollection visibleEntityCollection) {
		this.visibleEntityCollection = visibleEntityCollection;
	}

	protected EntityCollection getHiddenEntityCollection() {
		return hiddenEntityCollection;
	}

	protected void setHiddenEntityCollection(EntityCollection hiddenEntityCollection) {
		this.hiddenEntityCollection = hiddenEntityCollection;
	}

	protected void pack(String[] visiableEntities) {
		getVisibleEntityCollection().removeAllEntites();

		for (String key : visiableEntities) {
			Entity entity = getHiddenEntityCollection().getEntity(key);
			getVisibleEntityCollection().addEntity(key, entity);
		}
	}

	protected void hide(String entityName) {
		getVisibleEntityCollection().removeEntity(entityName);
	}

	protected void reveal(String entityName) {
		getVisibleEntityCollection().addEntity(entityName, getHiddenEntityCollection().getEntity(entityName));
	}

	abstract public boolean isComplete();

	abstract public void tick();

	abstract public void init();

	abstract public void mouseClick(MouseEvent mouseEvent);

	abstract public void establishPins();

	abstract public void wipePins();

}
