package de.hs_bochum.ss.model;

import java.util.HashSet;
import java.util.Set;

import de.hs_bochum.ss.exception.IsOutOfRangeException;

public class FieldValue {

	private byte value;

	private boolean lock;

	private Set<Byte> usedValueSet = new HashSet<Byte>();

	public FieldValue() {
		this.lock = false;
	}
	
	public void setValue(byte value) throws IsOutOfRangeException {
		if (value < 1 || value > 9) {
			throw new IsOutOfRangeException("Value is out of range!");
		}

		this.value = value;
	}

	public byte getValue() {
		return this.value;
	}
	
	public void lock() {
		this.lock = true;
	}
	
	public boolean isLocked() {
		return lock;
	}
	
	public Set<Byte> usedValueSet() {
		return usedValueSet;
	}
}
