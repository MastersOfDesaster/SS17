package de.hs_bochum.ss.interfaces;

import de.hs_bochum.ss.algorithms.BacktrackAlgorithm;
import de.hs_bochum.ss.algorithms.CrookAlgorithm;
import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.exception.UnknownAlgorithmException;

enum Algorithm {
	BACKTRACK("Backtrack Algorithmus"), CROOK("Crook ALgorithmus");
	
	private final String name;

	
	Algorithm(String name){
		this.name= name;
	}
	
	public String toString(){
		return name;
	}


AbstractAlgorithm toStrategy(Algorithm type, SudokuSolverControl control) throws UnknownAlgorithmException {
    switch(type) {
        case BACKTRACK:
            return new BacktrackAlgorithm(control);
        case CROOK:
            return new CrookAlgorithm(control);
            default:
            	throw new UnknownAlgorithmException(String.format("%s is unknown", type.toString()));
    }
}
}