package com.tetris.implementation;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MediaResources {
{
	loadMusicSettingsFromFile();
}
	
	
	
	private static float volumeValue=-1000f;
	private static boolean isMute;
	private static Clip clip;
	private static FloatControl gainControl;
	private static AudioInputStream audioInputStream;
	private static long clipTime = -10;
	private static String volumeValueFromFileString = "";
	private static String musicIsMuteFromFileString = "";

	

	public static void prepareLoopMusic() {
		loadMusicSettingsFromFile();
		if (!isMute) {
		try {
			File musicThemePath = new File("src/main/resources/music/Tetris_theme.WAV");

			if (musicThemePath.exists()) {
				audioInputStream = AudioSystem.getAudioInputStream(musicThemePath);
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(volumeValue);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}

	public float getVolumeValue() {
		return volumeValue;
	}

	

	public static void startMusic() {
		if (!isMute) {
			prepareLoopMusic();
			clip.setMicrosecondPosition(clipTime);
			clip.start();
		}
	}

	public static void pauseMusic() {
		if (!isMute) {
			try {
			clipTime = (long) clip.getMicrosecondPosition();
			clip.stop();
			} catch (NullPointerException ex) {}
			
		} 
	}

	public static void stopMusic() {
		if (!isMute) {
			clipTime = 0;
			clip.stop();
		}

	}

	public static void loadMusicSettingsFromFile() {

		try {
			FileInputStream fstream = new FileInputStream("config.txt");

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) {

				String[] splitted = strLine.split("#");
				volumeValueFromFileString = (splitted[3]);
				musicIsMuteFromFileString = (splitted[4]);

				volumeValue = (Float.parseFloat(getVolumeValueFromFileString()));
				isMute = Boolean.parseBoolean(getMusicIsMuteFromFileString());

			}

			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	

	public static String getVolumeValueFromFileString() {
		return volumeValueFromFileString;
	}


	public static String getMusicIsMuteFromFileString() {
		return musicIsMuteFromFileString;
	}


}
