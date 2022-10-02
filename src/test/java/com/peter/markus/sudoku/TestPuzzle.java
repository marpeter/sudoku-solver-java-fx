package com.peter.markus.sudoku;

import java.io.IOException;

public class TestPuzzle {
	
	public static Puzzle createEasyPuzzle07() { 
		return TestPuzzle.fromString(
			    "1 93725 8\n 8695132 \n52 4 8 17"
		    + "\n8941 5673\n61  3  82\n3726 4195"	
			+ "\n23 5 6 49\n 5129783 \n9 78432 1");
	}
	
	public static Puzzle getEasySolution07() {
		return TestPuzzle.fromString(
			    "149372568\n786951324\n523468917"
			+ "\n894125673\n615739482\n372684195"
			+ "\n238516749\n451297836\n967843251");
	}
	
	public static Puzzle createMediumPuzzle211() {
		return TestPuzzle.fromString(
		        " 58 2 34 \n3  8 4  9\n2 47368 5"
	        + "\n 42 9 15 \n9 61 57 2\n 15 7 69 "
			+ "\n5 34179 8\n4  3 2  1\n 27 8 43 ");
	}

	public static Puzzle getMediumSolution211() {
		return TestPuzzle.fromString(
		       "658921347\n371854269\n294736815"
	        + "\n742698153\n936145782\n815273694"
			+ "\n563417928\n489362571\n127589436");
	}
	
	public static Puzzle createDifficultPuzzle290() {
		return TestPuzzle.fromString(
		        "  8 9 3  \n   684   \n9 4   6 8"
	        + "\n 8 3 1 2 \n23     14\n 4 5 7 6 "
			+ "\n7 5   4 2\n   439   \n  3 5 9  ");
	}
	
	public static Puzzle getDifficultSolution290() {
		return TestPuzzle.fromString(
		        "678295341\n351684297\n924173658"
	        + "\n586341729\n237968514\n149527863"
			+ "\n795816432\n862439175\n413752986");
	}
		
	private static Puzzle fromString(String puzzleString) {
		try {
			return Puzzle.fromString(puzzleString);
		} catch (IOException e) {
			return null;
		}
	}
	
}
