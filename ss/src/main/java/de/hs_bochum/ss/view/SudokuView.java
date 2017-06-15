package de.hs_bochum.ss.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;

import de.hs_bochum.ss.control.SudokuSolverControl;
import de.hs_bochum.ss.interfaces.Algorithm;
import de.hs_bochum.ss.modelNew.GridModel;

public class SudokuView extends JFrame{
	
	private GridModel model;
	private SudokuSolverControl control;
	
	private static final long serialVersionUID = 1L;

	private JComboBox<Algorithm> cbAlgorithm;
	private JButton btnSingle;
	private JButton btnStart;
	private JButton btnPause;
	private JButton btnStop;
	private JPanel borderEast;
	private JSlider sliderEast;
	private JTextField waitTime;
	private SudokuGridView guiField;
	private JPanel southGrid;
	private boolean manual;

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
			btnStart.setEnabled(true);
		});
		this.btnStart.addActionListener(al -> {
			control.startAlgo();
			btnStart.setEnabled(false);
		});
		this.btnPause.addActionListener(al -> {
			if (btnPause.getText().equals("Pause")){ 
				control.pauseAlgo();
				btnPause.setText("Continue");
				btnStart.setEnabled(true);
			}
			else if (btnPause.getText().equals("Continue")){ 
				control.continueAlgo(); 
				btnPause.setText("Pause");
				btnStart.setEnabled(false);
			}
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
			control.setAlgo((Algorithm)cbAlgorithm.getSelectedItem());
		});
		this.sliderEast.addChangeListener(cl -> {
			control.setWaittime(sliderEast.getValue());
			waitTime.setText(sliderEast.getValue() + "");
		});
	}

	private void addGUIObjects() {
		this.southGrid.add(btnStart);
		this.southGrid.add(btnPause);
		this.southGrid.add(btnStop);
		this.southGrid.add(btnSingle);
		this.southGrid.add(cbAlgorithm);
		this.borderEast.add(sliderEast, BorderLayout.CENTER);
		this.borderEast.add(waitTime, BorderLayout.EAST);
		this.add(new JScrollPane(guiField), BorderLayout.CENTER);
		this.add(southGrid, BorderLayout.SOUTH);
		this.add(borderEast, BorderLayout.EAST);
	}

	private void initGUIObjects() {
		int waitMillis = (int) control.getWaitMillis(); 
		this.southGrid = new JPanel(new GridLayout(1, 5, 25, 25));
		this.guiField = new SudokuGridView(model, this);
		this.btnStart = new JButton("Start");
		this.btnPause = new JButton("Pause");
		this.btnStop = new JButton("Stop");
		this.btnSingle = new JButton("Single Step");
		this.sliderEast = new JSlider(JSlider.VERTICAL, 0, 5000, waitMillis);
		this.borderEast = new JPanel(new BorderLayout());
		this.waitTime = new JTextField(waitMillis + "");
		this.cbAlgorithm = new JComboBox<>(Algorithm.values());
	}

	private void setGUIParams() {
		this.setVisible(true);
		this.setTitle("SodokuSolver");
		this.setSize(750, 750);
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
	
	public void setManual(boolean b){
		this.manual = b;
	}
	
	public boolean getManual(){
		return this.manual;
	}
	
	public boolean isCellValid(int x, int y){
		return control.isCellValid(x, y);
	}
}
