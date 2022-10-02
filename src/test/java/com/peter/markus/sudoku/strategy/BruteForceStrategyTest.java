package com.peter.markus.sudoku.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.peter.markus.sudoku.Puzzle;
import com.peter.markus.sudoku.TestPuzzle;

public class BruteForceStrategyTest {
	
	@Test
	public void solvedEasyPuzzleIsOwnSolution() {
		// Given
		Puzzle solvedPuzzle = TestPuzzle.getEasySolution07();
		BruteForceStrategy solver = new BruteForceStrategy(solvedPuzzle);
		// When
		Puzzle solution = solver.solve();
		// Then
		assertEquals(solvedPuzzle,solution);
	}
	
	@Test
	public void unsolvedEasyPuzzleIsSolved()  {
		// Given
		Puzzle unsolvedPuzzle = TestPuzzle.createEasyPuzzle07();
		Puzzle solvedPuzzle = TestPuzzle.getEasySolution07();
		BruteForceStrategy solver = new BruteForceStrategy(unsolvedPuzzle);
		// When
		Puzzle solution = solver.solve();
		// Then
		assertTrue(solution.isSolved());
		assertTrue(solvedPuzzle.equals(solution));
	}
	
	@Test
	public void solvedMediumPuzzleIsOwnSolution() {
		// Given
		Puzzle solvedPuzzle = TestPuzzle.createMediumPuzzle211();
		BruteForceStrategy solver = new BruteForceStrategy(solvedPuzzle);
		// When
		Puzzle solution = solver.solve();
		// Then
		assertEquals(solvedPuzzle,solution);
	}
	
	@Test
	public void unsolvedMediumPuzzleIsSolved()  {
		// Given
		Puzzle unsolvedPuzzle = TestPuzzle.createMediumPuzzle211();
		Puzzle solvedPuzzle = TestPuzzle.getMediumSolution211();
		BruteForceStrategy solver = new BruteForceStrategy(unsolvedPuzzle);
		// When
		Puzzle solution = solver.solve();
		// Then
		assertTrue(solution.isSolved());
		assertTrue(solvedPuzzle.equals(solution));
	}
	
	@Test
	public void unsolveddiffucultPuzzleIsSolved()  {
		// Given
		Puzzle unsolvedPuzzle = TestPuzzle.createDifficultPuzzle290();
		Puzzle solvedPuzzle = TestPuzzle.getDifficultSolution290();
		BruteForceStrategy solver = new BruteForceStrategy(unsolvedPuzzle);
		// When
		Puzzle solution = solver.solve();
		System.out.println(solution.toString());
		// Then
		assertTrue(solution.isSolved());
		assertTrue(solvedPuzzle.equals(solution));
	}
//	@Test unsolvable puzzle is left unchanged
	
}
