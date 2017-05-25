package de.hs_bochum.ss.algorithms;

import de.hs_bochum.ss.interfaces.ISodokuSolver;
import de.hs_bochum.ss.model.Field;

public class BacktrackAlgorithm implements ISodokuSolver{


	public void solve(Field sudoku) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean solve(Field sudoku, int x, int y){
		if (y == 9){
			return true;
		}
		if(x == 9){
			x = 0;
			y++;
		}
		return false;
	}

}
