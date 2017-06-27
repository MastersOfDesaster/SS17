package de.hs_bochum.ss.model;

import java.util.HashSet;
import java.util.Set;

public class GridCell {

	private int value;
	private int x, y;
	
	
	private Set<Integer> possibleValues;
	
	private boolean locked;
	
	private boolean valid;
	
	public GridCell(int x, int y){
		this.x = x;
		this.y = y;
		this.locked = false;
		this.valid = true;
		this.possibleValues = new HashSet<>();
		this.value = 0;
	}
	
	public GridCell(int x, int y, int value){
		this.x = x;
		this.y = y;
		this.locked = false;
		this.valid = true;
		this.possibleValues = new HashSet<>();
		this.value = value;
	}
	
	public GridCell(int x, int y, int value, boolean locked){
		this.x = x;
		this.y = y;
		this.locked = locked;
		this.valid = true;
		this.possibleValues = new HashSet<>();
		this.value = value;
	}	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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

	public void lock() {
		this.locked = true;
	}
	
	public void unlock(){
		this.locked = false;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public void lockByValue() {
		if(value>0 && valid)
			locked = true;
	}

}