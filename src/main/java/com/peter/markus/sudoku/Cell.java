package com.peter.markus.sudoku;

import java.util.HashSet;
import java.util.Set;

public class Cell {
	
	protected Integer value;
	Set<CellListener> listeners = new HashSet<>();

	public Integer getValue() {
		return value;
	}

	public void setValue(int value) {
		for(CellListener listener: listeners) {
			listener.onValueChange(this.value, Integer.valueOf(value));
		}
		this.value = value;
	}

	public void clearValue() {
		for(CellListener listener: listeners) {
			listener.onValueChange(this.value, null);
		}
		value = null;
	}

	public void addListener(CellListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(CellListener listener) {
		listeners.remove(listener);
	}
	
}
