package de.hs_bochum.ss.exception;

public class CoordinateOutOfBoundsException extends Exception {
	private static final long serialVersionUID = 1L;

	public CoordinateOutOfBoundsException() {
		
	}
	
	public CoordinateOutOfBoundsException(String message) {
		super(message);
	}
}
