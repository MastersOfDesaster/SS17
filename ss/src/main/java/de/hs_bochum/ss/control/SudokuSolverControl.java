package de.hs_bochum.ss.control;

import de.hs_bochum.ss.gui.Window;
import de.hs_bochum.ss.interfaces.ISodokuSolver;
import de.hs_bochum.ss.model.Field;

public class SudokuSolverControl {
	
	private ISodokuSolver solver;
	private Window view;
	private Field model;
	
	
	public ISodokuSolver getSolver() {
		return solver;
	}
	public void setSolver(ISodokuSolver solver) {
		this.solver = solver;
	}
	public Window getView() {
		return view;
	}
	public void setView(Window view) {
		this.view = view;
	}
	public Field getModel() {
		return model;
	}
	public void setModel(Field model) {
		this.model = model;
	}
}
