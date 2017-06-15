package de.hs_bochum.ss.modelNew;

public class ReportModel {
	private int diffCount;
	private int writeCount;
	private int invalidCount;
	
	@Override
	public String toString() {
		return "DiffCount: " + diffCount + "\nWriteCount: " + writeCount + "\nInvalidCount: " + invalidCount;
	}
	
	public void clear() {
		diffCount = 0;
		writeCount = 0;
		invalidCount = 0;
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
