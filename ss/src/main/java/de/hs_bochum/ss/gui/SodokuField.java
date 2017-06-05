package de.hs_bochum.ss.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.model.Field;

public class SodokuField extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;

	private Field field;
	
	private FocusListenerImpl focusListener;
	
	private Point lastChange;
	
	private JTextField[][] txtFields;
	private JTextField[][] usedValueFields;
	private JPanel[][] subFields;
	
	public SodokuField() {
		this.setLayout(new GridLayout(3, 3));
		this.field = new Field();
		this.focusListener = new FocusListenerImpl(field);
		initTextFields();
		this.setVisible(true);
		this.repaint();
		field.addObserver(this);
	}
	
	public Field getField(){
		return this.field;
	}
	
	public void initTextFields(){
		this.subFields = new JPanel[3][3];
		this.txtFields = new JTextField[9][9];
		this.usedValueFields = new JTextField[9][9];
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				this.subFields[i][j] = new JPanel(new GridLayout(3, 3));
				this.subFields[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
				this.add(this.subFields[i][j]);
			}
		}
		for (int i=0; i<9; i++){
			for (int j=0; j<9; j++){
				this.txtFields[i][j] = new JTextField();
				this.txtFields[i][j].setName(i + "." + j);
				this.txtFields[i][j].setDocument(new JTextFieldLimit(1));
				this.txtFields[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
				this.txtFields[i][j].setHorizontalAlignment(this.txtFields[i][j].getWidth()/2);
				this.txtFields[i][j].addFocusListener(focusListener);
				this.subFields[i/3][j/3].add(this.txtFields[i][j]);
				
				JTextField usedValueField = new JTextField();
				usedValueField.setText("123456789");
				this.usedValueFields[i][j] = usedValueField;
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Field){
			if (arg instanceof Point){
				int x = (int) ((Point)arg).getX();
				int y = (int) ((Point)arg).getY();
				try {
					this.txtFields[y][x].setText(field.getFieldValue(x, y) + "");
					if (lastChange != null){
						this.txtFields[(int) lastChange.getX()][(int) lastChange.getY()].setBackground(Color.WHITE);
					}
					this.txtFields[y][x].setBackground(Color.GREEN);
					lastChange = new Point(y, x);
				} catch (CoordinateOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
			else{
				for (int i=0; i<9; i++){
					for (int j=0; j<9; j++){
						try {
							this.txtFields[j][i].setText(field.getFieldValue(i, j) + "");
						} catch (CoordinateOutOfBoundsException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
