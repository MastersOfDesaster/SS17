package de.hs_bochum.ss.control;

import java.awt.Point;
import java.io.File;
import java.util.List;

import de.hs_bochum.ss.algorithms.AbstractAlgorithm;
import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;
import de.hs_bochum.ss.exception.UnknownAlgorithmException;
import de.hs_bochum.ss.interfaces.Algorithm;
import de.hs_bochum.ss.io.SudokuFileReader;
import de.hs_bochum.ss.io.SudokuFileWriter;
import de.hs_bochum.ss.model.GridCell;
import de.hs_bochum.ss.model.GridModel;
import de.hs_bochum.ss.model.ReportModel;
import de.hs_bochum.ss.view.SudokuView;

public class SudokuSolverControl {
    private GridValidator validator;
    private AbstractAlgorithm algo;

    private SudokuView view;
    private GridModel model;
    private ReportModel report;

    public SudokuSolverControl(GridModel model, SudokuView view, GridValidator validator) {
        this.model = model;
        this.view = view;
        this.validator = validator;

        report = new ReportModel();
    }

    public boolean isValid() {
        report.increaseDiffCount();
        return validator.isValid();
    }

    public void updateAll() {
        model.startNotify();
    }

    public boolean isValid(int x, int y) {
        report.increaseDiffCount();
        return validator.isValid(x, y);
    }

    public boolean isValueValid(int x, int y, int value) {
        try {
            return validator.isValueValid(x, y, value);
        } catch (CoordinateOutOfBoundsException e) {
            handleError(e);
        }

        return false;
    }

    public GridCell getCell(int x, int y) throws CoordinateOutOfBoundsException {
        return model.getCell(x, y);
    }

    public void resetCellValue(int x, int y) {
    	try {
	        if (model.getCellValue(x, y) != 0){
	            model.resetCellValue(x, y);
	            model.setCellValid(x, y, validator.isValid(x, y));
	            model.startNotify(new Point(x, y));
	
	            if (model.isCellValid(x, y) == false) {
	                report.increaseInvalidCount();
	            }
	            report.increaseWriteCount();
	        }
	    } catch (CoordinateOutOfBoundsException e) {
            handleError(e);
        }
    }

    public void setCellValueLocked(int x, int y, int value) {
        try {
            model.setCellValueLocked(x, y, value);
            model.setCellValid(x, y, validator.isValid(x, y));
        } catch (IsOutOfRangeException | CoordinateOutOfBoundsException e) {
            handleError(e);
        }
    }

    public void setCellValue(int x, int y, int value) {
        try {
        	if (model.getCellValue(x, y) != value){
	            model.setCellValue(x, y, value);
	            model.setCellValid(x, y, validator.isValid(x, y));
	            model.startNotify(new Point(x, y));
	
	            if (model.isCellValid(x, y) == false) {
	                report.increaseInvalidCount();
	            }
	            report.increaseWriteCount();
        	}
        } catch (Exception e) {
            handleError(e);
        }
    }

    public void setCellValue(int x, int y, int value, boolean notify) {
        try {
            model.setCellValue(x, y, value);
            model.setCellValid(x, y, validator.isValid(x, y));
            if (notify) {
                model.startNotify(new Point(x, y));
            }

            if (model.isCellValid(x, y) == false) {
                report.increaseInvalidCount();
            }
            report.increaseWriteCount();
        } catch (IsLockedException | IsOutOfRangeException | CoordinateOutOfBoundsException e) {
            handleError(e);
        }
    }

    public void incrementCellValue(int x, int y) {
        try {
            model.incrementCellValue(x, y);
            model.setCellValid(x, y, validator.isValid(x, y));

            if (model.isCellValid(x, y) == false) {
                report.increaseInvalidCount();
            }
            report.increaseWriteCount();
        } catch (CoordinateOutOfBoundsException e) {
            handleError(e);
        }
    }

    public void setAlgo(Algorithm algo) {
        try {
            this.algo = algo.toStrategy(this);
        } catch (UnknownAlgorithmException e) {
            handleError(e);
        }
    }

    public void startAlgo() {
        if (algo != null) {
            algo.start();

            model.setSolved(false);
            report.clear();
        } else {
            view.showError(new NullPointerException("Algo is null!"));
        }
    }

    public void pauseAlgo() {
        if (algo != null) {
            algo.pause();
        } else {
            view.showError(new NullPointerException("Algo is null!"));
        }
    }

    public void continueAlgo() {
        if (algo != null) {
            algo.resume();
        } else {
            view.showError(new NullPointerException("Algo is null!"));
        }
    }

    public void stopAlgo() {
        if (algo != null) {
            algo.stop();
        } else {
            view.showError(new NullPointerException("Algo is null!"));
        }
    }

    public void nextStepAlgo() {
        if (algo != null) {
            algo.pause();
            algo.singleStep();
        } else {
            view.showError(new NullPointerException("Algo is null!"));
        }
    }

    public void handleError(Exception e) {
        view.showError(e);
        if (!view.getManual()) stopAlgo();
    }

    public void setWaittime(int millis) {
        algo.setMillisToWait(millis);
    }

    public boolean isCellLocked(int x, int y) {
        return model.isCellLocked(x, y);
    }

    public void addPossibleValue(int x, int y, byte value) {
        try {
            model.addPossibleValue(x, y, value);
        } catch (Exception e) {
            handleError(e);
        }
    }

    public boolean isCellValid(int x, int y) {
        try {
            return model.isCellValid(x, y);
        } catch (CoordinateOutOfBoundsException e) {
            view.showError(e);
            return false;
        }
    }

    public List<GridCell> getRow(int row) {
        return model.getRow(row);
    }

    public List<GridCell> getColumn(int column) {
        return model.getColumn(column);
    }

    public List<GridCell> getSquare(int x, int y) {
        return model.getSquare(x, y);
    }

    public List<GridCell> getSquare(int square) {
        return getSquare(square % 3, (int) (square / 3));
    }

    public long getWaitMillis() {
        return algo.getWaitMillis();
    }

    public void removePossibleValue(int x, int y, int value)
            throws IsOutOfRangeException, CoordinateOutOfBoundsException {
        model.removePossibleValue(x, y, value);

    }

    public void removeAllPossibleValues(int x, int y) {
        model.removeAllPossibleValues(x, y);
    }

    public void resetSudoku() {
        model.reset();
        model.startNotify();
    }

    public void lockCells() {
        model.lockCells();
    }

    public void onAlgoFinished() {
        try {
            model.setSolved(validator.isSolved());
            report.setSolved(model.isSolved());
            view.showMessage(report.toString());
            System.out.println(report);
        } catch (CoordinateOutOfBoundsException e) {
            view.showError(e);
        }
    }

    public void setSelectedFile(File selectedFile) {
        try {
            model.setGrid(SudokuFileReader.readSudoku(selectedFile));
        } catch (Exception e) {
            handleError(e);
        }
    }

    public void saveFile(File f) {
        try {
            SudokuFileWriter.saveSudoku(f, model.getGrid());
        } catch (Exception e) {
            handleError(e);
        }
    }
}
