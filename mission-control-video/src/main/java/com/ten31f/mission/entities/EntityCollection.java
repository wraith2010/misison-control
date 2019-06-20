package com.ten31f.mission.entities;

import java.util.ArrayList;
import java.util.List;

import com.ten31f.mission.gfx.Screen;

public class EntityCollection {

	private List<Entity> entities = null;

	public EntityCollection() {
		setEntities(new ArrayList<>());
	}

	public void renderEntities(Screen screen) {

		getEntities().forEach(entity -> entity.render(screen));

	}

	public void tick() {

		getEntities().forEach(entity -> entity.tick());

	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public List<Entity> getEntities() {
		return entities;
	}

}
