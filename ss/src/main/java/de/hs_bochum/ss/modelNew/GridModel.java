package de.hs_bochum.ss.modelNew;

import java.awt.Point;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Set;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;
import de.hs_bochum.ss.modelNew.GridCell;

public class GridModel extends Observable {

	private GridCell[][] field;

	private GridCell activeCell;

	private int invalidCellCount;

	private boolean solved;

	public GridModel() {
		this.field = new GridCell[9][9];
		for (int j = 0; j < field.length; j++) {
			for (int i = 0; i < field[j].length; i++) {
				field[j][i] = new GridCell();
			}
		}
	}

	public void reset() {
		this.field = new GridCell[9][9];
		for (int j = 0; j < field.length; j++) {
			for (int i = 0; i < field[j].length; i++) {
				field[j][i] = new GridCell();
			}
		}
		this.solved = false;
		this.invalidCellCount = 0;
	}

	public void setCellValueLocked(int x, int y, int value)
			throws IsOutOfRangeException, CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		field[x][y].setValue(value);
		field[x][y].lock();
		setChanged();
		notifyObservers(new Point(x, y));
	}

	public void setCellValue(int x, int y, int value)
			throws IsLockedException, IsOutOfRangeException, CoordinateOutOfBoundsException {
		setCellValue(x, y, value, true);
	}

	public void setCellValue(int x, int y, int value, boolean notify)
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

	public void incrementCellValue(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		field[x][y].setValue(1 + field[x][y].getValue());
	}

	public void resetCell(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		field[x][y].setValue(0);
		setChanged();
		notifyObservers();
	}

	public int getCellValue(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		return field[x][y].getValue();
	}

	public Set<Integer> getPossibleCellValues(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		return field[x][y].getPossibleValues();
	}

	public GridCell getCell(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		return field[x][y];
	}

	public void addPossibleValue(int x, int y, int value) throws CoordinateOutOfBoundsException, IsOutOfRangeException {
		checkCoordinate(x, y);
		checkValue(value);
		field[x][y].addPossibleValue(value);
		
		setChanged();
		notifyObservers(new Point(x, y));
	}

	public void removePossibleValue(int x, int y, int value) throws IsOutOfRangeException, CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		checkValue(value);
		field[x][y].removePossibleValue(value);
	}

	public GridCell[][] getField() {
		return field;
	}

	public void setField(GridCell[][] field) {
		this.field = field;
		setChanged();
		notifyObservers();
	}

	public GridCell getActiveCell() {
		return activeCell;
	}

	public void setActiveCell(GridCell active) {
		this.activeCell = active;
	}

	public int getInvalidCellCount() {
		return invalidCellCount;
	}

	public void setInvalidCellCount(int invalidCellCount) {
		this.invalidCellCount = invalidCellCount;
	}

	public void incrementInvalidCellCount() {
		this.invalidCellCount++;
	}

	public void decrementInvalidCellCount() {
		this.invalidCellCount--;
	}

	public boolean isSolved() {
		return solved;
	}

	public void solved() {
		this.solved = true;
	}

	public GridCell[] getRow(int row) {
		return field[row];
	}

	public GridCell[] getColumn(int column) {
		List<GridCell> gridColumn = new ArrayList<>();
		Arrays.stream(field).forEach(row -> gridColumn.add(row[column]));
		return (GridCell[]) gridColumn.toArray();
	}

	public GridCell[][] getSquare(int x, int y) {
		GridCell[][] gridSquare = new GridCell[3][3];
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				gridSquare[r][c] = field[r + x * 3][c + y * 3];
			}
		}
		return gridSquare;
	}

	public boolean isValid() {
		return true;
	}

	private void checkCoordinate(int x, int y) throws CoordinateOutOfBoundsException {
		if (x < 0 || x > 8 || y < 0 || y > 8) {
			throw new CoordinateOutOfBoundsException("Coordinate is out of Bounds!");
		}
	}

	private void checkValue(int value) throws IsOutOfRangeException {
		if (value < 1 || value > 9) {
			throw new IsOutOfRangeException("Value is out of Range!");
		}
	}

}
