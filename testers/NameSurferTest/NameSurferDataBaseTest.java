import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NameSurferDataBaseTest {

	@Test
	public void testFindEntry() {
		NameSurferDataBase db = new NameSurferDataBase("names-data-small.txt");
		assertTrue("Entry was not found in database", db.findEntry("A").getName().equals("A"));
		assertTrue("Entry was not found in database", db.findEntry("Aylin").getName().equals("Aylin"));
		assertTrue("Entry was not found in database", db.findEntry("Baby").getName().equals("Baby"));
		assertTrue("Entry was not found in database", db.findEntry("Coby").getName().equals("Coby"));
		assertTrue("Entry was not found in database", db.findEntry("Cyrus").getName().equals("Cyrus"));
		assertTrue("Entry was not found in database", db.findEntry("Ezra").getName().equals("Ezra"));
		assertTrue("Entry was not found in database", db.findEntry("Fabian").getName().equals("Fabian"));
		assertTrue("Entry was not found in database", db.findEntry("Dagmar").getName().equals("Dagmar"));
		assertTrue("Entry was not found in database", db.findEntry("Darrin").getName().equals("Darrin"));
		assertTrue("Entry was not found in database", db.findEntry("Tyreek").getName().equals("Tyreek"));
		assertTrue("Entry was not found in database", db.findEntry("Zulma").getName().equals("Zulma"));
	}	
	
	@Test
	public void testFindEntryNonExisting() {
		NameSurferDataBase db = new NameSurferDataBase("names-data-small.txt");
		assertTrue("Extra data was found in database", null == db.findEntry("Bla"));
		assertTrue("Extra data was found in database", null == db.findEntry("Elene"));
		assertTrue("Extra data was found in database", null == db.findEntry("Giorgi"));
		assertTrue("Extra data was found in database", null == db.findEntry("Aleksandre"));
	}	
	
	@Test
	public void testFindEntrySingleData() {
		NameSurferDataBase db = new NameSurferDataBase("names-data-small.txt");
		NameSurferEntry entry = db.findEntry("A");
		assertTrue("Name for entry was not correct", true == entry.getName().equals("A"));
		assertTrue("Rank for entry was not correct", 83 == entry.getRank(0));
		assertTrue("Rank for entry was not correct", 140 == entry.getRank(1));
		assertTrue("Rank for entry was not correct", 228 == entry.getRank(2));
		assertTrue("Rank for entry was not correct", 286 == entry.getRank(3));
		assertTrue("Rank for entry was not correct", 426 == entry.getRank(4));
		assertTrue("Rank for entry was not correct", 612 == entry.getRank(5));
		assertTrue("Rank for entry was not correct", 486 == entry.getRank(6));
		assertTrue("Rank for entry was not correct", 577 == entry.getRank(7));
		assertTrue("Rank for entry was not correct", 836 == entry.getRank(8));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(9));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(10));
	}	
	
	@Test
	public void testFindEntryMultipleData() {
		NameSurferDataBase db = new NameSurferDataBase("names-data-small.txt");
		
		NameSurferEntry entry = db.findEntry("A");
		assertTrue("Name for entry was not correct", true == entry.getName().equals("A"));
		assertTrue("Rank for entry was not correct", 83 == entry.getRank(0));
		assertTrue("Rank for entry was not correct", 140 == entry.getRank(1));
		assertTrue("Rank for entry was not correct", 228 == entry.getRank(2));
		assertTrue("Rank for entry was not correct", 286 == entry.getRank(3));
		assertTrue("Rank for entry was not correct", 426 == entry.getRank(4));
		assertTrue("Rank for entry was not correct", 612 == entry.getRank(5));
		assertTrue("Rank for entry was not correct", 486 == entry.getRank(6));
		assertTrue("Rank for entry was not correct", 577 == entry.getRank(7));
		assertTrue("Rank for entry was not correct", 836 == entry.getRank(8));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(9));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(10));
		
		entry = db.findEntry("Aylin");
		assertTrue("Name for entry was not correct", true == entry.getName().equals("Aylin"));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(0));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(1));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(2));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(3));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(4));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(5));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(6));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(7));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(8));
		assertTrue("Rank for entry was not correct", 0 == entry.getRank(9));
		assertTrue("Rank for entry was not correct", 728 == entry.getRank(10));
	}	
	
}
