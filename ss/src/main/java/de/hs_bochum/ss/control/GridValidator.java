package de.hs_bochum.ss.control;

import de.hs_bochum.ss.modelNew.GridModel;

public class GridValidator {
	
	private GridModel model;
	
	public GridValidator(GridModel model){
		this.model = model;
	}
	
	public boolean isValid(){
		return true;
	}
	
	public boolean isValid(int row, int col){
		return true;
	}
	
	private boolean checkSquares(){
		return true;
	}
	
	public boolean checkSquare(int row, int col){
		return true;
	}
	
	private boolean checkRows(){
		return true;
	}
	
	public boolean checkRow(int row){
		return true;
	}
	
	private boolean checkColumns(){
		return true;
	}
	
	public boolean checkColumn(int col){
		return true;
	}
	
}
