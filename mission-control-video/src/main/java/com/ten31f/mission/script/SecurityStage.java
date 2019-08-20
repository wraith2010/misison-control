package com.ten31f.mission.script;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.ten31f.mission.PixelPanel;
import com.ten31f.mission.entities.Button.ButtonState;
import com.ten31f.mission.entities.Entity;
import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Illuminated;
import com.ten31f.mission.entities.Illuminated.LEDState;
import com.ten31f.mission.entities.Toggle;
import com.ten31f.mission.pin.IPINController;

public class SecurityStage extends Stage implements GpioPinListenerDigital {

	private enum Phase {
		ADDBUTTON, DISPLAYSEQUENCE, LISTEN, RESULT, CELEBRATE;
	}

	public static final String[] VISABLE_ENTITIES = { EntityNames.STARFIELD, EntityNames.PROFESSOR,
			EntityNames.BUTTON_SECURITY_01, EntityNames.BUTTON_SECURITY_02, EntityNames.BUTTON_SECURITY_03,
			EntityNames.BUTTON_SECURITY_04, EntityNames.BUTTON_SECURITY_05, EntityNames.BUTTON_SECURITY_06,
			EntityNames.BUTTON_SECURITY_07, EntityNames.BUTTON_SECURITY_08, EntityNames.BUTTON_SECURITY_09,
			EntityNames.BUTTON_SUBSYSTEM_01, EntityNames.BUTTON_SUBSYSTEM_02, EntityNames.BUTTON_SUBSYSTEM_03,
			EntityNames.BUTTON_SUBSYSTEM_04, EntityNames.BUTTON_SUBSYSTEM_05, EntityNames.BUTTON_SUBSYSTEM_06,
			EntityNames.TOGGLE_PYRO_01, EntityNames.BUTTON_PYRO_01, EntityNames.TOGGLE_PYRO_02,
			EntityNames.BUTTON_PYRO_02, EntityNames.TOGGLE_PYRO_03, EntityNames.BUTTON_PYRO_03,
			EntityNames.LAUNCH_BUTTON, EntityNames.SUB_PANEL_01, EntityNames.SUB_PANEL_02, EntityNames.SUB_PANEL_03,
			EntityNames.SUB_PANEL_04 };

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

	private Random random = new Random(System.nanoTime());

	public SecurityStage(PixelPanel panel, EntityCollection visibleEntityCollection,
			EntityCollection hiddenEntityCollection) {
		super(panel, visibleEntityCollection, hiddenEntityCollection);
	}

	@Override
	public boolean isComplete() {
		return complete;
	}

	private int pause = 0;

	@Override
	public void tick() {

		if (!getProfessor().isIdle() || getProfessor().isSpeaking())
			return;

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
			String nextSelection = null;
			do {
				nextSelection = BUTTON_KEYS[random.nextInt(BUTTON_KEYS.length)];
			} while (getSequence().contains(nextSelection));

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
		Illuminated illuminated = (Illuminated) getVisibleEntityCollection()
				.getEntity(getSequence().get(getSquenceIndex()));
		illuminated.setLedState(LEDState.HIGH);

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
		for (String key : BUTTON_KEYS) {
			((Illuminated) getVisibleEntityCollection().getEntity(key)).setLedState(LEDState.LOW);
		}
	}

	private void result() {
		if (getSequence().size() < maxSequenceLenght) {
			getProfessor().setDialog("Good Job! Next Sequence");
		}
		setCurrentPhase(Phase.ADDBUTTON);
	}

	private void celebrate() {
		setComplete(true);
	}

	@Override
	public void init() {

		pack(VISABLE_ENTITIES);
		setComplete(false);
		reset();

		getProfessor().setX((int) (getPanel().getXCenter() - (getPanel().getWidth() / 4d * 1.0)));

		int x = (int) (getPanel().getXCenter() - (getPanel().getWidth() / 4d * 1.5));
		getProfessor().moveToXY(x, getProfessor().getY());

		getProfessor().setDialog(INSTRUCTIONS);

		setSequence(new ArrayList<>());
		setCurrentPhase(Phase.ADDBUTTON);
	}

