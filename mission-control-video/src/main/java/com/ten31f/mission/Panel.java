package com.ten31f.mission;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.LargeRoundButton;
import com.ten31f.mission.entities.Professor;
import com.ten31f.mission.entities.RoundButton;
import com.ten31f.mission.entities.SquareButton;
import com.ten31f.mission.entities.SubPanel;
import com.ten31f.mission.gfx.Colours;
import com.ten31f.mission.gfx.Screen;
import com.ten31f.mission.gfx.SpriteSheet;

public class Panel extends Canvas implements Runnable {

	private int redON = Colours.get(-1, 111, 200, 500);
	private int redOFF = Colours.get(-1, 111, 200, 200);
	private int blueON = Colours.get(-1, 111, 2, 5);
	private int blueOFF = Colours.get(-1, 111, 2, 2);
	private int yellowON = Colours.get(-1, 111, 220, 550);
	private int yellowOFF = Colours.get(-1, 111, 220, 220);
	private int greenON = Colours.get(-1, 111, 20, 50);
	private int greenOFF = Colours.get(-1, 111, 20, 20);
	private int whiteON = Colours.get(-1, 111, 222, 555);
	private int whiteOFF = Colours.get(-1, 111, 222, 555);

	private int whiteSquareOFF = Colours.get(-1, 111, 111, 2222);
	private int whiteSquareON = Colours.get(-1, 111, 111, 555);

	private static final long serialVersionUID = 1L;

	public static final int SCALE = 3;
	public static final String NAME = "Game";
	public static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
	public static Panel panel;

	private JFrame jFrame;

	private Thread thread;

	public boolean running = false;
	public int tickCount = 0;

