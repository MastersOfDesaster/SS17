package de.hs_bochum.ss.view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {

	private static final long serialVersionUID = 1L;
	private int limit;

	JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit) {
			try {
				if(Integer.parseInt(str) == 0){
					return;
				}
				super.insertString(offset, str, attr);
			} catch (Exception e) {
				return;
			}
		}
	}
}