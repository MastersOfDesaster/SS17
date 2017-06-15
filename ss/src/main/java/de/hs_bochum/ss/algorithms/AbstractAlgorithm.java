package de.hs_bochum.ss.algorithms;

import de.hs_bochum.ss.control.SudokuSolverControl;

public abstract class AbstractAlgorithm implements Runnable{
	
	
	
	protected SudokuSolverControl control;
	protected boolean paused;
	protected volatile boolean running;
	protected long waitMillis = 10;

	
	public AbstractAlgorithm(SudokuSolverControl control){
		this.control = control;
	}
		
	public void start() {
		if(!running){
			running = true;
			new Thread(this).start();	
		}
	}

	public void pause() {
		paused = true;
		
	}
	
	public void resume() {
		paused = false;
		synchronized (this) {
			this.notify();	
		}
	}
	
	public void stop() {
		running = false;
	}
	

	public void singleStep() {
		if(paused){
			synchronized (this) {
				this.notify();	
			}
		}
	}
	

	public void setSecondsToWait(int secondsToWait) {
		waitMillis= secondsToWait * 1000;
	}

}