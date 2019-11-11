import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import acm.graphics.GImage;
import acm.util.ErrorException;

public class FacePamphletProfileTest {

	@Test
	public void testSimple() {
		FacePamphletProfile profile = new FacePamphletProfile("John");
		assertEquals("Name for profile should be set -", "John", profile.getName());

		profile.addFriend("Bob");
		Iterator<String> friends = profile.getFriends();
		assertTrue("After adding one new friend, friend iterator should have next", friends.hasNext());
		assertEquals("After adding one new friend, it's name should be set correctly -", "Bob", friends.next());
		assertTrue("After adding one new friend, friend iterator should have only one element", !friends.hasNext());
	}

	@Test
	public void testStatus() {
		FacePamphletProfile profile = new FacePamphletProfile("John");

		profile.setStatus("testing");
		assertEquals("Status for profile should be set -", "testing", profile.getStatus());
	}

	@Test
	public void testFriends() {
		FacePamphletProfile profile = new FacePamphletProfile("John");

		assertTrue("Removing nonexisting friend should return false", !profile.removeFriend("Bob"));

		Iterator<String> friends = profile.getFriends();
		assertTrue("Before adding new friends, iterator should not have next", !friends.hasNext());

		assertTrue("Adding new friend should return true", profile.addFriend("Bob"));
		assertTrue("Removing existing friend should return true", profile.removeFriend("Bob"));

		friends = profile.getFriends();
		assertTrue("After removing added friend, iterator should not have next", !friends.hasNext());
	}
	
	@Test
	public void testFriendsExisting() {
		FacePamphletProfile profile = new FacePamphletProfile("John");

		profile.addFriend("Bob");
		assertTrue("Adding already existing friend should return false", !profile.addFriend("Bob"));
	}

	@Test
	public void testImage() {
		FacePamphletProfile profile = new FacePamphletProfile("John");

		GImage newImage = null;
		try {
			newImage = new GImage("MehranS.jpg");
		} catch (ErrorException ex) {

		}

		if (newImage != null) {
			profile.setImage(newImage);
			assertEquals("Profile image should be set -", profile.getImage().getImage().getSource(),
					newImage.getImage().getSource());
		}
	}

	@Test
	public void testToString() {
		FacePamphletProfile profile = new FacePamphletProfile("John");

		profile.setStatus("testing");
		profile.addFriend("Bob");

		assertEquals(
				"Profile data should be converted into string with following format - \"name (status): list of friends\" -",
				"John (testing): Bob", profile.toString());

		profile.addFriend("Maria");

		assertEquals(
				"Profile data should be converted into string with following format - \"name (status): list of friends\" -",
				"John (testing): Bob, Maria", profile.toString());
	}

}
