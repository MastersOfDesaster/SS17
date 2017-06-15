package de.hs_bochum.ss.modelNew;

import java.util.Set;

public class GridCell {

	private int value;
	private Set<Integer> possibleValues;
	private boolean locked;
	private boolean valid;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Set<Integer> getPossibleValues() {
		return possibleValues;
	}
	public void setPossibleValues(Set<Integer> possibleValues) {
		this.possibleValues = possibleValues;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
	
}