package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.ten31f.mission.PixelPanel;
import com.ten31f.mission.entities.Button;
import com.ten31f.mission.entities.Button.ButtonState;
import com.ten31f.mission.entities.Entity;
import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Illuminated;
import com.ten31f.mission.entities.Illuminated.LEDState;
import com.ten31f.mission.pin.IPINController;
import com.ten31f.mission.entities.Toggle;

public class PyroStage extends Stage implements GpioPinListenerDigital {

	public static final String NAME = "Pyrotechics Stage";

	private static final String INSTRUCTIONS = "Its time to initiate the boosters";

	private static final String[] BUTTON_KEYS = { EntityNames.TOGGLE_PYRO_01, EntityNames.BUTTON_PYRO_01,
			EntityNames.TOGGLE_PYRO_02, EntityNames.BUTTON_PYRO_02, EntityNames.TOGGLE_PYRO_03,
			EntityNames.BUTTON_PYRO_03 };

	public PyroStage(PixelPanel panel, EntityCollection visibleEntityCollection,
			EntityCollection hiddenEntityCollection) {
		super(panel, visibleEntityCollection, hiddenEntityCollection);
	}

	@Override
	public boolean isComplete() {

		for (String key : BUTTON_KEYS) {
			if (!LEDState.HIGH.equals(((Button) getVisibleEntityCollection().getEntity(key)).getLedState())) {
				return false;
			}
		}

		Clip mainLoop = getPanel().getMainLoop();
		if (mainLoop != null && mainLoop.isRunning()) {
			mainLoop.stop();
		}

		return true;
	}

	@Override
	public void tick() {

	}

	@Override
	public void init() {
		pack(SecurityStage.VISABLE_ENTITIES);

		int x = (int) (getPanel().getXCenter() + (getPanel().getWidth() / 4d * 0.5));
		getProfessor().moveToXY(x, getProfessor().getY());

		getProfessor().setDialog(INSTRUCTIONS);
		promptNextButton();
	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

		for (String key : BUTTON_KEYS) {

			Entity entity = getVisibleEntityCollection().getEntity(key);

			if (entity.withIN(mouseEvent.getX(), mouseEvent.getY())) {

				switch (key) {
				case EntityNames.BUTTON_PYRO_01:
					if (isDepressed(EntityNames.TOGGLE_PYRO_01))
						((Illuminated) entity).toggle();
					break;
				case EntityNames.BUTTON_PYRO_02:
					if (isDepressed(EntityNames.TOGGLE_PYRO_02))
						((Illuminated) entity).toggle();
					break;
				case EntityNames.BUTTON_PYRO_03:
					if (isDepressed(EntityNames.TOGGLE_PYRO_03))
						((Illuminated) entity).toggle();
					break;
				default:
				}

				promptNextButton();
			}
		}

	}

	@Override
	public void establishPins() {
		IPINController pinController = getPanel().getPinController();

		

		MCP23017GpioProvider mcp23017GpioProvider03 = pinController.getMco23017GpioProvider03();

		pinController.establishOuputPin(MCP23017Pin.GPIO_B5, IPINController.PIN_OUT_NAME_PYRO_FUEL_PUMP,
				mcp23017GpioProvider03);
		pinController.establishOuputPin(MCP23017Pin.GPIO_B6, IPINController.PIN_OUT_NAME_PYRO_SOLID_BOOSTER,
				mcp23017GpioProvider03);
		pinController.establishOuputPin(MCP23017Pin.GPIO_B7, IPINController.PIN_OUT_NAME_PYRO_MAIN_ENGINE,
				mcp23017GpioProvider03);

		pinController.establishOuputPin(MCP23017Pin.GPIO_A0, IPINController.PIN_OUT_NAME_PYRO_FUEL_PUMP_SWITCH,
				mcp23017GpioProvider03);
		pinController.establishOuputPin(MCP23017Pin.GPIO_A1, IPINController.PIN_OUT_NAME_PYRO_SOLID_BOOSTER_SWITCH,
				mcp23017GpioProvider03);
		pinController.establishOuputPin(MCP23017Pin.GPIO_A2, IPINController.PIN_OUT_NAME_PYRO_MAIN_ENGINE_SWITCH,
				mcp23017GpioProvider03);

		pinController.establishInputPin(MCP23017Pin.GPIO_A5, IPINController.PIN_IN_NAME_PYRO_MAIN_ENGINE,
				mcp23017GpioProvider03);
		pinController.establishInputSwitchPin(MCP23017Pin.GPIO_A6, IPINController.PIN_IN_NAME_PYRO_SOLID_BOOSTER_SWITCH,
				mcp23017GpioProvider03);
		pinController.establishInputPin(MCP23017Pin.GPIO_A7, IPINController.PIN_IN_NAME_PYRO_FUEL_PUMP,
				mcp23017GpioProvider03);

		pinController.establishInputSwitchPin(MCP23017Pin.GPIO_B0, IPINController.PIN_IN_NAME_PYRO_FUEL_PUMP_SWITCH,
				mcp23017GpioProvider03);
		pinController.establishInputPin(MCP23017Pin.GPIO_B1, IPINController.PIN_IN_NAME_PYRO_SOLID_BOOSTER,
				mcp23017GpioProvider03);
		pinController.establishInputSwitchPin(MCP23017Pin.GPIO_B2, IPINController.PIN_IN_NAME_PYRO_MAIN_ENGINE_SWITCH,
				mcp23017GpioProvider03);

		pinController.addGpioPinListener(this);
		
	}

