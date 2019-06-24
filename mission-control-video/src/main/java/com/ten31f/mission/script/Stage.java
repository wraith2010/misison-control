package com.ten31f.mission.script;

import java.util.HashMap;
import java.util.Map;

import com.ten31f.mission.entities.Button;

public abstract class Stage {

	private String name;
	private Stage nextStage;

	private Map<String, Button> buttons = null;

	public Stage(String name) {
		setButtons(new HashMap<String, Button>());
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected Map<String, Button> getButtons() {
		return buttons;
	}

	private void setButtons(Map<String, Button> buttons) {
		this.buttons = buttons;
	}

	public void addButton(String name, Button button) {
		getButtons().put(name, button);
	}

	public Stage getNextStage() {
		return nextStage;
	}

	public void setNextStage(Stage nextStage) {
		this.nextStage = nextStage;
	}

	abstract public boolean isComplete();

	abstract public void tick();

}
