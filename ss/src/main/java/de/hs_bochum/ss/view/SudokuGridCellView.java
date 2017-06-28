package de.hs_bochum.ss.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusListener;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SudokuGridCellView extends JPanel {

    private static final long serialVersionUID = -7870657366972381246L;

    private JTextField value;
    private JTextField possibleValues;

    public SudokuGridCellView(FocusListener listener, int x, int y) {
        this.value = new JTextField("");
        this.value.setDocument(new JTextFieldLimit(1));
        this.value.addFocusListener(listener);
        this.value.setName(x + "." + y);
        this.possibleValues = new JTextField("123456789");
        this.possibleValues.setDocument(new JTextFieldLimit(9));
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.add(value, BorderLayout.CENTER);
        this.add(possibleValues, BorderLayout.SOUTH);
    }

    public void setValueText(int value) {
        if (value == 0)
            this.value.setText("");
        else
            this.value.setText(value + "");
    }

    public void setPossibleValueText(Set<Integer> possibleValues) {
        String txt = "";
        for (int value : possibleValues)
            txt += value;
        this.possibleValues.setText(txt);
    }

    public String getValue() {
        return this.value.getText();
    }

    public void setColor(Color c) {
        this.value.setBackground(c);
    }

}
