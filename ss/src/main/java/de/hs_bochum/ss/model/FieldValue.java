package de.hs_bochum.ss.model;

import java.util.HashSet;
import java.util.Set;

public class FieldValue {

	private byte value;

	private boolean lock;

	private Set<Byte> usedValueSet = new HashSet<Byte>();

	public FieldValue() {
		this.lock = false;
	}
	
	public void setValue(int value) {
		this.value = (byte)value;
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
