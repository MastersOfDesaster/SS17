package de.hs_bochum.ss.algorithms;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;

public class MarkAlgorithm extends AbstractAlgorithm{

    public MarkAlgorithm(SudokuSolverControl control) {
        super(control);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
        try {
            mark();
            stop();
        } catch (Exception e) {
            control.handleError(e);
        }
        }
    
    private synchronized void mark() throws CoordinateOutOfBoundsException, InterruptedException {
        for (int y = 0; y <= 8; y++) {
            for (int x = 0; x <= 8; x++) {
                if (!running) {
                    return;
                }
                if (control.getCell(x, y).isLocked()) {
                    continue;
                }
                int value = control.getCell(x, y).getValue();
                if (value == 0) {
                    for (byte v = 1; v <= 9; v++) {
                        if (control.isValueValid(x, y, v)) {
                            control.addPossibleValue(x, y, v);
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

}
