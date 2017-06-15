package de.hs_bochum.ss;

import de.hs_bochum.ss.control.GridValidator;
import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.modelNew.GridModel;
import de.hs_bochum.ss.view.SudokuView;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
    	init();
    	start();
    }



	private static void init() {
		SudokuView view = new SudokuView();
    	GridModel model = new GridModel();
    	GridValidator validator = new GridValidator(model);
    	
    	@SuppressWarnings("unused")
		SudokuSolverControl control = new SudokuSolverControl(model, view, validator);
	}
	
	private static void start() {
		// TODO Auto-generated method stub
		
	}
    
    
}
