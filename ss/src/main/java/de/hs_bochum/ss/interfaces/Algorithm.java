package de.hs_bochum.ss.interfaces;

import com.sun.javafx.image.impl.ByteIndexed.Getter;

import de.hs_bochum.ss.algorithms.BacktrackAlgorithm;
import de.hs_bochum.ss.algorithms.CrookAlgorithm;
import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.exception.UnknownAlgorithmException;

public enum Algorithm {
	BACKTRACK("Backtrack Algorithmus"), CROOK("Crook ALgorithmus");

	private final String name;

	Algorithm(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public AbstractAlgorithm toStrategy(SudokuSolverControl control) throws UnknownAlgorithmException {
		switch (this) {
		case BACKTRACK:
			return new BacktrackAlgorithm(control);
		case CROOK:
			return new CrookAlgorithm(control);
		default:
			throw new UnknownAlgorithmException(String.format("%s is unknown", toString()));
		}
	}
}