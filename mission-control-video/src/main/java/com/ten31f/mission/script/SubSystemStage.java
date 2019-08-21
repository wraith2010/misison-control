package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.ten31f.mission.PixelPanel;
import com.ten31f.mission.entities.Button;
import com.ten31f.mission.entities.Entity;
import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Illuminated;
import com.ten31f.mission.entities.Illuminated.LEDState;
import com.ten31f.mission.pin.IPINController;

public class SubSystemStage extends Stage implements GpioPinListenerDigital {

	private static final String INSTRUCTIONS = "Now we need to boot the various systems on the Ship";

	private static final String[] BUTTON_KEYS = { EntityNames.BUTTON_SUBSYSTEM_01, EntityNames.BUTTON_SUBSYSTEM_02,
			EntityNames.BUTTON_SUBSYSTEM_03, EntityNames.BUTTON_SUBSYSTEM_04, EntityNames.BUTTON_SUBSYSTEM_05,
			EntityNames.BUTTON_SUBSYSTEM_06 };

	public SubSystemStage(PixelPanel panel, EntityCollection visibleEntityCollection,
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

		return true;
	}

	@Override
	public void tick() {

	}

	@Override
	public void init() {
		pack(SecurityStage.VISABLE_ENTITIES);

		int x = (int) (getPanel().getXCenter() - (getPanel().getWidth() / 4d * 0.5));
		getProfessor().moveToXY(x, getProfessor().getY());
		getProfessor().setDialog(INSTRUCTIONS);

		promptNextButton();
	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {
		for (String key : BUTTON_KEYS) {
			Entity entity = getVisibleEntityCollection().getEntity(key);
			if (entity.withIN(mouseEvent.getX(), mouseEvent.getY())) {
				((Illuminated) entity).toggle();
				promptNextButton();
			}
		}
	}

	@Override
	public void establishPins() {
		IPINController pinController = getPanel().getPinController();

		

		MCP23017GpioProvider mcp23017GpioProvider01 = getPanel().getPinController().getMco23017GpioProvider01();
		MCP23017GpioProvider mcp23017GpioProvider02 = getPanel().getPinController().getMco23017GpioProvider02();

		GpioPinDigitalOutput pinECOut = pinController.establishOuputPin(MCP23017Pin.GPIO_A1,
				IPINController.PIN_OUT_NAME_SUB_SYSTEM_ENVIRONMENTAL_CONTROL, mcp23017GpioProvider01);
		GpioPinDigitalOutput pinTANGOut = pinController.establishOuputPin(MCP23017Pin.GPIO_A2,
				IPINController.PIN_OUT_NAME_SUB_SYSTEM_TANG, mcp23017GpioProvider01);
		GpioPinDigitalOutput pinLSOut = pinController.establishOuputPin(MCP23017Pin.GPIO_A3,
				IPINController.PIN_OUT_NAME_SUB_SYSTEM_LIFE_SUPPORT, mcp23017GpioProvider01);
		GpioPinDigitalOutput pinWOut = pinController.establishOuputPin(MCP23017Pin.GPIO_A4,
				IPINController.PIN_OUT_NAME_SUB_SYSTEM_WATER, mcp23017GpioProvider01);
		GpioPinDigitalOutput pinCOMMOut = pinController.establishOuputPin(MCP23017Pin.GPIO_A6,
				IPINController.PIN_OUT_NAME_SUB_SYSTEM_COMMS, mcp23017GpioProvider01);
		GpioPinDigitalOutput pinTFOut = pinController.establishOuputPin(MCP23017Pin.GPIO_A7,
				IPINController.PIN_OUT_NAME_SUB_SYSTEM_TWITCH_FEED, mcp23017GpioProvider01);

		pinController.establishInputPin(MCP23017Pin.GPIO_B1, IPINController.PIN_IN_NAME_SUB_SYSTEM_TWITCH_FEED,
				mcp23017GpioProvider02, pinTFOut);
		pinController.establishInputPin(MCP23017Pin.GPIO_B2, IPINController.PIN_IN_NAME_SUB_SYSTEM_COMMS,
				mcp23017GpioProvider02, pinCOMMOut);
		pinController.establishInputPin(MCP23017Pin.GPIO_B3, IPINController.PIN_IN_NAME_SUB_SYSTEM_WATER,
				mcp23017GpioProvider02, pinWOut);
		pinController.establishInputPin(MCP23017Pin.GPIO_B4, IPINController.PIN_IN_NAME_SUB_SYSTEM_LIFE_SUPPORT,
				mcp23017GpioProvider02, pinLSOut);
		pinController.establishInputPin(MCP23017Pin.GPIO_B5, IPINController.PIN_IN_NAME_SUB_SYSTEM_TANG,
				mcp23017GpioProvider02, pinTANGOut);
		pinController.establishInputPin(MCP23017Pin.GPIO_B6,
				IPINController.PIN_IN_NAME_SUB_SYSTEM_ENVIRONMENTAL_CONTROL, mcp23017GpioProvider02, pinECOut);

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

		pinController.removePin(IPINController.PIN_OUT_NAME_SUB_SYSTEM_ENVIRONMENTAL_CONTROL);
		pinController.removePin(IPINController.PIN_OUT_NAME_SUB_SYSTEM_TANG);
		pinController.removePin(IPINController.PIN_OUT_NAME_SUB_SYSTEM_LIFE_SUPPORT);
		pinController.removePin(IPINController.PIN_OUT_NAME_SUB_SYSTEM_WATER);
		pinController.removePin(IPINController.PIN_OUT_NAME_SUB_SYSTEM_COMMS);
		pinController.removePin(IPINController.PIN_OUT_NAME_SUB_SYSTEM_TWITCH_FEED);

		pinController.removePin(IPINController.PIN_IN_NAME_SUB_SYSTEM_TWITCH_FEED);
		pinController.removePin(IPINController.PIN_IN_NAME_SUB_SYSTEM_COMMS);
		pinController.removePin(IPINController.PIN_IN_NAME_SUB_SYSTEM_WATER);
		pinController.removePin(IPINController.PIN_IN_NAME_SUB_SYSTEM_LIFE_SUPPORT);
		pinController.removePin(IPINController.PIN_IN_NAME_SUB_SYSTEM_TANG);
		pinController.removePin(IPINController.PIN_IN_NAME_SUB_SYSTEM_ENVIRONMENTAL_CONTROL);

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

	@Override
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

		System.out.println(String.format(" --> GPIO PIN STATE CHANGE(%s): %s = %s", event.getPin().getPin(),
				event.getPin().getName(), event.getState()));

		if (!PinState.LOW.equals(event.getState()))
			return;

		String pinName = event.getPin().getName().replace("_IN", "_OUT");

		for (String key : BUTTON_KEYS) {
			Entity entity = getVisibleEntityCollection().getEntity(key);

			System.out.println(String.format("%s == %s : %s", pinName, ((Illuminated) entity).getOutputPinName(),
					((Illuminated) entity).getOutputPinName().equals(pinName)));

			if (((Illuminated) entity).getOutputPinName().equals(pinName)) {
				((Illuminated) entity).toggle();
				promptNextButton();
			}
		}
	}
}
