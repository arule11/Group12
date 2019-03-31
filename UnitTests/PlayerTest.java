package UnitTests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import Console.Board;
import Console.Player;
import Console.Site;
import junit.framework.Assert;

public class PlayerTest {

	@Test
	public void testPlayer() {
		Player p = new Player();

		assertEquals("Expected points to be 0", 0,p.getPoints(), 0.00001);
		assertEquals("Expected num_Ships to be 4", 4, p.getNumShips(), 0.00001);
		assertEquals("Expected shipLength to be 5", 1, p.getShipLength(), 0.00001);
	}

	@Test
	public void testSetupShip() {
		Player p = new Player();
		p.setupShip();
		p.setupShip();
		p.setupShip();
		p.setupShip();
		assertEquals("Expected ships to be 0", 0, p.getNumShips(), 0.00001);
		p.setupShip();
		assertEquals("setupShip shouldn't run when numShips is 0", 0, p.getNumShips(), 0.00001);
	}

	@Test
	public void testGetPoints() {
		Player p = new Player();
		p.addPoint();
		p.addPoint();
		int points = p.getPoints();
		assertEquals("Expected points to be 2", 2, points, 0.00001);
	}

	@Test
	public void testAddPoint() {
		Player p = new Player();
		p.addPoint();
		p.addPoint();
		assertEquals("Expected points to be 2", 2, p.getPoints(), 0.00001);
	}

	@Test
	public void testGetNum_ships() {
		Player p = new Player();
		assertEquals("Expected num_Ships to be 4", 4, p.getNumShips(), 0.00001);
	}
/*
	@Test
	public void testGetPlayerBoard() {	
		fail("Not yet implemented");
	}


	@Test
	public void testGetPlayerShips() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOppBoard() {
		fail("Not yet implemented");
	}
*/
}
