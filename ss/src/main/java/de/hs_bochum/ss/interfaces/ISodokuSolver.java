package de.hs_bochum.ss.interfaces;

import de.hs_bochum.ss.model.Field;

public interface ISodokuSolver {
	
	public void solve(Field sudoku) throws Exception;

	public void nextStep();
	
}
