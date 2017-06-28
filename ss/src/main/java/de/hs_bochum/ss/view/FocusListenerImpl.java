package de.hs_bochum.ss.view;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class FocusListenerImpl implements FocusListener {

    private SudokuView mainView;

    public FocusListenerImpl(SudokuView mainView) {
        this.mainView = mainView;
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        // #DoNothing
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField txt = (JTextField) e.getComponent();
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
        } catch (NumberFormatException e1) {
            e1.printStackTrace();
        }
    }

}
