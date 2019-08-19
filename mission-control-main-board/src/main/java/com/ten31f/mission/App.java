package com.ten31f.mission;

import java.io.Console;
import java.io.IOException;

import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.ten31f.mission.pin.IPINController;
import com.ten31f.mission.pin.PINControllerOnBoard;

public class App {

	public static void main(String[] args) throws InterruptedException, UnsupportedBusNumberException, IOException {

		System.out.println("<--Pi4J--> GPIO Control Example ... started.");

		IPINController pinControllerOnBoard = new PINControllerOnBoard();

		while (true) {

			Thread.sleep(500);

		}

	}

	public static void waitForEnter(String message, Object... args) {
		Console c = System.console();
		if (c != null) {
			// printf-like arguments
			if (message != null)
				c.format(message, args);
			c.format("\nPress ENTER to proceed.\n");
			c.readLine();
		}
	}

}
