package com.peter.markus.sudoku;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CellTest implements CellListener {
	
	Cell cell;
	Integer oldValue;
	Integer newValue;
	
	@Override
	public void onValueChange(Integer oldValue, Integer newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	@BeforeEach
	public void setup() {
		cell = new Cell();
		cell.addListener(this);
	}

	@Test
	public void canRegisterCellListener() {
		// Given setup
		// When
		// Then
	    assertSame(cell.listeners.iterator().next(),this);
	}

	@Test
	public void cellListenerIsCalledOnSetValue() {
		// Given setup
		// When
		cell.setValue(1);
		// Then
		assertAll(
			() -> assertNull(oldValue),
			() -> assertEquals(newValue,Integer.valueOf(1))
		);
	}
	
	@Test
	public void cellListenerIsCalledOnClear() {
		// Given setup
		// When
		cell.setValue(1);
		cell.clearValue();
		// Then
		assertAll(
			() -> assertNull(newValue),
			() -> assertEquals(oldValue,Integer.valueOf(1))
		);
	}
	
}
