import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

public class FacePamphletDatabaseTest {

	@Test
	public void testSimple() {
		FacePamphletDatabase db = new FacePamphletDatabase();
		assertEquals("Initially database should be empty", false, db.containsProfile("John"));
		FacePamphletProfile profile = new FacePamphletProfile("John");
		db.addProfile(profile);
		assertEquals("After adding new profile, contain should return true", true, db.containsProfile("John"));
		assertEquals("After adding new profile, getProfile should return corresponding profile", "John",
				db.getProfile("John").getName());
		db.deleteProfile("John");
		assertEquals("After deleting a profile, contain should return false", false, db.containsProfile("John"));
	}

	@Test
	public void testNonexistant() {
		FacePamphletDatabase db = new FacePamphletDatabase();
		String initiallyEmptyMsg = "Initially database should be empty";
		assertEquals(initiallyEmptyMsg, false, db.containsProfile("John"));
		FacePamphletProfile profile = new FacePamphletProfile("Maria");
		FacePamphletProfile profile1 = new FacePamphletProfile("Ann");
		FacePamphletProfile profile2 = new FacePamphletProfile("Sam");
		FacePamphletProfile profile3 = new FacePamphletProfile("Bob");
		assertEquals(initiallyEmptyMsg, false, db.containsProfile("John"));
		assertEquals(initiallyEmptyMsg, false, db.containsProfile("Teresa"));
		assertEquals(initiallyEmptyMsg, false, db.containsProfile("Diana"));
		assertEquals(initiallyEmptyMsg, false, db.containsProfile("Jake"));
		assertEquals(initiallyEmptyMsg, false, db.containsProfile("Eric"));
		db.addProfile(profile);
		db.addProfile(profile1);
		db.addProfile(profile2);
		db.addProfile(profile3);
		String newProfileContainMsg = "After adding new profile, contain should return true";
		assertEquals(newProfileContainMsg, true, db.containsProfile("Maria"));
		assertEquals(newProfileContainMsg, true, db.containsProfile("Ann"));
		assertEquals(newProfileContainMsg, true, db.containsProfile("Sam"));
		assertEquals(newProfileContainMsg, true, db.containsProfile("Bob"));
		String unknownProfileContainMsg = "Database should only contain profiles that were added after calling addProfile";
		assertEquals(unknownProfileContainMsg, false, db.containsProfile("John"));
		assertEquals(unknownProfileContainMsg, false, db.containsProfile("Teresa"));
		assertEquals(unknownProfileContainMsg, false, db.containsProfile("Diana"));
		assertEquals(unknownProfileContainMsg, false, db.containsProfile("Jake"));
		assertEquals(unknownProfileContainMsg, false, db.containsProfile("Eric"));
	}

	@Test
	public void testReplace() {
		FacePamphletDatabase db = new FacePamphletDatabase();
		assertEquals("Initially database should be empty", false, db.containsProfile("John"));
		FacePamphletProfile profile = new FacePamphletProfile("Maria");
		profile.setStatus("testing");
		assertEquals("Initially database should be empty", false, db.containsProfile("Maria"));
		db.addProfile(profile);
		assertEquals("After adding new profile, contain should return true", true, db.containsProfile("Maria"));
		assertEquals("After adding new profile, get not working correctly", "testing",
				db.getProfile("Maria").getStatus());
		FacePamphletProfile profileNew = new FacePamphletProfile("Maria");
		profileNew.setStatus("coding");
		db.addProfile(profileNew);
		assertEquals("After adding new profile with already existing name, contains does not work correctly", true,
				db.containsProfile("Maria"));
		assertEquals("After adding new profile with already existing name, old one was not replaced", "coding",
				db.getProfile("Maria").getStatus());
	}

	@Test
	public void testDelete() {
		FacePamphletDatabase db = new FacePamphletDatabase();

		FacePamphletProfile profile1 = new FacePamphletProfile("Maria");
		FacePamphletProfile profile2 = new FacePamphletProfile("Bob");

		profile1.addFriend("Bob");
		profile2.addFriend("Maria");

		db.addProfile(profile1);
		db.addProfile(profile2);

		assertEquals("To test deleting profile, it should first be added", true, db.containsProfile("Maria"));
		assertEquals("To test deleting profile, it should first be added", true, db.containsProfile("Bob"));

		db.deleteProfile("Bob");
		assertEquals("After deleting profile, it should should be deleted from database", false,
				db.containsProfile("Bob"));
		
		Iterator friends = db.getProfile("Maria").getFriends();
		if(friends.hasNext()) {
			String name = (String)friends.next();
			assertNotEquals("After deleting profile, it should should be removed from friends' friend list", name,
					"Maria");
		}
	}
}
