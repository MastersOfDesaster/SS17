package de.hs_bochum.ss.control;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.UnknownAlgorithmException;
import de.hs_bochum.ss.interfaces.AbstractAlgorithm;
import de.hs_bochum.ss.interfaces.Algorithm;
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
			view.showError(e);
			stopAlgo();
		}
		
		return false;
	}
	
	public void setCellValueLocked(int x, int y, int value) {
		
	}
	
	public void setCellValue(int x, int y, int value) {
		
	}
	
	public void setCellValue(int x, int y, int value, boolean notify) {
		
	}
	
	public void incrementCellValue(int x, int y) {
		
	}
	
	public void resetFieldValue(int x, int y) {
		
	}
	
	public void setAlgo(Algorithm algo) {
		try {
			this.algo = algo.toStrategy(this);
		} catch (UnknownAlgorithmException e) {
			view.showError(e);
		}
	}
	
	public void startAlgo() {
		//TODO
	}
	
	public void stopAlgo() {
		//TODO
	}
	
	public void nextStepAlgo() {
		//TODO
	}
}
