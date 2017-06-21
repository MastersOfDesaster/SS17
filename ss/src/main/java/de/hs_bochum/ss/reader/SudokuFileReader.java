package de.hs_bochum.ss.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.InvalidFormatException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;
import de.hs_bochum.ss.modelNew.GridCell;
import de.hs_bochum.ss.modelNew.GridModel;

public class SudokuFileReader {

	private File file;

	public GridCell[][] readSudoku(File file) throws IOException, InvalidFormatException, NumberFormatException,
			IsLockedException, IsOutOfRangeException, CoordinateOutOfBoundsException {
		GridCell[][] grid = new GridCell[9][9];

		FileReader reader = new FileReader(file);
		BufferedReader b = new BufferedReader(reader);

		String line;

		for (int y = 0; y < 9; y++) {
			line = b.readLine();
			if (line == null || line.length() != 9) {
				throw new InvalidFormatException("Das Sudoku konnte nicht gelesen werden");
			}

			for (int x = 0; x < 9; x++) {
				int value = Integer.parseInt(line.substring(x, x + 1));
				grid[x][y] = new GridCell(value);
			}
		}
		return grid;
	}
}
