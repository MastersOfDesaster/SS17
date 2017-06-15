package de.hs_bochum.ss.view;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;



public class FocusListenerImpl implements FocusListener {
	
	private SudokuView mainView;
	private int failure;
	
	public FocusListenerImpl(SudokuView mainView){
		this.mainView = mainView;
	}

	public void focusGained(FocusEvent e) {
		//#DoNothing
	}

	public void focusLost(FocusEvent e) {
		SudokuGridCellView sgcv = (SudokuGridCellView)e.getComponent();
		int x = Integer.parseInt((sgcv.getName().split("\\."))[0]);
		int y = Integer.parseInt((sgcv.getName().split("\\."))[1]);
		try {
			if (sgcv.getValue().isEmpty()) {
				return;
			}
			int value = Integer.parseInt(sgcv.getValue());
			mainView.setValueInModel(x, y, value);
//			if(control.isValueValid(x, y, Integer.parseInt(sgcv.getText())))
//				sgcv.setBackground(Color.WHITE);
//			else{
//				sgcv.setBackground(Color.PINK);
//				failure++;
//			}
		} catch (NumberFormatException  e1) {
			e1.printStackTrace();
		}
	}

}
