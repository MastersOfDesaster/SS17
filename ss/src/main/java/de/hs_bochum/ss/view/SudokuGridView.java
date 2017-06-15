package de.hs_bochum.ss.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.modelNew.GridModel;
import de.hs_bochum.ss.modelNew.GridCell;

public class SudokuGridView extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;

	private GridModel field;
	
	private FocusListenerImpl focusListener;
	
	private Point lastChange;
	
	private JTextPane[][] txtFields;
	private JPanel[][] subFields;
	
	public SudokuGridView() {
		this.setLayout(new GridLayout(3, 3));
		this.field = new GridModel();
		this.focusListener = new FocusListenerImpl(field);
		initTextFields();
		this.setVisible(true);
		this.repaint();
		field.addObserver(this);
	}
	
	public GridModel getField(){
		return this.field;
	}
	
	public void initTextFields(){
		this.subFields = new JPanel[3][3];
		this.txtFields = new JTextPane[9][9];
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				this.subFields[i][j] = new JPanel(new GridLayout(3, 3));
				this.subFields[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
				this.add(this.subFields[i][j]);
			}
		}
		for (int i=0; i<9; i++){
			for (int j=0; j<9; j++){
				// center text
				SimpleAttributeSet attribs = new SimpleAttributeSet();
				StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
				StyleConstants.setFontSize(attribs, 30);
				
				JTextPane txt = new JTextPane();
				txt.setParagraphAttributes(attribs, true);	
				txt.setName(i + "." + j);
				txt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
				txt.addFocusListener(focusListener);
				
//				txt.setText("1 2 3\n4 5 6\n7 8 9");
				txt.setText("");
				
				this.txtFields[i][j] = txt;
				this.subFields[i/3][j/3].add(txt);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GridModel){
			//update single cell
			if (arg instanceof Point){
				int x = (int) ((Point)arg).getX();
				int y = (int) ((Point)arg).getY();
				try {
					updateCell(field.getFieldValue(x,y),x,y);
					if (lastChange != null){
						this.txtFields[(int) lastChange.getY()][(int) lastChange.getX()].setBackground(Color.WHITE);
					}
					this.txtFields[y][x].setBackground(Color.GREEN);
					lastChange = new Point(x, y);
				} catch (CoordinateOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
			//update whole field
			else{
				for (int y=0; y<9; y++){
					for (int x=0; x<9; x++){
						try {
								updateCell(field.getFieldValue(x,y),x,y);						
						} catch (CoordinateOutOfBoundsException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	
	private void updateCell(GridCell value, int x, int y){
		if(value.getValue() == 0){
			if(value.usedValueSet().isEmpty()){
				this.txtFields[x][y].setText("");
			}else{
				String text = new String();
				for(Byte i=1;i<=9;i++){
					if(value.usedValueSet().contains(i)){
						if(i % 3 == 0){
							text = text + i;
							if(i != 9){
								text = text + "\n";	
							}
						}else{
							text = text + i +" ";
						}
					}else{
						if(i % 3 == 0){
							text = text + " ";
							if(i != 9){
								text = text + "\n";	
							}
						}else{
							text = text + "  ";
						}
					}
				}
				this.txtFields[x][y].setText(text);
			}
		}else{
			this.txtFields[x][y].setText(String.valueOf(value.getValue()));
		}
	}
}
