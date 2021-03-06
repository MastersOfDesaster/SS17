package de.hs_bochum.ss.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;
import de.hs_bochum.ss.model.GridCell;

public class GridModel extends Observable {

    private GridCell[][] grid;

    private GridCell activeCell;

    private int invalidCellCount;

    private boolean solved;

    public GridModel() {
        this.grid = new GridCell[9][9];
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[j].length; i++) {
                grid[j][i] = new GridCell(i, j);
            }
        }
    }

    public void reset() {
        this.grid = new GridCell[9][9];
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[j].length; i++) {
                grid[j][i] = new GridCell(i, j);
            }
        }
        this.solved = false;
        this.invalidCellCount = 0;
    }

    public void resetNonLocked() {
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[j].length; i++) {
                if (!grid[j][i].isLocked()){
                	grid[j][i] = new GridCell(i, j);
                }
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
        	startNotify(new Point(x, y));
            throw new IsLockedException("This field is locked!");
        }
        grid[x][y].setValue(value);
    }

    public void incrementCellValue(int x, int y) throws CoordinateOutOfBoundsException {
        checkCoordinate(x, y);
        grid[x][y].setValue(1 + grid[x][y].getValue());
    }

    public void resetCellValue(int x, int y) throws CoordinateOutOfBoundsException {
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

    // O(1)
    public void addPossibleValue(int x, int y, int value) throws CoordinateOutOfBoundsException, IsOutOfRangeException {
        checkCoordinate(x, y);
        checkValue(value);
        grid[x][y].addPossibleValue(value);
        startNotify(new Point(x, y));
    }

    public void removePossibleValue(int x, int y, int value)
            throws IsOutOfRangeException, CoordinateOutOfBoundsException {
        checkCoordinate(x, y);
        checkValue(value);
        grid[x][y].removePossibleValue(value);
        startNotify(new Point(x, y));
    }

    public void removeAllPossibleValues(int x, int y) {
        grid[x][y].getPossibleValues().clear();
    }

    public GridCell[][] getGrid() {
        return grid;
    }

    public void setGrid(GridCell[][] grid) {
        this.grid = grid;
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

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public List<GridCell> getColumn(int column) {
        return Arrays.asList(grid[column]);
    }

    public List<GridCell> getRow(int row) {
        List<GridCell> gridRow = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            gridRow.add(grid[i][row]);
        }
        return gridRow;
    }

    public List<GridCell> getSquare(int x, int y) {
        List<GridCell> gridSquare = new ArrayList<>();
        for (int ix = 0; ix < 3; ix++) {
            for (int iy = 0; iy < 3; iy++) {
                gridSquare.add(grid[x * 3 + ix][y * 3 + iy]);
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

    public void startNotify() {
        setChanged();
        notifyObservers();
    }

    public void startNotify(Point p) {
        setChanged();
        notifyObservers(p);
    }

    public void lockCells(boolean lock) {
        for (GridCell[] gridRow : grid) {
            for (GridCell cell : gridRow) {
                cell.lockByValue(lock);
            }
        }
        startNotify();
    }

	public void switchLockByCell(int x, int y) {
		grid[x][y].switchLock();
		startNotify(new Point(x, y));
	}
}
