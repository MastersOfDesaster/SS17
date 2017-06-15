package de.hs_bochum.ss.algorithms;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;

public class CrookAlgorithm extends AbstractAlgorithm{

	public CrookAlgorithm(SudokuSolverControl control) {
		super(control);
		// TODO Auto-generated constructor stub
	}

	public void solve() throws Exception {
		// TODO Auto-generated method stub
		findForced();
		mark();
		findPreemptives();
		
	}
	
	private synchronized void mark() throws CoordinateOutOfBoundsException, InterruptedException{
		for(int y = 0; y <= 8; y++){
			for(int x = 0; x <= 8; x++){
				int value = control.getCell(x, y).getValue();
				if(value == 0){
					for(byte v = 1 ; v <= 9 ; v++){
						if(control.isValueValid(x, y, v)){
							control.addPossibleValue(x, y, v);
							if(paused){
								wait();
							}else{
								wait(waitMillis);
							}
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
	
	private void checkRows(){
		for(int row =0; row <=8; row ++){
			for(int i = 8; i > 1 ;i--){
				
			}
		}

	}

	@Override
	public void run() {
		try {
			solve();
		} catch (Exception e) {
			control.handleError(e);
		}
		
	}

}
