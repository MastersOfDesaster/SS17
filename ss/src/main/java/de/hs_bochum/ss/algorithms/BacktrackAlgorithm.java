package de.hs_bochum.ss.algorithms;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.interfaces.AbstractAlgorithm;
import de.hs_bochum.ss.model.GridModel;

public class BacktrackAlgorithm extends AbstractAlgorithm {

	public BacktrackAlgorithm(SudokuSolverControl control) {
		super(control);
		// TODO Auto-generated constructor stub
	}

	public void solve(GridModel sudoku) throws Exception {
		solve(sudoku, 0, 0);
	}

	public boolean solve(GridModel sudoku, int x, int y) throws Exception {
		print(sudoku);
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
	
	private void print(GridModel sudoku) throws CoordinateOutOfBoundsException{
		System.out.println();
		System.out.println();
		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 9; x++){
				System.out.print(sudoku.getFieldByte(x, y) + " ");
			}
			System.out.println();
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
