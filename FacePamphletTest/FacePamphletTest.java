import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.util.ErrorException;

public class FacePamphletTest {
	private int canvasWidth = 620;
	private int canvasHeight = 405;
	
	private FacePamphlet pamphlet;
	private FacePamphletCanvas canvas;
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton addButton;
	private JButton deleteButton;
	private JButton lookupButton;
	private JTextField changeStatusField;
	private JButton changeStatusButton;
	private JTextField changePictureField;
	private JButton changePictureButton;
	private JTextField addFriendField;
	private JButton addFriendButton;

	@Before
	public void init() {
		pamphlet = new FacePamphlet();
		pamphlet.init();

		Container pane = pamphlet.getContentPane();
		for (int i = 0; i < pane.getComponentCount(); i++) {
			Component component = pane.getComponent(i);
			if (component instanceof JPanel) {
				JPanel panel = (JPanel) component;
				for (int j = 0; j < panel.getComponentCount(); j++) {
					if (panel.getComponent(j) instanceof JButton) {
						String name = ((JButton) panel.getComponent(j)).getText();
						if (!matchButton((JButton) panel.getComponent(j))) {
							fail("Some unknown button was found with label \"" + name + "\"");
						}
						if (j == 0) {
							continue;
						}
						if (panel.getComponent(j - 1) instanceof JTextField) {
							if (!matchField((JTextField) panel.getComponent(j - 1), name)) {
								fail("Some unknown text field was found");
							}
						}
					} else if (panel.getComponent(j) instanceof JLabel) {
						if (((JLabel) panel.getComponent(j)).getText().equals("Name")) {
							nameLabel = (JLabel) panel.getComponent(j);
						}
					} else if (panel.getComponent(j) instanceof JTextField) {
						if (j == panel.getComponentCount() - 1 || !(panel.getComponent(j + 1) instanceof JButton)) {
							fail("Some unknown text field was found");
						}
					} else if (panel.getComponent(j) instanceof FacePamphletCanvas) {
						canvas = (FacePamphletCanvas) panel.getComponent(j);
						canvas.setSize(canvasWidth, canvasHeight);
					} else {
						fail("Some unknown interactor detected");
					}
				}
			}
		}
		
		if (nameLabel == null) {
			fail("\"Name\" label was not found");
		}

		if (nameField == null) {
			fail("\"Name\" field was not found");
		}

		if (deleteButton == null) {
			fail("\"Delete\" button was not found");
		}

		if (lookupButton == null) {
			fail("\"Lookup\" button was not found");
		}

		if (changeStatusField == null) {
			fail("\"Change Status\" field was not found");
		}

		if (changeStatusButton == null) {
			fail("\"Change Status\" button was not found");
		}

		if (changePictureField == null) {
			fail("\"Change Picture\" field was not found");
		}

		if (changePictureButton == null) {
			fail("\"Change Picture\" button was not found");
		}

		if (addFriendField == null) {
			fail("\"Add Friend\" field was not found");
		}

		if (addFriendButton == null) {
			fail("\"Add Friend\" button was not found");
		}
	}

	private boolean matchButton(JButton button) {
		String name = button.getText();
		switch (name) {
		case "Add":
			addButton = button;
			return true;
		case "Delete":
			deleteButton = button;
			return true;
		case "Lookup":
			lookupButton = button;
			return true;
		case "Change Status":
			changeStatusButton = button;
			return true;
		case "Change Picture":
			changePictureButton = button;
			return true;
		case "Add Friend":
			addFriendButton = button;
			return true;
		}
		return false;
	}

	private boolean matchField(JTextField field, String nextButtonName) {
		switch (nextButtonName) {
		case "Add":
			nameField = field;
			return true;
		case "Change Status":
			changeStatusField = field;
			return true;
		case "Change Picture":
			changePictureField = field;
			return true;
		case "Add Friend":
			addFriendField = field;
			return true;
		}
		return false;
	}

	@Test
	public void testSize() {
		assertEquals("Application width should be the same as APPLICATION_WIDTH",
				FacePamphletConstants.APPLICATION_WIDTH, pamphlet.getSize().width);
		assertEquals("Application height should be the same as APPLICATION_HEIGHT",
				FacePamphletConstants.APPLICATION_HEIGHT, pamphlet.getSize().height);
	}

