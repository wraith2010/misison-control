package com.ten31f.mission;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.ten31f.mission.entities.Button;
import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.Illuminated;
import com.ten31f.mission.entities.LargeRoundButton;
import com.ten31f.mission.entities.Professor;
import com.ten31f.mission.entities.Rocket;
import com.ten31f.mission.entities.RoundButton;
import com.ten31f.mission.entities.SquareButton;
import com.ten31f.mission.entities.SubPanel;
import com.ten31f.mission.entities.Toggle;
import com.ten31f.mission.gfx.Screen;
import com.ten31f.mission.gfx.SpriteSheet;
import com.ten31f.mission.script.SecurityStage;
import com.ten31f.mission.script.Stage;

public class Panel extends Canvas implements Runnable {

	public static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
	public static final String NAME = "Misison Control";

	private static final long serialVersionUID = 1L;

	public static final String BUTTON_NAME_BUTTON01 = "COMMS";
	public static final String BUTTON_NAME_BUTTON02 = "LIFE\nSUPPORT";
	public static final String BUTTON_NAME_BUTTON03 = "ENVIRONMENT\nControl";
	public static final String BUTTON_NAME_BUTTON04 = "WATER";
	public static final String BUTTON_NAME_BUTTON05 = "WASTE";
	public static final String BUTTON_NAME_BUTTON06 = "TANG";
	public static final String BUTTON_NAME_BUTTON07 = "FUEL\nPUMP";
	public static final String BUTTON_NAME_BUTTON08 = "SOLID\nBOOSTER";
	public static final String BUTTON_NAME_BUTTON09 = "MAIN\nENGINE";

	public static final String BUTTON_NAME_TOGGLE01 = "FUEL\nPUMP\nARM";
	public static final String BUTTON_NAME_TOGGLE02 = "SOLID\nBOOSTER\nARM";
	public static final String BUTTON_NAME_TOGGLE03 = "MAIN\nENGINE\nARM";

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

	private Stage activeStage = null;
	private List<Stage> stages = null;

	public Panel() {
		setStages(new ArrayList<>());
	}

