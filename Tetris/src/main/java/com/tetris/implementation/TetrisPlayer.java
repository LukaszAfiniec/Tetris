package com.tetris.implementation;

public class TetrisPlayer {

	private String nameGamer;
	private int score;

	public TetrisPlayer() {
		nameGamer = "";
		score = 0;
	}

	public TetrisPlayer(String nameGamer, int score) {
		super();
		this.nameGamer = nameGamer;
		this.score = score;
	}

	public String getNameGamer() {
		return nameGamer;
	}

	public void setNameGamer(String nameGamer) {
		this.nameGamer = nameGamer;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
