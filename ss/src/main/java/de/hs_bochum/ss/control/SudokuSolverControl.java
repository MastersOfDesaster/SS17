package de.hs_bochum.ss.control;

import de.hs_bochum.ss.gui.SudokuView;
import de.hs_bochum.ss.interfaces.ISudokuSolver;
import de.hs_bochum.ss.model.GridModel;

public class SudokuSolverControl {
	
	private ISudokuSolver solver;
	private SudokuView view;
	private GridModel model;
	
	public ISudokuSolver getSolver() {
		return solver;
	}
	public void setSolver(ISudokuSolver solver) {
		this.solver = solver;
	}
	public SudokuView getView() {
		return view;
	}
	public void setView(SudokuView view) {
		this.view = view;
	}
	public GridModel getModel() {
		return model;
	}
	public void setModel(GridModel model) {
		this.model = model;
	}
}
