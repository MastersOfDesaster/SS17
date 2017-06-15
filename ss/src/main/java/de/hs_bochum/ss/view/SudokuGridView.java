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

public class SudokuGridView extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private GridModel grid;

	private FocusListenerImpl focusListener;

	private Point lastChange;

	private SudokuView mainView;

	// private JTextPane[][] txtFields;
	private SudokuGridCellView[][] gridViews;
	private JPanel[][] subFields;

	public SudokuGridView(GridModel grid, SudokuView mainView) {
		this.mainView = mainView;
		this.setLayout(new GridLayout(3, 3));
		this.grid = grid;
		this.focusListener = new FocusListenerImpl(this.mainView);
		initTextFields();
		this.setVisible(true);
		this.repaint();
		this.grid.addObserver(this);
	}

	public void initTextFields() {
		this.subFields = new JPanel[3][3];
		this.gridViews = new SudokuGridCellView[9][9];
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				this.subFields[x][y] = new JPanel(new GridLayout(3, 3));
				this.subFields[x][y].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
				this.add(this.subFields[x][y]);
			}
		}

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				// center text
				// SimpleAttributeSet attribs = new SimpleAttributeSet();
				// StyleConstants.setAlignment(attribs,
				// StyleConstants.ALIGN_CENTER);
				// StyleConstants.setFontSize(attribs, 30);

				// JTextPane txt = new JTextPane();
				// txt.setParagraphAttributes(attribs, true);
				// txt.setName(i + "." + j);
				// txt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,
				// 1));
				// txt.addFocusListener(focusListener);
				//
				//// txt.setText("1 2 3\n4 5 6\n7 8 9");
				// txt.setText("");

				this.gridViews[x][y] = new SudokuGridCellView(focusListener, x, y);
				this.subFields[x / 3][y / 3].add(this.gridViews[x][y]);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GridModel) {
			// update single cell
			if (arg instanceof Point) {
				int x = (int) ((Point) arg).getX();
				int y = (int) ((Point) arg).getY();
				try {
					updateCell(grid.getCell(x, y), x, y);
				} catch (CoordinateOutOfBoundsException e) {
					e.printStackTrace();
				}
				if (lastChange != null) {
					this.gridViews[(int) lastChange.getX()][(int) lastChange.getY()].setBackground(Color.WHITE);
				}
				if (!mainView.getManual())
					this.gridViews[x][y].setBackground(Color.GREEN);
				lastChange = new Point(x, y);
			}
			// update whole field
			else {
				for (int y = 0; y < 9; y++) {
					for (int x = 0; x < 9; x++) {
						try {
							updateCell(grid.getCell(x, y), x, y);
						} catch (CoordinateOutOfBoundsException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		mainView.setManual(false);
	}

	private void updateCell(GridCell value, int x, int y) {
		this.gridViews[x][y].setValueText(value.getValue());
		this.gridViews[x][y].setPossibleValueText(value.getPossibleValues());
		this.gridViews[x][y].setColor((mainView.isCellValid(x, y) ? Color.WHITE : Color.PINK));
	}
}
