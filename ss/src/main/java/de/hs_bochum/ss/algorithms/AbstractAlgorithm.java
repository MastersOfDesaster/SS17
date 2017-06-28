package de.hs_bochum.ss.algorithms;

import de.hs_bochum.ss.control.SudokuSolverControl;

public abstract class AbstractAlgorithm implements Runnable {

    protected SudokuSolverControl control;
    protected volatile boolean paused;
    protected volatile boolean running;
    protected volatile long waitMillis = 0;

    public AbstractAlgorithm(SudokuSolverControl control) {
        this.control = control;
    }

    public void start() {
        if (!running) {
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

        control.onAlgoFinished();
    }

    public void singleStep() {
        if (paused) {
            synchronized (this) {
                this.notify();
            }
        }
    }

    public void setSecondsToWait(int secondsToWait) {
        waitMillis = secondsToWait * 1000;
    }

    public void setMillisToWait(int millis) {
        waitMillis = millis;
    }

    public long getWaitMillis() {
        return waitMillis;
    }
}