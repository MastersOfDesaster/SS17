package de.hs_bochum.ss.control;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.model.GridCell;
import de.hs_bochum.ss.model.GridModel;

public class GridValidator {

    private GridModel model;

    public GridValidator(GridModel model) {
        this.model = model;
    }

    public boolean isSolved() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                try {
                    if (model.getCellValue(x, y) == 0) {
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return isValid();
    }

    public boolean isValid() {
        return checkColumns() && checkRows() && checkSquares();
    }

    public boolean isValid(int x, int y) {
        return checkColumn(x) && checkRow(y) && checkSquare(x / 3, y / 3);
    }

    // O(3n)
    public boolean isValid(int x, int y, int value) throws CoordinateOutOfBoundsException {
        // check columns
        for (int xx = 0; xx < 9; xx++) {
            if ((xx != x) && (model.getCellValue(xx, y) == value)) {
                return false;
            }
        }

        // check rows
        for (int yy = 0; yy < 9; yy++) {
            if ((yy != y) && (model.getCellValue(x, yy) == value)) {
                return false;
            }
        }

        // check square
        int x1 = (x / 3) * 3;
        int y1 = (y / 3) * 3;
        for (int xx = x1; xx < x1 + 3; xx++) {
            for (int yy = y1; yy < y1 + 3; yy++) {
                if ((xx != x || yy != y) && (model.getCellValue(xx, yy) == value)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkSquares() {
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                if (checkSquare(x, y) == false) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkSquare(int x, int y) {
        Set<Integer> valueSet = new HashSet<Integer>();

        List<GridCell> square = model.getSquare(x, y);
        for (int ix = 0; ix < 9; ix++) {
            if (square.get(ix).getValue() != 0) {
                if (valueSet.contains(square.get(ix).getValue())) {
                    return false;
                } else {
                    valueSet.add(square.get(ix).getValue());
                }
            }
        }

        return true;
    }

    private boolean checkRows() {
        for (int y = 0; y < 9; y++) {
            if (checkRow(y) == false) {
                return false;
            }
        }

        return true;
    }

    public boolean checkRow(int y) {
        Set<Integer> valueSet = new HashSet<Integer>();

        List<GridCell> row = model.getRow(y);
        for (int x = 0; x < 9; x++) {
            if (row.get(x).getValue() != 0) {
                if (valueSet.contains(row.get(x).getValue())) {
                    return false;
                } else {
                    valueSet.add(row.get(x).getValue());
                }
            }
        }

        return true;
    }

    private boolean checkColumns() {
        for (int x = 0; x < 9; x++) {
            if (checkColumn(x) == false) {
                return false;
            }
        }

        return true;
    }

    public boolean checkColumn(int x) {
        Set<Integer> valueSet = new HashSet<Integer>();

        List<GridCell> column = model.getColumn(x);
        for (int y = 0; y < 9; y++) {
            if (column.get(y).getValue() != 0) {
                if (valueSet.contains(column.get(y).getValue())) {
                    return false;
                } else {
                    valueSet.add(column.get(y).getValue());
                }
            }
        }

        return true;
    }

}
