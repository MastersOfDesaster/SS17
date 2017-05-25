package de.hs_bochum.ss.model;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;

public class Field extends Observable {

	private FieldValue[][] field;

	private Set<Byte> valueSet = new HashSet<Byte>();

	public Field() {
		this.field = new FieldValue[9][9];
		for (int j = 0; j < field.length; j++) {
			for (int i = 0; i < field[j].length; i++) {
				field[j][i] = new FieldValue();
			}
		}
	}
	
	public void init() {
		this.field = new FieldValue[9][9];
		for (int j = 0; j < field.length; j++) {
			for (int i = 0; i < field[j].length; i++) {
				field[j][i] = new FieldValue();
			}
		}
	}
	
	public void setFieldValueLocked(byte value, int x, int y) throws IsOutOfRangeException, CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		field[x][y].setValue(value);
		field[x][y].lock();
		setChanged();
		notifyObservers();
	}

	public void setFieldValue(byte value, int x, int y)
			throws IsLockedException, IsOutOfRangeException, CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		
		if (field[x][y].isLocked()) {
			throw new IsLockedException("This field is locked!");
		}
		
		field[x][y].setValue(value);
		setChanged();
		notifyObservers();
	}

	public void setField(FieldValue[][] field) {
		this.field = field;
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

	public void incrementSingleField(int x, int y)
			throws IsLockedException, IsOutOfRangeException, CoordinateOutOfBoundsException {
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
		// for (byte )

		return true;
	}

//	private boolean storeValue(byte value) {
//		if (valueSet.contains(value)) {
//			return false;
//		} else {
//			valueSet.add(value);
//		}
//
//		return true;
//	}

	public boolean isFieldValid(int x, int y) {

		// check columns
		for (int xx = 0; xx < 9; xx++) {
			if ((xx != x) && (field[xx][y].getValue() == field[x][y].getValue())) {
				return false;
			}
		}

		// check rows
		for (int yy = 0; yy < 9; yy++) {
			if ((yy != y) && (field[x][yy].getValue() == field[x][y].getValue())) {
				return false;
			}
		}

		// check square
		int x1 = (x / 3) * 3;
		int y1 = (y / 3) * 3;
		for (int xx = x1; xx < x1 + 3; xx++) {
			for (int yy = y1; yy < y1 + 3; yy++) {
				if ((xx != x || yy != y) && field[xx][yy] == field[x][y]) {
					return false;
				}
			}
		}
		
		return true;
	}
}
