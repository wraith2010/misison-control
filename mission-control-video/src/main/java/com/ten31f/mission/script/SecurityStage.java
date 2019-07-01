package com.ten31f.mission.script;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.ten31f.mission.Panel;
import com.ten31f.mission.entities.Button;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Illuminated.LEDState;

public class SecurityStage extends Stage {

	private enum Phase {
		ADDBUTTON, DISPLAYSEQUENCE, LISTEN, RESULT, CELEBRATE;
	}

	private static final String[] BUTTON_KEYS = { EntityNames.BUTTON_SECURITY_01, EntityNames.BUTTON_SECURITY_02,
			EntityNames.BUTTON_SECURITY_03, EntityNames.BUTTON_SECURITY_04, EntityNames.BUTTON_SECURITY_05,
			EntityNames.BUTTON_SECURITY_06, EntityNames.BUTTON_SECURITY_07, EntityNames.BUTTON_SECURITY_08,
			EntityNames.BUTTON_SECURITY_09 };

	private static final String INSTRUCTIONS = "Repeat the security sequences to unlock the console";

	private int initialSequenceLenght = 2;
	private int maxSequenceLenght = 6;
	private int squenceIndex = -1;

	private List<String> sequence = null;

	private boolean complete = false;

	private Phase currentPhase = null;

	public SecurityStage(Panel panel) {
		super(panel);
	}

	@Override
	public boolean isComplete() {
		return complete;
	}

	private int pause = 0;
	// private int tickCount = 0;

	@Override
	public void tick() {

		if (!getProfessor().isIdle() || getProfessor().isSpeaking())
			return;

		// tickCount++;

		switch (getCurrentPhase()) {
		case ADDBUTTON:
			addNextButton();
			break;
		case DISPLAYSEQUENCE:
			displaySequence();
			break;
		case LISTEN:
			listen();
			break;
		case RESULT:
			result();
			break;
		case CELEBRATE:
			celebrate();
			break;
		default:
			setCurrentPhase(Phase.ADDBUTTON);
			break;

		}
	}

	private void addNextButton() {
		do {

			String lastSelection = null;
			String nextSelection = null;

			if (!getSequence().isEmpty())
				lastSelection = getSequence().get(getSequence().size() - 1);

			do {
				nextSelection = BUTTON_KEYS[RANDOM.nextInt(getButtons().entrySet().size())];
			} while (nextSelection.equals(lastSelection));

			getSequence().add(nextSelection);
		} while (getSequence().size() < initialSequenceLenght);

		setCurrentPhase((getSequence().size() > maxSequenceLenght) ? Phase.CELEBRATE : Phase.DISPLAYSEQUENCE);
	}

	private void displaySequence() {
		if (getPause() != 0) {
			setPause(getPause() - 1);
			return;
		}

		setSquenceIndex(getSquenceIndex() + 1);
		if (getSquenceIndex() >= getSequence().size()) {
			setCurrentPhase(Phase.LISTEN);
			allButtonsLEDOFF();
			setSquenceIndex(0);
			return;
		}

		allButtonsLEDOFF();
		getButtons().get(getSequence().get(getSquenceIndex())).setLedState(LEDState.HIGH);

		setPause(60);

	}

	private void listen() {

		if (getSquenceIndex() >= getSequence().size()) {
			setCurrentPhase(Phase.RESULT);
			setPause(30);
			setSquenceIndex(-1);

		}

	}

	private void allButtonsLEDOFF() {
		getButtons().entrySet().forEach(entry -> entry.getValue().setLedState(LEDState.LOW));
	}

	private void result() {
		getProfessor().setDialog("Good Job! Next Sequence");
		setCurrentPhase(Phase.ADDBUTTON);
	}

	private void celebrate() {
		// TODO: professor should celebrate and animate buttons than move on
		setComplete(true);
	}

	@Override
	public void init() {
		setComplete(false);

		int x = (int) (getPanel().getXCenter() - (getPanel().getWidth() / 4d * 1.5));
		int y = getPanel().getYCenter() - 300;

		getProfessor().moveToXY(x, y);

		getProfessor().setDialog(INSTRUCTIONS);

		setSequence(new ArrayList<>());
		setCurrentPhase(Phase.ADDBUTTON);
	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

		if (!Phase.LISTEN.equals(getCurrentPhase()))
			return;

		for (Entry<String, Button> entry : getButtons().entrySet()) {
			if (entry.getValue().withIN(mouseEvent.getX(), mouseEvent.getY())) {
				if (getSequence().get(getSquenceIndex()).equals(entry.getKey())) {
					getProfessor().setDialog("Correct");

					setSquenceIndex(getSquenceIndex() + 1);
				} else {
					getProfessor().setDialog("Watch Again");
					setSquenceIndex(-1);
					setCurrentPhase(Phase.DISPLAYSEQUENCE);
				}
			}
		}

	}

	private void setSequence(List<String> sequence) {
		this.sequence = sequence;
	}

	private List<String> getSequence() {
		return sequence;
	}

	private void setComplete(boolean complete) {
		this.complete = complete;
	}

	private void setCurrentPhase(Phase currentPhase) {
		this.currentPhase = currentPhase;
	}

	private Phase getCurrentPhase() {
		return currentPhase;
	}

	private int getSquenceIndex() {
		return squenceIndex;
	}

	private void setSquenceIndex(int squenceIndex) {
		this.squenceIndex = squenceIndex;
	}

	private int getPause() {
		return pause;
	}

	private void setPause(int pause) {
		this.pause = pause;
	}

}
