import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

import acm.graphics.GMath;
import acm.graphics.GObject;
import acm.graphics.GRectangle;

public class HangmanTest {
	public static final String LEXICON_HANGMAN = "HangmanLexicon.txt";
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
	
	public static void printOutput(String status, String message) {
		System.out.println("=== " + status + " === " + message);
	}

	public static Set<String> loadWords(String filename) {
		Set<String> words = new HashSet<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while (line != null) {
				words.add(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return words;
	}

	public static void changeWords(String filename, List<String> words) {
		try {
			FileWriter writer = new FileWriter(new File(filename), false);
			for (String word : words)
				writer.write(word);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Set<String> loadHangmanLexicon() {
		return loadWords(LEXICON_HANGMAN);
	}

	public static Set<String> loadStudentLexicon() {
		Set<String> studentLexiconWords = new HashSet<String>();
		HangmanLexicon lexicon = new HangmanLexicon();
		for (int i = 0; i < lexicon.getWordCount(); i++) {
			studentLexiconWords.add(lexicon.getWord(i));
		}

		return studentLexiconWords;
	}

	public static boolean setsMatch(Set<String> lexiconA, Set<String> lexiconB) {
		for (String aWord : lexiconA)
			if (!lexiconB.contains(aWord))
				return false;

		return true;
	}

	public static void testLexicon() {
		printOutput("LEXICON TESTS", "");
		Set<String> studentWords = loadStudentLexicon();
		Set<String> hangmanWords = loadHangmanLexicon();

		if (studentWords.size() == hangmanWords.size()) {
			printOutput("PASSED", "Lexicon size is correct");
			if (setsMatch(hangmanWords, studentWords)) {
				printOutput("PASSED", "All words are present in the lexicon");
				System.out.println("");
				System.out.println("");
				return;
			}
		} else {
			printOutput("FAILED", "Lexicon size is incorrect");
		}
		printOutput("FAILED", "Some words are missing from the lexicon");
		System.out.println("");
		System.out.println("");
	}

	public static boolean containsWin(String word) {
		return word.contains("win") || word.contains("Win")
				|| word.contains("WIN") || word.contains("won") || word.contains("Won") || word.contains("WON");
	}
	
	public static boolean containsLose(String word){
		return word.contains("lose") || word.contains("Lose")
				|| word.contains("LOSE") || word.contains("lost") || word.contains("Lost") || word.contains("LOST");
	}

	public static void testConsoleWin() {
		changeWords(LEXICON_HANGMAN, Arrays.asList(new String[] { "ABC" }));
		final Hangman hangman = new Hangman();
		hangman.init();
		hangman.lineList = new ArrayList<String>();
		hangman.lineList.add("A");
		hangman.lineList.add("B");
		hangman.lineList.add("C");

		try {
			hangman.run();
		} catch (Exception e) {

		}

		if (containsWin(hangman.printedStrings.get(hangman.printedStrings
				.size() - 1))
				|| containsWin(hangman.printedStrings
						.get(hangman.printedStrings.size() - 2)))
			printOutput("PASSED", "Hangman correctly won the game");
		else
			printOutput("FAILED", "Hangman should've won the game but didn't");
	}

	public static void testConsoleTotalLose(){
		changeWords(LEXICON_HANGMAN, Arrays.asList(new String[] { "ABC" }));
		final Hangman hangman = new Hangman();
		hangman.init();
		hangman.lineList = new ArrayList<String>();
		hangman.lineList.add("Z");
		hangman.lineList.add("Z");
		hangman.lineList.add("Z");
		hangman.lineList.add("Z");
		hangman.lineList.add("Z");
		hangman.lineList.add("Z");
		hangman.lineList.add("Z");
		hangman.lineList.add("Z");

		try {
			hangman.run();
		} catch (Exception e) {

		}
			
		if (containsLose(hangman.printedStrings.get(hangman.printedStrings
				.size() - 1))
				|| containsLose(hangman.printedStrings
						.get(hangman.printedStrings.size() - 2)))
			printOutput("PASSED", "Hangman correctly lost the game");
		else
			printOutput("FAILED", "Hangman should've lost the game but didn't");
	}
	
	public static void testConsolePartlyLose(){
		changeWords(LEXICON_HANGMAN, Arrays.asList(new String[] { "ABC" }));
		final Hangman hangman = new Hangman();
		hangman.init();
		hangman.lineList = new ArrayList<String>();
		hangman.lineList.add("A");
		hangman.lineList.add("Z");
		hangman.lineList.add("Z");
		hangman.lineList.add("Z");
		hangman.lineList.add("Z");
		hangman.lineList.add("B");
		hangman.lineList.add("Z");
		hangman.lineList.add("Z");
		hangman.lineList.add("Z");
		hangman.lineList.add("Z");

		try {
			hangman.run();
		} catch (Exception e) {

		}
		
		if (containsLose(hangman.printedStrings.get(hangman.printedStrings
				.size() - 1))
				|| containsLose(hangman.printedStrings
						.get(hangman.printedStrings.size() - 2)))
			printOutput("PASSED", "Hangman correctly lost the game after some guesses");
		else
			printOutput("FAILED", "Hangman should've lost the game after some guesses but didn't");
	}
	
	public static void testConsole() {
		printOutput("CONSOLE TESTS", "");
		testConsoleWin();
		testConsoleTotalLose();
		testConsolePartlyLose();
		System.out.println("");
		System.out.println("");
	}

	public static boolean isElementPresent(HangmanCanvas hCanvas, double x, double y){
		//System.out.println("x: " + x + " y: " + y);
		Iterator<GObject> iterator = hCanvas.iterator();
		if(iterator.hasNext())
			iterator.next();
		while (iterator.hasNext()){
			GRectangle rect = iterator.next().getBounds();
			//System.out.println(rect.getX() + " " + rect.getY() + " " + rect.getWidth() + " " + rect.getHeight());
			if(rect != null && rect.contains(GMath.round(x), GMath.round(y)))
				return true;
		}
		
		return false;
	}
	
	public static void checkSinglePart(HangmanCanvas hCanvas, double x, double y, String okMessage, String badMessage){
		if(isElementPresent(hCanvas, x, y)){
			printOutput("FAILED", badMessage);
			return;
		}
		
		hCanvas.noteIncorrectGuess('A');
		
		if(isElementPresent(hCanvas, x, y))
			printOutput("PASSED", okMessage);
		else
			printOutput("FAILED", badMessage);
	}
	
	public static void testCanvas() {
		printOutput("CANVAS TESTS", "");
		HangmanCanvas hCanvas = new HangmanCanvas();
		hCanvas.reset();
		checkSinglePart(hCanvas, hCanvas.getWidth() / 2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH, "Head was added correctly", "Head was not added correctly");
		checkSinglePart(hCanvas, hCanvas.getWidth() / 2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH / 2, "Body was added correctly", "Body was not added correctly");
		checkSinglePart(hCanvas, hCanvas.getWidth() / 2 - UPPER_ARM_LENGTH + 1, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + 1, "Left hand was added correctly", "Left hand was not added correctly");
		checkSinglePart(hCanvas, hCanvas.getWidth() / 2 + UPPER_ARM_LENGTH - 1, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + 1, "Right hand was added correctly", "Right hand was not added correctly");
		checkSinglePart(hCanvas, hCanvas.getWidth() / 2 - HIP_WIDTH + 1, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + 1, "Left leg was added correctly", "Left leg was not added correctly");
		checkSinglePart(hCanvas, hCanvas.getWidth() / 2 + HIP_WIDTH - 1, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + 1, "Right leg was added correctly", "Right leg was not added correctly");
		checkSinglePart(hCanvas, hCanvas.getWidth() / 2 - HIP_WIDTH - 1, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, "Left foot was added correctly", "Left foot was not added correctly");
		checkSinglePart(hCanvas, hCanvas.getWidth() / 2 + HIP_WIDTH + 1, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, "Right foot was added correctly", "Right foot was not added correctly");
	}

	public static void main(String[] args) {
		testLexicon();
		testConsole();
		testCanvas();
	}
}
	