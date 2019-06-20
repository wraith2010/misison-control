package com.ten31f.mission;

import java.applet.Applet;
import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings({ "serial", "deprecation" })
public class AppLauncher extends Applet {

	private static Panel panel = new Panel();
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
		panel.setMinimumSize(panel.DIMENSIONS);
		panel.setMaximumSize(panel.DIMENSIONS);
		panel.setPreferredSize(panel.DIMENSIONS);

		panel.setjFrame(new JFrame(panel.NAME));

		panel.getjFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
		panel.getjFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.getjFrame().setUndecorated(true);

		panel.getjFrame().add(panel, BorderLayout.CENTER);
		panel.getjFrame().pack();

		panel.getjFrame().setResizable(false);
		panel.getjFrame().setLocationRelativeTo(null);
		panel.getjFrame().setVisible(true);

		panel.windowHandler = new WindowHandler(panel);
		panel.debug = DEBUG;

		panel.start();
	}

}
