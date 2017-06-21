package de.hs_bochum.ss.model;

public class ReportModel {
	private int diffCount;
	private int writeCount;
	private int invalidCount;
	private boolean solved;

	@Override
	public String toString() {
		return "DiffCount: " + diffCount + "\nWriteCount: " + writeCount + "\nInvalidCount: " + invalidCount
				+ "\n\nSolved: " + solved;
	}

	public void clear() {
		diffCount = 0;
		writeCount = 0;
		invalidCount = 0;
		solved = false;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public int getDiffCount() {
		return diffCount;
	}

	public void increaseDiffCount() {
		diffCount++;
	}

	public int getWriteCount() {
		return writeCount;
	}

	public void increaseWriteCount() {
		writeCount++;
	}

	public int getInvalidCount() {
		return invalidCount;
	}

	public void increaseInvalidCount() {
		invalidCount++;
	}
}