	@Override
	public void wipePins() {
		IPINController pinController = getPanel().getPinController();

		pinController.removeGpioPinListener(this);

		for (String key : BUTTON_KEYS) {
			Entity entity = getVisibleEntityCollection().getEntity(key);
			((Illuminated) entity).setLedState(LEDState.LOW);
		}

		pinController.removePin(IPINController.PIN_OUT_NAME_PYRO_FUEL_PUMP);
		pinController.removePin(IPINController.PIN_OUT_NAME_PYRO_SOLID_BOOSTER);
		pinController.removePin(IPINController.PIN_OUT_NAME_PYRO_MAIN_ENGINE);

		pinController.removePin(IPINController.PIN_IN_NAME_PYRO_MAIN_ENGINE);
		pinController.removePin(IPINController.PIN_IN_NAME_PYRO_SOLID_BOOSTER);
		pinController.removePin(IPINController.PIN_IN_NAME_PYRO_FUEL_PUMP);

		pinController.removePin(IPINController.PIN_IN_NAME_PYRO_FUEL_PUMP_SWITCH);
		pinController.removePin(IPINController.PIN_IN_NAME_PYRO_SOLID_BOOSTER_SWITCH);
		pinController.removePin(IPINController.PIN_IN_NAME_PYRO_MAIN_ENGINE_SWITCH);
	}

	private void promptNextButton() {

		for (String key : BUTTON_KEYS) {
			Button button = (Button) getVisibleEntityCollection().getEntity(key);
			if (!LEDState.HIGH.equals(button.getLedState())) {
				button.setLedState(LEDState.PROMPT);
				return;
			}
		}

	}

	private boolean isDepressed(String key) {
		return ButtonState.DEPRESSED.equals(((Button) getVisibleEntityCollection().getEntity(key)).getButtonState());
	}

	@Override
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

		System.out.println(String.format(" --> GPIO PIN STATE CHANGE(%s): %s = %s", event.getPin().getPin(),
				event.getPin().getName(), event.getState()));

		String pinName = event.getPin().getName().replace("_IN", "_OUT");

		for (String key : BUTTON_KEYS) {
			Entity entity = getVisibleEntityCollection().getEntity(key);

			if (((Illuminated) entity).getOutputPinName().equals(pinName)) {
				switch (key) {
				case EntityNames.BUTTON_PYRO_01:
					if (!PinState.LOW.equals(event.getState()))
						return;
					if (isDepressed(EntityNames.TOGGLE_PYRO_01))
						((Illuminated) entity).toggle();
					break;
				case EntityNames.BUTTON_PYRO_02:
					if (!PinState.LOW.equals(event.getState()))
						return;
					if (isDepressed(EntityNames.TOGGLE_PYRO_02))
						((Illuminated) entity).toggle();
					break;
				case EntityNames.BUTTON_PYRO_03:
					if (!PinState.LOW.equals(event.getState()))
						return;
					if (isDepressed(EntityNames.TOGGLE_PYRO_03))
						((Illuminated) entity).toggle();
					break;
				case EntityNames.TOGGLE_PYRO_01:
					if (event.getState().equals(PinState.LOW))
						((Toggle) entity).depress();
					else
						((Toggle) entity).unDepress();
					break;
				case EntityNames.TOGGLE_PYRO_02:
					if (event.getState().equals(PinState.LOW))
						((Toggle) entity).depress();
					else
						((Toggle) entity).unDepress();
					break;
				case EntityNames.TOGGLE_PYRO_03:
					if (event.getState().equals(PinState.LOW))
						((Toggle) entity).depress();
					else
						((Toggle) entity).unDepress();
					break;
				default:
				}

				promptNextButton();
			}

		}

	}
}