	private void reset() {
		for (String key : VISABLE_ENTITIES) {

			Entity entity = getHiddenEntityCollection().getEntity(key);

			if (entity instanceof Illuminated) {
				Illuminated illuminated = (Illuminated) entity;
				illuminated.setLedState(LEDState.LOW);
			}

			if (entity instanceof Toggle) {
				Toggle toggle = (Toggle) entity;
				toggle.setButtonState(ButtonState.NOTDEPRESSED);
			}
		}

	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

		if (!Phase.LISTEN.equals(getCurrentPhase()))
			return;

		for (String key : BUTTON_KEYS) {
			if (getVisibleEntityCollection().getEntity(key).withIN(mouseEvent.getX(), mouseEvent.getY())) {
				testInput(key);
			}
		}
	}

	private void testInput(String key) {

		if (getSequence().get(getSquenceIndex()).equals(key)) {
			getProfessor().setDialog("Correct");
			setSquenceIndex(getSquenceIndex() + 1);
		} else {
			getProfessor().setDialog("Watch Again");
			setSquenceIndex(-1);
			setCurrentPhase(Phase.DISPLAYSEQUENCE);
		}

	}

	@Override
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

		System.out.println(String.format(" --> GPIO PIN STATE CHANGE(%s): %s = %s", event.getPin().getPin(),
				event.getPin().getName(), event.getState()));

		if (!Phase.LISTEN.equals(getCurrentPhase()) || !PinState.LOW.equals(event.getState()))
			return;

		GpioPin gpioPin = event.getPin();

