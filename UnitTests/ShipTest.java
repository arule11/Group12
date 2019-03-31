package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Console.Ship;

public class ShipTest {

	@Test
	public void testShip() {
		Ship s = new Ship();
		
		assertEquals("Expected 0 for initial shipLength", 0, s.getShipLength());
		assertEquals("Expected 0 for initial row", 0, s.getRow());
		assertEquals("Expected 0 for initial column", 0, s.getColumn());
		assertEquals("Expected null for initial direction", '\0', s.getDirection());
	}

	@Test
	public void testShipInt() {
		Ship s = new Ship(20);

		assertEquals("Expected null for initial direction", '\0', s.getDirection());
	}

	@Test
	public void testInBounds() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetColumn() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetColumn() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRow() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetRow() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDirection() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDirection() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetShipLength() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetShipLength() {
		fail("Not yet implemented");
	}

}
