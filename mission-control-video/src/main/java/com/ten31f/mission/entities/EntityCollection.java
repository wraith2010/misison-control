package com.ten31f.mission.entities;

import java.util.HashMap;
import java.util.Map;

import com.ten31f.mission.gfx.Screen;

public class EntityCollection {

	private Map<String, Entity> entities = null;

	public EntityCollection() {
		setEntities(new HashMap<>());
	}

	public void renderEntities(Screen screen) {
		getEntities().values().forEach(entity -> entity.render(screen));
	}

	public void tick() {
		getEntities().values().forEach(Entity::tick);
	}

	private Map<String, Entity> getEntities() {
		return entities;
	}

	private void setEntities(Map<String, Entity> entities) {
		this.entities = entities;
	}

	public void addEntity(String key, Entity entity) {
		getEntities().put(key, entity);
	}

	public Entity removeEntity(String key) {
		return getEntities().remove(key);
	}

	public Entity getEntity(String key) {
		return getEntities().get(key);
	}

	public void removeAllEntites() {

		getEntities().clear();

	}

}
