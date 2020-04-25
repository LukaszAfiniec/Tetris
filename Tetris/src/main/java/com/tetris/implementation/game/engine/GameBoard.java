package com.tetris.implementation.game.engine;

import com.tetris.implementation.TetrisLevel;
import com.tetris.implementation.game.engine.Shape.Tetrominoe;
import com.tetris.ui.MainWindowParts.ControlPanel;
import com.tetris.ui.MainWindowParts.MainWindow;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class GameBoard extends JPanel implements ActionListener {

	private final int TETRIS_BOARD_WIDTH = 10;
	private final int TETRIS_BOARD_HEIGHT = 22;

	Timer timer;
	boolean isStarted = false;
	boolean isPaused = false;
	String booleanValueString;
	String[] tabelaGraczyString = new String[91];
	String hardLevelGameFromString = "";
	private boolean isFallingFinished = false;
	public int numLinesRemoved = 0;
	private int curX = 0;
	private int curY = 0;
	private Shape curPiece;
	private Shape nextPiece;
	private Tetrominoe[] board;
	int gameScore = 0;
	TetrisLevel tetrisLevel = new TetrisLevel();

	public GameBoard() {
		setFocusable(true);
		curPiece = new Shape();
		nextPiece = new Shape();

		curPiece.setRandomShape();
		nextPiece.setRandomShape();
		timer = new Timer(1000, this);
		board = new Tetrominoe[TETRIS_BOARD_WIDTH * TETRIS_BOARD_HEIGHT];
		addKeyListener(new KeyListenerAdapter());
		clearBoard();
		gameScore = 0;
		tetrisLevel.setHardLevel(1);
		tetrisLevel.setTetrisLevel(1);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isFallingFinished) {
			isFallingFinished = false;
			newPiece();
		} else {
			oneLineDown();
		}
	}

	private int squareWidth() {
		return (int) getWidth() / TETRIS_BOARD_WIDTH;
	}

	private int squareHeight() {
		return (int) getHeight() / TETRIS_BOARD_HEIGHT;
	}

	private Tetrominoe shapeAt(int x, int y) {
		return board[(y * TETRIS_BOARD_WIDTH) + x];
	}

	public void firstStart() {
		MainWindow.statusbar.setText("Game started.");
		setFocusable(true);
		requestFocusInWindow();
		isFallingFinished = false;
		numLinesRemoved = 0;
		clearBoard();
		newPiece();
		timer.start();

		curPiece.setRandomShape();
		nextPiece.setRandomShape();
		tetrisLevel.setTetrisLevel(1);
		isStarted = true;
		isPaused = false;
		loadHardLevelGameSettingsFromFile();
		loadUserScoreFromFile();
	}

	public void pauseGameMethod() {
		timer.stop();
		MainWindow.statusbar.setText("Game paused.");
		repaint();
		isStarted = false;
		isPaused = true;
	}

	public void stopGameMethod() {
		isFallingFinished = true;
		numLinesRemoved = 0;
		clearBoard();
		timer.stop();
		gameScore = 0;
		MainWindow.statusbar.setText("Game stoped.");
		tetrisLevel.setTetrisLevel(1);
		isStarted = false;
		isPaused = false;
		loadHardLevelGameSettingsFromFile();
	}

	public void startGameMethod() {
		MainWindow.statusbar.setText("Game started.");
		timer.start();
		isStarted = true;
		isPaused = false;
	}

	private void doDrawing(Graphics g) {

		Dimension size = getSize();
		int boardTop = (int) size.getHeight() - TETRIS_BOARD_HEIGHT * squareHeight();

		for (int i = 0; i < TETRIS_BOARD_HEIGHT; ++i) {
			for (int j = 0; j < TETRIS_BOARD_WIDTH; ++j) {

				Tetrominoe shape = shapeAt(j, TETRIS_BOARD_HEIGHT - i - 1);

				if (shape != Tetrominoe.NoShape)
					drawSquare(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
			}
		}

		if (curPiece.getShape() != Tetrominoe.NoShape) {

			for (int i = 0; i < 4; ++i) {

				int x = curX + curPiece.x(i);
				int y = curY - curPiece.y(i);

				drawSquare(g, x * squareWidth(), boardTop + (TETRIS_BOARD_HEIGHT - y - 1) * squareHeight(),
						curPiece.getShape());
			}
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(!Boolean.parseBoolean(booleanValueString)) {
		// Grid start
		g.setColor(Color.lightGray);
		int sideLength = 32;
		int nRowCount = (int) TETRIS_BOARD_WIDTH * 2 + 2;
		int currentX = sideLength;
		for (int i = 0; i < nRowCount; i++) {
			g.drawLine(0, currentX, getWidth(), currentX);
			currentX = currentX + sideLength;
		}

		int nColumnCount = (int) TETRIS_BOARD_HEIGHT;
		int currentY = sideLength;
		for (int i = 0; i < nColumnCount; i++) {
			g.drawLine(currentY, 0, currentY, getHeight());
			currentY = currentY + sideLength;
		}
		// Grid end
		}
		
		doDrawing(g);
	}

	private void dropDown() {
		if (curPiece.getShape() == Tetrominoe.NoShape) {
		}
		int newY = curY;
		while (newY > 0) {
			if (!tryMove(curPiece, curX, newY - 1)) {
				break;
			}
			--newY;
		}
		if (curPiece.getShape() == Tetrominoe.NoShape) {
		}
		pieceDropped();
	}

	private void oneLineDown() {
		if (curPiece.getShape() == Tetrominoe.NoShape) {
		}

		if (!tryMove(curPiece, curX, curY - 1)) {
			pieceDropped();
		}

		if (curPiece.getShape() == Tetrominoe.NoShape) {
		}

	}

	private void clearBoard() {
		for (int i = 0; i < TETRIS_BOARD_HEIGHT * TETRIS_BOARD_WIDTH; ++i) {
			board[i] = Tetrominoe.NoShape;
		}
	}

	private void pieceDropped() {

		for (int i = 0; i < 4; ++i) {
			int x = curX + curPiece.x(i);
			int y = curY - curPiece.y(i);

			curPiece.getShape();

			board[(y * TETRIS_BOARD_WIDTH) + x] = curPiece.getShape();
		}

		removeFullLines();

		if (!isFallingFinished) {
			if (curPiece.getShape() != Tetrominoe.NoShape && nextPiece.getShape() != Tetrominoe.NoShape) {
				curPiece.setShape(nextPiece.getShape());
				newPiece();
			} else if (curPiece.getShape() == Tetrominoe.NoShape && nextPiece.getShape() != Tetrominoe.NoShape) {
				nextPiece.setRandomShape();
				curPiece.setShape(nextPiece.getShape());
			}

		}

	}

	private void newPiece() {
		if (curPiece.getShape() == Tetrominoe.NoShape && nextPiece.getShape() != Tetrominoe.NoShape) {
			curPiece.setShape(nextPiece.getShape());
		}
		nextPiece.setRandomShape();

		curX = TETRIS_BOARD_WIDTH / 2 + 1;
		curY = TETRIS_BOARD_HEIGHT - 1 + curPiece.minY();

		if (!tryMove(curPiece, curX, curY)) {
			// Game Over Method

			curPiece.setShape(Tetrominoe.NoShape);
			timer.stop();
			
			
			JFrame frameGameOver = new JFrame();;
			JOptionPane.showMessageDialog(frameGameOver,"Game Over :(");

			
			
			ControlPanel.userScoreLabel.setText(Integer.toString(gameScore));
			MainWindow.statusbar.setText("Game over.");
		}

	}

	private boolean tryMove(Shape newPiece, int newX, int newY) {
		for (int i = 0; i < 4; ++i) {
			int x = newX + newPiece.x(i);
			int y = newY - newPiece.y(i);

			if (x < 0 || x >= TETRIS_BOARD_WIDTH || y < 0 || y >= TETRIS_BOARD_HEIGHT)
				return false;

			if (shapeAt(x, y) != Tetrominoe.NoShape)
				return false;
		}

		curPiece = newPiece;
		curX = newX;
		curY = newY;

		repaint();

		return true;
	}

	private void removeFullLines() {
		int numFullLines = 0;
		for (int i = TETRIS_BOARD_HEIGHT - 1; i >= 0; --i) {
			boolean lineIsFull = true;
			for (int j = 0; j < TETRIS_BOARD_WIDTH; ++j) {
				if (shapeAt(j, i) == Tetrominoe.NoShape) {
					lineIsFull = false;
					break;
				}
			}

			if (lineIsFull) {
				++numFullLines;
				for (int k = i; k < TETRIS_BOARD_HEIGHT - 1; ++k) {
					for (int j = 0; j < TETRIS_BOARD_WIDTH; ++j)
						board[(k * TETRIS_BOARD_WIDTH) + j] = shapeAt(j, k + 1);
				}
			}
		}

		if (numFullLines > 0) {

			
			//Score settings
			if (numFullLines == 1 && hardLevelGameFromString.equals("Latwy"))
				gameScore = gameScore + 1;
			else if (numFullLines == 1 && hardLevelGameFromString.equals("Sredni"))
				gameScore = gameScore + 2;
			else if (numFullLines == 1 && hardLevelGameFromString.equals("Trudny"))
				gameScore = gameScore + 3;
			
			
			else if (numFullLines == 2 && hardLevelGameFromString.equals("Latwy"))
				gameScore = gameScore + 2;
			else if (numFullLines == 2 && hardLevelGameFromString.equals("Sredni"))
				gameScore = gameScore + 3;
			else if (numFullLines == 2 && hardLevelGameFromString.equals("Trudny"))
				gameScore = gameScore + 4;


			else if (numFullLines == 3 && hardLevelGameFromString.equals("Latwy"))
				gameScore = gameScore + 4;
			else if (numFullLines == 3 && hardLevelGameFromString.equals("Sredni"))
				gameScore = gameScore + 6;
			else if (numFullLines == 3 && hardLevelGameFromString.equals("Trudny"))
				gameScore = gameScore + 7;
			
			
			else if (numFullLines == 4 && hardLevelGameFromString.equals("Latwy"))
				gameScore = gameScore + 10;
			else if (numFullLines == 4 && hardLevelGameFromString.equals("Sredni"))
				gameScore = gameScore + 15;
			else if (numFullLines == 4 && hardLevelGameFromString.equals("Trudny"))
				gameScore = gameScore + 20;
			
			else if (numFullLines ==5 && hardLevelGameFromString.equals("Latwy"))
				gameScore = gameScore + 15;
			else if (numFullLines == 5 && hardLevelGameFromString.equals("Sredni"))
				gameScore = gameScore + 24;
			else if (numFullLines == 5 && hardLevelGameFromString.equals("Trudny"))
				gameScore = gameScore + 32;
		

			
			
			//Timer settings
			if (tetrisLevel.getTetrisLevel() == 2 && hardLevelGameFromString.equals("Latwy")) {
				timer.setDelay(850);
				timer.restart();
			} else if (tetrisLevel.getTetrisLevel() == 2 && hardLevelGameFromString.equals("Sredni")) {
				timer.setDelay(700);
				timer.restart();
			} else if (tetrisLevel.getTetrisLevel() == 2 && hardLevelGameFromString.equals("Trudny")) {
				timer.setDelay(500);
				timer.restart();
			}
			
			
			
			if (tetrisLevel.getTetrisLevel() == 3 && hardLevelGameFromString.equals("Latwy")) {
				timer.setDelay(710);
				timer.restart();
			} else if (tetrisLevel.getTetrisLevel() == 3 && hardLevelGameFromString.equals("Sredni")) {
				timer.setDelay(550);
				timer.restart();
			} else if (tetrisLevel.getTetrisLevel() == 3 && hardLevelGameFromString.equals("Trudny")) {
				timer.setDelay(400);
				timer.restart();
			}

			
			
			if (tetrisLevel.getTetrisLevel() == 4 && hardLevelGameFromString.equals("Latwy")) {
				timer.setDelay(560);
				timer.restart();
			} else if (tetrisLevel.getTetrisLevel() == 4 && hardLevelGameFromString.equals("Sredni")) {
				timer.setDelay(450);
				timer.restart();
			} else if (tetrisLevel.getTetrisLevel() == 4 && hardLevelGameFromString.equals("Trudny")) {
				timer.setDelay(300);
				timer.restart();
			}
			
			
			
			if (tetrisLevel.getTetrisLevel() == 5 && hardLevelGameFromString.equals("Latwy")) {
				timer.setDelay(450);
				timer.restart();
			} else if (tetrisLevel.getTetrisLevel() == 5 && hardLevelGameFromString.equals("Sredni")) {
				timer.setDelay(340);
				timer.restart();
			} else if (tetrisLevel.getTetrisLevel() == 5 && hardLevelGameFromString.equals("Trudny")) {
				timer.setDelay(200);
				timer.restart();
			}
	
			tetrisLevel.tetrisLevelCounter(gameScore, 1);
			ControlPanel.userScoreLabel.setText(Integer.toString(gameScore));
			ControlPanel.userGameLevelLabel.setText(Integer.toString(tetrisLevel.getTetrisLevel()));
			numLinesRemoved += numFullLines;
			isFallingFinished = true;

			curPiece.setShape(Tetrominoe.NoShape);

			repaint();
		}

	}

	private void drawSquare(Graphics g, int x, int y, Tetrominoe shape) {

		Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), new Color(102, 204, 102),
				new Color(102, 102, 204), new Color(204, 204, 102), new Color(204, 102, 204), new Color(102, 204, 204),
				new Color(218, 170, 0) };

		Color color = colors[shape.ordinal()];

		g.setColor(color);
		g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

		g.setColor(color.brighter());
		g.drawLine(x, y + squareHeight() - 1, x, y);
		g.drawLine(x, y, x + squareWidth() - 1, y);

		g.setColor(color.darker());
		g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
		g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);

	}

	class KeyListenerAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode();

			if (isStarted == false) {
				return;
			}

			if (isPaused == true) {
				return;
			}

			if (curPiece.getShape() == Tetrominoe.NoShape) {
				return;
			}

			switch (keycode) {

			case KeyEvent.VK_LEFT:
				tryMove(curPiece, curX - 1, curY);
				break;

			case KeyEvent.VK_RIGHT:
				tryMove(curPiece, curX + 1, curY);
				break;

			case KeyEvent.VK_DOWN:
				tryMove(curPiece.rotateRight(), curX, curY);
				break;

			case KeyEvent.VK_UP:
				tryMove(curPiece.rotateLeft(), curX, curY);
				break;

			case KeyEvent.VK_SPACE:
				dropDown();
				break;

			case KeyEvent.VK_D:
				oneLineDown();
				break;
			}
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
				hardLevelGameFromString = splitted[7];
				booleanValueString = splitted[11];
				
		
			}

			in.close();
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	
	
	public void loadUserScoreFromFile() {

		try {
			FileInputStream fstream = new FileInputStream("userhighscore.txt");

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) {

				String [] splitted = strLine.split("#");

				for (int i = 0; i < splitted.length; i++)
					 tabelaGraczyString[i] = splitted[i] ;
			}
			in.close();
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
