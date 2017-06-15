package de.hs_bochum.ss.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.hs_bochum.ss.algorithms.BacktrackAlgorithm;
import de.hs_bochum.ss.algorithms.CrookAlgorithm;
import de.hs_bochum.ss.interfaces.ISudokuSolver;

public class SudokuView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> cbAlgorithm;
	private JButton btnSingle;
	private JButton btnRun;
	private SudokuGridView guiField;
	private JPanel southGrid;
	
	private ISudokuSolver solver;
	
	private ExecutorService executor;
	
	public SudokuView() {
		this.solver = new CrookAlgorithm();
		setGUIParams();
		initGUIObjects();
		addGUIObjects();
		addActionListener();
		//this.repaint();
		this.setVisible(true);
		executor = Executors.newCachedThreadPool();
	}

	private void addActionListener() {
		this.btnSingle.addActionListener(al -> {
		//	solver.nextStep();
		});
		this.btnRun.addActionListener(al -> {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						solver.solve(guiField.getField());
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		});
		this.cbAlgorithm.addActionListener(al -> {
			//TODO Algorithmus über Enum wechseln
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
		this.guiField = new SudokuGridView();
		this.btnRun = new JButton("Run");
		this.btnSingle = new JButton("Single Step");
		this.cbAlgorithm = new JComboBox<String>(new String[]{"Thousand Monkeys", "Brute Force", "Dancing Links", "Pencil and Paper"});
	}

	private void setGUIParams() {
		this.setVisible(true);
		this.setTitle("SodokuSolver");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
	}
}
