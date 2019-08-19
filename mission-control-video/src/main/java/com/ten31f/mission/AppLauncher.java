package com.ten31f.mission;

import java.applet.Applet;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.ten31f.mission.pin.IPINController;
import com.ten31f.mission.pin.PINControllerOnBoard;
import com.ten31f.mission.pin.PINControllerOffBoard;

@SuppressWarnings({ "serial" })
public class AppLauncher extends Applet {

	private static PixelPanel panel = new PixelPanel();
	public static final boolean DEBUG = false;

	@Override
	public void init() {
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		panel.getjFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
		panel.debug = DEBUG;
		panel.isApplet = true;
	}

	@Override
	public void start() {
		panel.start();
	}

	@Override
	public void stop() {
		panel.stop();
	}

	public static void main(String[] args) {

		IPINController pinController = null;

		try {
			pinController = new PINControllerOnBoard();
		} catch (IllegalArgumentException | UnsatisfiedLinkError exception) {
			pinController = new PINControllerOffBoard();
		}

		panel.setPinController(pinController);
		panel.setMinimumSize(PixelPanel.DIMENSION);
		panel.setMaximumSize(PixelPanel.DIMENSION);
		panel.setPreferredSize(PixelPanel.DIMENSION);

		panel.setjFrame(new JFrame(PixelPanel.NAME));

		panel.getjFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
		panel.getjFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.getjFrame().add(panel, BorderLayout.CENTER);
		panel.getjFrame().pack();

		panel.getjFrame().setResizable(false);
		panel.getjFrame().setLocationRelativeTo(null);
		panel.getjFrame().setVisible(true);

		panel.windowHandler = new WindowHandler(panel);
		panel.debug = DEBUG;

		panel.start();
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
