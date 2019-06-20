package com.ten31f.mission.entities;

import com.ten31f.mission.gfx.Screen;

public abstract class Entity {

	private int x, y;
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

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public abstract void tick();

	public abstract void render(Screen screen);
}
