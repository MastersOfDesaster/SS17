package de.hs_bochum.ss.model;

import java.util.Observable;

public class Field extends Observable {
	
	private FieldValue[][] field;
	
	public Field() {
		this.field = new FieldValue[9][9];
	}

}