	public void init() {
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

		// Sub Panel 1 Security
		int subPanel1XCenter = (int) (getXCenter() - (getWidth() / 4 * 1.5));
		int subPanel1yCenter = (int) (getYCenter() + (getHeight() / 4 * 0.5));

		getEntityCollection().addEntity(new SubPanel(subPanel1XCenter, subPanel1yCenter, "SECURITY"));

		Button securityButton01 = new RoundButton(subPanel1XCenter - buttonShift, subPanel1yCenter - buttonShift,
				Illuminated.BLUE_ON, Illuminated.BLUE_OFF);
		getEntityCollection().addEntity(securityButton01);

		Button securityButton02 = new RoundButton(subPanel1XCenter, subPanel1yCenter - buttonShift,
				Illuminated.YELLOW_ON, Illuminated.YELLOW_OFF);
		getEntityCollection().addEntity(securityButton02);

		Button securityButton03 = new RoundButton(subPanel1XCenter + buttonShift, subPanel1yCenter - buttonShift,
				Illuminated.BLUE_ON, Illuminated.BLUE_OFF);
		getEntityCollection().addEntity(securityButton03);

		Button securityButton04 = new RoundButton(subPanel1XCenter - buttonShift, subPanel1yCenter,
				Illuminated.GREEN_ON, Illuminated.GREEN_OFF);
		getEntityCollection().addEntity(securityButton04);

		Button securityButton05 = new RoundButton(subPanel1XCenter, subPanel1yCenter, Illuminated.WHITE_ON,
				Illuminated.WHITE_OFF);
		getEntityCollection().addEntity(securityButton05);

		Button securityButton06 = new RoundButton(subPanel1XCenter + buttonShift, subPanel1yCenter,
				Illuminated.GREEN_ON, Illuminated.GREEN_OFF);
		getEntityCollection().addEntity(securityButton06);

		Button securityButton07 = new RoundButton(subPanel1XCenter - buttonShift, subPanel1yCenter + buttonShift,
				Illuminated.RED_ON, Illuminated.RED_OFF);
		getEntityCollection().addEntity(securityButton07);

		Button securityButton08 = new RoundButton(subPanel1XCenter, subPanel1yCenter + buttonShift,
				Illuminated.YELLOW_ON, Illuminated.YELLOW_OFF);
		getEntityCollection().addEntity(securityButton08);

		Button securityButton09 = new RoundButton(subPanel1XCenter + buttonShift, subPanel1yCenter + buttonShift,
				Illuminated.RED_ON, Illuminated.RED_OFF);
		getEntityCollection().addEntity(securityButton09);

		// sub panel 2 primers
		int subPanel2XCenter = (int) (getXCenter() - ((getWidth() / 4) * 0.5));
		int subPanel2YCenter = (int) (getYCenter() + ((getHeight() / 4) * 0.5));

		getEntityCollection().addEntity(new SubPanel(subPanel2XCenter, subPanel2YCenter, "SUB SYSTEMS"));

		getEntityCollection().addEntity(new SquareButton(BUTTON_NAME_BUTTON01, subPanel2XCenter - buttonShift,
				subPanel2YCenter - (buttonShift / 2), Illuminated.WHITE_SUQARE_ON, Illuminated.WHITE_SUQARE_OFF));
		getEntityCollection().addEntity(new SquareButton(BUTTON_NAME_BUTTON02, subPanel2XCenter,
				subPanel2YCenter - (buttonShift / 2), Illuminated.WHITE_SUQARE_ON, Illuminated.WHITE_SUQARE_OFF));
		getEntityCollection().addEntity(new SquareButton(BUTTON_NAME_BUTTON03, subPanel2XCenter + buttonShift,
				subPanel2YCenter - (buttonShift / 2), Illuminated.WHITE_SUQARE_ON, Illuminated.WHITE_SUQARE_OFF));

		getEntityCollection().addEntity(new SquareButton(BUTTON_NAME_BUTTON04, subPanel2XCenter - buttonShift,
				subPanel2YCenter + (buttonShift / 2), Illuminated.WHITE_SUQARE_ON, Illuminated.WHITE_SUQARE_OFF));
		getEntityCollection().addEntity(new SquareButton(BUTTON_NAME_BUTTON05, subPanel2XCenter,
				subPanel2YCenter + (buttonShift / 2), Illuminated.WHITE_SUQARE_ON, Illuminated.WHITE_SUQARE_OFF));
		getEntityCollection().addEntity(new SquareButton(BUTTON_NAME_BUTTON06, subPanel2XCenter + buttonShift,
				subPanel2YCenter + (buttonShift / 2), Illuminated.WHITE_SUQARE_ON, Illuminated.WHITE_SUQARE_OFF));

		// sub panel 3 pyro
		int subPanel3XCenter = (int) (getXCenter() + (getWidth() / 4 * 0.5));
		int subPanel3YCenter = (int) (getYCenter() + (getHeight() / 4 * 0.5));

		getEntityCollection().addEntity(new SubPanel(subPanel3XCenter, subPanel3YCenter, "PYROTECHNICS"));

		getEntityCollection().addEntity(new SquareButton(BUTTON_NAME_BUTTON07, subPanel3XCenter - buttonShift,
				subPanel3YCenter - (buttonShift / 2), Illuminated.WHITE_SUQARE_ON, Illuminated.WHITE_SUQARE_OFF));
		getEntityCollection().addEntity(new SquareButton(BUTTON_NAME_BUTTON08, subPanel3XCenter,
				subPanel3YCenter - (buttonShift / 2), Illuminated.WHITE_SUQARE_ON, Illuminated.WHITE_SUQARE_OFF));
		getEntityCollection().addEntity(new SquareButton(BUTTON_NAME_BUTTON09, subPanel3XCenter + buttonShift,
				subPanel3YCenter - (buttonShift / 2), Illuminated.WHITE_SUQARE_ON, Illuminated.WHITE_SUQARE_OFF));

		getEntityCollection().addEntity(new Toggle(BUTTON_NAME_TOGGLE01, subPanel3XCenter - buttonShift,
				subPanel3YCenter + (buttonShift / 2), Illuminated.TOGLE_ON, Illuminated.TOGLE_OFF));
		getEntityCollection().addEntity(new Toggle(BUTTON_NAME_TOGGLE02, subPanel3XCenter,
				subPanel3YCenter + (buttonShift / 2), Illuminated.TOGLE_ON, Illuminated.TOGLE_OFF));
		getEntityCollection().addEntity(new Toggle(BUTTON_NAME_TOGGLE03, subPanel3XCenter + buttonShift,
				subPanel3YCenter + (buttonShift / 2), Illuminated.TOGLE_ON, Illuminated.TOGLE_OFF));

		// sub panel 4 big button
		int subPanel4XCenter = (int) (getXCenter() + (getWidth() / 4 * 1.5));
		int subPanel4YCenter = (int) (getYCenter() + (getHeight() / 4 * 0.5));

		getEntityCollection().addEntity(new SubPanel(subPanel4XCenter, subPanel4YCenter, "LAUNCH"));

		getEntityCollection().addEntity(new LargeRoundButton(subPanel4XCenter, subPanel4YCenter,
				Illuminated.LARGE_RED_ON, Illuminated.LARGE_RED_OFF));

		Professor professor = new Professor(getXCenter(), getYCenter() - 300);

		getEntityCollection().addEntity(professor);
		getEntityCollection().addEntity(new Rocket(getXCenter() + 600, getYCenter() - 300));

		SecurityStage securityStage = new SecurityStage(this);
		securityStage.addButton(SecurityStage.BUTTON_SECURITY_01, securityButton01);
		securityStage.addButton(SecurityStage.BUTTON_SECURITY_02, securityButton02);
		securityStage.addButton(SecurityStage.BUTTON_SECURITY_03, securityButton03);
		securityStage.addButton(SecurityStage.BUTTON_SECURITY_04, securityButton04);
		securityStage.addButton(SecurityStage.BUTTON_SECURITY_05, securityButton05);
		securityStage.addButton(SecurityStage.BUTTON_SECURITY_06, securityButton06);
		securityStage.addButton(SecurityStage.BUTTON_SECURITY_07, securityButton07);
		securityStage.addButton(SecurityStage.BUTTON_SECURITY_08, securityButton08);
		securityStage.addButton(SecurityStage.BUTTON_SECURITY_09, securityButton09);
		securityStage.setProfessor(professor);

		setActiveStage(securityStage);

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
		if (getActiveStage().isComplete()) {
			setActiveStage(getActiveStage().getNextStage());
		}
		getActiveStage().tick();
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
		g.drawImage(image, 0, 0, DIMENSION.width, DIMENSION.height, null);
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

	public List<Stage> getStages() {
		return stages;
	}

	public void setStages(List<Stage> stages) {
		this.stages = stages;
	}

	public Stage getActiveStage() {
		return activeStage;
	}

	public void setActiveStage(Stage activeStage) {
		activeStage.init();
		this.activeStage = activeStage;
	}

	public int getXCenter() {
		return (int) (DIMENSION.getWidth() / 2);
	}

	public int getYCenter() {
		return (int) (DIMENSION.getHeight() / 2);
	}

	@Override
	public int getWidth() {
		return (int) (DIMENSION.getWidth() * 0.8);
	}

	@Override
	public int getHeight() {
		return (int) (DIMENSION.getHeight() * 0.8);
	}
}
