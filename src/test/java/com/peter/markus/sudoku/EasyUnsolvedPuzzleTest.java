package com.peter.markus.sudoku;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EasyUnsolvedPuzzleTest {
	
	Puzzle puzzle;
	
	@BeforeEach
	public void setup() {
		puzzle = TestPuzzle.createEasyPuzzle07();
	}
	
	@Test
	public void shouldCreatePuzzleFromString() {
		// Given
		// When
		// Then
		assertAll(
			() -> assertEquals(1,puzzle.getValueAt(0,0)),
			() -> assertEquals(3,puzzle.getValueAt(3, 8)),
			() -> assertNull(puzzle.getValueAt(0, 1))
		);
	}
	
	
	@Test
	public void unsolvedPuzzleDoesNotHaveAllValues() {
		// Given
		// When
		// Then
		assertFalse(puzzle.hasAllValues());	
	}
	
	@Test
	public void unsolvedPuzzleIsDetected() {
		// Given
		// When
		boolean isSolved = puzzle.isSolved();
		// Then
		assertFalse(isSolved);
	}
	
	// Separate tests for row, column and block consistency since these methods
	// may have to be used by some solution strategies
	// and we don't assume that puzzle.isSolved() uses these methods internally
	@Test
	public void unsolvedRowIsConsistent() {
		// Given
		// When
		// Then
		assertTrue( puzzle.isRowConsistent(1) );
	}
	
	@Test
	public void unsolvedColumnIsConsistent() {
		// Given
		// When
		// Then
		assertTrue( puzzle.isColumnConsistent(1) );
	}
	
	@Test
	public void unsolvedBlocksAreConsistent() {
		// Given
		// When
		// Then
		for(int blockRow=0; blockRow<3; blockRow++) {
			for(int blockColumn=0; blockColumn<3; blockColumn++) {
			  assertTrue(puzzle.isBlockConsistent(blockRow,blockColumn));
			}
		}
	}
	
	@Test
	public void repeatedNumberInRowIsInconsistent() {
		// Given
		// When
		puzzle.setValueAt(0,1,1);
		// Then
		assertFalse(puzzle.isRowConsistent(0));
	}
	
	@Test
	public void repeatedNumberInColumnIsInconsistent() {
		// Given
		// When
		puzzle.setValueAt(1,0,1);
		// Then
		assertFalse(puzzle.isColumnConsistent(0));
	}
	
	@Test
	public void repeatedNumberInUpperLeftBlockIsInconsistent() {
		// Given
		// When
		puzzle.setValueAt(0,1,6);
		// Then
		assertFalse(puzzle.isBlockConsistent(0,0));		
	}
	@Test
	public void repeatedNumberInLowerRightBlockIsInconsistent() {
		// Given
		// When
		puzzle.setValueAt(6,6,1);
		// Then
		assertFalse(puzzle.isBlockConsistent(2,2));		
	}
}
