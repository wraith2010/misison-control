package com.ten31f.mission;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowHandler implements WindowListener {

	private PixelPanel panel;

	public WindowHandler(PixelPanel panel) {
		setPanel(panel);
		getPanel().getjFrame().addWindowListener(this);
	}

	@Override
	public void windowActivated(WindowEvent event) {
	}

	@Override
	public void windowClosed(WindowEvent event) {
	}

	@Override
	public void windowClosing(WindowEvent event) {

	}

	@Override
	public void windowDeactivated(WindowEvent event) {
	}

	@Override
	public void windowDeiconified(WindowEvent event) {
	}

	@Override
	public void windowIconified(WindowEvent event) {
	}

	@Override
	public void windowOpened(WindowEvent event) {
	}

	private void setPanel(PixelPanel panel) {
		this.panel = panel;
	}

	public PixelPanel getPanel() {
		return panel;
	}

}
