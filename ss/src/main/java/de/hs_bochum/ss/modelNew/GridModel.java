package de.hs_bochum.ss.modelNew;

import java.awt.Point;

import java.util.Observable;
import java.util.Set;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;
import de.hs_bochum.ss.modelNew.GridCell;

public class GridModel extends Observable {

	private GridCell[][] grid;

	private GridCell activeCell;

	private int invalidCellCount;

	private boolean solved;

	public GridModel() {
		this.grid = new GridCell[9][9];
		for (int j = 0; j < grid.length; j++) {
			for (int i = 0; i < grid[j].length; i++) {
				grid[j][i] = new GridCell();
			}
		}
	}

	public void reset() {
		this.grid = new GridCell[9][9];
		for (int j = 0; j < grid.length; j++) {
			for (int i = 0; i < grid[j].length; i++) {
				grid[j][i] = new GridCell();
			}
		}
		this.solved = false;
		this.invalidCellCount = 0;
	}

	public void setCellValueLocked(int x, int y, int value)
			throws IsOutOfRangeException, CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		grid[x][y].setValue(value);
		grid[x][y].lock();
		startNotify(new Point(x, y));
	}

	public void setCellValue(int x, int y, int value)
			throws IsLockedException, IsOutOfRangeException, CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		if (value < 1 || value > 9) {
			throw new IsOutOfRangeException("Value is out of range!");
		}
		if (grid[x][y].isLocked()) {
			throw new IsLockedException("This field is locked!");
		}
		grid[x][y].setValue(value);
	}

	public void incrementCellValue(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		grid[x][y].setValue(1 + grid[x][y].getValue());
	}

	public void resetCell(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		grid[x][y].setValue(0);
		startNotify();
	}

	public int getCellValue(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		return grid[x][y].getValue();
	}

	public Set<Integer> getPossibleCellValues(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		return grid[x][y].getPossibleValues();
	}

	public GridCell getCell(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		return grid[x][y];
	}

	public void addPossibleValue(int x, int y, int value) throws CoordinateOutOfBoundsException, IsOutOfRangeException {
		checkCoordinate(x, y);
		checkValue(value);
		grid[x][y].addPossibleValue(value);
		startNotify(new Point(x, y));
	}

	public void removePossibleValue(int x, int y, int value) throws IsOutOfRangeException, CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		checkValue(value);
		grid[x][y].removePossibleValue(value);
		startNotify(new Point(x, y));
	}

	public GridCell[][] getField() {
		return grid;
	}

	public void setField(GridCell[][] field) {
		this.grid = field;
		startNotify();
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

	public GridCell[] getColumn(int row) {
		return grid[row];
	}

	public GridCell[] getRow(int column) {
		GridCell[] gridColumn = new GridCell[9];
		for(int i=0; i<grid.length; i++){
			gridColumn[i] = grid[i][column]; 
		}
		return gridColumn;
	}

	public GridCell[][] getSquare(int x, int y) {
		GridCell[][] gridSquare = new GridCell[3][3];
		for (int ix = 0; ix < 3; ix++) {
			for (int iy = 0; iy < 3; iy++) {
				gridSquare[ix][iy] = grid[ix + x * 3][iy + y * 3];
			}
		}
		return gridSquare;
	}
	
	public boolean isValid() {
		return (invalidCellCount == 0);
	}
	
	public void setCellValid(int x, int y, boolean valid) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		grid[x][y].setValid(valid);
	}
	
	public boolean isCellValid(int x, int y) throws CoordinateOutOfBoundsException {
		checkCoordinate(x, y);
		return grid[x][y].isValid();
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

	public boolean isCellLocked(int x, int y) {
		return grid[x][y].isLocked();
	}

	public void startNotify(){
		setChanged();
		notifyObservers();
	}

	public void startNotify(Point p){
		setChanged();
		notifyObservers(p);
	}

	public void lockCells() {
		for(GridCell[] gridRow : grid){
			for(GridCell cell : gridRow){
				cell.lockByValue();
			}
		}
	}
}
