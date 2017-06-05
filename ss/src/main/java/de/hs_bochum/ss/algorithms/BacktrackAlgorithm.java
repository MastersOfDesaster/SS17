package de.hs_bochum.ss.algorithms;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.interfaces.ISudokuSolver;
import de.hs_bochum.ss.model.Field;

public class BacktrackAlgorithm extends ISudokuSolver {

	public void solve(Field sudoku) throws Exception {
		solve(sudoku, 0, 0);
	}

	public boolean solve(Field sudoku, int x, int y) throws Exception {
		print(sudoku);
		if (x == 9) {
			x = 0;
			y++;
		}

		if (y == 9) {
			return true;
		}

		if (sudoku.getFieldValue(x, y) != 0) {
			return solve(sudoku, x+1, y);
		} else {
			for (byte i = 1; i < 10; i++) {
				sudoku.setFieldValue(i, x, y);
				Thread.sleep(waitMillis);
				steps++;
				if (!sudoku.isFieldValid(x, y)) {
					continue;
				}
				if(solve(sudoku, x+1, y)){
					return true;
				};
			}
			sudoku.resetFieldValue(x, y);
			Thread.sleep(waitMillis);
			steps++;
			return false;
		}
	}
	
	private void print(Field sudoku) throws CoordinateOutOfBoundsException{
		System.out.println();
		System.out.println();
		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 9; x++){
				System.out.print(sudoku.getFieldValue(x, y) + " ");
			}
			System.out.println();
		}
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}


}
