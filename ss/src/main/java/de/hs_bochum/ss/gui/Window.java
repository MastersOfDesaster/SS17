package de.hs_bochum.ss.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.hs_bochum.ss.algorithms.ThousandMonkeyAlgorithm;
import de.hs_bochum.ss.interfaces.ISodokuSolver;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> cbAlgorithm;
	private JButton btnSingle;
	private JButton btnRun;
	private SodokuField guiField;
	private JPanel southGrid;
	
	private ISodokuSolver solver;
	
	public Window() {
		this.solver = new ThousandMonkeyAlgorithm();
		setGUIParams();
		initGUIObjects();
		addGUIObjects();
		addActionListener();
		this.repaint();
		
	}

	private void addActionListener() {
		this.btnSingle.addActionListener(al -> {
			solver.nextStep();
		});
		this.btnRun.addActionListener(al -> {
			solver.solve();
		});
		this.cbAlgorithm.addActionListener(al -> {
			switch (cbAlgorithm.getSelectedItem().toString()) {
			case "":
				
				break;
			default:
				break;
			}
		});
	}

	private void addGUIObjects() {
		this.southGrid.add(btnRun);
		this.southGrid.add(btnSingle);
		this.southGrid.add(cbAlgorithm);
		this.add(guiField, BorderLayout.CENTER);
		this.add(southGrid, BorderLayout.NORTH);
	}

	private void initGUIObjects() {
		this.southGrid = new JPanel(new GridLayout(1, 3, 25, 25));
		this.guiField = new SodokuField();
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
