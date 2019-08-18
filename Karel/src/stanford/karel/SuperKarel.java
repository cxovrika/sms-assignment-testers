/*
 * This class implements an extension of the basic Karel class that supports
 * more operations.
 * - 2015/03/31: Changed to use Swing graphical components.
 */

package stanford.karel;

import java.awt.*;

/**
 * Extended Karel class . . .
 */
public class SuperKarel extends Karel {
	public static final Color BLACK = Color.BLACK;
	public static final Color BLUE = Color.BLUE;
	public static final Color CYAN = Color.CYAN;
	public static final Color DARK_GRAY = Color.DARK_GRAY;
	public static final Color GRAY = Color.GRAY;
	public static final Color GREEN = Color.GREEN;
	public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
	public static final Color MAGENTA = Color.MAGENTA;
	public static final Color ORANGE = Color.ORANGE;
	public static final Color PINK = Color.PINK;
	public static final Color RED = Color.RED;
	public static final Color WHITE = Color.WHITE;
	public static final Color YELLOW = Color.YELLOW;

	public SuperKarel() {
		/* Empty */
	}

	public void run() {
		/* Empty */
	}

	public void turnRight() {
		checkWorld("turnRight");
		setDirection(KarelWorld.rightFrom(getDirection()));
	}

	public void turnAround() {
		checkWorld("turnAround");
		setDirection(KarelWorld.oppositeDirection(getDirection()));
	}

	public void paintCorner(Color color) {
		/* Empty */
	}

	public void paintCorner(int rgb) {
		paintCorner(new Color(rgb));
	}

	public void paintCorner(double red, double blue, double green) {
		paintCorner(new Color((float) red, (float) blue, (float) green));
	}

	public Color newColor(int rgb) {
		return new Color(rgb);
	}

	public boolean random() {
		checkWorld("random");
		return random(0.5);
	}

	public boolean random(double p) {
		checkWorld("random");
		return Math.random() < p;
	}

	public boolean cornerColorIs(Color color) {
		return false;
	}

	public boolean cornerColorIs(int rgb) {
		return cornerColorIs(new Color(rgb));
	}

	public boolean cornerColorIs(double red, double blue, double green) {
		Color color = new Color((float) red, (float) blue, (float) green);
		return cornerColorIs(color);
	}

	public void pause(double milliseconds) {
		/* Empty */
	}
	
	/**
	 * Causes Karel to pop up a speech bubble with the given text in it.
	 */
	public void say(String text) {
		// TODO
	}
}
