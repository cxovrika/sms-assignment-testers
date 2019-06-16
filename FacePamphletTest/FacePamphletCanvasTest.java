import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.util.Iterator;

import org.junit.Test;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.util.ErrorException;

public class FacePamphletCanvasTest {

	@Test
	public void testSimple() {
		FacePamphletCanvas canvas = new FacePamphletCanvas();
		canvas.setSize(620, 405);

		Iterator it = canvas.iterator();

		assertTrue("Initially canvas should be empty", !it.hasNext());
	}

	@Test
	public void testMessage() {
		FacePamphletCanvas canvas = new FacePamphletCanvas();
		canvas.setSize(620, 405);

		canvas.showMessage("Testing");

		Iterator it = canvas.iterator();

		assertTrue("After calling showMessage, canvas should not be empty", it.hasNext());

		Object element = it.next();
		if (element instanceof GLabel) {
			assertEquals(
					"After calling showMessage(\"Testing\"), label with the text \"Testing\" should be added on canvas -",
					"Testing", ((GLabel) element).getLabel());
		}

		assertEquals("Font for the message should be the same as MESSAGE_FONT in constants file -",
				FacePamphletConstants.MESSAGE_FONT,
				((GLabel) element).getFont().getName() + "-" + ((GLabel) element).getFont().getSize());
		assertTrue("After calling showMessage, canvas should contain only one label", !it.hasNext());
	}

	@Test
	public void testDisplayProfileName() {
		FacePamphletCanvas canvas = new FacePamphletCanvas();
		canvas.setSize(620, 405);

		FacePamphletProfile profile = new FacePamphletProfile("John");
		canvas.displayProfile(profile);

		Object nameLabel = canvas.getElementAt(FacePamphletConstants.LEFT_MARGIN, FacePamphletConstants.TOP_MARGIN);

		if (nameLabel != null && nameLabel instanceof GLabel) {
			assertEquals("Name label text is not correct", "John", ((GLabel) nameLabel).getLabel());
			assertEquals("Font for name label should be PROFILE_NAME_FONT", FacePamphletConstants.PROFILE_NAME_FONT,
					((GLabel) nameLabel).getFont().getName() + "-" + ((GLabel) nameLabel).getFont().getSize());
			assertEquals("Color for name label should be BLUE", Color.BLUE, ((GLabel) nameLabel).getColor());
		} else {
			fail("Name label was not found on correct coordinates");
		}
	}

	@Test
	public void testDisplayProfileNoImage() {
		FacePamphletCanvas canvas = new FacePamphletCanvas();
		canvas.setSize(620, 405);

		FacePamphletProfile profile = new FacePamphletProfile("John");
		canvas.displayProfile(profile);

		GLabel nameLabel = new GLabel("John");
		nameLabel.setFont(FacePamphletConstants.PROFILE_NAME_FONT);
		Object rectangle = canvas.getElementAt(FacePamphletConstants.LEFT_MARGIN,
				FacePamphletConstants.TOP_MARGIN + nameLabel.getAscent() + FacePamphletConstants.IMAGE_MARGIN);

		if (rectangle != null && rectangle instanceof GRect) {
			assertEquals("Height for \"No Image\" picture should be the same as IMAGE_WIDTH ",
					FacePamphletConstants.IMAGE_WIDTH, ((GRect) rectangle).getWidth(), 0.1);
			assertEquals("Width for \"No Image\" picture should be the same as IMAGE_HEIGHT ",
					FacePamphletConstants.IMAGE_HEIGHT, ((GRect) rectangle).getHeight(), 0.1);
		} else {
			fail("\"No Image\" picture was not found on correct coordinates");
		}

		double rectangleY = FacePamphletConstants.TOP_MARGIN + nameLabel.getAscent()
				+ FacePamphletConstants.IMAGE_MARGIN;
		GLabel noImageLabelCopy = new GLabel("No Image");
		noImageLabelCopy.setFont(FacePamphletConstants.PROFILE_IMAGE_FONT);
		Object noImageLabel = canvas.getElementAt(
				FacePamphletConstants.LEFT_MARGIN + FacePamphletConstants.IMAGE_WIDTH / 2,
				rectangleY + FacePamphletConstants.IMAGE_HEIGHT / 2);

		if (noImageLabel != null && noImageLabel instanceof GLabel) {
			assertEquals("\"No Image\" label text is not correct", "No Image", ((GLabel) noImageLabel).getLabel());
			assertEquals("Font for \"No Image\" label should be PROFILE_IMAGE_FONT",
					FacePamphletConstants.PROFILE_IMAGE_FONT,
					((GLabel) noImageLabel).getFont().getName() + "-" + ((GLabel) noImageLabel).getFont().getSize());
		} else {
			fail("\"No Image\" label was not found on correct coordinates");
		}
	}

