package de.hs_bochum.ss.algorithms;

import java.util.Iterator;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;
import de.hs_bochum.ss.model.GridCell;

public class CrookAlgorithm extends AbstractAlgorithm{

	public CrookAlgorithm(SudokuSolverControl control) {
		super(control);
		// TODO Auto-generated constructor stub
	}

	public void solve() throws Exception {
		// TODO Auto-generated method stub
		mark();
		findSingle();
		findForced();
		//findPreemptives();
		
	}
	
	private synchronized void mark() throws CoordinateOutOfBoundsException, InterruptedException{
		for(int y = 0; y <= 8; y++){
			for(int x = 0; x <= 8; x++){
				if(!running){
					return;
				}
				if(control.getCell(x, y).isLocked()){
					continue;
				}
				int value = control.getCell(x, y).getValue();
				if(value == 0){
					for(byte v = 1 ; v <= 9 ; v++){
						if(control.isValueValid(x, y, v)){
							control.addPossibleValue(x, y, v);
							if(paused){
								wait();
							}else{
								if(waitMillis != 0){
									wait(waitMillis);		
								}
							}
						}
					}
				}
			}
		}
		System.out.println("Finished marking");
	}
	
	private void findSingle() throws CoordinateOutOfBoundsException, IsOutOfRangeException{
		for(int y = 0; y <= 8; y++){
			for(int x = 0; x <= 8; x++){
				if(!running){
					return;
				}
				GridCell cell = control.getCell(x, y);
				if(!cell.isLocked()){
					Iterator<Integer> iterator = cell.getPossibleValues().iterator();
					int value;
					if(iterator.hasNext()){
						value = iterator.next();
					}else{
						continue;
					}
					if(!iterator.hasNext()){
						control.setCellValue(x, y, value);
						control.removePossibleValue(x, y, value);
						cell.removePossibleValue(value);
					}
				}
			}
		}
	}
	
	
	private void findForced() throws IsOutOfRangeException, CoordinateOutOfBoundsException{
		checkColumns();
		checkRows();
		checkSquares();
	}
	
	private void findPreemptives(){
		
	}
	
	private void checkSquares(){
		
	}
	
	private void checkColumns() throws IsOutOfRangeException, CoordinateOutOfBoundsException{
		//iterate through rows
		for(int column = 0; column < 9; column++){
			GridCell[] cells = control.getColumn(column);
			GridCell cell = null;
			//iterate through possible values
			for(int v = 1; v < 10; v++){
				int cellId = -1;
				
				//iterate through cells in the row
				for(int id = 0; id < 9; id++){
					cell = cells[id];
					if(cell.getPossibleValues().contains(v)){
						if(cellId == -1){
							cellId = id;
						}else{
							cellId = -1;
							break;
						}
					}
				}	
				if(cellId != -1){
					System.out.println(String.format("Found forced in column %s, id: %s, value: %s", column, cellId, v));
					//control.removePossibleValue(column, cellId, v);
					//control.setCellValue(column, cellId, v);
				}
			}
		}
		
	}
	
	private void checkRows() throws IsOutOfRangeException, CoordinateOutOfBoundsException{
		//iterate through rows
		for(int row = 0; row < 9; row++){
			GridCell[] cells = control.getRow(row);
			GridCell cell = null;
			//iterate through possible values
			for(int v = 1; v < 10; v++){
				int cellId = -1;
				
				//iterate through cells in the row
				for(int id = 0; id < 9; id++){
					cell = cells[id];
					if(cell.getPossibleValues().contains(v)){
						if(cellId == -1){
							cellId = id;
						}else{
							cellId = -1;
							break;
						}
					}
				}	
				if(cellId != -1){
					System.out.println(String.format("Found forced in row %s, id: %s, value: %s", row, cellId, v));
					control.removePossibleValue(cellId, row, v);
					
					
					GridCell[] column = control.getColumn(cellId);
					for(GridCell cCell : control.getColumn(cellId)){
						control.removePossibleValue(cCell.getX(), cCell.getY(), v);
					}
					
					GridCell[][] square = control.getSquare(cell.getX() / 3, cell.getY() / 3);
					for(int x = 0; x <9 ; x++){
						
					}
					
					
					control.setCellValue(cellId, row, v);
				}
			}
		}

	}

	@Override
	public void run() {
		try {
			solve();
			stop();
		} catch (Exception e) {
			control.handleError(e);
		}
		
	}

}
