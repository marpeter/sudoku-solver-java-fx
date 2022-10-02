package com.peter.markus.sudoku.strategy;

import com.peter.markus.sudoku.AttemptedCell;
import com.peter.markus.sudoku.Coordinate;
import com.peter.markus.sudoku.Puzzle;

public class BruteForceStrategy {

	private Puzzle puzzle;
	
	public BruteForceStrategy(Puzzle puzzle) {
		this.puzzle = puzzle;
	}

	public Puzzle solve() {		
		solveFrom(Coordinate.START);
		return puzzle;
	}
	
	private void solveFrom(Coordinate from) {
		if( puzzle.hasAllValues()) return;
		
		Coordinate next = this.getNextUnknownCellCoordinate(from);
		// start with next unknown cell
		AttemptedCell attempt = new AttemptedCell(puzzle, next);	
		// for each value in 1 .. 9
		//   create solution attempt by setting the cell to value
		//   if puzzle is consistent continue with next unknown cell
		// if all values tried without success, restore the puzzle
		// so that attempts on earlier cells can continue without being influenced
	    int valueToTry = 0;
	    while( !puzzle.isSolved() && (valueToTry < Puzzle.MAXIMUM_VALUE) ) {
            valueToTry++;
	    	attempt.setAttemptedValue(valueToTry);
	        if(puzzle.isConsistent()) {
            	solveFrom(next);
            }
	    }
	    if(!puzzle.isSolved() && valueToTry==Puzzle.MAXIMUM_VALUE) attempt.restorePuzzle();
	}
	
	private Coordinate getNextUnknownCellCoordinate(Coordinate startCoordinate) {
		int startRow    = startCoordinate.row;
		int startColumn = startCoordinate.column;
		for(int row=startRow; row<Puzzle.ROWS; row++) {
			for(int column=startColumn; column<Puzzle.COLUMNS; column++) {
				if(puzzle.getValueAt(row, column)==null) {
					return new Coordinate(row,column);		
				}
			}
			startColumn = 0;
		}
		return null;
	}
	
}
