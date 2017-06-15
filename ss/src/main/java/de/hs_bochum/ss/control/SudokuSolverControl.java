package de.hs_bochum.ss.control;

import de.hs_bochum.ss.exception.UnknownAlgorithmException;
import de.hs_bochum.ss.gui.SudokuView;
import de.hs_bochum.ss.interfaces.AbstractAlgorithm;
import de.hs_bochum.ss.interfaces.Algorithm;
import de.hs_bochum.ss.modelNew.GridModel;
import de.hs_bochum.ss.modelNew.ReportModel;

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
	
	public void setAlgo(Algorithm algo) {
		try {
			this.algo = algo.toStrategy(this);
		} catch (UnknownAlgorithmException e) {
			e.printStackTrace();
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
