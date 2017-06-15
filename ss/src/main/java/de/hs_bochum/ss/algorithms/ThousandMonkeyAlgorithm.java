package de.hs_bochum.ss.algorithms;

import java.util.Random;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;
import de.hs_bochum.ss.model.GridModel;

public class ThousandMonkeyAlgorithm extends AbstractAlgorithm{


	public ThousandMonkeyAlgorithm(SudokuSolverControl control) {
		super(control);
		// TODO Auto-generated constructor stub
	}


	public void solve(GridModel sudoku) throws CoordinateOutOfBoundsException, IsLockedException, IsOutOfRangeException {
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
	public void pause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void singleStep() {
		// TODO Auto-generated method stub
		
	}

}
