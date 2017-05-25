package de.hs_bochum.ss.model;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;

public class Field extends Observable {

	private FieldValue[][] field;

	public Field() {
		this.field = new FieldValue[9][9];
		for (int j = 0; j < field.length; j++) {
			for (int i = 0; i < field[j].length; i++) {
				field[j][i] = new FieldValue();
			}
		}
	}

	public void setFieldValue(byte value, int x, int y) throws IsLockedException, IsOutOfRangeException, CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		field[x][y].setValue(value);
		setChanged();
		notifyObservers();
	}

	public byte getFieldValue(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		return field[x][y].getValue();

	}
	
	public boolean isFieldLocked(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		
		return field[x][y].isLocked();
	}

	private void checkCoordinate(int x, int y) throws CoordinateOutOfBoundsException {
		if (x < 0 || x > 8 || y < 0 || y > 8) {
			throw new CoordinateOutOfBoundsException("Invalid Index!");
		}
	}

	public void incrementSingleField(int x, int y) throws IsLockedException, IsOutOfRangeException, CoordinateOutOfBoundsException {
		setFieldValue((byte) (getFieldValue(x, y) + 1), x, y);
	}

	public boolean isValid() throws CoordinateOutOfBoundsException {
		Set<Byte> valueSet = new HashSet<Byte>();
		
		// check if rows are valid
		for (byte row = 0; row < 9; row++) {
			for (byte col = 0; col < 0; col++) {
				if (!valueSet.contains(getFieldValue(row, col))) {
				}
			}
		}
		
		// check if colums are valid
		for (byte col = 0; col < 9; col++) {
			
		}
		
		// check if squares are valid
//		for (byte )
			
		return true;
	}
	
	public boolean isFieldValid(int x, int y) {
		return true;
	}
}
