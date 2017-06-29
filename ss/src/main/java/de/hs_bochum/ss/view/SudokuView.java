package de.hs_bochum.ss.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.interfaces.Algorithm;
import de.hs_bochum.ss.model.GridModel;

public class SudokuView extends JFrame {

    private GridModel model;
    private SudokuSolverControl control;

    private static final long serialVersionUID = 1L;

    private JComboBox<Algorithm> cbAlgorithm;
    private JButton btnSingle;
    private JButton btnStart;
    private JButton btnPause;
    private JButton btnStop;
    private JButton btnLock;
    private JButton btnUnlock;
    private JButton btnReset;
    private JButton btnResetAll;
    private JButton btnContinue;
    private JPanel borderEast;
    private JSlider sliderEast;
    private JTextField waitTime;
    private SudokuGridView guiField;
    private JPanel southGrid;
    private SudokuMenu menuBar;
    private boolean manual;

    public SudokuView(GridModel model) {
        this.model = model;
        setGUIParams();
        initGUIObjects();
        addGUIObjects();
        addActionListener();
        this.menuBar = new SudokuMenu(this);
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }
    
    public void showMessage(String titel, String msg){
    	JOptionPane.showMessageDialog(this, msg, titel, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(Exception e) {
    	JOptionPane.showMessageDialog(this, e.getMessage(), e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }

    private void addActionListener() {
        this.btnSingle.addActionListener(al -> {
            control.nextStepAlgo();
            btnStart.setEnabled(true);
        });
        this.btnStart.addActionListener(al -> {
            control.startAlgo();
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
        });
        this.btnPause.addActionListener(al -> {
            control.pauseAlgo();
            btnPause.setEnabled(false);
            btnContinue.setEnabled(true);
        });
        this.btnContinue.addActionListener(al -> {
            control.continueAlgo();
            btnPause.setEnabled(true);
            btnContinue.setEnabled(false);
        });
        this.btnSingle.addActionListener(al -> {
            control.nextStepAlgo();
            btnStart.setEnabled(true);
        });
        this.btnStop.addActionListener(al -> {
            control.stopAlgo();
            btnStart.setEnabled(true);
        });
        this.cbAlgorithm.addActionListener(al -> {
            control.setAlgo((Algorithm) cbAlgorithm.getSelectedItem());
        });
        this.sliderEast.addChangeListener(cl -> {
            control.setWaittime(sliderEast.getValue());
            StringBuilder sb = new StringBuilder(4);
            sb.append(sliderEast.getValue());
            waitTime.setText(sb.toString());
        });
        this.btnReset.addActionListener(al -> {
            control.resetNonLockedSudoku();
            guiField.resetLastChanged();
            btnStart.setEnabled(true);
        });
        this.btnResetAll.addActionListener(al -> {
            control.resetSudoku();
            guiField.resetLastChanged();
            btnStart.setEnabled(true);
        });
        this.btnLock.addActionListener(al -> {
            control.lockCells(true);
        });
        this.btnUnlock.addActionListener(al -> {
            control.lockCells(false);
        });
    }

    private void addGUIObjects() {
        this.southGrid.add(btnStart);
        this.southGrid.add(btnPause);
        this.southGrid.add(btnSingle);
        this.southGrid.add(btnReset);
        this.southGrid.add(btnResetAll);
        this.southGrid.add(btnStop);
        this.southGrid.add(btnContinue);
        this.southGrid.add(btnLock);
        this.southGrid.add(btnUnlock);
        this.southGrid.add(cbAlgorithm);
        this.borderEast.add(sliderEast, BorderLayout.CENTER);
        this.borderEast.add(waitTime, BorderLayout.SOUTH);
        this.add(new JScrollPane(guiField), BorderLayout.CENTER);
        this.add(southGrid, BorderLayout.SOUTH);
        this.add(borderEast, BorderLayout.EAST);
    }

    private void initGUIObjects() {
        this.southGrid = new JPanel(new GridLayout(2, 5, 5, 5));
        this.guiField = new SudokuGridView(model, this);
        this.btnStart = new JButton("Start");
        this.btnPause = new JButton("Pause");
        this.btnSingle = new JButton("Single Step");
        this.btnReset = new JButton("Reset");
        this.btnResetAll = new JButton("ResetAll");
        this.btnStop = new JButton("Stop");
        this.btnContinue = new JButton("Continue");
        this.btnLock = new JButton("Lock");
        this.btnUnlock = new JButton("Unlock");
        this.sliderEast = new JSlider(JSlider.VERTICAL, 0, 1000, 10);
        this.borderEast = new JPanel(new BorderLayout());
        this.waitTime = new JTextField(10 + "    ");
        this.cbAlgorithm = new JComboBox<>(Algorithm.values());
        
        btnStop.setEnabled(false);
        btnContinue.setEnabled(false);
    }

    private void setGUIParams() {
        this.setVisible(true);
        this.setTitle("SodokuSolver");
        this.setSize(750, 750);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
    }

    public void setControl(SudokuSolverControl control) {
        this.control = control;
        control.setAlgo((Algorithm) cbAlgorithm.getSelectedItem());
    }

    public void setValueInModel(int x, int y, int value) {
        control.setCellValue(x, y, value);
    }

    public void setManual(boolean b) {
        this.manual = b;
    }

    public boolean getManual() {
        return this.manual;
    }

    public boolean isCellValid(int x, int y) {
        return control.isCellValid(x, y);
    }

    public void setSelectedFile(File selectedFile) {
        control.setSelectedFile(selectedFile);
    }

    public boolean isCellLocked(int x, int y) {
        return control.isCellLocked(x, y);
    }

    public void saveFile(File f) {
        control.saveFile(f);
    }

    public void resetCellValue(int x, int y) {
        control.resetCellValue(x, y);
    }

	public void openContextMenu(JTextField field, int x, int y) {
		JPopupMenu menu = new JPopupMenu();
		JMenuItem resetCell = new JMenuItem("Reset Cell");
		resetCell.addActionListener(al -> {
			control.resetCellValue(x, y);
		});
		menu.add(resetCell);
		JMenuItem lockCell = new JMenuItem((control.isCellLocked(x, y))?"Unlock Cell":"Lock Cell");
		lockCell.addActionListener(al -> {
			control.switchLockByCell(x, y);
		});
		menu.add(lockCell);
		menu.show(field, x, y);
	}

    public void disableStopButton() {
        btnStop.setEnabled(false);
        
    }

    public void enableStartButton() {
        btnStart.setEnabled(true);
    }
}
