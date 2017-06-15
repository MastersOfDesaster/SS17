package de.hs_bochum.ss.control;

import de.hs_bochum.ss.gui.SudokuView;
import de.hs_bochum.ss.interfaces.AbstractAlgorithm;
import de.hs_bochum.ss.model.GridModel;
import de.hs_bochum.ss.modelNew.ReportModel;

public class SudokuSolverControl {
	
	private GridValidator validator;
	private AbstractAlgorithm algo;
	private SudokuView view;
	private GridModel sudoku;
	private ReportModel report;
	public GridValidator getValidator() {
		return validator;
	}
	public void setValidator(GridValidator validator) {
		this.validator = validator;
	}
	public AbstractAlgorithm getAlgo() {
		return algo;
	}
	public void setAlgo(AbstractAlgorithm algo) {
		this.algo = algo;
	}
	public SudokuView getView() {
		return view;
	}
	public void setView(SudokuView view) {
		this.view = view;
	}
	public GridModel getSudoku() {
		return sudoku;
	}
	public void setSudoku(GridModel sudoku) {
		this.sudoku = sudoku;
	}
	public ReportModel getReport() {
		return report;
	}
	public void setReport(ReportModel report) {
		this.report = report;
	}
	
	
	

}
