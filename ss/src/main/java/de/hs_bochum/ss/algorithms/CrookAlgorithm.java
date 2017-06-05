package de.hs_bochum.ss.algorithms;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.interfaces.ISudokuSolver;
import de.hs_bochum.ss.model.Field;
import de.hs_bochum.ss.model.FieldValue;

public class CrookAlgorithm extends ISudokuSolver{

	@Override
	public void solve(Field sudoku) throws Exception {
		// TODO Auto-generated method stub
		findForced();
		mark(sudoku);
		findPreemptives();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	private void mark(Field sudoku) throws CoordinateOutOfBoundsException{
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
	
	private void checkRows(Field sudoku){
		for(int row =0; row <=8; row ++){
			for(int i = 8; i > 1 ;i--){
				
			}
		}

	}
}
