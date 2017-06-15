package de.hs_bochum.ss.algorithms;

import java.util.Random;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;

public class ThousandMonkeyAlgorithm extends AbstractAlgorithm{


	public ThousandMonkeyAlgorithm(SudokuSolverControl control) {
		super(control);
	}


	public void solve() throws CoordinateOutOfBoundsException, IsLockedException, IsOutOfRangeException, InterruptedException {
		Random rand = new Random();
		do{
		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 9; x++){
				if(!control.isCellLocked(x, y)){
					control.setCellValue(x, y, (rand.nextInt(8)+1));
				}
			}
		}
		if(paused){
			wait();
		}else{
			wait(waitMillis);
		}
		}while(!control.isValid());
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