	@Test
	public void testDisplayProfileImage() {
		FacePamphletCanvas canvas = new FacePamphletCanvas();
		canvas.setSize(620, 405);

		FacePamphletProfile profile = new FacePamphletProfile("John");

		GImage newImage = null;
		try {
			newImage = new GImage("MehranS.jpg");
		} catch (ErrorException ex) {

		}

		if (newImage != null) {
			profile.setImage(newImage);
			canvas.displayProfile(profile);

			GLabel nameLabel = new GLabel("John");
			nameLabel.setFont(FacePamphletConstants.PROFILE_NAME_FONT);

			Object image = canvas.getElementAt(FacePamphletConstants.LEFT_MARGIN,
					FacePamphletConstants.TOP_MARGIN + nameLabel.getAscent() + FacePamphletConstants.IMAGE_MARGIN);

			if (image != null && image instanceof GImage) {
				assertEquals("Height for profile picture should be the same as IMAGE_WIDTH ",
						FacePamphletConstants.IMAGE_WIDTH, ((GImage) image).getWidth(), 0.1);
				assertEquals("Width for profile picture should be the same as IMAGE_HEIGHT ",
						FacePamphletConstants.IMAGE_HEIGHT, ((GImage) image).getHeight(), 0.1);
				assertEquals("Image source mismatched", newImage.getImage().getSource(),
						((GImage) image).getImage().getSource());
			} else {
				fail("Profile picture was not found on correct coordinates");
			}
		}
	}

	@Test
	public void testDisplayProfileStatus() {
		FacePamphletCanvas canvas = new FacePamphletCanvas();
		canvas.setSize(620, 405);

		FacePamphletProfile profile = new FacePamphletProfile("John");
		profile.setStatus("running");

		canvas.displayProfile(profile);

		GLabel nameLabel = new GLabel("John");
		nameLabel.setFont(FacePamphletConstants.PROFILE_NAME_FONT);

		Object statusLabel = canvas.getElementAt(FacePamphletConstants.LEFT_MARGIN,
				FacePamphletConstants.TOP_MARGIN + nameLabel.getAscent() + FacePamphletConstants.IMAGE_MARGIN
						+ FacePamphletConstants.IMAGE_HEIGHT + FacePamphletConstants.STATUS_MARGIN);

		if (statusLabel != null && statusLabel instanceof GLabel) {
			assertEquals("Status label text is not correct", "John is running", ((GLabel) statusLabel).getLabel());
			assertEquals("Font for status label should be the same as PROFILE_STATUS_FONT",
					FacePamphletConstants.PROFILE_STATUS_FONT,
					((GLabel) nameLabel).getFont().getName() + "-" + ((GLabel) statusLabel).getFont().getSize() + "-"
							+ (((GLabel) statusLabel).getFont().isBold() ? "bold" : ""));
		} else {
			fail("Status label was not found on correct coordinates");
		}
	}

	@Test
	public void testDisplayProfile() {
		FacePamphletCanvas canvas = new FacePamphletCanvas();
		canvas.setSize(620, 405);

		FacePamphletProfile profile = new FacePamphletProfile("John");
		canvas.displayProfile(profile);

		Iterator it = canvas.iterator();

		Object element = it.next();

		while (it.hasNext()) {
			Object object = it.next();
			if (object instanceof GLabel) {

			}
		}
	}

	@Test
	public void testFriendsLabel() {
		FacePamphletCanvas canvas = new FacePamphletCanvas();
		canvas.setSize(620, 405);

		FacePamphletProfile profile = new FacePamphletProfile("John");
		canvas.displayProfile(profile);

		GLabel nameLabel = new GLabel("John");
		nameLabel.setFont(FacePamphletConstants.PROFILE_NAME_FONT);

		Object friendsLabel = canvas.getElementAt(310,
				FacePamphletConstants.TOP_MARGIN + nameLabel.getAscent() + FacePamphletConstants.IMAGE_MARGIN);

		if (friendsLabel != null && friendsLabel instanceof GLabel) {
			assertEquals("Name for friends label was not correct", "Friends:", ((GLabel) friendsLabel).getLabel());
			assertEquals("Font for the friends label should be PROFILE_FRIEND_LABEL_FONT",
					FacePamphletConstants.PROFILE_FRIEND_LABEL_FONT,
					((GLabel) nameLabel).getFont().getName() + "-" + ((GLabel) friendsLabel).getFont().getSize() + "-"
							+ (((GLabel) friendsLabel).getFont().isBold() ? "bold" : ""));
		} else {
			fail("Friends label was not found");
		}
	}

	@Test
	public void testFriendsList() {
		FacePamphletCanvas canvas = new FacePamphletCanvas();
		canvas.setSize(620, 405);

		FacePamphletProfile profile = new FacePamphletProfile("John");
		profile.addFriend("Bob");
		canvas.displayProfile(profile);

		GLabel nameLabel = new GLabel("John");
		nameLabel.setFont(FacePamphletConstants.PROFILE_NAME_FONT);

		GLabel friendsLabel = new GLabel("Friends:");
		nameLabel.setFont(FacePamphletConstants.PROFILE_FRIEND_LABEL_FONT);

		Object bobLabel = canvas.getElementAt(310, FacePamphletConstants.TOP_MARGIN + nameLabel.getAscent()
				+ FacePamphletConstants.IMAGE_MARGIN + friendsLabel.getHeight());

		if (bobLabel != null && bobLabel instanceof GLabel) {
			assertEquals("Name for friend's label was not correct -", "Bob", ((GLabel) bobLabel).getLabel());
			assertEquals("Font for the name for friend's label should be PROFILE_FRIEND_FONT -",
					FacePamphletConstants.PROFILE_FRIEND_FONT,
					((GLabel) bobLabel).getFont().getName() + "-" + ((GLabel) bobLabel).getFont().getSize());
		} else {
			fail("Label for friend's name was not found");
		}
	}
	
	
}
