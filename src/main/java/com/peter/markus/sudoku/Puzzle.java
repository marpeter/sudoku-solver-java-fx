package com.peter.markus.sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

public class Puzzle {
	
	public static final int ROWS = 9;
	public static final int COLUMNS = 9;
	public static final int MAXIMUM_VALUE = 9;

	private Cell[][] cell;

	public Puzzle() {
		cell = new Cell[ROWS][COLUMNS];
		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLUMNS; column++) {
				cell[row][column] = new Cell();
			}
		}
	}

	public static Puzzle fromString(String puzzleString) throws IOException {
		try (Reader reader = new StringReader(puzzleString)) {
			return Puzzle.fromReader(reader);
		} catch (IOException e) {
			throw new IOException("Unable to read String " + puzzleString,e);
		}
	}

	public static Puzzle fromFile(String fileName) throws IOException {
		try (Reader reader = new FileReader(fileName)) {
			return Puzzle.fromReader(reader);
		} catch (FileNotFoundException e) {
			throw new IOException("Unable to find readable file: " + new File(fileName).getAbsolutePath(),e);
		} catch (IOException e) {
			throw new IOException("Unable to read file " + fileName,e);
		}
	}

	@Override
	public boolean equals(Object that) {
		if(this==that) return true;
		if(!(that instanceof Puzzle)) return false;
		Puzzle other = (Puzzle)that;
		for(int row=0; row<ROWS; row++) {
			for(int column=0; column<COLUMNS; column++) {
				if( !(this.cell[row][column].getValue()==other.cell[row][column].getValue()) ) return false;
			}
		}
		return true;		
	}
	
	@Override 
	public String toString() {
		StringBuilder result = new StringBuilder();
		for(int row=0; row<ROWS; row++) {
			for(int column=0; column<COLUMNS; column++) {
				Integer value = cell[row][column].getValue();
				if(value==null)
					result.append(' ');
				else 
					result.append(value.intValue());
			}
			result.append('\n');
		}
		return result.toString();
	}
	
	public Integer getValueAt(int row, int column) {
		return cell[row][column].getValue();
	}

	public void setValueAt(int row, int column, int value) {
		cell[row][column].setValue(value);
	}
	
	public boolean isSolved() {
		return isConsistent() && hasAllValues();
	}

	public boolean hasAllValues() {
		for(int row=0; row<ROWS; row++) {
			for(int column=0; column<COLUMNS; column++) {
				if(cell[row][column].getValue()==null) return false;
			}
		}
		return true;
	}

	public boolean isConsistent() {
		for(int row=0; row<ROWS; row++) {
			if(!isRowConsistent(row)) return false;
		}
		for(int column=0; column<COLUMNS; column++) {
			if(!isColumnConsistent(column)) return false;
		}
		for(int blockRow=0; blockRow<3; blockRow++) {
			for(int blockColumn=0; blockColumn<3; blockColumn++) {
			  if(!isBlockConsistent(blockRow,blockColumn)) return false;
			}
		}
		return true;
	}

	Cell getCellAt(Coordinate coordinate) {
		return cell[coordinate.row][coordinate.column];
	}
	
	protected boolean isRowConsistent(int row) {
		Coordinate[] coordinatesToTest = new Coordinate[COLUMNS];
		for(int column = 0; column<COLUMNS; column++) {
			coordinatesToTest[column] = new Coordinate(row,column);
		}
		return isGroupConsistent(coordinatesToTest);
	}

	protected boolean isColumnConsistent(int column) {
		Coordinate[] coordinatesToTest = new Coordinate[ROWS];
		for(int row = 0; row<ROWS; row++) {
			coordinatesToTest[row] = new Coordinate(row,column);
		}	
		return isGroupConsistent(coordinatesToTest);
	}
	
	protected boolean isBlockConsistent(int blockRow, int blockColumn) {
		Coordinate[] coordinatesToTest = new Coordinate[ROWS];
		for(int rowInBlock=0; rowInBlock<3; rowInBlock++) {
			for(int colInBlock=0; colInBlock<3; colInBlock++) {
				coordinatesToTest[rowInBlock*3+colInBlock] = new Coordinate(blockRow*3 + rowInBlock, blockColumn*3 + colInBlock);
			}
		}
		return isGroupConsistent(coordinatesToTest);
	}
	
	public void clearValueAt(int row, int column) {
		cell[row][column].clearValue();;
	}


	private static Puzzle fromReader(Reader reader) throws IOException {
		Puzzle puzzle = new Puzzle();
		try (BufferedReader lineReader = new BufferedReader(reader)) {
			for (int row = 0; row < ROWS; row++) {
				char[] value = lineReader.readLine().toCharArray();
				for (int column = 0; column < value.length; column++) {
					if (value[column] != ' ') {
						puzzle.cell[row][column].setValue(Character.getNumericValue(value[column]));
					}
				}
			}
		}
		return puzzle;
	}
	
	private boolean isGroupConsistent(Coordinate[] coordinates) {
		ArrayList<Integer> usedValues = new ArrayList<>(9);
		for(Coordinate coordinate: coordinates) {
			Integer usedValue = getValueAt(coordinate.row,coordinate.column);
			if(usedValue!=null) {
				if(usedValues.contains(usedValue)) {
					return false;
				} else {
					usedValues.add(usedValue);
				}
			}
		}	
		return true;
	}
}
