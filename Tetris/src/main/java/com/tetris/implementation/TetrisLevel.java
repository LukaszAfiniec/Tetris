package com.tetris.implementation;

public class TetrisLevel {

	private int hardLevel;
	private int tetrisLevel;

	public TetrisLevel() {
		hardLevel = 0;
		tetrisLevel = 0;
	}

	public void tetrisLevelCounter(int gameScore, int hardLevel) {
		if (gameScore >= 11 * hardLevel && gameScore < 30 * hardLevel)
			tetrisLevel=2;
		else if (gameScore >= 31 * hardLevel && gameScore < 70 * hardLevel)
			tetrisLevel=3;
		else if (gameScore >= 71 * hardLevel && gameScore < 100 * hardLevel)
			tetrisLevel=4;
		else if (gameScore >= 101 * hardLevel)
			tetrisLevel=5;
	}

	public TetrisLevel(int gameHardLevel, int tetrisLevel) {
		this.hardLevel = gameHardLevel;
		this.tetrisLevel = tetrisLevel;
	}


	public void setHardLevel(int gameHardLevel) {
		this.hardLevel = gameHardLevel;
	}

	public int getTetrisLevel() {
		return tetrisLevel;
	}

	public void setTetrisLevel(int tetrisLevel) {
		this.tetrisLevel = tetrisLevel;
	}

}
