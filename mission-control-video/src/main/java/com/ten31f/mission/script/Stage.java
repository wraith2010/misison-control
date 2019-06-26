package com.ten31f.mission.script;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.ten31f.mission.Panel;
import com.ten31f.mission.entities.Button;
import com.ten31f.mission.entities.Professor;

public abstract class Stage {

	private Stage nextStage;
	private Professor professor = null;
	private Panel panel = null;

	private Map<String, Button> buttons = null;

	protected static Random RANDOM = new Random(System.nanoTime());

	public Stage(Panel panel) {
		setButtons(new HashMap<String, Button>());
		setPanel(panel);
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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	protected void setPanel(Panel panel) {
		this.panel = panel;
	}

	protected Panel getPanel() {
		return panel;
	}

	abstract public boolean isComplete();

	abstract public void tick();

	abstract public void init();
}
