import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

public class FacePamphletDatabaseTest {

	@Test
	public void testSimple() {
		FacePamphletDatabase db = new FacePamphletDatabase();
		assertTrue("Initially database should be empty", false == db.containsProfile("John"));
		FacePamphletProfile profile = new FacePamphletProfile("John");
		db.addProfile(profile);
		assertTrue("After adding new profile, contain should return true", true == db.containsProfile("John"));
		assertTrue("After adding new profile, getProfile should return corresponding profile", 
				db.getProfile("John").getName().equals("John"));
		db.deleteProfile("John");
		assertTrue("After deleting a profile, contain should return false", false == db.containsProfile("John"));
	}

	@Test
	public void testNonexistant() {
		FacePamphletDatabase db = new FacePamphletDatabase();
		String initiallyEmptyMsg = "Initially database should be empty";
		assertTrue(initiallyEmptyMsg, !db.containsProfile("John"));
		FacePamphletProfile profile = new FacePamphletProfile("Maria");
		FacePamphletProfile profile1 = new FacePamphletProfile("Ann");
		FacePamphletProfile profile2 = new FacePamphletProfile("Sam");
		FacePamphletProfile profile3 = new FacePamphletProfile("Bob");
		assertTrue(initiallyEmptyMsg, !db.containsProfile("John"));
		assertTrue(initiallyEmptyMsg, !db.containsProfile("Teresa"));
		assertTrue(initiallyEmptyMsg, !db.containsProfile("Diana"));
		assertTrue(initiallyEmptyMsg, !db.containsProfile("Jake"));
		assertTrue(initiallyEmptyMsg, !db.containsProfile("Eric"));
		db.addProfile(profile);
		db.addProfile(profile1);
		db.addProfile(profile2);
		db.addProfile(profile3);
		String newProfileContainMsg = "After adding new profile, contain should return true";
		assertTrue(newProfileContainMsg, db.containsProfile("Maria"));
		assertTrue(newProfileContainMsg, db.containsProfile("Ann"));
		assertTrue(newProfileContainMsg, db.containsProfile("Sam"));
		assertTrue(newProfileContainMsg, db.containsProfile("Bob"));
		String unknownProfileContainMsg = "Database should only contain profiles that were added after calling addProfile";
		assertTrue(unknownProfileContainMsg, !db.containsProfile("John"));
		assertTrue(unknownProfileContainMsg, !db.containsProfile("Teresa"));
		assertTrue(unknownProfileContainMsg, !db.containsProfile("Diana"));
		assertTrue(unknownProfileContainMsg, !db.containsProfile("Jake"));
		assertTrue(unknownProfileContainMsg, !db.containsProfile("Eric"));
	}

	@Test
	public void testReplace() {
		FacePamphletDatabase db = new FacePamphletDatabase();
		assertTrue("Initially database should be empty", !db.containsProfile("John"));
		FacePamphletProfile profile = new FacePamphletProfile("Maria");
		profile.setStatus("testing");
		assertTrue("Initially database should be empty", !db.containsProfile("Maria"));
		db.addProfile(profile);
		assertTrue("After adding new profile, contain should return true", db.containsProfile("Maria"));
		assertTrue("After adding new profile, get not working correctly", 
				db.getProfile("Maria").getStatus().equals("testing"));
		FacePamphletProfile profileNew = new FacePamphletProfile("Maria");
		profileNew.setStatus("coding");
		db.addProfile(profileNew);
		assertTrue("After adding new profile with already existing name, contains does not work correctly", 
				db.containsProfile("Maria"));
		assertTrue("After adding new profile with already existing name, old one was not replaced",
				db.getProfile("Maria").getStatus().equals("coding"));
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

		assertTrue("To test deleting profile, it should first be added", db.containsProfile("Maria"));
		assertTrue("To test deleting profile, it should first be added", db.containsProfile("Bob"));

		db.deleteProfile("Bob");
		assertTrue("After deleting profile, it should should be deleted from database", !db.containsProfile("Bob"));
		
		Iterator friends = db.getProfile("Maria").getFriends();
		if(friends.hasNext()) {
			String name = (String)friends.next();
			assertTrue("After deleting profile, it should should be removed from friends' friend list", !name.equals("Maria"));
		}
	}
}
