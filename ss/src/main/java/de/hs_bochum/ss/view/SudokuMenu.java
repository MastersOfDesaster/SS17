package de.hs_bochum.ss.view;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class SudokuMenu extends JMenuBar{

	private static final long serialVersionUID = 4177218095759051941L;

	private JMenu menu;

	private JMenuItem read;
	private JMenuItem save;
	private JMenuItem quit;
	
	JFileChooser chooser;
	
	SudokuView mainView;
	
	public SudokuMenu(SudokuView mainView) {
		this.mainView = mainView;
		chooser = new JFileChooser("./");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.read = new JMenuItem("read");
		this.read.addActionListener(al -> {
			chooser.showOpenDialog(mainView);
			mainView.setSelectedFile(chooser.getSelectedFile());
		});
		this.save = new JMenuItem("save");
		this.save.addActionListener(al -> {
			chooser.showSaveDialog(mainView);
			mainView.saveFile(chooser.getSelectedFile());
		});
		this.quit = new JMenuItem("quit");
		this.quit.addActionListener(al -> {
			System.exit(0);
		});
		this.menu = new JMenu("main");
		menu.add(read);
		menu.add(save);
		menu.add(quit);
		this.add(menu);
		this.setVisible(true);
	}
}
