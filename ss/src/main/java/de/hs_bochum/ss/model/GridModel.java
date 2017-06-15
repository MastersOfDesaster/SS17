package de.hs_bochum.ss.model;

import java.awt.Point;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;

public class GridModel extends Observable {

	private GridCell[][] field;

	public GridModel() {
		this.field = new GridCell[9][9];
		for (int j = 0; j < field.length; j++) {
			for (int i = 0; i < field[j].length; i++) {
				field[j][i] = new GridCell();
			}
		}
	}

	public void init() {
		this.field = new GridCell[9][9];
		for (int j = 0; j < field.length; j++) {
			for (int i = 0; i < field[j].length; i++) {
				field[j][i] = new GridCell();
			}
		}
	}

	public void setFieldValueLocked(byte value, int x, int y)
			throws IsOutOfRangeException, CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		field[x][y].setValue(value);
		field[x][y].lock();
		setChanged();
		notifyObservers(new Point(x, y));
	}

	public void setFieldValue(int value, int x, int y)
			throws IsLockedException, IsOutOfRangeException, CoordinateOutOfBoundsException {

		setFieldValue(value, x, y, true);
	}

	public void setFieldValue(int value, int x, int y, boolean notify)
			throws IsLockedException, IsOutOfRangeException, CoordinateOutOfBoundsException {

		checkCoordinate(x, y);

		if (value < 1 || value > 9) {
			throw new IsOutOfRangeException("Value is out of range!");
		}

		if (field[x][y].isLocked()) {
			throw new IsLockedException("This field is locked!");
		}

		field[x][y].setValue(value);

		if (notify) {
			setChanged();
			notifyObservers(new Point(x, y));
		}
	}

	public void resetFieldValue(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		field[x][y].setValue(0);
		setChanged();
		notifyObservers();
	}

	public void setField(GridCell[][] field) {
		this.field = field;
		setChanged();
		notifyObservers();
	}

	public byte getFieldByte(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		return field[x][y].getValue();

	}
	
	public GridCell getFieldValue(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		return field[x][y];

	}


	public boolean isFieldLocked(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		return field[x][y].isLocked();
	}

	public void addUsedValue(int x, int y, byte value) {
		try {
			checkCoordinate(x, y);
			checkValue(value);
			field[x][y].usedValueSet().add((byte) value);
			setChanged();
			notifyObservers(new Point(x, y));
		} catch (CoordinateOutOfBoundsException e) {
			e.printStackTrace();
		} catch (IsOutOfRangeException e) {
			e.printStackTrace();
		}
	}

	public void removeUsedValue(int x, int y, byte value) {
		try {
			checkCoordinate(x, y);
			checkValue(value);
			field[x][y].usedValueSet().remove(value);
		} catch (CoordinateOutOfBoundsException e) {
			e.printStackTrace();
		} catch (IsOutOfRangeException e) {
			e.printStackTrace();
		}
	}

	private void checkCoordinate(int x, int y) throws CoordinateOutOfBoundsException {
		if (x < 0 || x > 8 || y < 0 || y > 8) {
			throw new CoordinateOutOfBoundsException("Coordinate is out of Bounds!");
		}
	}
	
	private void checkValue(int value) throws IsOutOfRangeException
	{
		if (value < 1 || value > 9) {
			throw new IsOutOfRangeException("Value is out of Range!");
		}
	}

	public void incrementSingleField(int x, int y)
			throws IsLockedException, IsOutOfRangeException, CoordinateOutOfBoundsException {
		setFieldValue((byte) (getFieldByte(x, y) + 1), x, y);
	}

	public boolean isValueValid(int x, int y, int value) {
		// check columns
		for (int xx = 0; xx < 9; xx++) {
			if ((xx != x) && (field[xx][y].getValue() == value)) {
				return false;
			}
		}

		// check rows
		for (int yy = 0; yy < 9; yy++) {
			if ((yy != y) && (field[x][yy].getValue() == value)) {
				return false;
			}
		}

		// check square
		int x1 = (x / 3) * 3;
		int y1 = (y / 3) * 3;
		for (int xx = x1; xx < x1 + 3; xx++) {
			for (int yy = y1; yy < y1 + 3; yy++) {
				if ((xx != x || yy != y) && (field[xx][yy].getValue() == value)) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean isValid() throws CoordinateOutOfBoundsException {
		// check columns
		if (!checkColumns()) {
			return false;
		}

		// check rows
		if (!checkRows()) {
			return false;
		}

		// check squares
		for (int x1 = 0; x1 < 9; x1 += 3) {
			for (int y1 = 0; y1 < 9; y1 += 3) {
				if (!checkSquare(x1, y1)) {
					return false;
				}
			}
		}

		return true;
	}

	private boolean checkColumns() {
		Set<Byte> valueSet = new HashSet<Byte>();

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (valueSet.contains(field[x][y].getValue())) {
					return false;
				} else {
					valueSet.add(field[x][y].getValue());
				}
			}
		}

		return true;
	}

	private boolean checkRows() {
		Set<Byte> valueSet = new HashSet<Byte>();

		// check if colums are valid
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (valueSet.contains(field[x][y].getValue())) {
					return false;
				} else {
					valueSet.add(field[x][y].getValue());
				}
			}
		}

		return true;
	}

	private boolean checkSquare(int x1, int y1) {
		Set<Byte> valueSet = new HashSet<Byte>();

		for (int xx = x1; xx < x1 + 3; xx++) {
			for (int yy = y1; yy < y1 + 3; yy++) {
				if (valueSet.contains(field[xx][yy].getValue())) {
					return false;
				} else {
					valueSet.add(field[xx][yy].getValue());
				}
			}
		}

		return true;
	}
}