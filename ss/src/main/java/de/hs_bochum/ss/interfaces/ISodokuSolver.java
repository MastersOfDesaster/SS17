package de.hs_bochum.ss.interfaces;

import de.hs_bochum.ss.model.Field;

public interface ISodokuSolver {
	
	public void solve(Field sudoku);

	public void nextStep();
	
}
