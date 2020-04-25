package com.tetris.apk;

import java.awt.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;


import com.tetris.ui.SplashScreen;
import com.tetris.ui.WinnerWindow;
import com.tetris.ui.MainWindowParts.MainWindow;

public class Main {

	private static String splashScreenStatusFromFileString;
	private static boolean welcomeWindowStatus;

	public static void main(String[] args) {
		
		WinnerWindow ww = new WinnerWindow("Latwy",100);
		ww.setVisible(true);
		loadSettingsFromFile();
		
		if(!welcomeWindowStatus) {
		SplashScreen splash = new SplashScreen();
		splash.show(500);
		splash.hide();
		}

		EventQueue.invokeLater(() -> {
			MainWindow mainWindow = new MainWindow();
			mainWindow.setVisible(true);
		});
	}

	
	
	public static void loadSettingsFromFile() {

		try {
			FileInputStream fstream = new FileInputStream("config.txt");

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) {

				String[] splitted = strLine.split("#");
				splashScreenStatusFromFileString = splitted[10];

				welcomeWindowStatus = Boolean.parseBoolean(getSplashScreenStatusFromFileString());

			}

			in.close();
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static String getSplashScreenStatusFromFileString() {
		return splashScreenStatusFromFileString;
	}


	public static boolean getWelcomeWindowStatus() {
		return welcomeWindowStatus;
	}

}
