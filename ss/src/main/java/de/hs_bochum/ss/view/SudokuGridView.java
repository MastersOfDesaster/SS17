package de.hs_bochum.ss.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import de.hs_bochum.ss.modelNew.GridModel;
import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.modelNew.GridCell;

public class SudokuGridView extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;

	private GridModel grid;
	
	private FocusListenerImpl focusListener;
	
	private Point lastChange;
	
//	private JTextPane[][] txtFields;
	private SudokuGridCellView[][] gridViews;
	private JPanel[][] subFields;
	
	public SudokuGridView(GridModel grid, SudokuView mainView) {
		this.setLayout(new GridLayout(3, 3));
		this.grid = grid;
		this.focusListener = new FocusListenerImpl(mainView);
		initTextFields();
		this.setVisible(true);
		this.repaint();
		this.grid.addObserver(this);
	}
	
	public void initTextFields(){
		this.subFields = new JPanel[3][3];
		this.gridViews = new SudokuGridCellView[9][9];
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
//				SimpleAttributeSet attribs = new SimpleAttributeSet();
//				StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
//				StyleConstants.setFontSize(attribs, 30);
				
//				JTextPane txt = new JTextPane();
//				txt.setParagraphAttributes(attribs, true);	
//				txt.setName(i + "." + j);
//				txt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
//				txt.addFocusListener(focusListener);
//				
////				txt.setText("1 2 3\n4 5 6\n7 8 9");
//				txt.setText("");
				
				this.gridViews[i][j] = new SudokuGridCellView(focusListener);
				this.subFields[i/3][j/3].add(this.gridViews[i][j]);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("hier");
		if (o instanceof GridModel){
			//update single cell
			if (arg instanceof Point){
				int x = (int) ((Point)arg).getX();
				int y = (int) ((Point)arg).getY();
				try {
					updateCell(grid.getCell(x,y),x,y);
				} catch (CoordinateOutOfBoundsException e) {
					e.printStackTrace();
				}
				if (lastChange != null){
					this.gridViews[(int) lastChange.getY()][(int) lastChange.getX()].setBackground(Color.WHITE);
				}
				this.gridViews[y][x].setBackground(Color.GREEN);
				lastChange = new Point(x, y);
			}
			//update whole field
			else{
				for (int y=0; y<9; y++){
					for (int x=0; x<9; x++){
						try {
							updateCell(grid.getCell(x,y),x,y);
						} catch (CoordinateOutOfBoundsException e) {
							e.printStackTrace();
						}						
					}
				}
			}
		}
	}
	
	
	private void updateCell(GridCell value, int x, int y){
		this.gridViews[x][y].setValueText(value.getValue());
		this.gridViews[x][y].setPossibleValueText(value.getPossibleValues());
	}
}
