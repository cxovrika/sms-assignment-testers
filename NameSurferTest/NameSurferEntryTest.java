import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NameSurferEntryTest {
	@Test
	public void testSimple() {
		String line = "A 83 140 228 286 426 612 486 577 836 0 0";
		NameSurferEntry entry = new NameSurferEntry(line);
		assertTrue("Returned name was not correct", entry.getName().equals("A"));
		assertTrue("Rank was not correct", entry.getRank(0) == 83);
		assertTrue("Rank was not correct", entry.getRank(1) == 140);
		assertTrue("Rank was not correct", entry.getRank(2) == 228);
		assertTrue("Rank was not correct", entry.getRank(3) == 286);
		assertTrue("Rank was not correct", entry.getRank(4) == 426);
		assertTrue("Rank was not correct", entry.getRank(5) == 612);
		assertTrue("Rank was not correct", entry.getRank(6) == 486);
		assertTrue("Rank was not correct", entry.getRank(7) == 577);
		assertTrue("Rank was not correct", entry.getRank(8) == 836);
		assertTrue("Rank was not correct", entry.getRank(9) == 0);
		assertTrue("Rank was not correct", entry.getRank(10) == 0);
	}
	
	@Test
	public void testAllZero() {
		String line = "A 0 0 0 0 0 0 0 0 0 0 0";
		NameSurferEntry entry = new NameSurferEntry(line);
		assertTrue("Rank was not correct", 0 == entry.getRank(0));
		assertTrue("Rank was not correct", 0 == entry.getRank(1));
		assertTrue("Rank was not correct", 0 == entry.getRank(2));
		assertTrue("Rank was not correct", 0 == entry.getRank(3));
		assertTrue("Rank was not correct", 0 == entry.getRank(4));
		assertTrue("Rank was not correct", 0 == entry.getRank(5));
		assertTrue("Rank was not correct", 0 == entry.getRank(6));
		assertTrue("Rank was not correct", 0 == entry.getRank(7));
		assertTrue("Rank was not correct", 0 == entry.getRank(8));
		assertTrue("Rank was not correct", 0 == entry.getRank(9));
		assertTrue("Rank was not correct", 0 == entry.getRank(10));
	}
	
}
