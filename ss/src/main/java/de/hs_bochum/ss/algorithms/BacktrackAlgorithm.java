package de.hs_bochum.ss.algorithms;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.exception.AlgorithmStoppedException;

public class BacktrackAlgorithm extends AbstractAlgorithm {

    public BacktrackAlgorithm(SudokuSolverControl control) {
        super(control);
    }

    public synchronized boolean solve(int x, int y) throws Exception {
        if (!running) {
            // stop recursion without return value
            throw new AlgorithmStoppedException();
        }
        if (x == 9) {
            x = 0;
            y++;
        }

        if (y == 9) {
            return true;
        }

        if (control.getCell(x, y).getValue() != 0) {
            return solve(x + 1, y);
        } else {
            for (byte i = 1; i < 10; i++) {
                control.setCellValue(x, y, i);
                if (paused) {
                    wait();
                } else {
                    if (waitMillis != 0) {
                        wait(waitMillis);
                    }
                }
                if (!control.isValueValid(x, y, i)) {
                    continue;
                }
                if (solve(x + 1, y)) {
                    return true;
                }
                ;
            }
            control.resetCellValue(x, y);
            if (paused) {
                wait();
            } else {
                if (waitMillis != 0) {
                    wait(waitMillis);
                }
            }
            return false;
        }
    }

    @Override
    public void run() {
        try {
            solve(0, 0);
            stop();
        } catch (AlgorithmStoppedException e) {
            // do nothing
        } catch (Exception e) {
            e.printStackTrace();
            control.handleError(e);
        }
    }

}
