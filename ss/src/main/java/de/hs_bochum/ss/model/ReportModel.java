package de.hs_bochum.ss.model;

public class ReportModel {
    private int setValueCount[] = new int[2]; // Wie oft wurde ein Wert
                                              // geschrieben?
    private int resetValueCount; // Wie oft wurde ein Feld geleert?

    private int singleValueValidCount[] = new int[2]; // Wie oft wurde geprüft
                                                      // ob ein einzelnes
    // Feld valide ist?
    private int allValuesValidCount[] = new int[2]; // Wie oft wurde geprüft ob
                                                    // das ganze Feld
    // valide ist?

    private int isSolvedCount[] = new int[2]; // Wurde das Sudoku gelöst?

    private boolean solved; // Wurde das Sudoku gelöst?

    @Override
    public String toString() {
        return "\nsetValue(x, y) valid: " + setValueCount[0] + " invalid: " + setValueCount[1]
                + "\nresetValue(x,y): " + resetValueCount
                + "\n\nisValid() true: " + allValuesValidCount[0] + ", false: " + allValuesValidCount[1]
                + "\nisValid(x, y) true: " + singleValueValidCount[0] + ", false: " + singleValueValidCount[1]
                + "\nisSolved() true: " + isSolvedCount[0] + ", false: " + isSolvedCount[1] 
                + "\n\nSolved: " + solved;
    }

    public void clear() {
        resetValueCount = 0;
        solved = false;

        for (int i = 0; i < 2; i++) {
            setValueCount[i] = 0;
            singleValueValidCount[i] = 0;
            allValuesValidCount[i] = 0;
            isSolvedCount[i] = 0;
        }
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public void increaseSingleValueValidCount(boolean valid) {
        if (valid) {
            singleValueValidCount[0]++;
        } else {
            singleValueValidCount[1]++;
        }
    }

    public void increaseAllValuesValidCount(boolean valid) {
        if (valid) {
            allValuesValidCount[0]++;
        } else {
            allValuesValidCount[1]++;
        }
    }

    public void increaseSudokuSolvedCount(boolean valid) {
        if (valid) {
            isSolvedCount[0]++;
        } else {
            isSolvedCount[1]++;
        }
    }

    public void increaseSetValueCount(boolean valid) {
        if (valid) {
            setValueCount[0]++;
        } else {
            setValueCount[1]++;
        }
    }

    public void increaseResetValueCount() {
        resetValueCount++;
    }
}
