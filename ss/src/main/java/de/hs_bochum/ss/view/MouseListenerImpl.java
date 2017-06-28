package de.hs_bochum.ss.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class MouseListenerImpl implements MouseListener {

    private SudokuView mainView;

    public MouseListenerImpl(SudokuView mainView) {
        this.mainView = mainView;
    }
    
    @Override
	public void mouseReleased(MouseEvent e) {
        // #DoNothing
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 3){
			JTextField txt = (JTextField) e.getComponent();
	        int x = Integer.parseInt((txt.getName().split("\\."))[0]);
	        int y = Integer.parseInt((txt.getName().split("\\."))[1]);
			mainView.openContextMenu(x, y);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
        // #DoNothing
	}
	@Override
	public void mouseEntered(MouseEvent e) {
        // #DoNothing
	}
	@Override
	public void mouseClicked(MouseEvent e) {
        // #DoNothing
	}

}
