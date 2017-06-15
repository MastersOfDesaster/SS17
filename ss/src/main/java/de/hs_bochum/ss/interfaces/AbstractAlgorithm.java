package de.hs_bochum.ss.interfaces;

import de.hs_bochum.ss.control.SudokuSolverControl;

public abstract class AbstractAlgorithm {
	
	
	
	protected SudokuSolverControl control;
	protected boolean singleStep;
	protected long waitMillis = 100;

	
	public AbstractAlgorithm(SudokuSolverControl control){
		this.control = control;
	}
		
	public abstract void start();
	
	public abstract void stop();
	
	public abstract void singleStep();
	

	public void setSecondsToWait(int secondsToWait) {
		waitMillis= secondsToWait * 1000;
	}

}