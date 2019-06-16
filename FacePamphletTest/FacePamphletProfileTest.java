import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import acm.graphics.GImage;
import acm.util.ErrorException;

public class FacePamphletProfileTest {

	@Test
	public void testSimple() {
		FacePamphletProfile profile = new FacePamphletProfile("John");
		assertEquals("Name for profile should be set", "John", profile.getName());

		profile.addFriend("Bob");
		Iterator<String> friends = profile.getFriends();
		assertEquals("After adding one new friend, friend iterator should have next", true, friends.hasNext());
		assertEquals("After adding one new friend, it's name should be set correctly", "Bob", friends.next());
		assertEquals("After adding one new friend, friend iterator should have only one element", false,
				friends.hasNext());
	}

	@Test
	public void testStatus() {
		FacePamphletProfile profile = new FacePamphletProfile("John");

		profile.setStatus("testing");
		assertEquals("Status for profile should be set", "testing", profile.getStatus());
	}

	@Test
	public void testFriends() {
		FacePamphletProfile profile = new FacePamphletProfile("John");

		assertEquals("Removing nonexisting friend should return false", false, profile.removeFriend("Bob"));

		Iterator<String> friends = profile.getFriends();
		assertEquals("Before adding new friends, iterator should not have next", false, friends.hasNext());

		assertEquals("Adding new friend should return true", true, profile.addFriend("Bob"));
		assertEquals("Removing nonexisting friend should return false", true, profile.removeFriend("Bob"));

		friends = profile.getFriends();
		assertEquals("Before removing added friend, iterator should not have next", false, friends.hasNext());
	}
	
	@Test
	public void testFriendsExisting() {
		FacePamphletProfile profile = new FacePamphletProfile("John");

		profile.addFriend("Bob");
		assertEquals("Adding already existing friend should return false", false, profile.addFriend("Bob"));
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
			assertEquals("Profile image should be set", profile.getImage().getImage().getSource(),
					newImage.getImage().getSource());
		}
	}

	@Test
	public void testToString() {
		FacePamphletProfile profile = new FacePamphletProfile("John");

		profile.setStatus("testing");
		profile.addFriend("Bob");

		assertEquals(
				"Profile data should be converted into string with following format - \"name (status): list of friends\"",
				"John (testing): Bob", profile.toString());

		profile.addFriend("Maria");

		assertEquals(
				"Profile data should be converted into string with following format - \"name (status): list of friends\"",
				"John (testing): Bob, Maria", profile.toString());
	}

}
