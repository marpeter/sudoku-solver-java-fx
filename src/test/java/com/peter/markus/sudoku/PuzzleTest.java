package com.peter.markus.sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class PuzzleTest {
	
	@Test
	public void nonExistingFileShouldThrowError() {
		// Given
		assertThrows(IOException.class,() -> { Puzzle.fromFile("src/test/resources/easy_puzzle_XX.txt"); } );
	}
	
	@Test
	public void loadingShouldPopulateBoard() throws IOException {
		// Given
		Puzzle puzzle = Puzzle.fromFile("src/test/resources/easy_puzzle_07.txt");
		//When
		//Then
		assertEquals(1,puzzle.getValueAt(0,0));
		assertEquals(3,puzzle.getValueAt(3, 8));
		assertNull(puzzle.getValueAt(0, 1));
	}
	
	@Test
	public void puzzleInstanceEqualsItself() {
		// Given
		Puzzle that = TestPuzzle.createEasyPuzzle07();
		// When
		// Then
		assertTrue(that.equals(that));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void puzzleNotEqualsOtherClass() {
		// Given
		Puzzle that = TestPuzzle.createEasyPuzzle07();
		// When
		// Then
		assertFalse(that.equals(null));
		assertFalse(that.equals(Coordinate.START));
	}
	
	@Test
	public void puzzlesWithSameCellValuesAreEqual() {
		// Given
		Puzzle theOne = TestPuzzle.createEasyPuzzle07();
		Puzzle theOther = TestPuzzle.createEasyPuzzle07();
		assertFalse(theOne==theOther);
		assertTrue(theOne.equals(theOther));
	}
}
