package de.hs_bochum.ss.gui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.JTextPane;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;
import de.hs_bochum.ss.model.GridModel;

public class FocusListenerImpl implements FocusListener {
	
	private GridModel field;
	private int failure;
	
	public FocusListenerImpl(GridModel field){
		this.field = field;
	}

	public void focusGained(FocusEvent e) {
		//#DoNothing
	}

	public void focusLost(FocusEvent e) {
		JTextPane txt = (JTextPane)e.getComponent();
		int x = Integer.parseInt((txt.getName().split("\\."))[0]);
		int y = Integer.parseInt((txt.getName().split("\\."))[1]);
		try {
			if (txt.getText().isEmpty()) {
				return;
			}
			field.setFieldValue(Byte.parseByte(txt.getText()), x, y, false);
			if(field.isValueValid(x, y, Integer.parseInt(txt.getText())))
				txt.setBackground(Color.WHITE);
			else{
				txt.setBackground(Color.PINK);
				failure++;
			}
		} catch (NumberFormatException | IsLockedException | IsOutOfRangeException
				| CoordinateOutOfBoundsException e1) {
			e1.printStackTrace();
		}
	}

}
