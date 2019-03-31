package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Console.Board;
import Console.Ship;

public class BoardTest {

/*	@Test
	public void testBoard() {
		Board b = new Board();
		
		fail("Not yet implemented");
	}

	@Test
	public void testShowBoard() {
		Board b = new Board();

		fail("Not yet implemented");
	}
*/
	@Test
	public void testFreeSpace() {
		Board b = new Board();
		b.getBoard()[2][2].setStatus(1);
		Ship s = new Ship(1);
		s.setRow(2);
		s.setColumn(2);
		s.setDirection('H');
		assertFalse("Spot is already marked. Should not return a free space.", b.freeSpace(s));
		s.setColumn(5);
		assertTrue("Spot is a free space. Returned false but should return true.", b.freeSpace(s));

	}

	@Test
	public void testAddShip_horizontal() {
		Board b = new Board();
		Ship s = new Ship(3);
		s.setRow(2);
		s.setColumn(2);
		s.setDirection('H');
		b.addShip(s);
		assertEquals("1st spot of horizontal ship didn't place", 1,  b.getBoard()[2][2].getStatus());
		assertEquals("2nd spot of horizontal didn't place", 1, b.getBoard()[2][3].getStatus());
		assertEquals("3rd spot of horizontal didn't place", 1, b.getBoard()[2][4].getStatus());
		
	
	}
	
	@Test
	public void testAddShip_vertical() {
		Board b = new Board();
		Ship s = new Ship(3);
		s.setRow(6);
		s.setColumn(6);
		s.setDirection('V');
		b.addShip(s);
		assertEquals("1st spot of vertical ship didn't place", 1,  b.getBoard()[6][6].getStatus());
		assertEquals("2nd spot of vertical didn't place", 1, b.getBoard()[7][6].getStatus());
		assertEquals("3rd spot of vertical didn't place", 1, b.getBoard()[8][6].getStatus());
	}

	@Test
	public void testCheckGuess_hit() {
		Board b = new Board();
		b.getBoard()[2][2].setStatus(1);
		assertEquals("Checked status of row 2, column 2. Should have returned a hit.", 1, b.checkGuess(2, 2));
	}
	
	@Test
	public void testCheckGuess_miss() {
		Board b = new Board();
		b.getBoard()[2][2].setStatus(0);
		assertEquals("Checked status of row 2, column 2. Should have returned a miss.", 0, b.checkGuess(2, 2));
	}
	
	@Test
	public void testCheckGuess_alreadyGuessed() {
		Board b = new Board();
		b.getBoard()[2][2].setStatus(3);
		assertEquals("Checked status of row 2, column 2. Should have returned that the spot was already guessed.", -1, b.checkGuess(2, 2));
		b.getBoard()[2][2].setStatus(2);
		assertEquals("Checked status of row 2, column 2. Should have returned that the spot was already guessed.", -1, b.checkGuess(2, 2));
	}
	
	@Test
	public void testMarkHit() {
		Board b = new Board();
		b.markHit(2, 2);
		assertEquals("When checking site, should have returned that it was marked as hit", 2, b.getBoard()[2][2].getStatus());
	}

	@Test
	public void testMarkMiss() {
		Board b = new Board();
		b.markMiss(2, 2);
		assertEquals("When checking site, should have returned that it was marked as hit", 3, b.getBoard()[2][2].getStatus());
	}

	@Test
	public void testGetSunkShips() {
		Board b = new Board();
		b.getBoard()[2][2].setStatus(1);
		b.checkGuess(2,2);
		assertEquals("Ships sunk should be 1", 1, b.getSunkShips());
		b.getBoard()[3][3].setStatus(1);
		b.checkGuess(3,3);
		assertEquals("Ships sunk should be 2", 2, b.getSunkShips());
	}
/*
	@Test
	public void testGetBoard() {
		Board b = new Board();

		fail("Not yet implemented");
	}
*/
}
