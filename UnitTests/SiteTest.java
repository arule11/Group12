package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Console.Site;

public class SiteTest {

	@Test
	public void testSite() {
		Site s = new Site();
		assertEquals("Initial status should be set to 0", 0, s.getStatus());
	}

/*	@Test
	public void testSiteMark() {
		Site s = new Site();
		s.setStatus(1);
	}
*/

	@Test
	public void testGetStatus() {
		Site s = new Site();
		s.setStatus(0);
		assertEquals("Status should be 0", 0, s.getStatus());
		s.setStatus(4);
		assertEquals("Status should be 0 when status is set with a number that's higher than 3", 0, s.getStatus());
		s.setStatus(-1);
		assertEquals("Status should be 0 when status is set with a number lower than 0", 0, s.getStatus());
	}

	@Test
	public void testSetStatus() {
		Site s = new Site();
		s.setStatus(0);
		assertEquals("Status should be 0", 0, s.getStatus());
		s.setStatus(4);
		assertEquals("Status should be 0 when status is set with a number higher than 3", 0, s.getStatus());
		s.setStatus(-1);
		assertEquals("Status should be 0 when status is set with a number lower than 0", 0, s.getStatus());
	}

}
