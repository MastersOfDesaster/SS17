package de.hs_bochum.ss.interfaces;

import de.hs_bochum.ss.model.Field;

public abstract class ISudokuSolver {
	

	protected long waitMillis = 100;
	protected int steps;

		
	public abstract void solve(Field sudoku) throws Exception;
	
	public abstract void pause();
	

	public void setSecondsToWait(int secondsToWait) {
		waitMillis= secondsToWait * 1000;
	}

	public int getSteps() {
		return steps;
	}
	
	
}
