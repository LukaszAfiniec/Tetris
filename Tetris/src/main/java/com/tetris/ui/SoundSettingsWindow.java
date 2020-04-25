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
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.tetris.implementation.FileManager;
import com.tetris.implementation.MediaResources;

public class SoundSettingsWindow extends JFrame {

	MediaResources mr;

	private JSlider slider;
	private JCheckBox jCheckBox;
	private FileManager fileManager;
	private StringBuilder sb;
	MediaResources mediaResources; 

	private int volumeValue;
	private boolean isMute;
	private String volumeValueFromFileString = "";
	private String musicIsMuteFromFileString = "";

	public SoundSettingsWindow() {

		loadMusicSettingsFromFile();
		fileManager = new FileManager();
	

		
		setTitle("Ustawienia muzyki");
		setContentPane(new JLabel(new ImageIcon("src/main/resources/TetrisMusicSettings.png")));
		setLayout(null);
		setSize(550, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(false);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblName = new JLabel("Regulacja g³oœnoœci w grze",SwingConstants.CENTER);
		lblName.setFont(new Font("Arial", Font.BOLD, 16));
		lblName.setBounds(145, 135, 240, 30);
		lblName.setBorder(BorderFactory.createEmptyBorder());
		lblName.setBackground(new Color(224, 224, 224));
		getContentPane().add(lblName);

		slider = new JSlider(JSlider.HORIZONTAL, -80, 6, getVolumeValue());
		slider.setBounds(90, 170, 370, 40);
		slider.setBorder(BorderFactory.createEmptyBorder());
		slider.setBackground(new Color(224, 224, 224));
		slider.setMajorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(false);
		getContentPane().add(slider);

		JLabel lblSliderMinValue = new JLabel("0");
		lblSliderMinValue.setBounds(95, 215, 20, 20);
		lblSliderMinValue.setBorder(BorderFactory.createEmptyBorder());
		lblSliderMinValue.setBackground(new Color(224, 224, 224));
		getContentPane().add(lblSliderMinValue);

		JLabel lblSliderMaxValue = new JLabel("100");
		lblSliderMaxValue.setBounds(435, 215, 40, 20);
		lblSliderMaxValue.setBorder(BorderFactory.createEmptyBorder());
		lblSliderMaxValue.setBackground(new Color(224, 224, 224));
		getContentPane().add(lblSliderMaxValue);
		
        JSeparator s = new JSeparator(); 
        s.setOrientation(SwingConstants.HORIZONTAL); 
        s.setBounds(75, 307, 385, 15);
        getContentPane().add(s);
        
		jCheckBox = new JCheckBox("Wy³¹cz muzykê w grze.");
		jCheckBox.setBounds(90, 340, 180, 20);
		jCheckBox.setBorder(BorderFactory.createEmptyBorder());
		jCheckBox.setBackground(new Color(224, 224, 224));
		jCheckBox.setFocusable(false);
		jCheckBox.setSelected(getMute());
		jCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jCheckBox.isSelected()) {
					setMute(true);
					saveMusicConfToFile();
				} else {
					setMute(false);
					saveMusicConfToFile();
				}
			}
		});

		getContentPane().add(jCheckBox);

		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent ce) {
				slider = (JSlider) ce.getSource();
				
				setVolumeValue(slider.getValue());
				saveMusicConfToFile();
			}
		});

		setVisible(true);
	}

	public void loadMusicSettingsFromFile() {

		try {
			FileInputStream fstream = new FileInputStream("config.txt");

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) {

				String[] splitted = strLine.split("#");
				setVolumeValueFromFileString(splitted[3]);
				setMusicIsMuteFromFileString(splitted[4]);

				setVolumeValue(Integer.parseInt(getVolumeValueFromFileString()));
				setMute(Boolean.parseBoolean(getMusicIsMuteFromFileString()));

			}

			in.close();
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void saveMusicConfToFile() {
		sb = new StringBuilder();
		sb.append("#1#Music#");
		sb.append(getVolumeValue());
		sb.append("#");
		sb.append(getMute());
		sb.append("#");
		fileManager.writeAppConfigFileMethod(sb);
	}

	public String getVolumeValueFromFileString() {
		return volumeValueFromFileString;
	}

	public void setVolumeValueFromFileString(String volumeValueFromFileString) {
		this.volumeValueFromFileString = volumeValueFromFileString;
	}

	public String getMusicIsMuteFromFileString() {
		return musicIsMuteFromFileString;
	}

	public void setMusicIsMuteFromFileString(String musicIsMuteFromFileString) {
		this.musicIsMuteFromFileString = musicIsMuteFromFileString;
	}

	public void setMute(boolean isMute) {
		this.isMute = isMute;
	}

	public boolean getMute() {
		return isMute;
	}

	public int getVolumeValue() {
		return volumeValue;
	}

	public void setVolumeValue(int volumeValue) {
		this.volumeValue = volumeValue;
	}

	protected void finalize() {
		saveMusicConfToFile();
	}
}
