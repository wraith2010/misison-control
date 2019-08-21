package com.ten31f.mission.script;

import java.awt.event.MouseEvent;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.ten31f.mission.PixelPanel;
import com.ten31f.mission.entities.Entity;
import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Illuminated;
import com.ten31f.mission.entities.Illuminated.LEDState;
import com.ten31f.mission.pin.IPINController;

public class LaunchStage extends Stage implements GpioPinListenerDigital {

	private static final String INSTRUCTIONS = "Mission is GO!";

	public LaunchStage(PixelPanel panel, EntityCollection visibleEntityCollection,
			EntityCollection hiddenEntityCollection) {
		super(panel, visibleEntityCollection, hiddenEntityCollection);
	}

	@Override
	public boolean isComplete() {
		return LEDState.HIGH.equals(
				((Illuminated) getVisibleEntityCollection().getEntity(EntityNames.LAUNCH_BUTTON)).getLedState());
	}

	@Override
	public void tick() {

	}

	@Override
	public void init() {
		pack(SecurityStage.VISABLE_ENTITIES);
		int x = (int) (getPanel().getXCenter() + (getPanel().getWidth() / 4d * 1.5));
		getProfessor().moveToXY(x, getProfessor().getY());

		getProfessor().setDialog(INSTRUCTIONS);

		((Illuminated) getVisibleEntityCollection().getEntity(EntityNames.LAUNCH_BUTTON)).setLedState(LEDState.PROMPT);
	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

		Entity entity = getVisibleEntityCollection().getEntity(EntityNames.LAUNCH_BUTTON);

		if (entity.withIN(mouseEvent.getX(), mouseEvent.getY())) {
			((Illuminated) entity).toggle();
		}

	}

	@Override
	public void establishPins() {
		IPINController pinController = getPanel().getPinController();

		

		MCP23017GpioProvider mcp23017GpioProvider03 = pinController.getMco23017GpioProvider03();

		pinController.establishOuputPin(MCP23017Pin.GPIO_B4, IPINController.PIN_OUT_NAME_LAUNCH,
				mcp23017GpioProvider03);
		pinController.establishInputPin(MCP23017Pin.GPIO_B3, IPINController.PIN_IN_NAME_LAUNCH, mcp23017GpioProvider03);
		
		pinController.addGpioPinListener(this);
	}

	@Override
	public void wipePins() {
		IPINController pinController = getPanel().getPinController();

		pinController.removeGpioPinListener(this);

		pinController.removePin(IPINController.PIN_OUT_NAME_LAUNCH);
		pinController.removePin(IPINController.PIN_IN_NAME_LAUNCH);
	}

	@Override
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		System.out.println(String.format(" --> GPIO PIN STATE CHANGE(%s): %s = %s", event.getPin().getPin(),
				event.getPin().getName(), event.getState()));

		if (!PinState.LOW.equals(event.getState()))
			return;

		String pinName = event.getPin().getName().replace("_IN", "_OUT");

		Entity entity = getVisibleEntityCollection().getEntity(EntityNames.LAUNCH_BUTTON);

		if (pinName.equals(((Illuminated) entity).getOutputPinName())) {
			((Illuminated) entity).toggle();
		}

	}

}
