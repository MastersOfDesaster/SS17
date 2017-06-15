package de.hs_bochum.ss.algorithms;

import de.hs_bochum.ss.control.SudokuSolverControl;

public abstract class AbstractAlgorithm implements Runnable{
	
	
	
	protected SudokuSolverControl control;
	protected boolean paused;
	protected long waitMillis = 100;

	
	public AbstractAlgorithm(SudokuSolverControl control){
		this.control = control;
	}
		
	public abstract void start();
	
	public abstract void pause();
	
	public abstract void resume();
	
	public abstract void singleStep();
	

	public void setSecondsToWait(int secondsToWait) {
		waitMillis= secondsToWait * 1000;
	}

}