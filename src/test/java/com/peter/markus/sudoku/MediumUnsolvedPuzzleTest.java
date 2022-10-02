package com.peter.markus.sudoku;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;

public class MediumUnsolvedPuzzleTest {

	Puzzle puzzle;
	
	@BeforeEach
	public void setup() throws IOException {
		puzzle = TestPuzzle.createMediumPuzzle211();
	}
}
