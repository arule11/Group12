package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Console.Board;
import GUI.AIPlayer;
import GUI.GUI;

public class AIPlayerTest {
//	public class MockGUI extends GUI{}
		
	
	@Test
	public void testSetup_initVariables() {
		AIPlayer ai = new AIPlayer();
		assertEquals("Good guess row should inititalize to -1", -1, ai.getGoodRow());
		assertEquals("Good guess column should inititalize to -1", -1, ai.getGoodCol());
		assertEquals("Misses should inititalize to 0", 0, ai.getMisses());

	}
	
	@Test
	public void testSetup_placedShips() {
		AIPlayer ai = new AIPlayer();
		Board board = new Board();
		ai.setup(board);
		int shipCount = 0;
		for(int row = 0 ; row < 10 ; row++ ){
			 for(int column = 0 ; column < 10 ; column++ ){
				 if (board.getBoard()[row][column].getStatus() == 1) {
					 shipCount++;
				 }
			 }
		}
		assertEquals("Either more ships than was expected or all ships were not set.", 14, shipCount);

	}

	@Test
	public void testAddMiss() {
		AIPlayer ai = new AIPlayer();
		ai.addMiss();
		ai.addMiss();
		assertEquals("Misses should be 2", 2, ai.getMisses());
	}
	
/*
	@Test
	public void testGuess() {
		fail("Not yet implemented");
	}

	@Test
	public void testSmartGuess() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakeRightGuess() {
	//	MockGUI gui = new MockGUI();
		Board board = new Board();
		AIPlayer ai = new AIPlayer();
		board.getBoard()[3][3].setStatus(1);
		
		ai.makeRightGuess(gui, board);
		
		assertEquals("Should have marked a hit", 2, board.getBoard()[3][3].getStatus());
	}
*/
}
