
import static org.junit.Assert.*;
import org.junit.Test;

public class GameTest {

	@Test
	public void testSetupComp(){
			Game g = new Game();
			Player p = new Player();
			g.setupComp(p);

			assertTrue(p.getPlayerBoard() != null);
	}

	@Test
	public void testChooseRow() {
		// IntegerAsker asker = mock(IntegerAsker.class);
    // when(asker.ask(anyString())).thenReturn(3);
    // assertEquals(chooseRow(asker), 3);
		Game g = new Game();
		int max = 10;
		int min = 0;
		int actual = g.chooseRow();
		int i;
		assertTrue("Row must be between 1 and 10",actual <= max && actual >= min);
	}

	@Test
	public void testChooseColumn() {
		Game g = new Game();
		int max = 10;
		int min = 0;
		int actual = g.chooseColumn();
		int i;
		assertTrue("column must be between 1 and 10",actual <= max && actual >= min);
	}

	@Test
	public void testChooseDirection() {
		Game g = new Game();
		int actual = g.chooseDirection();
		assertTrue("Direction must be either H or V" ,actual == 'H' || actual == 'V');
	}

}
