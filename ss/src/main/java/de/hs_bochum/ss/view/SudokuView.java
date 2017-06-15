package de.hs_bochum.ss.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.interfaces.Algorithm;
import de.hs_bochum.ss.modelNew.GridModel;

public class SudokuView extends JFrame{
	
	private GridModel model;
	private SudokuSolverControl control;
	
	private static final long serialVersionUID = 1L;

	private JComboBox<Algorithm> cbAlgorithm;
	private JButton btnSingle;
	private JButton btnRun;
	private SudokuGridView guiField;
	private JPanel southGrid;


	public SudokuView(GridModel model) {
		this.model = model;
		setGUIParams();
		initGUIObjects();
		addGUIObjects();
		addActionListener();
		this.setVisible(true);
	}
	
	
	public void showError(Exception e){
		JOptionPane.showMessageDialog(this, e.getMessage());
		e.printStackTrace();
	}
	
	private void addActionListener() {
		this.btnSingle.addActionListener(al -> {
			control.nextStepAlgo();
		});
		this.btnRun.addActionListener(al -> {
			control.startAlgo();
		});
		this.cbAlgorithm.addActionListener(al -> {
			control.setAlgo((Algorithm)cbAlgorithm.getSelectedItem());
		});
	}

	private void addGUIObjects() {
		this.southGrid.add(btnRun);
		this.southGrid.add(btnSingle);
		this.southGrid.add(cbAlgorithm);
		this.add(new JScrollPane(guiField), BorderLayout.CENTER);
		this.add(southGrid, BorderLayout.SOUTH);
	}

	private void initGUIObjects() {
		this.southGrid = new JPanel(new GridLayout(1, 3, 25, 25));
		this.guiField = new SudokuGridView(model, this);
		this.btnRun = new JButton("Run");
		this.btnSingle = new JButton("Single Step");
		this.cbAlgorithm = new JComboBox<>(Algorithm.values());
	}

	private void setGUIParams() {
		this.setVisible(true);
		this.setTitle("SodokuSolver");
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
	}
	
	public void setControl(SudokuSolverControl control){
		this.control = control;
		control.setAlgo((Algorithm)cbAlgorithm.getSelectedItem());
	}
	
	public void setValueInModel(int x, int y, int value){
		control.setCellValue(x, y, value);
	}
}
