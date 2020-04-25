package com.tetris.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.tetris.implementation.FileManager;

public class GameLevelWindow extends JFrame {

	private FileManager fileManager;
	private StringBuilder sb;
	private JRadioButton lowGameLevelButton, mediumGameLevelButton, highGameLevelButton;
	private ButtonGroup buttonGroup;
	        String[] splitted;
	private String hardLevelGameFromFileString ="";
	private String lowGameLevelString = "Latwy";
	private String mediumGameLevelString = "Sredni";
	private String highGameLevelString = "Trudny";

	public GameLevelWindow() {
		
		loadHardLevelGameSettingsFromFile();
		
		fileManager = new FileManager();
		
		setTitle("Poziom trudnoœci gry");
		setContentPane(new JLabel(new ImageIcon("src/main/resources/TetrisHardLevellTheme.png")));
		setLayout(null);
		setSize(995, 695);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(false);
		
		Font font = new Font("Arial", Font.PLAIN, 32);
		Font font2 = new Font("Arial", Font.PLAIN, 22);
		
		buttonGroup = new ButtonGroup();
		
		
		
		
		
		
		
		
		JTextField textfield1 = new JTextField();
		textfield1.setFont(font);
		textfield1.setEditable(false);
		textfield1.setText(" Wybierz poziom trudnoœci: ");
		textfield1.setBounds(325, 160, 420, 50);
		textfield1.setBorder(BorderFactory.createEmptyBorder());
		textfield1.setBackground(new Color(224, 224, 224));
		add(textfield1);


		lowGameLevelButton = new JRadioButton(lowGameLevelString);
		lowGameLevelButton.setFont(font2);
		lowGameLevelButton.setActionCommand(lowGameLevelString);
		//lowGameLevelButton.setSelected(true);
		lowGameLevelButton.setBounds(215, 410, 120, 40);
		lowGameLevelButton.setBorder(BorderFactory.createEmptyBorder());
		lowGameLevelButton.setBackground(new Color(224, 224, 224));
		buttonGroup.add(lowGameLevelButton);
		add(lowGameLevelButton);
		
		lowGameLevelButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	setHardLevelGameFromFileString(new String(lowGameLevelString));
	        	saveGameHardLevelConfToFile();
	        }
	    });

			
		
		mediumGameLevelButton = new JRadioButton(mediumGameLevelString);
		mediumGameLevelButton.setFont(font2);
		mediumGameLevelButton.setActionCommand(mediumGameLevelString);
		mediumGameLevelButton.setBounds(455, 410,120, 40);
		mediumGameLevelButton.setBackground(new Color(224, 224, 224));
		mediumGameLevelButton.setBorder(BorderFactory.createEmptyBorder());
		buttonGroup.add(mediumGameLevelButton);
		add(mediumGameLevelButton);
		
		mediumGameLevelButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	setHardLevelGameFromFileString(new String(mediumGameLevelString));
	        	saveGameHardLevelConfToFile();
	        }
	    });
		
		
		
		highGameLevelButton = new JRadioButton(highGameLevelString);
		highGameLevelButton.setFont(font2);
		highGameLevelButton.setActionCommand(highGameLevelString);
		highGameLevelButton.setBounds(695, 410,120, 40);
		highGameLevelButton.setBackground(new Color(224, 224, 224));
		highGameLevelButton.setBorder(BorderFactory.createEmptyBorder());
		buttonGroup.add(highGameLevelButton);
		add(highGameLevelButton);
		
		highGameLevelButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	setHardLevelGameFromFileString(new String(highGameLevelString));
	        	saveGameHardLevelConfToFile();
	        }
	    });
		
		
		
		
		if(hardLevelGameFromFileString.equals(lowGameLevelString))
			lowGameLevelButton.setSelected(true);
		else if(hardLevelGameFromFileString.equals(mediumGameLevelString))
			mediumGameLevelButton.setSelected(true);
		else if(hardLevelGameFromFileString.equals(highGameLevelString))
			highGameLevelButton.setSelected(true);
		
		

	}
	
	public String getSelectedButtonValueText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
	

	public String getHardLevelGameFromFileString() {
		return hardLevelGameFromFileString;
	}

	public void setHardLevelGameFromFileString(String hardLevelGameFromFileString) {
		this.hardLevelGameFromFileString = hardLevelGameFromFileString;
	}
	
	
	public void loadHardLevelGameSettingsFromFile() {

		try {
			FileInputStream fstream = new FileInputStream("config.txt");

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) {

				splitted = strLine.split("#");
				setHardLevelGameFromFileString(splitted[7]);

			}

			in.close();
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	
	private void saveGameHardLevelConfToFile() {
		sb = new StringBuilder();
		splitted[7]=getHardLevelGameFromFileString();
		String joined = String.join("#", splitted);
		sb.append(joined);
		sb.append("#");
		fileManager.writeAppConfigFileMethod(sb);
	}


	protected void finalize() {
		saveGameHardLevelConfToFile();
	}
}