		switch (gpioPin.getName()) {

		case IPINController.PIN_IN_NAME_SIMON_GREEN01:
			testInput(EntityNames.BUTTON_SECURITY_04);
			break;
		case IPINController.PIN_IN_NAME_SIMON_GREEN02:
			testInput(EntityNames.BUTTON_SECURITY_06);
			break;
		case IPINController.PIN_IN_NAME_SIMON_WHITE:
			testInput(EntityNames.BUTTON_SECURITY_05);
			break;
		case IPINController.PIN_IN_NAME_SIMON_BLUE01:
			testInput(EntityNames.BUTTON_SECURITY_01);
			break;
		case IPINController.PIN_IN_NAME_SIMON_BLUE02:
			testInput(EntityNames.BUTTON_SECURITY_03);
			break;
		case IPINController.PIN_IN_NAME_SIMON_RED01:
			testInput(EntityNames.BUTTON_SECURITY_07);
			break;
		case IPINController.PIN_IN_NAME_SIMON_RED02:
			testInput(EntityNames.BUTTON_SECURITY_09);
			break;
		case IPINController.PIN_IN_NAME_SIMON_YELLOW01:
			testInput(EntityNames.BUTTON_SECURITY_02);
			break;
		case IPINController.PIN_IN_NAME_SIMON_YELLOW02:
			testInput(EntityNames.BUTTON_SECURITY_08);
			break;

		default:
		}

	}

	@Override
	public void establishPins() {

		IPINController pinController = getPanel().getPinController();

		pinController.addGpioPinListener(this);

		MCP23017GpioProvider mcp23017GpioProvider01 = getPanel().getPinController().getMco23017GpioProvider01();
		MCP23017GpioProvider mcp23017GpioProvider02 = getPanel().getPinController().getMco23017GpioProvider02();

		GpioPinDigitalOutput pinGreen01 = pinController.establishOuputPin(MCP23017Pin.GPIO_A0,
				IPINController.PIN_OUT_NAME_SIMON_GREEN01, mcp23017GpioProvider01);
		GpioPinDigitalOutput pinBlue01 = pinController.establishOuputPin(MCP23017Pin.GPIO_A5,
				IPINController.PIN_OUT_NAME_SIMON_BLUE01, mcp23017GpioProvider01);
		GpioPinDigitalOutput pinYellow01 = pinController.establishOuputPin(MCP23017Pin.GPIO_B0,
				IPINController.PIN_OUT_NAME_SIMON_YELLOW01, mcp23017GpioProvider01);
		GpioPinDigitalOutput pinGreen02 = pinController.establishOuputPin(MCP23017Pin.GPIO_B1,
				IPINController.PIN_OUT_NAME_SIMON_GREEN02, mcp23017GpioProvider01);
		GpioPinDigitalOutput pinWhite = pinController.establishOuputPin(MCP23017Pin.GPIO_B2,
				IPINController.PIN_OUT_NAME_SIMON_WHITE, mcp23017GpioProvider01);
		GpioPinDigitalOutput pinRed02 = pinController.establishOuputPin(MCP23017Pin.GPIO_B3,
				IPINController.PIN_OUT_NAME_SIMON_RED02, mcp23017GpioProvider01);
		GpioPinDigitalOutput pinRed01 = pinController.establishOuputPin(MCP23017Pin.GPIO_B4,
				IPINController.PIN_OUT_NAME_SIMON_RED01, mcp23017GpioProvider01);
		GpioPinDigitalOutput pinYellow02 = pinController.establishOuputPin(MCP23017Pin.GPIO_B5,
				IPINController.PIN_OUT_NAME_SIMON_YELLOW02, mcp23017GpioProvider01);
		GpioPinDigitalOutput pinBlue02 = pinController.establishOuputPin(MCP23017Pin.GPIO_B6,
				IPINController.PIN_OUT_NAME_SIMON_BLUE02, mcp23017GpioProvider01);

		pinController.establishInputPin(MCP23017Pin.GPIO_A0, IPINController.PIN_IN_NAME_SIMON_GREEN01,
				mcp23017GpioProvider02, pinGreen01);
		pinController.establishInputPin(MCP23017Pin.GPIO_A1, IPINController.PIN_IN_NAME_SIMON_GREEN02,
				mcp23017GpioProvider02, pinGreen02);
		pinController.establishInputPin(MCP23017Pin.GPIO_A2, IPINController.PIN_IN_NAME_SIMON_WHITE,
				mcp23017GpioProvider02, pinWhite);
		pinController.establishInputPin(MCP23017Pin.GPIO_A3, IPINController.PIN_IN_NAME_SIMON_YELLOW02,
				mcp23017GpioProvider02, pinYellow02);
		pinController.establishInputPin(MCP23017Pin.GPIO_A4, IPINController.PIN_IN_NAME_SIMON_RED02,
				mcp23017GpioProvider02, pinRed02);
		pinController.establishInputPin(MCP23017Pin.GPIO_A5, IPINController.PIN_IN_NAME_SIMON_YELLOW01,
				mcp23017GpioProvider02, pinYellow01);
		pinController.establishInputPin(MCP23017Pin.GPIO_A6, IPINController.PIN_IN_NAME_SIMON_BLUE02,
				mcp23017GpioProvider02, pinBlue02);
		pinController.establishInputPin(MCP23017Pin.GPIO_A7, IPINController.PIN_IN_NAME_SIMON_BLUE01,
				mcp23017GpioProvider02, pinBlue01);
		pinController.establishInputPin(MCP23017Pin.GPIO_B0, IPINController.PIN_IN_NAME_SIMON_RED01,
				mcp23017GpioProvider02, pinRed01);
	}

	@Override
	public void wipePins() {

		IPINController pinController = getPanel().getPinController();

		pinController.removeGpioPinListener(this);

		pinController.removePin(IPINController.PIN_OUT_NAME_SIMON_GREEN01);
		pinController.removePin(IPINController.PIN_OUT_NAME_SIMON_BLUE01);
		pinController.removePin(IPINController.PIN_OUT_NAME_SIMON_YELLOW01);
		pinController.removePin(IPINController.PIN_OUT_NAME_SIMON_GREEN02);
		pinController.removePin(IPINController.PIN_OUT_NAME_SIMON_WHITE);
		pinController.removePin(IPINController.PIN_OUT_NAME_SIMON_RED02);
		pinController.removePin(IPINController.PIN_OUT_NAME_SIMON_RED01);
		pinController.removePin(IPINController.PIN_OUT_NAME_SIMON_YELLOW02);
		pinController.removePin(IPINController.PIN_OUT_NAME_SIMON_BLUE02);

		pinController.removePin(IPINController.PIN_IN_NAME_SIMON_GREEN01);
		pinController.removePin(IPINController.PIN_IN_NAME_SIMON_GREEN02);
		pinController.removePin(IPINController.PIN_IN_NAME_SIMON_WHITE);
		pinController.removePin(IPINController.PIN_IN_NAME_SIMON_YELLOW02);
		pinController.removePin(IPINController.PIN_IN_NAME_SIMON_RED02);
		pinController.removePin(IPINController.PIN_IN_NAME_SIMON_YELLOW01);
		pinController.removePin(IPINController.PIN_IN_NAME_SIMON_BLUE02);
		pinController.removePin(IPINController.PIN_IN_NAME_SIMON_BLUE01);
		pinController.removePin(IPINController.PIN_IN_NAME_SIMON_RED01);
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
