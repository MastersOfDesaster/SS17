package de.hs_bochum.ss.model;

import java.util.HashSet;
import java.util.Set;

import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;

public class FieldValue {

	private byte value;

	private boolean lock;

	private Set<Byte> usedValueSet = new HashSet<Byte>();

	public FieldValue(byte value) throws IsLockedException, IsOutOfRangeException {
		this.lock = true;
		setValue(value);
	}

	public FieldValue() {
		this.lock = false;
		this.value = 0;
	}

	public void setValue(byte value) throws IsLockedException, IsOutOfRangeException {
		if (this.lock) {
			throw new IsLockedException("This field is locked!");
		}

		if (value < 1 || value > 9) {
			throw new IsOutOfRangeException("Value is out of range!");
		}

		this.value = value;
	}

	public byte getValue() {
		return this.value;
	}
	
	public boolean isLocked() {
		return lock;
	}
	
	public Set<Byte> usedValueSet() {
		return usedValueSet;
	}
}
