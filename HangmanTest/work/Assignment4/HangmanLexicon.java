/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import acm.util.*;

public class HangmanLexicon {
	private ArrayList<String> lexicon = new ArrayList<String>();
	private BufferedReader reader;

	public HangmanLexicon() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			String line = reader.readLine();
			while(line != null){
				lexicon.add(line);
				line = reader.readLine();
			}
		} catch (IOException e){
			e.printStackTrace();
		}

	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexicon.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return lexicon.get(index);
	}
}
