package de.hs_bochum.ss.algorithms;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.interfaces.AbstractAlgorithm;
import de.hs_bochum.ss.model.GridModel;

public class CrookAlgorithm extends AbstractAlgorithm{

	public CrookAlgorithm(SudokuSolverControl control) {
		super(control);
		// TODO Auto-generated constructor stub
	}

	public void solve(GridModel sudoku) throws Exception {
		// TODO Auto-generated method stub
		findForced();
		mark(sudoku);
		findPreemptives();
		
	}
	
	private void mark(GridModel sudoku) throws CoordinateOutOfBoundsException{
		for(int y = 0; y <= 8; y++){
			for(int x = 0; x <= 8; x++){
				byte value = sudoku.getFieldByte(x, y);
				if(value == 0){
					for(byte v = 1 ; v <= 9 ; v++){
						if(sudoku.isValueValid(x, y, v)){
							sudoku.addUsedValue(x, y, v);
						}
					}
				}
			}
		}
		System.out.println("Finished marking");
	}
	
	private void findForced(){
		//look for naked single usw.
	}
	
	private void findPreemptives(){
		
	}
	
	private void checkBoxes(){
		
	}
	
	private void checkColumns(){
		
	}
	
	private void checkRows(GridModel sudoku){
		for(int row =0; row <=8; row ++){
			for(int i = 8; i > 1 ;i--){
				
			}
		}

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void singleStep() {
		// TODO Auto-generated method stub
		
	}
}
