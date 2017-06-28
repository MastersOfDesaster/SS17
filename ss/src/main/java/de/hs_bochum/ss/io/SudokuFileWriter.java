package de.hs_bochum.ss.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.exception.InvalidFormatException;
import de.hs_bochum.ss.exception.IsLockedException;
import de.hs_bochum.ss.exception.IsOutOfRangeException;
import de.hs_bochum.ss.model.GridCell;

public class SudokuFileWriter {

    public static void saveSudoku(File file, GridCell[][] grid) throws IOException, InvalidFormatException,
            NumberFormatException, IsLockedException, IsOutOfRangeException, CoordinateOutOfBoundsException {
        FileWriter writer = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(writer);

        for (int x = 0; x < grid.length; x++) {
            String line = "";
            for (int y = 0; y < grid[x].length; y++) {
                line += ((grid[y][x].isLocked()) ? grid[y][x].getValue() : 0);
            }
            bw.write(line + "\n");
        }
        bw.close();
        writer.close();
    }
}
