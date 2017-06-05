package de.hs_bochum.ss.algorithms;

import java.util.Random;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;
import de.hs_bochum.ss.interfaces.ISudokuSolver;
import de.hs_bochum.ss.model.Field;

public class ThousandMonkeyAlgorithm extends ISudokuSolver{

	@Override
	public void solve(Field sudoku) throws CoordinateOutOfBoundsException, IsLockedException, IsOutOfRangeException {
		Random rand = new Random();
		do{
		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 9; x++){
				if(!sudoku.isFieldLocked(x, y)){
					sudoku.setFieldValue((byte) (rand.nextInt(8)+1), x, y);
				}
			}
		}
		print(sudoku);
		}while(!sudoku.isValid());
	}

	
	private void print(Field sudoku) throws CoordinateOutOfBoundsException{
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
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}
