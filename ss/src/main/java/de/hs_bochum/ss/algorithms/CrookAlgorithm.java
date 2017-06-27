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
		//findForced();
		//findPreemptives();
		
	}
	
	private synchronized void mark() throws CoordinateOutOfBoundsException, InterruptedException{
		for(int y = 0; y <= 8; y++){
			for(int x = 0; x <= 8; x++){
				if(!running){
					return;
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
					}
				}
			}
		}
	}
	
	
	private void findForced(){
		for(int i = 0; i < 9; i++){
			for(int v = 1; v < 10; v++){
				int cellPos = 0;
				for(GridCell cell : control.getColumn(i)){
					if(cell.getPossibleValues().contains(v)){
						//save cellPosition
						//wenn zahl schon gefunden -> return
					}
				}
				//Zahl in Zelle schreiben
			}			
		}
		
	}
	
	private void findPreemptives(){
		
	}
	
	private void checkBoxes(){
		
	}
	
	private void checkColumns(){
		
	}
	
	private void checkRows(){
		for(int row =0; row <=8; row ++){
			for(int i = 8; i > 1 ;i--){
				
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
