import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NameSurferEntryTest {
	@Test
	public void testSimple() {
		String line = "A 83 140 228 286 426 612 486 577 836 0 0";
		NameSurferEntry entry = new NameSurferEntry(line);
		assertEquals(entry.getName(), "A");
		assertEquals(entry.getRank(0), 83);
		assertEquals(entry.getRank(1), 140);
		assertEquals(entry.getRank(2), 228);
		assertEquals(entry.getRank(3), 286);
		assertEquals(entry.getRank(4), 426);
		assertEquals(entry.getRank(5), 612);
		assertEquals(entry.getRank(6), 486);
		assertEquals(entry.getRank(7), 577);
		assertEquals(entry.getRank(8), 836);
		assertEquals(entry.getRank(9), 0);
		assertEquals(entry.getRank(10), 0);
	}
	
	@Test
	public void testAllZero() {
		String line = "A 0 0 0 0 0 0 0 0 0 0 0";
		NameSurferEntry entry = new NameSurferEntry(line);
		assertEquals(0, entry.getRank(0));
		assertEquals(0, entry.getRank(1));
		assertEquals(0, entry.getRank(2));
		assertEquals(0, entry.getRank(3));
		assertEquals(0, entry.getRank(4));
		assertEquals(0, entry.getRank(5));
		assertEquals(0, entry.getRank(6));
		assertEquals(0, entry.getRank(7));
		assertEquals(0, entry.getRank(8));
		assertEquals(0, entry.getRank(9));
		assertEquals(0, entry.getRank(10));
	}
	
}
