package de.hs_bochum.ss.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.hs_bochum.ss.model.Field;

public class SodokuField extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private Field field;
	
	private JTextField[][] txtFields;
	private JPanel[][] subFields;
	
	public SodokuField() {
		this.setLayout(new GridLayout(3, 3));
		this.field = new Field();
		initTextFields();
		this.setVisible(true);
		this.repaint();
	}
	
	public Field getField(){
		return this.field;
	}
	
	public void initTextFields(){
		this.subFields = new JPanel[3][3];
		this.txtFields = new JTextField[9][9];
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				this.subFields[i][j] = new JPanel(new GridLayout(3, 3));
				this.subFields[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
//				this.subFields[i][j].setBackground(Color.GREEN);
				this.add(this.subFields[i][j]);
			}
		}
		for (int i=0; i<9; i++){
			for (int j=0; j<9; j++){
				this.txtFields[i][j] = new JTextField();
				this.txtFields[i][j].setDocument(new JTextFieldLimit(1));
				this.txtFields[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
				this.txtFields[i][j].setHorizontalAlignment(this.txtFields[i][j].getWidth()/2);
				this.subFields[i/3][j/3].add(this.txtFields[i][j]);
			}
		}
	}
	
	
}
