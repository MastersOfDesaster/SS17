package de.hs_bochum.ss.algorithms;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;
import de.hs_bochum.ss.model.GridCell;

public class CrookAlgorithm extends AbstractAlgorithm {
    volatile boolean foundNew = true;

    public CrookAlgorithm(SudokuSolverControl control) {
        super(control);
    }

    // O(n^5 + n^4)
    public synchronized void solve() throws Exception {
        mark(); // O(n^4)
        foundNew = true;
        
        // O(n^5)
        while (foundNew) { // O(n^2) kann maximal n*n Felder füllen 
            foundNew = false;
            if (!running) {
                return;
            }
            if (paused) {
                wait();
            } else {
                if (waitMillis != 0) {
                    wait(waitMillis);
                }
            }

            //Vielleicht durch If schachteln, wenn einer was findet, brauchen die anderen nicht arbeiten
            findSingle(); // O(n^3)
            findForced(); // O(2n^3)
            findPreemptives();
          //  findAllNaked();
        }
    }

    // O(n^4)
    private synchronized void mark() throws CoordinateOutOfBoundsException, InterruptedException {
        for (int y = 0; y <= 8; y++) { // O(n)
            for (int x = 0; x <= 8; x++) { // O(n)
                if (!running) {
                    return;
                }
                if (control.getCell(x, y).isLocked()) {
                    continue;
                }
                int value = control.getCell(x, y).getValue();
                if (value == 0) {
                    for (byte v = 1; v <= 9; v++) { // O(n)
                        if (control.isValid(x, y, v)) { // O(n)
                            control.addPossibleValue(x, y, v); //O(1)
                            if (paused) {
                                wait();
                            } else {
                                if (waitMillis != 0) {
                                    wait(waitMillis);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Finished marking");
    }

    // O (n^3)
    private synchronized void findSingle()
            throws CoordinateOutOfBoundsException, IsOutOfRangeException, InterruptedException {
        for (int y = 0; y <= 8; y++) { // O(n)
            for (int x = 0; x <= 8; x++) { // O(n)
                if (!running) {
                    return;
                }
                GridCell cell = control.getCell(x, y);
                if (!cell.isLocked()) {
                    Iterator<Integer> iterator = cell.getPossibleValues().iterator();
                    int value;
                    if (iterator.hasNext()) { // O(n)
                        value = iterator.next();
                    } else {
                        continue;
                    }
                    if (!iterator.hasNext()) { // O(3n)
                        control.setCellValue(x, y, value);
                        foundNew = true;
                        if (paused) {
                            wait();
                        } else {
                            if (waitMillis != 0) {
                                wait(waitMillis);
                            }
                        }
                        control.removePossibleValue(x, y, value);
                        cell.removePossibleValue(value);

                        for (GridCell cCell : control.getRow(cell.getY())) { // O(n)
                            control.removePossibleValue(cCell.getX(), cCell.getY(), value);
                        }

                        for (GridCell cCell : control.getColumn(cell.getX())) { // O(n)
                            control.removePossibleValue(cCell.getX(), cCell.getY(), value);
                        }

                        for (GridCell cCell : control.getSquare(cell.getX() / 3, cell.getY() / 3)) { // O(n)
                            control.removePossibleValue(cCell.getX(), cCell.getY(), value);
                        }

                    }
                }
            }
        }
    }

    private void findForced() throws IsOutOfRangeException, CoordinateOutOfBoundsException, InterruptedException {
       checkColumns(); // O(2n^3)
       checkRows(); // O(2n^3)
       checkSquares(); // O(2n^3)
    }

    private void findPreemptives() {

    }

    // O(2n^3) die drei For-schleifen ergeben ein n^3 und die ersten beiden For-schleifen & das IF ergeben n^3
    private synchronized void checkSquares() throws IsOutOfRangeException, CoordinateOutOfBoundsException, InterruptedException {
        // iterate through squares
        for (int square = 0; square < 9; square++) { // O(n)
            List<GridCell> cells = control.getSquare(square);
            GridCell cell = null;
            // iterate through possible values
            for (int v = 1; v < 10; v++) { // O(n)
                GridCell found = null;
                // iterate through cells in the square
                for (int id = 0; id < 9; id++) { // O(n)
                    cell = cells.get(id);
                    if (cell.getPossibleValues().contains(v)) {
                        if (found == null) {
                            found = cell;
                        } else {
                            found = null;
                            break;
                        }
                    }
                }
                if (found != null) {  // O(2n)
                    System.out
                            .println(String.format("Found forced in column %s, id: %s, value: %s", found.getX(), found.getY(), v));
                    control.removeAllPossibleValues(found.getX(), found.getY());

                    for (GridCell cCell : control.getRow(found.getY())) { // O(n)
                        control.removePossibleValue(cCell.getX(), cCell.getY(), v);
                    }

                    for (GridCell cCell : control.getColumn(found.getX())){ // O(n)
                        control.removePossibleValue(cCell.getX(), cCell.getY(), v);
                    }

                    control.setCellValue(found.getX(), found.getY(), v);
                   System.out.println("Writing value: " + v);
                    if (paused) {
                        wait();
                    } else {
                        if (waitMillis != 0) {
                            wait(waitMillis);
                        }
                    }
                    foundNew = true;
                }

            }
        }
    }

    // O(2n^3)
    private synchronized void checkColumns()
            throws IsOutOfRangeException, CoordinateOutOfBoundsException, InterruptedException {
        // iterate through rows
        for (int column = 0; column < 9; column++) { // O(n)
            List<GridCell> cells = control.getColumn(column);
            GridCell cell = null;
            // iterate through possible values
            for (int v = 1; v < 10; v++) { // O(n)
                int cellId = -1;

                // iterate through cells in the row
                for (int id = 0; id < 9; id++) { // O(n)
                    cell = cells.get(id);
                    if (cell.getPossibleValues().contains(v)) {
                        if (cellId == -1) {
                            cellId = id;
                        } else {
                            cellId = -1;
                            break;
                        }
                    }
                }
                if (cellId != -1) { // O(2n)
                    System.out
                            .println(String.format("Found forced in column %s, id: %s, value: %s", column, cellId, v));
                    control.removeAllPossibleValues(column, cellId);

                    for (GridCell cCell : control.getRow(cellId)) { // O(n)
                        control.removePossibleValue(cCell.getX(), cCell.getY(), v);
                    }

                    for (GridCell cCell : control.getSquare(column / 3, cellId / 3)) { // O(n)
                        control.removePossibleValue(cCell.getX(), cCell.getY(), v);
                    }

                    control.setCellValue(column, cellId, v);
                    if (paused) {
                        wait();
                    } else {
                        if (waitMillis != 0) {
                            wait(waitMillis);
                        }
                    }
                    foundNew = true;
                }

            }
        }

    }

    // O(2n^3)
    private synchronized void checkRows()
            throws IsOutOfRangeException, CoordinateOutOfBoundsException, InterruptedException {
        // iterate through rows
        for (int row = 0; row < 9; row++) { // O(n)
            List<GridCell> cells = control.getRow(row);
            GridCell cell = null;
            // iterate through possible values
            for (int v = 1; v < 10; v++) { // O(n)
                int cellId = -1;

                // iterate through cells in the row
                for (int id = 0; id < 9; id++) { // O(n)
                    cell = cells.get(id);
                    if (cell.getPossibleValues().contains(v)) {
                        if (cellId == -1) {
                            cellId = id;
                        } else {
                            cellId = -1;
                            break;
                        }
                    }
                }
                if (cellId != -1) { // O(2n)
                    System.out.println(String.format("Found forced in row %s, id: %s, value: %s", row, cellId, v));
                    control.removeAllPossibleValues(cellId, row);

                    for (GridCell cCell : control.getColumn(cellId)) { // O(n)
                        control.removePossibleValue(cCell.getX(), cCell.getY(), v);
                    }

                    for (GridCell cCell : control.getSquare(cellId / 3, row / 3)) { // O(n)
                        control.removePossibleValue(cCell.getX(), cCell.getY(), v);
                    }

                    control.setCellValue(cellId, row, v);
                    if (paused) {
                        wait();
                    } else {
                        if (waitMillis != 0) {
                            wait(waitMillis);
                        }
                    }
                    foundNew = true;
                }
            }
        }

    }

    private void findAllNaked() {
        for (int count = 2; count < 5; count++) {
            for (int y = 0; y < 9; y++) {
                findNaked(control.getRow(y), count);
            }
            for (int x = 0; x < 9; x++) {
                findNaked(control.getColumn(x), count);
            }
            for (int square = 0; square < 9; square++) {
                findNaked(control.getSquare(square), count);
            }
        }
    }

    private void findNaked(List<GridCell> cells, int size) {
        int i1 = 0; // first occurance
        int i2 = 0; // second occurance
        Set<Integer> values = null;
        boolean foundNakedPair = false;

        for (i1 = 0; i1 < 8; i1++) { // 9-1
            if (cells.get(i1).getPossibleValues().size() == size) { // first
                // occurance
                values = cells.get(i1).getPossibleValues();
                for (i2 = i1 + 1; i2 < 9; i2++) {
                    if (cells.get(i2).getPossibleValues().equals(values)) { // second
                                                                            // occurance
                        foundNakedPair = true;
                        break;
                    }
                }
            }

            if (foundNakedPair) {
                for (int i = 0; i < 9; i++) {
                    if ((i != i1) && (i != i2)) {
                        for (Integer value : values) {
                            try {
                                control.removePossibleValue(cells.get(i).getX(), cells.get(i).getY(), value);
                            } catch (IsOutOfRangeException | CoordinateOutOfBoundsException e) {
                                control.handleError(e);
                            }
                        }
                    }
                }

                foundNakedPair = false;
            }
        }
    }

    @Override
    public void run() {
        try {
            solve();
            stop();
        } catch (Exception e) {
            control.handleError(e);
        }

    }

}
