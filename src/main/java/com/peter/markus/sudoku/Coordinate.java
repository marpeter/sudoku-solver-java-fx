package com.peter.markus.sudoku;

public class Coordinate {
	public final int row;
	public final int column;
	
	public static final Coordinate START = new Coordinate(0,0);
	
	public Coordinate(int row, int column) {
		this.row = row;
		this.column = column;
	}
}