	private BufferedImage image = new BufferedImage((int) DIMENSION.getWidth(), (int) DIMENSION.getHeight(),
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colours = new int[6 * 6 * 6];

	private Screen screen;

	public WindowHandler windowHandler;
	private EntityCollection entityCollection;

	public boolean debug = true;
	public boolean isApplet = false;

	public void init() {
		setPanel(this);
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);

					colours[index++] = rr << 16 | gg << 8 | bb;
				}
			}
		}
		setScreen(new Screen((int) DIMENSION.getWidth(), (int) DIMENSION.getHeight(),
				new SpriteSheet("/sprite_sheet.png")));
		setEntityCollection(new EntityCollection());

		int buttonShift = 100;

		int xcenter = (int) (DIMENSION.getWidth() / 2);
		int ycenter = (int) (DIMENSION.getHeight() / 2);

		int width = (int) (DIMENSION.getWidth() * 0.8);
		int height = (int) (DIMENSION.getWidth() * 0.8);

		// Sub Panel 1 Security
		int subPanel1XCenter = (int) (xcenter - (width / 4 * 1.5));
		int subPanel1yCenter = (int) (ycenter + (height / 4 * 0.5));

		getEntityCollection().addEntity(new SubPanel(subPanel1XCenter, subPanel1yCenter, "SECURITY"));

		getEntityCollection().addEntity(
				new RoundButton(subPanel1XCenter - buttonShift, subPanel1yCenter - buttonShift, blueON, blueOFF));
		getEntityCollection()
				.addEntity(new RoundButton(subPanel1XCenter, subPanel1yCenter - buttonShift, yellowON, yellowOFF));
		getEntityCollection().addEntity(
				new RoundButton(subPanel1XCenter + buttonShift, subPanel1yCenter - buttonShift, blueON, blueOFF));
		getEntityCollection()
				.addEntity(new RoundButton(subPanel1XCenter - buttonShift, subPanel1yCenter, greenON, greenOFF));
		getEntityCollection().addEntity(new RoundButton(subPanel1XCenter, subPanel1yCenter, whiteON, whiteOFF));
		getEntityCollection()
				.addEntity(new RoundButton(subPanel1XCenter + buttonShift, subPanel1yCenter, greenON, greenOFF));
		getEntityCollection().addEntity(
				new RoundButton(subPanel1XCenter - buttonShift, subPanel1yCenter + buttonShift, redON, redOFF));
		getEntityCollection()
				.addEntity(new RoundButton(subPanel1XCenter, subPanel1yCenter + buttonShift, yellowON, yellowOFF));
		getEntityCollection().addEntity(
				new RoundButton(subPanel1XCenter + buttonShift, subPanel1yCenter + buttonShift, redON, redOFF));

		// sub panel 2 primers
		int subPanel2XCenter = (int) (xcenter - ((width / 4 * 0.5)));
		int subPanel2YCenter = (int) (ycenter + (height / 4 * 0.5));

		getEntityCollection().addEntity(new SubPanel(subPanel2XCenter, subPanel2YCenter, "SUB SYSTEMS"));

		getEntityCollection().addEntity(new SquareButton(subPanel2XCenter - buttonShift,
				subPanel2YCenter - (buttonShift / 2), whiteSquareON, whiteSquareOFF));
		getEntityCollection().addEntity(new SquareButton(subPanel2XCenter, subPanel2YCenter - (buttonShift / 2),
				whiteSquareON, whiteSquareOFF));
		getEntityCollection().addEntity(new SquareButton(subPanel2XCenter + buttonShift,
				subPanel2YCenter - (buttonShift / 2), whiteSquareON, whiteSquareOFF));

		getEntityCollection().addEntity(new SquareButton(subPanel2XCenter - buttonShift,
				subPanel2YCenter + (buttonShift / 2), whiteSquareON, whiteSquareOFF));
		getEntityCollection().addEntity(new SquareButton(subPanel2XCenter, subPanel2YCenter + (buttonShift / 2),
				whiteSquareON, whiteSquareOFF));
		getEntityCollection().addEntity(new SquareButton(subPanel2XCenter + buttonShift,
				subPanel2YCenter + (buttonShift / 2), whiteSquareON, whiteSquareOFF));

		// sub panel 3 pyro
		int subPanel3XCenter = (int) (xcenter + ((width / 4 * 0.5)));
		int subPanel3YCenter = (int) (ycenter + (height / 4 * 0.5));

		getEntityCollection().addEntity(new SubPanel(subPanel3XCenter, subPanel3YCenter, "PYROTECHNICS"));

		getEntityCollection().addEntity(new SquareButton(subPanel3XCenter - buttonShift,
				subPanel3YCenter - (buttonShift / 2), whiteSquareON, whiteSquareOFF));
		getEntityCollection().addEntity(new SquareButton(subPanel3XCenter, subPanel3YCenter - (buttonShift / 2),
				whiteSquareON, whiteSquareOFF));
		getEntityCollection().addEntity(new SquareButton(subPanel3XCenter + buttonShift,
				subPanel3YCenter - (buttonShift / 2), whiteSquareON, whiteSquareOFF));

		// sub panel 4 big button
		int subPanel4XCenter = (int) (xcenter + ((width / 4 * 1.5)));
		int subPanel4YCenter = (int) (ycenter + (height / 4 * 0.5));

		getEntityCollection().addEntity(new SubPanel(subPanel4XCenter, subPanel4YCenter, "LAUNCH"));

		getEntityCollection().addEntity(new LargeRoundButton(subPanel4XCenter, subPanel4YCenter, redON, redOFF));
		
		getEntityCollection().addEntity(new Professor(xcenter, ycenter - 300));
	}

	public synchronized void start() {

		setRunning(true);

		thread = new Thread(this, NAME + "_main");
		thread.start();

	}

	public synchronized void stop() {
		setRunning(false);

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		init();

		while (isRunning()) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;

			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				debug(DebugLevel.INFO, ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;
			}
		}
	}

	public void tick() {
		tickCount++;
		getEntityCollection().tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		for (int y = 0; y < getScreen().getHeight(); y++) {
			for (int x = 0; x < getScreen().getWidth(); x++) {
				getScreen().pixels[x + y * getScreen().getWidth()] = 0;
			}
		}

		getEntityCollection().renderEntities(screen);

		for (int y = 0; y < getScreen().getHeight(); y++) {
			for (int x = 0; x < getScreen().getWidth(); x++) {
				int colourCode = getScreen().pixels[x + y * getScreen().getWidth()];
				if (colourCode < 255)
					pixels[x + y * getScreen().getWidth()] = colours[colourCode];
			}
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static long fact(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return n * fact(n - 1);
		}
	}

	public void debug(DebugLevel level, String msg) {
		switch (level) {
		default:
		case INFO:
			if (debug) {
				System.out.println("[" + NAME + "] " + msg);
			}
			break;
		case WARNING:
			System.out.println("[" + NAME + "] [WARNING] " + msg);
			break;
		case SEVERE:
			System.out.println("[" + NAME + "] [SEVERE]" + msg);
			this.stop();
			break;
		}
	}

	public static enum DebugLevel {
		INFO, WARNING, SEVERE;
	}

	private static Panel getPanel() {
		return panel;
	}

	private static void setPanel(Panel panel) {
		Panel.panel = panel;
	}

	private boolean isRunning() {
		return running;
	}

	private void setRunning(boolean running) {
		this.running = running;
	}

	private Screen getScreen() {
		return screen;
	}

	private void setScreen(Screen screen) {
		this.screen = screen;
	}

	public JFrame getjFrame() {
		return jFrame;
	}

	public void setjFrame(JFrame jFrame) {
		this.jFrame = jFrame;
	}

	public void setEntityCollection(EntityCollection entityCollection) {
		this.entityCollection = entityCollection;
	}

	public EntityCollection getEntityCollection() {
		return entityCollection;
	}
}
