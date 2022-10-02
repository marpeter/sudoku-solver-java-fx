package com.peter.markus.sudoku;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AttemptedCellTest {
	
	@Test
	public void originalPuzzleIsReconstructed() {
		// Given
		Puzzle easyUnsolvedPuzzle = TestPuzzle.getEasySolution07();
		AttemptedCell attempt = new AttemptedCell(easyUnsolvedPuzzle, new Coordinate(0,1));
		// When
		attempt.setAttemptedValue(5);
		// Then
		assertEquals(5,easyUnsolvedPuzzle.getValueAt(0, 1));
		attempt.restorePuzzle();
		assertNull(easyUnsolvedPuzzle.getValueAt(0,1));
	}
}
