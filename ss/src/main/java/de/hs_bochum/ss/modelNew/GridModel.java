package de.hs_bochum.ss.modelNew;


import java.util.Observable;


public class GridModel extends Observable {

	private GridCell[][] field;
	private GridCell active;
	private int invalidCellCount;
	private boolean solved;
	
	
	
	public GridCell[][] getField() {
		return field;
	}

	public void setField(GridCell[][] field) {
		this.field = field;
	}

	public GridCell getActive() {
		return active;
	}

	public void setActive(GridCell active) {
		this.active = active;
	}

	public int getInvalidCellCount() {
		return invalidCellCount;
	}

	public void setInvalidCellCount(int invalidCellCount) {
		this.invalidCellCount = invalidCellCount;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public GridCell[] getRow(int row){
		return null;
	}
	
	public GridCell[] getColumn(int column){
		return null;
	}
	
	public GridCell[] getSquare(int row, int column){
		return null;
	}
	
	public boolean isValid(){
		return true;
	}
	
}
