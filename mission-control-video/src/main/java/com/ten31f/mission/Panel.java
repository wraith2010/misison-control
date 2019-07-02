package com.ten31f.mission;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Illuminated;
import com.ten31f.mission.entities.Professor;
import com.ten31f.mission.entities.Rocket;
import com.ten31f.mission.entities.RoundButton;
import com.ten31f.mission.entities.SquareButton;
import com.ten31f.mission.entities.SubPanel;
import com.ten31f.mission.entities.Toggle;
import com.ten31f.mission.gfx.Screen;
import com.ten31f.mission.gfx.SpriteSheet;
import com.ten31f.mission.script.AnimateLaunch;
import com.ten31f.mission.script.LaunchStage;
import com.ten31f.mission.script.PyroStage;
import com.ten31f.mission.script.SecurityStage;
import com.ten31f.mission.script.Stage;
import com.ten31f.mission.script.SubSystemStage;

public class Panel extends Canvas implements Runnable, MouseListener {

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
	private EntityCollection visiableEntityCollection = null;
	private EntityCollection hiddenEntityCollection = null;

	public boolean debug = true;
	public boolean isApplet = false;

	private Stage activeStage = null;
	private List<Stage> stages = null;

	public Panel() {
		setStages(new ArrayList<>());
		setVisiableEntityCollection(new EntityCollection());
		setHiddenEntityCollection(new EntityCollection());
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

		int buttonShift = 100;

		// Sub Panel 1 Security
		int subPanel1XCenter = (int) (getXCenter() - (getWidth() / 4d * 1.5));
		int subPanel1yCenter = (int) (getYCenter() + (getHeight() / 4d * 0.5));

		getVisiableEntityCollection().addEntity(EntityNames.SUB_PANEL_01,
				new SubPanel(subPanel1XCenter, subPanel1yCenter, "SECURITY"));

		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SECURITY_01,
				new RoundButton(subPanel1XCenter - buttonShift, subPanel1yCenter - buttonShift, Illuminated.BLUE_ON,
						Illuminated.BLUE_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SECURITY_02, new RoundButton(subPanel1XCenter,
				subPanel1yCenter - buttonShift, Illuminated.YELLOW_ON, Illuminated.YELLOW_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SECURITY_03,
				new RoundButton(subPanel1XCenter + buttonShift, subPanel1yCenter - buttonShift, Illuminated.BLUE_ON,
						Illuminated.BLUE_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SECURITY_04, new RoundButton(
				subPanel1XCenter - buttonShift, subPanel1yCenter, Illuminated.GREEN_ON, Illuminated.GREEN_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SECURITY_05,
				new RoundButton(subPanel1XCenter, subPanel1yCenter, Illuminated.WHITE_ON, Illuminated.WHITE_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SECURITY_06, new RoundButton(
				subPanel1XCenter + buttonShift, subPanel1yCenter, Illuminated.GREEN_ON, Illuminated.GREEN_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SECURITY_07,
				new RoundButton(subPanel1XCenter - buttonShift, subPanel1yCenter + buttonShift, Illuminated.RED_ON,
						Illuminated.RED_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SECURITY_08, new RoundButton(subPanel1XCenter,
				subPanel1yCenter + buttonShift, Illuminated.YELLOW_ON, Illuminated.YELLOW_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SECURITY_09,
				new RoundButton(subPanel1XCenter + buttonShift, subPanel1yCenter + buttonShift, Illuminated.RED_ON,
						Illuminated.RED_OFF));

		// sub panel 2 primers
		int subPanel2XCenter = (int) (getXCenter() - ((getWidth() / 4d) * 0.5));
		int subPanel2YCenter = (int) (getYCenter() + ((getHeight() / 4d) * 0.5));

		getVisiableEntityCollection().addEntity(EntityNames.SUB_PANEL_02,
				new SubPanel(subPanel2XCenter, subPanel2YCenter, "SUB SYSTEMS"));

		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SUBSYSTEM_01,
				new SquareButton(BUTTON_NAME_BUTTON01, subPanel2XCenter - buttonShift,
						subPanel2YCenter - (buttonShift / 2), Illuminated.WHITE_SQUARE_ON,
						Illuminated.WHITE_SQUARE_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SUBSYSTEM_02,
				new SquareButton(BUTTON_NAME_BUTTON02, subPanel2XCenter, subPanel2YCenter - (buttonShift / 2),
						Illuminated.WHITE_SQUARE_ON, Illuminated.WHITE_SQUARE_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SUBSYSTEM_03,
				new SquareButton(BUTTON_NAME_BUTTON03, subPanel2XCenter + buttonShift,
						subPanel2YCenter - (buttonShift / 2), Illuminated.WHITE_SQUARE_ON,
						Illuminated.WHITE_SQUARE_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SUBSYSTEM_04,
				new SquareButton(BUTTON_NAME_BUTTON04, subPanel2XCenter - buttonShift,
						subPanel2YCenter + (buttonShift / 2), Illuminated.WHITE_SQUARE_ON,
						Illuminated.WHITE_SQUARE_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SUBSYSTEM_05,
				new SquareButton(BUTTON_NAME_BUTTON05, subPanel2XCenter, subPanel2YCenter + (buttonShift / 2),
						Illuminated.WHITE_SQUARE_ON, Illuminated.WHITE_SQUARE_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_SUBSYSTEM_06,
				new SquareButton(BUTTON_NAME_BUTTON06, subPanel2XCenter + buttonShift,
						subPanel2YCenter + (buttonShift / 2), Illuminated.WHITE_SQUARE_ON,
						Illuminated.WHITE_SQUARE_OFF));

		// sub panel 3 pyro
		int subPanel3XCenter = (int) (getXCenter() + (getWidth() / 4d * 0.5));
		int subPanel3YCenter = (int) (getYCenter() + (getHeight() / 4d * 0.5));

		getVisiableEntityCollection().addEntity(EntityNames.SUB_PANEL_03,
				new SubPanel(subPanel3XCenter, subPanel3YCenter, "PYROTECHNICS"));

		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_PYRO_01,
				new SquareButton(BUTTON_NAME_BUTTON07, subPanel3XCenter - buttonShift,
						subPanel3YCenter - (buttonShift / 2), Illuminated.WHITE_SQUARE_ON,
						Illuminated.WHITE_SQUARE_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_PYRO_02,
				new SquareButton(BUTTON_NAME_BUTTON08, subPanel3XCenter, subPanel3YCenter - (buttonShift / 2),
						Illuminated.WHITE_SQUARE_ON, Illuminated.WHITE_SQUARE_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.BUTTON_PYRO_03,
				new SquareButton(BUTTON_NAME_BUTTON09, subPanel3XCenter + buttonShift,
						subPanel3YCenter - (buttonShift / 2), Illuminated.WHITE_SQUARE_ON,
						Illuminated.WHITE_SQUARE_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.TOGGLE_PYRO_01,
				new Toggle(BUTTON_NAME_TOGGLE01, subPanel3XCenter - buttonShift, subPanel3YCenter + (buttonShift / 2),
						Illuminated.TOGLE_ON, Illuminated.TOGLE_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.TOGGLE_PYRO_02, new Toggle(BUTTON_NAME_TOGGLE02,
				subPanel3XCenter, subPanel3YCenter + (buttonShift / 2), Illuminated.TOGLE_ON, Illuminated.TOGLE_OFF));
		getVisiableEntityCollection().addEntity(EntityNames.TOGGLE_PYRO_03,
				new Toggle(BUTTON_NAME_TOGGLE03, subPanel3XCenter + buttonShift, subPanel3YCenter + (buttonShift / 2),
						Illuminated.TOGLE_ON, Illuminated.TOGLE_OFF));

		// sub panel 4 big button
		int subPanel4XCenter = (int) (getXCenter() + (getWidth() / 4d * 1.5));
		int subPanel4YCenter = (int) (getYCenter() + (getHeight() / 4d * 0.5));

		getVisiableEntityCollection().addEntity(EntityNames.SUB_PANEL_04,
				new SubPanel(subPanel4XCenter, subPanel4YCenter, "LAUNCH"));

		RoundButton roundButton10 = new RoundButton(subPanel4XCenter, subPanel4YCenter, Illuminated.LARGE_RED_ON,
				Illuminated.LARGE_RED_OFF);
		roundButton10.setScale(3);
		getVisiableEntityCollection().addEntity(EntityNames.LAUNCH_BUTTON, roundButton10);

		getVisiableEntityCollection().addEntity(EntityNames.PROFESSOR, new Professor(getXCenter(), getYCenter() - 300));
		getVisiableEntityCollection().addEntity(EntityNames.ROCKET, new Rocket(getXCenter(), getYCenter() - 150));

		SecurityStage securityStage = new SecurityStage(this, visiableEntityCollection, hiddenEntityCollection);
		SubSystemStage subSystemStage = new SubSystemStage(this, visiableEntityCollection, hiddenEntityCollection);
		PyroStage pyroStage = new PyroStage(this, visiableEntityCollection, hiddenEntityCollection);
		LaunchStage launchStage = new LaunchStage(this, visiableEntityCollection, hiddenEntityCollection);
		AnimateLaunch animateLaunch = new AnimateLaunch(this, visiableEntityCollection, hiddenEntityCollection);

		securityStage.setNextStage(subSystemStage);
		pyroStage.setNextStage(launchStage);
		launchStage.setNextStage(animateLaunch);

		setActiveStage(launchStage);

		addMouseListener(this);
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
		getVisiableEntityCollection().tick();
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

		getVisiableEntityCollection().renderEntities(screen);

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

	public void setVisiableEntityCollection(EntityCollection visiableEntityCollection) {
		this.visiableEntityCollection = visiableEntityCollection;
	}

	public EntityCollection getVisiableEntityCollection() {
		return visiableEntityCollection;
	}

	public EntityCollection getHiddenEntityCollection() {
		return hiddenEntityCollection;
	}

	public void setHiddenEntityCollection(EntityCollection hiddenEntityCollection) {
		this.hiddenEntityCollection = hiddenEntityCollection;
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
		return (int) (DIMENSION.getWidth());
	}

	@Override
	public int getHeight() {
		return (int) (DIMENSION.getHeight());
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		getActiveStage().mouseClick(mouseEvent);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
