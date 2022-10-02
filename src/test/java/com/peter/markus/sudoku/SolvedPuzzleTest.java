package com.peter.markus.sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolvedPuzzleTest {
	
	private Puzzle puzzle;
	
	@BeforeEach
	public void setup() {
		puzzle = TestPuzzle.getEasySolution07();
	}
	@Test
	public void solvedPuzzleHasAllValues() {
		// Given
		// When
		boolean hasAllValues = puzzle.hasAllValues();
		// Then
		assertEquals(true,hasAllValues);
		
	}
	
	// Separate tests for row, column and block consistency since these methods
	// may have to be used by some solution strategies
	// and we don't assume that puzzle.isSolved() uses these methods internally
	@Test
	public void solvedRowsAreConsistent() {
		// Given
		// When
		for(int row=0; row<Puzzle.ROWS; row++) {
			// Then
			assertTrue(puzzle.isRowConsistent(row));
		}
	}
	
	@Test
	public void solvedColumnsAreConsistent() {
		// Given
		// When
		for(int column=0; column<Puzzle.COLUMNS; column++) {
			// Then
			assertTrue(puzzle.isColumnConsistent(column));			
		}
	}
	
	@Test
	public void solvedBlocksAreConsistent() {
		// Given
		// When
		for(int blockRow=0; blockRow<3; blockRow++) {
			for(int blockColumn=0; blockColumn<3; blockColumn++) {
			  assertTrue(puzzle.isBlockConsistent(blockRow,blockColumn));
			}
		}
	}
	
	@Test
	public void solvedPuzzleIsDetected() {
		// Given
		// When
		boolean isSolved = puzzle.isSolved();
		// Then
		assertEquals(true,isSolved);		
	}
	
}
