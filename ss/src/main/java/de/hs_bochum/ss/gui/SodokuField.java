package de.hs_bochum.ss.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;

import de.hs_bochum.ss.model.Field;

public class SodokuField extends JFrame{
	
	private Field field;
	
	private JTextField txtEmpty;
	
	public SodokuField() {
		this.field = new Field();
		
	}
	
	public Field getField(){
		return this.field;
	}
}
