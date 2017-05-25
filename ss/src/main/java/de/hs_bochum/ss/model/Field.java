package de.hs_bochum.ss.model;

import java.util.Observable;

import de.hs_bochum.ss.exception.IsLockedException;

public class Field extends Observable {
	
	private FieldValue[][] field;
	
	public Field() {
		this.field = new FieldValue[9][9];
		for(int j=0; j<field.length; j++){
			for (int i = 0; i < field[j].length; i++) {
				field[j][i] = new FieldValue();
			}
		}
	}
	
	public void setSingleField(byte value, int x, int y) throws IsLockedException{
		field[x][y].setValue(value);
		setChanged();
		notifyObservers();
	}
	
	public void setField(FieldValue[][] field){
		this.field = field;
		setChanged();
		notifyObservers();
	}

}
