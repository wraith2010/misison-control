package com.ten31f.mission.script;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.sound.sampled.Clip;

import com.ten31f.mission.PixelPanel;
import com.ten31f.mission.audio.SoundEffect;
import com.ten31f.mission.entities.EntityCollection;
import com.ten31f.mission.entities.EntityNames;
import com.ten31f.mission.entities.Professor;
import com.ten31f.mission.entities.Starfield;
import com.ten31f.mission.entities.Starfield.Animation;

public class AnimateLaunch extends Stage {

	private static final String RELAY_URL = "http://192.168.1.50:8080/SMOKE";
	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String[] VISABLE_ENTITIES = { EntityNames.STARFIELD, EntityNames.ROCKET,
			EntityNames.PROFESSOR };

	private int countDown = 10;

	private int tickCount = 0;

	private int flightTickCount = 0;

	private Clip music = null;

	public AnimateLaunch(PixelPanel panel, EntityCollection visibleEntityCollection,
			EntityCollection hiddenEntityCollection) {
		super(panel, visibleEntityCollection, hiddenEntityCollection);
	}

	@Override
	public boolean isComplete() {
		return getFlightTickCount() > 3600;
	}

	@Override
	public void tick() {

		Professor professor = (Professor) getVisibleEntityCollection().getEntity(EntityNames.PROFESSOR);

		setTickCount(getTickCount() + 1);

		if (getTickCount() % 60 == 0 && getCountDown() >= 0 && professor.isIdle()) {
			setCountDown(getCountDown() - 1);
			professor.setDialog(Integer.toString(getCountDown()));
			if (getCountDown() == 0) {
				try {
					sendGet();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (getCountDown() < 0) {

			Starfield starfield = (Starfield) getVisibleEntityCollection().getEntity(EntityNames.STARFIELD);
			starfield.setAnimation(Animation.FLY);
			getVisibleEntityCollection().addEntity(EntityNames.FLAME,
					getHiddenEntityCollection().getEntity(EntityNames.FLAME));
			getVisibleEntityCollection().removeEntity(EntityNames.PROFESSOR);

			setFlightTickCount(getFlightTickCount() + 1);
		}

	}

	private void sendGet() throws Exception {

		URL obj = new URL(RELAY_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + RELAY_URL);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	@Override
	public void init() {
		pack(VISABLE_ENTITIES);
		setTickCount(0);
		setCountDown(10);
		setFlightTickCount(0);
		setMusic(SoundEffect.MOONTHEME.play());
	}

	@Override
	public void establishPins() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wipePins() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClick(MouseEvent mouseEvent) {

	}

	private int getTickCount() {
		return tickCount;
	}

	private void setTickCount(int tickCount) {
		this.tickCount = tickCount;
	}

	private void setCountDown(int countDown) {
		this.countDown = countDown;
	}

	private int getCountDown() {
		return countDown;
	}

	private void setMusic(Clip music) {
		this.music = music;
	}

	private Clip getMusic() {
		return music;
	}

	public int getFlightTickCount() {
		return flightTickCount;
	}

	public void setFlightTickCount(int flightTickCount) {
		this.flightTickCount = flightTickCount;
	}

}
