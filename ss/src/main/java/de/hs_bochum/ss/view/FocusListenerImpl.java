package de.hs_bochum.ss.view;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;



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
		JTextField txt = (JTextField)e.getComponent();
		int x = Integer.parseInt((txt.getName().split("\\."))[0]);
		int y = Integer.parseInt((txt.getName().split("\\."))[1]);
		try {
			if (txt.getText().isEmpty()) {
				mainView.setManual(true);
				mainView.resetCellValue(x, y);
				return;
			}
			int value = Integer.parseInt(txt.getText());
			mainView.setManual(true);
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
