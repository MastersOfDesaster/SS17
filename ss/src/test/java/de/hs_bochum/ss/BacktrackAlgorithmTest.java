package de.hs_bochum.ss;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hs_bochum.ss.algorithms.BacktrackAlgorithm;
import de.hs_bochum.ss.exception.CoordinateOutOfBoundsException;
import de.hs_bochum.ss.model.Field;

public class BacktrackAlgorithmTest {

	Field sudoku = new Field();
	
	
	@Before
	public void setUp() throws Exception {

		
		sudoku.setFieldValue((byte) 2, 1, 0);
		sudoku.setFieldValue((byte) 4, 2, 0);
		sudoku.setFieldValue((byte) 1, 5, 0);
		sudoku.setFieldValue((byte) 5, 7, 0);
		sudoku.setFieldValue((byte) 7, 8, 0);
		
		sudoku.setFieldValue((byte) 7, 4, 1);
		sudoku.setFieldValue((byte) 4, 5, 1);
		
		sudoku.setFieldValue((byte) 8, 0, 2);
		sudoku.setFieldValue((byte) 2, 5, 2);
		sudoku.setFieldValue((byte) 9, 8, 2);
		
		sudoku.setFieldValue((byte) 1, 6, 3);
		sudoku.setFieldValue((byte) 5, 8, 3);
		
		sudoku.setFieldValue((byte) 4, 1, 4);
		sudoku.setFieldValue((byte) 6, 7, 4);
		
		sudoku.setFieldValue((byte) 7, 0, 5);
		sudoku.setFieldValue((byte) 5, 2, 5);
		
		sudoku.setFieldValue((byte) 6, 0, 6);
		sudoku.setFieldValue((byte) 1, 3, 6);
		sudoku.setFieldValue((byte) 2, 8, 6);
		
		sudoku.setFieldValue((byte) 9, 3, 7);
		sudoku.setFieldValue((byte) 5, 4, 7);
		
		sudoku.setFieldValue((byte) 9, 0, 8);
		sudoku.setFieldValue((byte) 1, 1, 8);
		sudoku.setFieldValue((byte) 7, 3, 8);
		sudoku.setFieldValue((byte) 5, 6, 8);
		sudoku.setFieldValue((byte) 3, 7, 8);
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSolveFieldIntInt(){
		try {
			BacktrackAlgorithm algo = new BacktrackAlgorithm();
			algo.solve(sudoku);
			System.out.println("Steps: " + algo.getSteps());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void print() throws CoordinateOutOfBoundsException{
		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 9; x++){
				System.out.print(sudoku.getFieldValue(x, y) + " ");
			}
			System.out.println();
		}
	}

}
