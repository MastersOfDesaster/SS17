package de.hs_bochum.ss.gui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;
import de.hs_bochum.ss.model.Field;

public class FocusListenerImpl implements FocusListener {
	
	private Field field;
	private int failure;
	
	public FocusListenerImpl(Field field){
		this.field = field;
	}

	public void focusGained(FocusEvent e) {
		//#DoNothing
	}

	public void focusLost(FocusEvent e) {
		JTextField txt = (JTextField)e.getComponent();
		int x = Integer.parseInt((txt.getName().split("\\."))[0]);
		int y = Integer.parseInt((txt.getName().split("\\."))[1]);
		try {
			field.setFieldValue(Byte.parseByte(txt.getText()), x, y, false);
			if(field.isFieldValid(x, y))
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
