package com.tetris.ui.MainWindowParts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.tetris.implementation.game.engine.GameBoard;


public class ControlPanel extends JPanel {

	private BufferedImage image;
	public static JButton startButton, pauseButton, stopButton;
	
	public static JLabel userGameLevelLabel;
	public static JLabel userScoreLabel;
	public static JLabel userHardGameLevelLabel;
	
	private JLabel EmptyLabel, EmptyLabel1, EmptyLabel2, EmptyLabel3, EmptyLabel4, EmptyLabel5, EmptyLabel6, EmptyLabel7, userScoreDescryptionLabel;
	GridBagLayout gridBagLayout = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	MainWindow mainWindow;
	
	String outputStringValueString = "";

	public ControlPanel(MainWindow parent) {

		loadHardLevelGameSettingsFromFile();
		
		this.setVisible(true);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		EmptyLabel = new JLabel(" ", SwingConstants.CENTER);
		EmptyLabel.setFont(new Font("Arial", Font.BOLD, 15));
		EmptyLabel.setPreferredSize(new Dimension(200, 25));
		EmptyLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(EmptyLabel, gbc);
		
		EmptyLabel1 = new JLabel(" ", SwingConstants.CENTER);
		EmptyLabel1.setFont(new Font("Arial", Font.BOLD, 15));
		EmptyLabel1.setPreferredSize(new Dimension(200, 25));
		EmptyLabel1.setAlignmentX(CENTER_ALIGNMENT);
		add(EmptyLabel1, gbc);

		EmptyLabel2 = new JLabel("Poziom gry: ", SwingConstants.CENTER);
		EmptyLabel2.setFont(new Font("Arial Black", Font.BOLD, 16));
		EmptyLabel2.setPreferredSize(new Dimension(200, 20));
		EmptyLabel2.setAlignmentX(CENTER_ALIGNMENT);
		add(EmptyLabel2, gbc);
		
		userGameLevelLabel = new JLabel(" ", SwingConstants.CENTER);
		userGameLevelLabel.setFont(new Font("Arial", Font.BOLD, 15));
		userGameLevelLabel.setPreferredSize(new Dimension(200, 40));
		userGameLevelLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(userGameLevelLabel, gbc);
		
		

		userScoreDescryptionLabel = new JLabel("Twój wynik to: ", SwingConstants.CENTER);
		userScoreDescryptionLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		userScoreDescryptionLabel.setPreferredSize(new Dimension(200, 30));
		userScoreDescryptionLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(userScoreDescryptionLabel, gbc);

		userScoreLabel = new JLabel("0", SwingConstants.CENTER);
		userScoreLabel.setFont(new Font("Arial", Font.BOLD, 15));
		userScoreLabel.setPreferredSize(new Dimension(200, 40));
		userScoreLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(userScoreLabel, gbc);
		
		EmptyLabel3 = new JLabel(" ", SwingConstants.CENTER);
		EmptyLabel3.setFont(new Font("Arial", Font.BOLD, 18));
		EmptyLabel3.setPreferredSize(new Dimension(200, 20));
		EmptyLabel3.setAlignmentX(CENTER_ALIGNMENT);
		add(EmptyLabel3, gbc);
		
		add(EmptyLabel3,gbc);
		startButton = new JButton("Start");
		startButton.setBackground(Color.green);
		startButton.setFocusable(false);
		startButton.setAlignmentX(CENTER_ALIGNMENT);
		startButton.getPreferredSize();
		startButton.addActionListener(parent);
		add(startButton, gbc);
		
		EmptyLabel4 = new JLabel(" ", SwingConstants.CENTER);
		EmptyLabel4.setFont(new Font("Arial", Font.BOLD, 18));
		EmptyLabel4.setPreferredSize(new Dimension(200, 20));
		EmptyLabel4.setAlignmentX(CENTER_ALIGNMENT);
		add(EmptyLabel4, gbc);
		

		pauseButton = new JButton("Pause");
		pauseButton.setBackground(Color.yellow);
		pauseButton.setFocusable(false);
		pauseButton.setAlignmentX(CENTER_ALIGNMENT);
		pauseButton.getPreferredSize();
		pauseButton.addActionListener(parent);
		add(pauseButton, gbc);
		
		EmptyLabel5 = new JLabel(" ", SwingConstants.CENTER);
		EmptyLabel5.setFont(new Font("Arial", Font.BOLD, 18));
		EmptyLabel5.setPreferredSize(new Dimension(200, 20));
		EmptyLabel5.setAlignmentX(CENTER_ALIGNMENT);
		add(EmptyLabel5, gbc);
		
		
		stopButton = new JButton("Stop");
		stopButton.setBackground(Color.red);
		stopButton.setFocusable(false);
		stopButton.setAlignmentX(CENTER_ALIGNMENT);
		stopButton.getPreferredSize();
		stopButton.addActionListener(parent);
		add(stopButton, gbc);
		
		EmptyLabel6 = new JLabel(" ", SwingConstants.CENTER);
		EmptyLabel6.setFont(new Font("Arial", Font.BOLD, 18));
		EmptyLabel6.setPreferredSize(new Dimension(200, 20));
		EmptyLabel6.setAlignmentX(CENTER_ALIGNMENT);
		add(EmptyLabel6, gbc);
		
		

		EmptyLabel7 = new JLabel("Poziom trudnoœci:", SwingConstants.CENTER);
		EmptyLabel7.setFont(new Font("Arial Black", Font.BOLD, 16));
		EmptyLabel7.setPreferredSize(new Dimension(240, 20));
		EmptyLabel7.setAlignmentX(CENTER_ALIGNMENT);
		add(EmptyLabel7, gbc);
		
		
		
		userHardGameLevelLabel = new JLabel(" ", SwingConstants.CENTER);
		userHardGameLevelLabel.setFont(new Font("Arial", Font.BOLD, 15));
		userHardGameLevelLabel.setPreferredSize(new Dimension(200, 40));
		userHardGameLevelLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		userHardGameLevelLabel.setText(outputStringValueString);
		
		
		add(userHardGameLevelLabel, gbc);

		
		
	
		try {
			image = ImageIO.read(new File("src/main/resources/TetrisControlPanel.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	
	
	
	
	
	public void loadHardLevelGameSettingsFromFile() {

		try {
			FileInputStream fstream = new FileInputStream("config.txt");

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) {

				String[] splitted = strLine.split("#");
				outputStringValueString = splitted[7];
			}

			in.close();
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
