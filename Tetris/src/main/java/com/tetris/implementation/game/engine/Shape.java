package com.tetris.implementation.game.engine;

import java.util.Random;

public class Shape {

	protected enum Tetrominoe {
		NoShape(new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } }),
		ZShape(new int[][] { { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }),
		SShape(new int[][] { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } }),
		IShape(new int[][] { { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }),
		TShape(new int[][] { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }),
		OShape(new int[][] { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }),
		LShape(new int[][] { { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }),
		JShape(new int[][] { { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } });

		private int[][] tetrominoCoordinates;

		Tetrominoe(int[][] tetrominoCoordinates) {
			this.tetrominoCoordinates = tetrominoCoordinates;
		}
	}
	
	private Tetrominoe currentTetrominoe;
	private int[][] shapeCoordinates;
	Random random;

	
	public Shape() {
		shapeCoordinates = new int[4][2];
		setShape(Tetrominoe.NoShape);
		random = new Random();	
	}

	public void setShape(Tetrominoe tetrominoe) {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 2; ++j)
				shapeCoordinates[i][j] = tetrominoe.tetrominoCoordinates[i][j];
		this.currentTetrominoe = tetrominoe;
	}

	public Tetrominoe getShape() {
		return this.currentTetrominoe;
	}

	
	
	public void setRandomShape() {
		Tetrominoe[] tetrominoeArray = Tetrominoe.values();
		
		setShape(tetrominoeArray[random.nextInt(7) + 1]);
	}
	

	
	

	private void setX(int idx, int x) {
		shapeCoordinates[idx][0] = x;
	}

	private void setY(int idx, int y) {
		shapeCoordinates[idx][1] = y;
	}

	public int x(int idx) {
		return shapeCoordinates[idx][0];
	}

	public int y(int idx) {
		return shapeCoordinates[idx][1];
	}

	public int minX() {
		int value = shapeCoordinates[0][0];
		for (int i = 0; i < 4; i++)
			value = Math.min(value, shapeCoordinates[i][0]);
		return value;
	}

	public int minY() {
		int value = shapeCoordinates[0][1];
		for (int i = 0; i < 4; i++)
			value = Math.min(value, shapeCoordinates[i][1]);
		return value;
	}

	Shape rotateLeft() {

		if (currentTetrominoe == Tetrominoe.OShape) {
			return this;
		}

		Shape result = new Shape();
		result.currentTetrominoe = currentTetrominoe;

		for (int i = 0; i < 4; ++i) {

			result.setX(i, y(i));
			result.setY(i, -x(i));
		}

		return result;
	}

	Shape rotateRight() {
		if (currentTetrominoe == Tetrominoe.OShape) {
			return this;
		}

		Shape result = new Shape();
		result.currentTetrominoe = currentTetrominoe;

		for (int i = 0; i < 4; ++i) {
			result.setX(i, -y(i));
			result.setY(i, x(i));
		}

		return result;
	}

	@Override
	public String toString() {
		return "Shape [tetrominoe=" + currentTetrominoe + "]";
	}

}
