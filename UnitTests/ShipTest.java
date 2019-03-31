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
		Ship s = new Ship(6);
		assertEquals("Ship length shouldn't be greater than 5", 5, s.getShipLength());
		s = new Ship(0);
		assertEquals("Ship length should be minimum 0", 5, s.getShipLength());
	}

	@Test
	public void testInBounds() {
		Ship s = new Ship(5);
		s.setColumn(0);
		s.setRow(0);
		s.setDirection('H');
		assertTrue("Ships should be in bounds.", s.inBounds());
		s.setColumn(9);
		s.setRow(9);
		s.setDirection('V');
		assertFalse("Ships should not be in bounds.", s.inBounds());
		
	}

	@Test
	public void testToString() {
		Ship s = new Ship(2);
		s.setColumn(2);
		s.setRow(2);
		s.setDirection('H');
		String shipString = s.getShipLength() + " " + (char)(s.getRow()+65) + " " + s.getColumn() + " " + s.getDirection();
		assertEquals("toString was not correct.", shipString, s.toString());
	}

	@Test
	public void testGetColumn() {
		Ship s = new Ship();
		s.setColumn(0);
		assertEquals("Ship column was set to 0", 0, s.getColumn());
	}

	@Test
	public void testSetColumn() {
		Ship s = new Ship();
		s.setColumn(0);
		assertEquals("Ship column was set to 0", 0, s.getColumn());
	}

	@Test
	public void testGetRow() {
		Ship s = new Ship();
		s.setRow(0);
		assertEquals("Ship row was set to 0", 0, s.getRow());
	}

	@Test
	public void testSetRow() {
		Ship s = new Ship();
		s.setRow(0);
		assertEquals("Ship row was set to 0", 0, s.getRow());
	}

	@Test
	public void testGetDirection() {
		Ship s = new Ship();
		s.setDirection('H');
		assertEquals("Ship direction was set to H", 'H', s.getDirection());
	}

	@Test
	public void testSetDirection() {
		Ship s = new Ship();
		s.setDirection('V');
		assertEquals("Ship direction was set to V", 'V', s.getDirection());
	}

	@Test
	public void testGetShipLength() {
		Ship s = new Ship();
		s.setShipLength(5);
		assertEquals("Ship length was set to 5", 5, s.getShipLength());
	}

	@Test
	public void testSetShipLength() {
		Ship s = new Ship();
		s.setShipLength(4);
		assertEquals("Ship length was set to 4", 4, s.getShipLength());
	}

}
