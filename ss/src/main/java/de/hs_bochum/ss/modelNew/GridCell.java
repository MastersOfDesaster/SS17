package de.hs_bochum.ss.modelNew;

import java.util.HashSet;
import java.util.Set;

public class GridCell {

	private int value;
	
	private Set<Integer> possibleValues;
	
	private boolean locked;
	
	private boolean valid;
	
	public GridCell(){
		this.locked = false;
		this.valid = true;
		this.possibleValues = new HashSet<>();
		this.value = 0;
	}

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
	
	public void addPossibleValue(int possibleValue){
		this.possibleValues.add(possibleValue);
	}
	
	public void removePossibleValue(int possibleValue){
		this.possibleValues.remove(possibleValue);
	}

	public boolean isLocked() {
		return locked;
	}

	//TODO: nur locken oder auch entlocken?
	public void lock() {
		this.locked = true;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}