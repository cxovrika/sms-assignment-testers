/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Font;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	private String wrong;
	private GLabel current;
	private int life;

	/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		makeScaffoldBeamRope();
		life = 8;
		wrong = "";
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		if (getElementAt(30, getHeight() - 60) != null)
			remove(getElementAt(30, getHeight() - 60));
		current = new GLabel(word, 30, getHeight() - 60);
		current.setFont(new Font(Font.DIALOG_INPUT, 15, 25));
		add(current);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user.
	 * Calling this method causes the next body part to appear on the scaffold
	 * and adds the letter to the list of incorrect guesses that appears at the
	 * bottom of the window.
	 */
	public void noteIncorrectGuess(char letter) {
		wrongLetter(letter);
		life--;
		makeNextPart();

	}
/* if letter is incorrect makes next part to appear on canvas */
	private void makeNextPart() {
		if (life == 7)
			makeHead();
		if (life == 6)
			makeBody();
		if (life == 5)
			makeLeftHand();
		if (life == 4)
			makeRightHand();
		if (life == 3)
			makeLeftLeg();
		if (life == 2)
			makeRightLeg();
		if (life == 1)
			makeLeftFoot();
		if (life == 0)
			makeRightFoot();

	}
/* makes right foot */
	private void makeRightFoot() {
		GLine RightFoot = new GLine(getWidth() / 2 + HIP_WIDTH,
				SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH
						+ LEG_LENGTH, getWidth() / 2 + HIP_WIDTH + FOOT_LENGTH,
				SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH
						+ LEG_LENGTH);
		add(RightFoot);
	}
/* makes left food */
	private void makeLeftFoot() {
		GLine LeftFoot = new GLine(getWidth() / 2 - HIP_WIDTH,
				SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH
						+ LEG_LENGTH, getWidth() / 2 - HIP_WIDTH - FOOT_LENGTH,
				SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH
						+ LEG_LENGTH);
		add(LeftFoot);
	}
/* makes right leg*/
	private void makeRightLeg() {
		GPolygon RightLeg = new GPolygon(0, 0);
		RightLeg.addVertex(getWidth() / 2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2
				* HEAD_RADIUS + BODY_LENGTH);
		RightLeg.addVertex(getWidth() / 2 + HIP_WIDTH, SCAFFOLD_Y_OFFSET
				+ ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		RightLeg.addVertex(getWidth() / 2 + HIP_WIDTH, SCAFFOLD_Y_OFFSET
				+ ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		RightLeg.addVertex(getWidth() / 2 + HIP_WIDTH, SCAFFOLD_Y_OFFSET
				+ ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		add(RightLeg);
	}
/* makes left leg*/
	private void makeLeftLeg() {
		GPolygon LeftLeg = new GPolygon(0, 0);
		LeftLeg.addVertex(getWidth() / 2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2
				* HEAD_RADIUS + BODY_LENGTH);
		LeftLeg.addVertex(getWidth() / 2 - HIP_WIDTH, SCAFFOLD_Y_OFFSET
				+ ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		LeftLeg.addVertex(getWidth() / 2 - HIP_WIDTH, SCAFFOLD_Y_OFFSET
				+ ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		LeftLeg.addVertex(getWidth() / 2 - HIP_WIDTH, SCAFFOLD_Y_OFFSET
				+ ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		add(LeftLeg);
	}
/* makes right hand */
	private void makeRightHand() {
		GPolygon RightHand = new GPolygon(0, 0);
		RightHand.addVertex(getWidth() / 2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2
				* HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		RightHand.addVertex(getWidth() / 2 + UPPER_ARM_LENGTH,
				SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
						+ ARM_OFFSET_FROM_HEAD);
		RightHand.addVertex(getWidth() / 2 + UPPER_ARM_LENGTH,
				SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
						+ ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		RightHand.addVertex(getWidth() / 2 + UPPER_ARM_LENGTH,
				SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
						+ ARM_OFFSET_FROM_HEAD);
		add(RightHand);
	}
/* draws left hand */
	private void makeLeftHand() {
		GPolygon LeftHand = new GPolygon(0, 0);
		LeftHand.addVertex(getWidth() / 2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2
				* HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		LeftHand.addVertex(getWidth() / 2 - UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET
				+ ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		LeftHand.addVertex(getWidth() / 2 - UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET
				+ ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD
				+ LOWER_ARM_LENGTH);
		LeftHand.addVertex(getWidth() / 2 - UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET
				+ ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		add(LeftHand);
	}
/* draws head */
	private void makeHead() {
		GOval Head = new GOval(getWidth() / 2 - HEAD_RADIUS, SCAFFOLD_Y_OFFSET
				+ ROPE_LENGTH, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
		add(Head);
	}
/*draws body */
	private void makeBody() {
		GLine Body = new GLine(getWidth() / 2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH
				+ 2 * HEAD_RADIUS, getWidth() / 2, SCAFFOLD_Y_OFFSET
				+ ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		add(Body);
	}
/* makes wrong guesses appear on canvas */
	private void wrongLetter(char letter) {
		if (wrong.indexOf(letter) == -1) {
			wrong = wrong + letter;
			GLabel wrongLetters = new GLabel(wrong, 30, getHeight() - 30);
			wrongLetters.setFont(new Font(Font.DIALOG_INPUT, 5, 15));
			add(wrongLetters);
		}
	}
/* makes the scaffold */
	private void makeScaffoldBeamRope() {
		GPolygon hollow = new GPolygon(0, 0);
		hollow.addVertex(getWidth() / 2 - BEAM_LENGTH, SCAFFOLD_Y_OFFSET
				+ SCAFFOLD_HEIGHT);
		hollow.addVertex(getWidth() / 2 - BEAM_LENGTH, SCAFFOLD_Y_OFFSET);
		hollow.addVertex(getWidth() / 2, SCAFFOLD_Y_OFFSET);
		hollow.addVertex(getWidth() / 2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH);
		hollow.addVertex(getWidth() / 2, SCAFFOLD_Y_OFFSET);
		hollow.addVertex(getWidth() / 2 - BEAM_LENGTH, SCAFFOLD_Y_OFFSET);
		add(hollow);
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int SCAFFOLD_Y_OFFSET = 10;

}
