package de.hs_bochum.ss.algorithms;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.modelNew.GridModel;

public class BacktrackAlgorithm extends AbstractAlgorithm {

	public BacktrackAlgorithm(SudokuSolverControl control) {
		super(control);
		// TODO Auto-generated constructor stub
	}

	public void solve() throws Exception {
		solve(0, 0);
	}

	public boolean solve(int x, int y) throws Exception {
		if (x == 9) {
			x = 0;
			y++;
		}

		if (y == 9) {
			return true;
		}

		if (sudoku.getFieldByte(x, y) != 0) {
			return solve(sudoku, x+1, y);
		} else {
			for (byte i = 1; i < 10; i++) {
				sudoku.setFieldValue(i, x, y);
				Thread.sleep(waitMillis);
				if (!sudoku.isValueValid(x, y, i)) {
					continue;
				}
				if(solve(sudoku, x+1, y)){
					return true;
				};
			}
			sudoku.resetFieldValue(x, y);
			Thread.sleep(waitMillis);
			return false;
		}
	}

	@Override
	public void start() {
		run();		
	}

	@Override
	public void pause() {
		paused = true;
		
	}
	
	@Override
	public void resume() {
		paused = false;
		
	}
	

	@Override
	public void singleStep() {
		if(paused){
			this.notify();
		}
		
	}

	@Override
	public void run() {	
	}
}
