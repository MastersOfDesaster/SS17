package de.hs_bochum.ss.algorithms;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.interfaces.ISodokuSolver;
import de.hs_bochum.ss.model.Field;

public class BacktrackAlgorithm implements ISodokuSolver {

	public void solve(Field sudoku) throws Exception {
		// TODO Auto-generated method stub
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

		if (sudoku.isFieldLocked(x, y)) {
			return solve(sudoku, x++, y);
		} else {
			for (byte i = 1; i < 9; i++) {
				sudoku.setFieldValue(i, x, y);
				if (!sudoku.isFieldValid(x, y)) {
					continue;
				}
				return solve(sudoku, x++, y);
			}
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

	public void nextStep() {

	}

}
