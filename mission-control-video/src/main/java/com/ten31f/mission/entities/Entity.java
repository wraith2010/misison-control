package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Screen;

public abstract class Entity {

	public int x, y;
	private EntityCollection entityCollection;

	public Entity(EntityCollection entityCollection) {
		init(entityCollection);
	}

	public final void init(EntityCollection entityCollection) {
		this.entityCollection = entityCollection;
	}

	public EntityCollection getEntityCollection() {
		return entityCollection;
	}

	public void setEntityCollection(EntityCollection entityCollection) {
		this.entityCollection = entityCollection;
	}

	public abstract void tick();

	public abstract void render(Screen screen);
}
