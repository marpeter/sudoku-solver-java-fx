package com.peter.markus.sudoku;

public class AttemptedCell {
	
	private Cell cell;

	public AttemptedCell(Puzzle puzzle, Coordinate cellCoordinate) {
		this.cell = puzzle.getCellAt(cellCoordinate);
	}

	public void setAttemptedValue(int attemptedValue) {
		cell.setValue(attemptedValue);
	}

	public void restorePuzzle() {
		cell.clearValue();
	}
}
