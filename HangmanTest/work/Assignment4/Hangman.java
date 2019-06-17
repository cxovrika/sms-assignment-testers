/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private HangmanLexicon hng = new HangmanLexicon();
	private RandomGenerator rnd = RandomGenerator.getInstance();
	private String current;
	private String word;
	private int Life;
	public HangmanCanvas canvas;

	/* initializes some thins */
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		openingMessage();
	}

	/* runs the program */
	public void run() {
		while (true) {
			getStarted();
			makeGame();
			while (true) {
				String cont = readLine("press c to continue");
				if (cont.equals("c"))
					break;
			}
		}

	}

	/* if guess is correct shows letter in the current word */
	private void Show(char letter) {

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == letter) {
				current = current.substring(0, i) + letter
						+ current.substring(i + 1, word.length());

			}
		}

	}

	/* makes game itself */
	private void makeGame() {
		while (true) {
			String letter = readLine("your guess ");
			if (letter.length() != 1)
				oneLetter();
			else {
				char let = letter.charAt(0);
				if ((let > 64 && let < 91) || (let > 96 && let < 123)) {
					let = Character.toUpperCase(let);
					correct(let);
					uncorrect(let);
					canvas.displayWord(current);
					if (win()) {
						winningMessages();
						break;
					}
					currentWord();
					guessesLeft();
					if (lose()) {
						losingMessages();
						break;
					}
				} else
					wrongSymbol();
			}
		}
	}

	/* informs user how many guesses are left */
	private void guessesLeft() {
		println("you have " + Life + " guesses left");
	}

	/* types losing messages */
	private void losingMessages() {
		noLife();
		trueWord();
		losing();
	}

	/* checks if user lost */
	private boolean lose() {
		if (Life == 0)
			return true;
		else
			return false;
	}

	/* types winning message */
	private void winningMessages() {
		println("you guessed the word " + word);
		println("you win");
	}

	/* checks if user won */
	private boolean win() {
		if (current.equals(word))
			return true;
		else
			return false;
	}

	/*
	 * checks if word is uncorrect and if it is takes one life and makes one
	 * part of body to appear on canvas
	 */
	private void uncorrect(char let) {
		if (word.indexOf(let) == -1) {
			Life--;
			println("there are no " + let + "'s in the word");
			canvas.noteIncorrectGuess(let);
		}
	}

	/* checks if the letter is correct and if it is shows the letter */
	private void correct(char let) {
		if (word.indexOf(let) != -1) {
			Show(let);
			correctGuess();
		}
	}

	/* types opening text */
	private void openingMessage() {
		println("Welcome to Hangman");
	}

	/* makes first view of the word */
	private void makeFirstCurrent() {
		current = "";
		int counter = 0;
		while (counter < word.length()) {
			current = current + "-";
			counter++;
		}
		canvas.displayWord(current);
		currentWord();
	}

	/* shows user how his current word looks like */
	private void currentWord() {
		println("your word now looks like this : " + current);

	}

	/* informs user that his guess was correct */
	private void correctGuess() {
		println("your guess is correct");
	}

	/* informs user that he has no lifes */
	private void noLife() {
		println("you're completely hung");
	}

	/* informs user what was the word */
	private void trueWord() {
		println("the word was " + word);
	}

	/* informs use that he lost */
	private void losing() {
		println("you lose");
	}

	/* informs user that his inpput wasn't a letter */
	private void wrongSymbol() {
		println("wrong symbol try another one ");
	}

	/* informs the user to use only one letter inputs */
	private void oneLetter() {
		println("only one letter is available");
	}

	/* resets canvas.resets life,takes new word.makes firs current word look */
	private void getStarted() {
		Life = 8;
		canvas.reset();
		int index = rnd.nextInt(0, hng.getWordCount() - 1);
		word = hng.getWord(index);
		makeFirstCurrent();
	}
}
