package de.hs_bochum.ss.control;

import de.hs_bochum.ss.algorithms.AbstractAlgorithm;
import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;
import de.hs_bochum.ss.exception.UnknownAlgorithmException;
import de.hs_bochum.ss.interfaces.Algorithm;
import de.hs_bochum.ss.modelNew.GridCell;
import de.hs_bochum.ss.modelNew.GridModel;
import de.hs_bochum.ss.modelNew.ReportModel;
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
	}
	
	public boolean isValid() {
		return validator.isValid();
	}
	
	public boolean isValid(int x, int y) {
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
	
	public GridCell getCell(int x, int y) {
		try {
			model.getCell(x, y);
		} catch (CoordinateOutOfBoundsException e) {
			handleError(e);
		}
		
		return null;
	}
	
	public void resetCell(int x, int y) {
		try {
			model.resetCell(x, y);
		} catch (CoordinateOutOfBoundsException e) {
			handleError(e);
		}
	}
	
	public void setCellValueLocked(int x, int y, int value) {
		try {
			model.setCellValueLocked(x, y, value);
		} catch (IsOutOfRangeException | CoordinateOutOfBoundsException e) {
			handleError(e);
		}
	}
	
	public void setCellValue(int x, int y, int value) {
		try {
			model.setCellValue(x, y, value);
		} catch (IsLockedException | IsOutOfRangeException | CoordinateOutOfBoundsException e) {
			handleError(e);
		}
	}
	
	public void setCellValue(int x, int y, int value, boolean notify) {
		try {
			model.setCellValue(x, y, value);
		} catch (IsLockedException | IsOutOfRangeException | CoordinateOutOfBoundsException e) {
			handleError(e);
		}
	}
	
	public void incrementCellValue(int x, int y) {
		try {
			model.incrementCellValue(x, y);
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
		} else {
			handleError(new NullPointerException("Algo is null!"));
		}
	}
	
	public void stopAlgo() {
		if (algo != null) {
			algo.pause();
		} else {
			handleError(new NullPointerException("Algo is null!"));
		}
	}
	
	public void nextStepAlgo() {
		if (algo != null) {
			algo.singleStep();
		} else {
			handleError(new NullPointerException("Algo is null!"));
		}
	}
	
	public void handleError(Exception e) {
		view.showError(e);
		stopAlgo();
	}

	public boolean isCellLocked(int x, int y) {
		return model.isCellLocked(x, y);
	}
}
