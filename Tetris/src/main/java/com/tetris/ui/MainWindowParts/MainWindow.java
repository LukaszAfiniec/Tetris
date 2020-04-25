package com.tetris.ui.MainWindowParts;

import com.tetris.implementation.game.engine.GameBoard;
import com.tetris.implementation.MediaResources;
import com.tetris.ui.AuthorsWindow;
import com.tetris.ui.GameInstruction;
import com.tetris.ui.GameInstruction2;
import com.tetris.ui.GameLevelWindow;
import com.tetris.ui.HighScoreWindow;
import com.tetris.ui.SettingsWindow;
import com.tetris.ui.SoundSettingsWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {

	private JPanel statusPanel = new JPanel();
	private JMenuBar menuBar;
	private JMenu menuGame, subMenuSettings, menuPlayer, menuHelp, subMenuHelp;
	private JMenuItem menuSettingsItem, menuSoundItem, menuHardLevelItem, menuPlayerScoreItem1, menuPlayerScoreItem2, menuPlayerScoreItem3, menuHelpInstructItem,
	menuHelpInstructItem2, menuHelpAuthorItem, menuExitItem;
	private ImageIcon imageIcon;
	public static JLabel statusbar = new JLabel("", SwingConstants.LEFT);
	private JLabel userStatusDescryptionLabel;
	String outputStringValueString = "";
	private boolean firstGameBoardLaunch = true;
	private boolean windowIsIconfied = false;
	private boolean windowGameIsRunning = false;
	
	GameBoard gameBoard = new GameBoard();
	ControlPanel controlPanel = new ControlPanel(this);

	public MainWindow() {
		
		setTitle("Projekt Tetris - okno g³ówne");

		// Ca³kowite wymiary GameBoard-> Width: 328 Heigth: 705
		// Wymiary MainWindow-> Width: 644, Heigth: 793
		setSize(584, 793);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		gameBoard.setVisible(false);
		
		controlPanel.pauseButton.setEnabled(false);
		controlPanel.stopButton.setEnabled(false);
		imageIcon = new ImageIcon("src/main/resources/TetrisIkona.png");
		setIconImage(imageIcon.getImage());
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuGame = new JMenu("Opcje gry");		
		subMenuSettings = new JMenu("Ustawienia");
		menuSettingsItem = new JMenuItem("Ustawienia gry");
		menuSoundItem = new JMenuItem("Muzyka");
		menuHardLevelItem = new JMenuItem("Poziom trudnoœci");
		subMenuSettings.add(menuSettingsItem);
		subMenuSettings.add(menuSoundItem);
		subMenuSettings.add(menuHardLevelItem);
	
		menuExitItem = new JMenuItem("Exit");
		menuBar.add(menuGame);
		menuGame.add(subMenuSettings);
		menuGame.addSeparator();
		menuGame.add(menuExitItem);
		
		
		
		menuPlayer = new JMenu("K¹cik gracza");
		menuPlayerScoreItem1 = new JMenuItem("Ranking graczy (latwy poziom)");
		menuPlayerScoreItem2 = new JMenuItem("Ranking graczy (sredni poziom)");
		menuPlayerScoreItem3 = new JMenuItem("Ranking graczy (trudny poziom)");
		menuBar.add(menuPlayer);
		menuPlayer.add(menuPlayerScoreItem1);
		menuPlayer.add(menuPlayerScoreItem2);
		menuPlayer.add(menuPlayerScoreItem3);
		
		
		menuHelp = new JMenu("Pomoc");
		subMenuHelp = new JMenu("Porady dotycz¹ce grania");	
		menuHelpInstructItem = new JMenuItem("Instrukcja do gry");
		menuHelpInstructItem2 = new JMenuItem("Opis sterowania w grze");
		subMenuHelp.add(menuHelpInstructItem);
		subMenuHelp.add(menuHelpInstructItem2);
		menuHelpAuthorItem = new JMenuItem("Autorzy");
		menuBar.add(menuHelp);
		menuHelp.add(subMenuHelp);
		menuHelp.add(menuHelpAuthorItem);

		

		

		
		
		userStatusDescryptionLabel = new JLabel("Status Programu: ", SwingConstants.LEFT);
		statusPanel.add(userStatusDescryptionLabel);
		statusPanel.add(statusbar);
		
		add(controlPanel, BorderLayout.EAST);
		add(statusPanel, BorderLayout.NORTH);
		add(gameBoard, BorderLayout.CENTER);
		
		
		controlPanel .setVisible(true);
		
		
		menuSettingsItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				settingsWindowActionPerformed(evt);
			}
		});
		
		menuSoundItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				soundWindowActionPerformed(evt);
			}
		});

		menuHardLevelItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuHardLevelActionPerformed(evt);
			}
		});
		
		
		menuPlayerScoreItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuPlayerScoreItem1ActionPerformed(evt);
			}
		});
		
		menuPlayerScoreItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuPlayerScoreItem2ActionPerformed(evt);
			}
		});
		
		menuPlayerScoreItem3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuPlayerScoreItem3ActionPerformed(evt);
			}
		});
		
		menuHelpInstructItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuGameInstructionActionPerformed(evt);
			}
		});
		
		menuHelpInstructItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuGameInstruction2ActionPerformed(evt);
			}
		});
		
		menuHelpAuthorItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				autorzyActionPerformed(evt);
			}
		});
		

		menuExitItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(0);
			}
		});

		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg0) {
				tetrisWindowStateChanged(arg0);
			}
		});

	}

	public void tetrisWindowStateChanged(WindowEvent e) {
		if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
			MediaResources.pauseMusic();
			windowIsIconfied = true;
			gameBoard.pauseGameMethod();
		} else if ((e.getNewState() & Frame.NORMAL) == Frame.NORMAL) {
			windowIsIconfied = false;
			if(windowGameIsRunning == true) {
				MediaResources.startMusic();
				gameBoard.startGameMethod();
			}
		}

	}
	
	

	private void settingsWindowActionPerformed(java.awt.event.ActionEvent evt) {
		SettingsWindow sw = new SettingsWindow();
		sw.setVisible(true);
	}
	
	private void soundWindowActionPerformed(java.awt.event.ActionEvent evt) {
		SoundSettingsWindow soundSettingsWindow = new SoundSettingsWindow();
		soundSettingsWindow.setVisible(true);
	}

	
	private void menuHardLevelActionPerformed(java.awt.event.ActionEvent evt) {
		GameLevelWindow glw = new GameLevelWindow();
		glw.setVisible(true);
	}
	
	
	private void menuPlayerScoreItem1ActionPerformed(java.awt.event.ActionEvent evt) {
		HighScoreWindow highScoreWindow = new HighScoreWindow(1);
		highScoreWindow.setVisible(true);
	}
	
	private void menuPlayerScoreItem2ActionPerformed(java.awt.event.ActionEvent evt) {
		HighScoreWindow highScoreWindow = new HighScoreWindow(2);
		highScoreWindow.setVisible(true);
	}
	
	private void menuPlayerScoreItem3ActionPerformed(java.awt.event.ActionEvent evt) {
		HighScoreWindow highScoreWindow = new HighScoreWindow(3);
		highScoreWindow.setVisible(true);
	}
	
	
	private void menuGameInstructionActionPerformed(java.awt.event.ActionEvent evt) {
		GameInstruction gi = new GameInstruction();
		gi.setVisible(true);
	}
	
	private void menuGameInstruction2ActionPerformed(java.awt.event.ActionEvent evt) {
		GameInstruction2 gi2 = new GameInstruction2();
		gi2.setVisible(true);
	}


	private void autorzyActionPerformed(java.awt.event.ActionEvent evt) {
		AuthorsWindow aw = new AuthorsWindow();
		aw.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == controlPanel.startButton) {
			windowGameIsRunning = true;
			controlPanel.startButton.setEnabled(false);
			MediaResources.loadMusicSettingsFromFile();
			if (firstGameBoardLaunch && windowIsIconfied == false) {
				MediaResources.prepareLoopMusic();
				loadHardLevelGameSettingsFromFile();
				firstGameBoardLaunch = false;
				gameBoard.setVisible(true);
				controlPanel.pauseButton.setEnabled(true);
				controlPanel.stopButton.setEnabled(true);
				gameBoard.firstStart();
				ControlPanel.userGameLevelLabel.setText("1");
				ControlPanel.userHardGameLevelLabel.setText(outputStringValueString);

			} else {
					controlPanel.pauseButton.setEnabled(true);
					MediaResources.startMusic();
					MediaResources.loadMusicSettingsFromFile();
					gameBoard.startGameMethod();
				}
			

		} else if (e.getSource() == controlPanel.pauseButton) {
			MediaResources.pauseMusic();
			controlPanel.startButton.setEnabled(true);
			controlPanel.pauseButton.setEnabled(false);
			windowGameIsRunning = false;
			gameBoard.pauseGameMethod();
			MediaResources.loadMusicSettingsFromFile();

		} else if (e.getSource() == controlPanel.stopButton) {
			MediaResources.stopMusic();
			MediaResources.loadMusicSettingsFromFile();
			loadHardLevelGameSettingsFromFile();
			gameBoard.stopGameMethod();
			gameBoard.setVisible(false);
			windowGameIsRunning = false;
			firstGameBoardLaunch = true;
			controlPanel.startButton.setEnabled(true);
			controlPanel.pauseButton.setEnabled(false);
			controlPanel.stopButton.setEnabled(false);
			ControlPanel.userScoreLabel.setText(Integer.toString(0));
			ControlPanel.userGameLevelLabel.setText(" ");
			ControlPanel.userHardGameLevelLabel.setText(outputStringValueString);
		}

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
