package de.hs_bochum.ss.algorithms;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
		
		for(int i = 0; i < 10; i++){
			findSingle();
			findForced();
			findPreemptives();
		}

		
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
						
						for(GridCell cCell : control.getRow(cell.getY())){
							control.removePossibleValue(cCell.getX(), cCell.getY(), value);
						}
						
						for(GridCell cCell : control.getColumn(cell.getX())){
							control.removePossibleValue(cCell.getX(), cCell.getY(), value);
						}
						
						for(GridCell cCell : control.getSquare(cell.getX() / 3, cell.getY() / 3)){
							control.removePossibleValue(cCell.getX(), cCell.getY(), value);
						}
						
						
					}
				}
			}
		}
	}
	
	
	private void findForced() throws IsOutOfRangeException, CoordinateOutOfBoundsException{
		checkColumns();
	//	checkRows();
	//	checkSquares();
	}
	
	private void findPreemptives(){
		
	}
	
	private void checkSquares(){
		
	}
	
	private void checkColumns() throws IsOutOfRangeException, CoordinateOutOfBoundsException{
		//iterate through rows
		for(int column = 0; column < 9; column++){
			List<GridCell> cells = control.getColumn(column);
			GridCell cell = null;
			//iterate through possible values
			for(int v = 1; v < 10; v++){
				int cellId = -1;
				
				//iterate through cells in the row
				for(int id = 0; id < 9; id++){
					cell = cells.get(id);
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
					control.removeAllPossibleValues(column, cellId);

					for(GridCell cCell : control.getRow(cellId)){
						control.removePossibleValue(cCell.getX(), cCell.getY(), v);
					}
					
					for(GridCell cCell : control.getSquare(column / 3, cellId / 3)){
						control.removePossibleValue(cCell.getX(), cCell.getY(), v);
					}
					
					
					control.setCellValue(column, cellId, v);
				}
				

			}
		}
		
	}
	
	private void checkRows() throws IsOutOfRangeException, CoordinateOutOfBoundsException{
		//iterate through rows
		for(int row = 0; row < 9; row++){
			List<GridCell> cells = control.getRow(row);
			GridCell cell = null;
			//iterate through possible values
			for(int v = 1; v < 10; v++){
				int cellId = -1;
				
				//iterate through cells in the row
				for(int id = 0; id < 9; id++){
					cell = cells.get(id);
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
					control.removeAllPossibleValues(cellId, row);
					
					
					for(GridCell cCell : control.getColumn(cellId)){
						control.removePossibleValue(cCell.getX(), cCell.getY(), v);
					}
					
					for(GridCell cCell : control.getSquare(cellId / 3, row / 3)){
						control.removePossibleValue(cCell.getX(), cCell.getY(), v);
					}
					
					
					control.setCellValue(cellId, row, v);
				}
			}
		}

	}
	
	private void findAllNakedPairs() {
		for (int y = 0; y < 9; y++) {
			findNakedPairs(control.getRow(y));
		}
		for (int x = 0; x < 9; x++) {
			findNakedPairs(control.getColumn(x));
		}
		for (int square = 0; square < 9; square++) {
			findNakedPairs(control.getSquare(square));
		}
	}

	private void findNakedPairs(List<GridCell> cells) {
		int i1 = 0; // first occurance
		int i2 = 0; // second occurance
		Set<Integer> values = null;
		boolean foundNakedPair = false;

		for (i1 = 0; i1 < 8; i1++) { // 9-1
			if (cells.get(i1).getPossibleValues().size() == 2) { // first
																// occurance
				values = cells.get(i1).getPossibleValues();
				for (i2 = i1 + 1; i2 < 9; i2++) {
					if (cells.get(i2).getPossibleValues().equals(values)) { // second
																			// occurance
						foundNakedPair = true;
						break;
					}
				}
			}

			if (foundNakedPair) {
				for (int i = 0; i < 9; i++) {
					if ((i != i1) && (i != i2)) {
						for (Integer value : values) {
							try {
								control.removePossibleValue(cells.get(i).getX(), cells.get(i).getY(), value);
							} catch (IsOutOfRangeException | CoordinateOutOfBoundsException e) {
								control.handleError(e);
							}
						}
					}
				}

				foundNakedPair = false;
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
