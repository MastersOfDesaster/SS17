package de.hs_bochum.ss.exception;

public class IsOutOfRangeException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public IsOutOfRangeException() {
		
	}
	
	public IsOutOfRangeException(String message) {
		super(message);
	}
}
