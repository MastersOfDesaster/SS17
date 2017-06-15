package de.hs_bochum.ss.view;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.modelNew.GridModel;

public class SudokuView {
	
	private GridModel model;
	private SudokuSolverControl control;
	

	


	public SudokuView(GridModel model, SudokuSolverControl control) {
		super();
		this.model = model;
		this.control = control;
	}
	
	
	public void showError(Exception e){
		
	}
	




	public GridModel getModel() {
		return model;
	}


	public void setModel(GridModel model) {
		this.model = model;
	}


	public SudokuSolverControl getControl() {
		return control;
	}


	public void setControl(SudokuSolverControl control) {
		this.control = control;
	}
	
	

}
