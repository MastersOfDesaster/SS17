package de.hs_bochum.ss.view;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class SudokuMenu extends JMenuBar{

	private JMenu menu;
	
	private JMenuItem read;
	
	SudokuView mainView;
	
	public SudokuMenu(SudokuView mainView) {
		this.mainView = mainView;
		this.read = new JMenuItem("read");
		this.read.addActionListener(al -> {
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(mainView);
			mainView.setSelectedFile(chooser.getSelectedFile());
		});
		this.menu = new JMenu("main");
		menu.add(read);
		this.add(menu);
		this.setVisible(true);
	}
}
