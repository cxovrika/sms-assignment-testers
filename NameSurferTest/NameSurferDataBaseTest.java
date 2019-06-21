import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NameSurferDataBaseTest {

	@Test
	public void testFindEntry() {
		NameSurferDataBase db = new NameSurferDataBase("names-data-small.txt");
		assertEquals(true, (db.findEntry("A")).getName().equals("A"));
		assertEquals(true, (db.findEntry("Aylin")).getName().equals("Aylin"));
		assertEquals(true, db.findEntry("Baby").getName().equals("Baby"));
		assertEquals(true, db.findEntry("Coby").getName().equals("Coby"));
		assertEquals(true, db.findEntry("Cyrus").getName().equals("Cyrus"));
		assertEquals(true, db.findEntry("Ezra").getName().equals("Ezra"));
		assertEquals(true, db.findEntry("Fabian").getName().equals("Fabian"));
		assertEquals(true, db.findEntry("Dagmar").getName().equals("Dagmar"));
		assertEquals(true, db.findEntry("Darrin").getName().equals("Darrin"));
		assertEquals(true, db.findEntry("Tyreek").getName().equals("Tyreek"));
		assertEquals(true, db.findEntry("Zulma").getName().equals("Zulma"));
	}	
	
	@Test
	public void testFindEntryNonExisting() {
		NameSurferDataBase db = new NameSurferDataBase("names-data-small.txt");
		assertEquals(null, db.findEntry("Bla"));
		assertEquals(null, db.findEntry("Elene"));
		assertEquals(null, db.findEntry("Giorgi"));
		assertEquals(null, db.findEntry("Aleksandre"));
	}	
	
	@Test
	public void testFindEntrySingleData() {
		NameSurferDataBase db = new NameSurferDataBase("names-data-small.txt");
		NameSurferEntry entry = db.findEntry("A");
		assertEquals(true, entry.getName().equals("A"));
		assertEquals(83, entry.getRank(0));
		assertEquals(140, entry.getRank(1));
		assertEquals(228, entry.getRank(2));
		assertEquals(286, entry.getRank(3));
		assertEquals(426, entry.getRank(4));
		assertEquals(612, entry.getRank(5));
		assertEquals(486, entry.getRank(6));
		assertEquals(577, entry.getRank(7));
		assertEquals(836, entry.getRank(8));
		assertEquals(0, entry.getRank(9));
		assertEquals(0, entry.getRank(10));
	}	
	
	@Test
	public void testFindEntryMultipleData() {
		NameSurferDataBase db = new NameSurferDataBase("names-data-small.txt");
		
		NameSurferEntry entry = db.findEntry("A");
		assertEquals(true, entry.getName().equals("A"));
		assertEquals(83, entry.getRank(0));
		assertEquals(140, entry.getRank(1));
		assertEquals(228, entry.getRank(2));
		assertEquals(286, entry.getRank(3));
		assertEquals(426, entry.getRank(4));
		assertEquals(612, entry.getRank(5));
		assertEquals(486, entry.getRank(6));
		assertEquals(577, entry.getRank(7));
		assertEquals(836, entry.getRank(8));
		assertEquals(0, entry.getRank(9));
		assertEquals(0, entry.getRank(10));
		
		entry = db.findEntry("Aylin");
		assertEquals(true, entry.getName().equals("Aylin"));
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
		assertEquals(728, entry.getRank(10));
	}	
	
}
