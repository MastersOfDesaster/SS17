package de.hs_bochum.ss;

import de.hs_bochum.ss.control.GridValidator;
import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.model.GridModel;
import de.hs_bochum.ss.view.SudokuView;


public class App {
    public static void main( String[] args ) {
    	init();
    }

	private static void init() {
		
		//Model
    	GridModel model = new GridModel();
		
		//View
		SudokuView view = new SudokuView(model);

		//Control
		GridValidator validator = new GridValidator(model);
		SudokuSolverControl control = new SudokuSolverControl(model, view, validator);
		
		//View
		view.setControl(control);
	} 
}