	@Test
	public void testAdd() {
		nameField.setText("John");
		addButton.doClick();

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
	public void testStatusDefault() {
		nameField.setText("John");
		addButton.doClick();

		GLabel nameLabel = new GLabel("John");
		nameLabel.setFont(FacePamphletConstants.PROFILE_NAME_FONT);

		Object statusLabel = canvas.getElementAt(FacePamphletConstants.LEFT_MARGIN,
				FacePamphletConstants.TOP_MARGIN + nameLabel.getAscent() + FacePamphletConstants.IMAGE_MARGIN
						+ FacePamphletConstants.IMAGE_HEIGHT + FacePamphletConstants.STATUS_MARGIN);

		if (statusLabel != null && statusLabel instanceof GLabel) {
			assertEquals("Initially status label text should be \"No current status\"", "No current status",
					((GLabel) statusLabel).getLabel());
			assertEquals("Font for status label should be the same as PROFILE_STATUS_FONT",
					FacePamphletConstants.PROFILE_STATUS_FONT,
					((GLabel) nameLabel).getFont().getName() + "-" + ((GLabel) statusLabel).getFont().getSize() + "-"
							+ (((GLabel) statusLabel).getFont().isBold() ? "bold" : ""));
		} else {
			fail("Name label was not found on correct coordinates");
		}
	}

	@Test
	public void testChangeStatus() {
		nameField.setText("John");
		addButton.doClick();

		GLabel nameLabel = new GLabel("John");
		nameLabel.setFont(FacePamphletConstants.PROFILE_NAME_FONT);

		changeStatusField.setText("running");
		changeStatusButton.doClick();

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
	public void testDefaultPicture() {
		nameField.setText("John");
		addButton.doClick();

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
	public void testChangePicture() {
		nameField.setText("John");
		addButton.doClick();

		GLabel nameLabel = new GLabel("John");
		nameLabel.setFont(FacePamphletConstants.PROFILE_NAME_FONT);

		changePictureField.setText("MehranS.jpg");
		changePictureButton.doClick();

		GImage newImage = null;
		try {
			newImage = new GImage("MehranS.jpg");
		} catch (ErrorException ex) {

		}

		if (newImage != null) {
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
	public void testFriendsLabel() {
		nameField.setText("John");
		addButton.doClick();

		GLabel nameLabel = new GLabel("John");
		nameLabel.setFont(FacePamphletConstants.PROFILE_NAME_FONT);

		Object friendsLabel = canvas.getElementAt(canvasWidth/2,
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
	public void testFriendNameLabel() {
		nameField.setText("Bob");
		addButton.doClick();
		
		nameField.setText("John");
		addButton.doClick();
		
		addFriendField.setText("Bob");
		addFriendButton.doClick();

		GLabel nameLabel = new GLabel("John");
		nameLabel.setFont(FacePamphletConstants.PROFILE_NAME_FONT);

		GLabel friendsLabel = new GLabel("Friends:");
		nameLabel.setFont(FacePamphletConstants.PROFILE_FRIEND_LABEL_FONT);

		Object bobLabel = canvas.getElementAt(canvasWidth/2, FacePamphletConstants.TOP_MARGIN + nameLabel.getAscent()
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
	
	@Test
	public void testFriendNotExisting() {
		nameField.setText("John");
		addButton.doClick();
		
		addFriendField.setText("Bob");
		addFriendButton.doClick();

		Object messageLabel = canvas.getElementAt(canvasWidth/2, canvasHeight - FacePamphletConstants.BOTTOM_MESSAGE_MARGIN);

		if (messageLabel != null && messageLabel instanceof GLabel) {
			assertEquals("Name for friend's label was not correct -", "Bob does not exist", ((GLabel) messageLabel).getLabel());
		} else {
			fail("Message label after adding not existing profile in friends was not found");
		}
		
	}
	
	@Test
	public void testLookup() {
		nameField.setText("Bob");
		addButton.doClick();
		
		nameField.setText("John");
		addButton.doClick();
		
		nameField.setText("Bob");
		lookupButton.doClick();

		Object nameLabel = canvas.getElementAt(FacePamphletConstants.LEFT_MARGIN, FacePamphletConstants.TOP_MARGIN);

		if (nameLabel != null && nameLabel instanceof GLabel) {
			assertEquals("After lookup valid profile was not displayed -", "Bob", ((GLabel) nameLabel).getLabel());
		} else {
			fail("After lookup name label was not found on correct coordinates");
		}
		
	}
	
	@Test
	public void testDelete() {
		nameField.setText("John");
		addButton.doClick();
		
		nameField.setText("John");
		deleteButton.doClick();

		Iterator it = canvas.iterator();

		assertTrue("After deleting profile, canvas should contain message label -", it.hasNext());

		Object element = it.next();
		if (element instanceof GLabel) {
			assertEquals(
					"After deleting profile, canvas should contain message label -",
					"Profile of John deleted", ((GLabel) element).getLabel());
		}
	}
	
	@Test
	public void testDeleteNonExisting() {		
		nameField.setText("John");
		deleteButton.doClick();

		Object messageLabel = canvas.getElementAt(canvasWidth/2, canvasHeight - FacePamphletConstants.BOTTOM_MESSAGE_MARGIN);

		if (messageLabel != null && messageLabel instanceof GLabel) {
			assertEquals("Label after deleting nonexisting profile was not correct -", "A profile with the name John does not exist", ((GLabel) messageLabel).getLabel());
		} else {
			fail("Message label after deleting not existing profile in friends was not found");
		}
	}
	
	@Test
	public void testLookupNonExisting() {		
		nameField.setText("John");
		lookupButton.doClick();

		Object messageLabel = getMessageLabel();

		if (messageLabel != null && messageLabel instanceof GLabel) {
			assertEquals("Label after lookuping nonexisting profile was not correct -", "A profile with the name John does not exist", ((GLabel) messageLabel).getLabel());
		} else {
			fail("Message label after lookuping not existing profile in friends was not found");
		}
	}
	
	@Test
	public void testMessageChangeStatusProfileNotChosen() {		
		changeStatusField.setText("John");
		changeStatusButton.doClick();

		Object messageLabel = getMessageLabel();

		if (messageLabel != null && messageLabel instanceof GLabel) {
			assertEquals("Label after changing status when profile is not chosen was not correct -", "Please select a profile to change status", ((GLabel) messageLabel).getLabel());
		} else {
			fail("Label after changing status when profile is not chosen was not found");
		}
	}
	
	@Test
	public void testMessageChangePictureProfileNotChosen() {		
		changePictureField.setText("John");
		changePictureButton.doClick();

		Object messageLabel = getMessageLabel();

		if (messageLabel != null && messageLabel instanceof GLabel) {
			assertEquals("Label after changing picture when profile is not chosen was not correct -", "Please select a profile to change picture", ((GLabel) messageLabel).getLabel());
		} else {
			fail("Label after changing picture when profile is not chosen was not found");
		}
	}
	
	@Test
	public void testMessageAddFriendProfileNotChosen() {		
		addFriendField.setText("John");
		addFriendButton.doClick();

		Object messageLabel = getMessageLabel();

		if (messageLabel != null && messageLabel instanceof GLabel) {
			assertEquals("Label after changing picture when profile is not chosen was not correct -", "Please select a profile to add friend", ((GLabel) messageLabel).getLabel());
		} else {
			fail("Label after changing picture when profile is not chosen was not found");
		}
	}
	
	@Test
	public void testMessageAddFriendAlreadyInFriend() {		
		nameField.setText("Bob");
		addButton.doClick();
		
		nameField.setText("John");
		addButton.doClick();
		
		addFriendField.setText("Bob");
		addFriendButton.doClick();
		
		addFriendField.setText("Bob");
		addFriendButton.doClick();

		Object messageLabel = getMessageLabel();

		if (messageLabel != null && messageLabel instanceof GLabel) {
			assertEquals("Label after adding friend which already has in friend was not correct -", "John already has Bob as a friend", ((GLabel) messageLabel).getLabel());
		} else {
			fail("Label after changing picture when profile is not chosen was not found");
		}
	}
	
	private Object getMessageLabel() {
		return canvas.getElementAt(canvasWidth/2, canvasHeight - FacePamphletConstants.BOTTOM_MESSAGE_MARGIN);
	}

}
