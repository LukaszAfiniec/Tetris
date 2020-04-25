package com.tetris.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.tetris.implementation.FileManager;


public class SettingsWindow extends JFrame{
	{
		 fileManager = new FileManager();
		 loadSettingsFromFile();
	}
		private JCheckBox jCheckBox,jCheckBox2;
		private StringBuilder sb;
		private FileManager fileManager;
		private boolean welcomeWindowStatus;
		private boolean gridValueStatus;
		private String SplashScreenStatusFromFileString;
		private String GridValueStatusFromFileString;
		String[] splitted;
	
		public SettingsWindow() {

			setTitle("Ustawienia gry");
			setBackground(new Color(224, 224, 224));
			setLayout(null);
			setSize(740, 350);
			setResizable(false);
			setLocationRelativeTo(null);
			setFocusable(false);
			getContentPane().setLayout(null);
			setLocationRelativeTo(null);

			JLabel lblName = new JLabel("Ekran z logiem gry");
			lblName.setFont(new Font("Arial", Font.BOLD, 15));
			lblName.setBounds(90, 90, 150, 30);
			lblName.setBorder(BorderFactory.createEmptyBorder());
			lblName.setBackground(new Color(224, 224, 224));
			getContentPane().add(lblName);
			

			jCheckBox = new JCheckBox("Wy³¹cz okno z logiem gry.");
			jCheckBox.setBounds(450, 90, 180, 30);
			jCheckBox.setBorder(BorderFactory.createEmptyBorder());
			jCheckBox.setBackground(new Color(224, 224, 224));
			jCheckBox.setFocusable(false);
			jCheckBox.setSelected(getWelcomeWindowStatus());
			jCheckBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (jCheckBox.isSelected()) {
						setWelcomeWindowStatus(true);
						saveSettingsConfToFile();
					} else {
						setWelcomeWindowStatus(false);
						saveSettingsConfToFile();
					}
				}
			});			
			getContentPane().add(jCheckBox);
			
			
			
			JSeparator s = new JSeparator(); 
	        s.setOrientation(SwingConstants.HORIZONTAL); 
	        s.setBounds(75, 140, 570, 15);
	        getContentPane().add(s);
			
			
			

			JLabel lblName2 = new JLabel("Rysowanie siatki pomocniczej na planszy gry.");
			lblName2.setFont(new Font("Arial", Font.BOLD, 15));
			lblName2.setBounds(90, 180, 450, 30);
			lblName2.setBorder(BorderFactory.createEmptyBorder());
			lblName2.setBackground(new Color(224, 224, 224));
			getContentPane().add(lblName2);
			getContentPane().add(lblName2);

			jCheckBox2 = new JCheckBox("Wy³¹cz siatkê.");
			jCheckBox2.setBounds(450, 180, 100, 30);
			jCheckBox2.setBorder(BorderFactory.createEmptyBorder());
			jCheckBox2.setBackground(new Color(224, 224, 224));
			jCheckBox2.setFocusable(false);
			jCheckBox2.setSelected(getGridValueStatus());
			jCheckBox2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (jCheckBox2.isSelected()) {
						setGridValueStatus(true);
						saveSettingsConfToFile();
					} else {
						setGridValueStatus(false);
						saveSettingsConfToFile();
					}
				}
			});
			getContentPane().add(jCheckBox2);	
			setVisible(true);
		}

		

		public void loadSettingsFromFile() {

			try {
				FileInputStream fstream = new FileInputStream("config.txt");

				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;

				while ((strLine = br.readLine()) != null) {

					splitted = strLine.split("#");
					setSplashScreenStatusFromFileString(splitted[10]);
					setGridValueStatusFileString(splitted[11]);
					
					setWelcomeWindowStatus(Boolean.parseBoolean(getSplashScreenStatusFromFileString()));
					setGridValueStatus(Boolean.parseBoolean(getGridValueStatusFileString()));
				}

				in.close();
				br.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		
		
		private void saveSettingsConfToFile() {
			sb = new StringBuilder();
			splitted[10]=Boolean.toString(getWelcomeWindowStatus());
			splitted[11]=Boolean.toString(getGridValueStatus());
			String joined = String.join("#", splitted);
			sb.append(joined);
			sb.append("#");
			fileManager.writeAppConfigFileMethod(sb);
		}
		
		
		public boolean getWelcomeWindowStatus() {
			return welcomeWindowStatus;
		}

		public void setWelcomeWindowStatus(boolean welcomeWindowStatus) {
			this.welcomeWindowStatus = welcomeWindowStatus;
		}
		
		public String getSplashScreenStatusFromFileString() {
			return SplashScreenStatusFromFileString;
		}

		public void setSplashScreenStatusFromFileString(String splashScreenStatusFromFileString) {
			SplashScreenStatusFromFileString = splashScreenStatusFromFileString;
		}

		public String getGridValueStatusFileString() {
			return GridValueStatusFromFileString;
		}

		public void setGridValueStatusFileString(String gridValueStatusFromFileString) {
			GridValueStatusFromFileString = gridValueStatusFromFileString;
		}

		public boolean getGridValueStatus() {
			return gridValueStatus;
		}

		public void setGridValueStatus(boolean gridValueStatus) {
			this.gridValueStatus = gridValueStatus;
		}
		
		
		
		
		
		protected void finalize() {
			saveSettingsConfToFile();
		}
}
