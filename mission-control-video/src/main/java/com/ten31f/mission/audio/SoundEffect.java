package com.ten31f.mission.audio;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum SoundEffect {

	FOOTSTEP("audio/footstep01.wav"), BLIP("audio/blip01.wav"), COMMS("audio/comms.wav"),
	LIFESUPPORT("audio/life-support.wav"), WATERSYSTEM("audio/waterSystem.wav"), TANG("audio/tang.wav"),
	CHARGE("audio/charge.wav"), FUELPUMP("audio/fuelPump.wav"), ROCKET1("audio/rumble1.wav"),
	ROCKET2("audio/rumble2.wav"), MOONTHEME("audio/duckTalesMusic-nes-moonTheme.wav"), MAINLOOP("audio/mainLoop.wav");

	// Nested class for specifying volume
	public static enum Volume {
		MUTE, LOW, MEDIUM, HIGH
	}

	public static Volume volume = Volume.LOW;

	// Each sound effect has its own clip, loaded with its own sound file.
	private Clip clip;

	// Constructor to construct each element of the enum with its own sound file.
	SoundEffect(String soundFileName) {
//		try {
//			// Use URL (instead of File) to read from disk and JAR.
//			URL url = this.getClass().getClassLoader().getResource(soundFileName);
//			// Set up an audio input stream piped from the sound file.
//			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
//			// Get a clip resource.
//			AudioFormat format = audioInputStream.getFormat();
//			DataLine.Info info = new DataLine.Info(Clip.class, format);
//			clip = (Clip) AudioSystem.getLine(info);
//			// Open audio clip and load samples from the audio input stream.
//			clip.open(audioInputStream);
//		} catch (UnsupportedAudioFileException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (LineUnavailableException e) {
//			e.printStackTrace();
//		}
		
		
//		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFileName);
//		
//		AudioFormat format = audioInputStream.getFormat();
//		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
//		// line is *capable* of being opened in format
//		SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
//		// open in desired format
//		line.open(format);
		
	}

	// Play or Re-play the sound effect from the beginning, by rewinding.
	public Clip play() {
//		if (volume != Volume.MUTE) {
//			if (clip.isRunning())
//				clip.stop(); // Stop the player if it is still running
//			clip.setFramePosition(0); // rewind to the beginning
//			clip.start(); // Start playing
//		}

		return clip;
	}

	// Optional static method to pre-load all the sound files.
	static void init() {
		values(); // calls the constructor for all the elements
	}

}
