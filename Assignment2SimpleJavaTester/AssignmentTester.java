import java.util.ArrayList;


import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.program.Program;

import java.awt.*;

public class AssignmentTester {
	
	private static boolean checkProgramOutputForExpectedPrefixes(ArrayList<String> expectedPrefixes) {
		boolean failed = false;
		for (int i = 0; i < expectedPrefixes.size(); i++) {
			if (i >= Program.printedStrings.size()) {
				System.out.println("line " + (i+1) + ", expected something like \"" + expectedPrefixes.get(i) + "\", "
									+ " no output found for that line");
				failed = true;
			} else {
				String line = Program.printedStrings.get(i);
				if (!line.startsWith(expectedPrefixes.get(i))) {
					System.out.println("line " + (i+1) + ", expected something like \"" + expectedPrefixes.get(i) + "\", "
							+ " found \"" + Program.printedStrings.get(i) + "\"");
					failed = true;
				}
			}
		}

		return failed;
	}
	
	private static void pythagoreanTheoremTest1() {
		PythagoreanTheorem testProgram = new PythagoreanTheorem();
		Program.printedStrings.clear();
		
		ArrayList<Integer> testInts = new ArrayList<Integer>();
		testInts.add(6);
		testInts.add(11);
		
		Program.intList = testInts;
		testProgram.run();
		
		ArrayList<String> expectedPrefixes = new ArrayList<String>();
		expectedPrefixes.add("Enter values to compute Pythagorean Theorem.");
		expectedPrefixes.add("a: ");
		expectedPrefixes.add("b: ");
		expectedPrefixes.add("c = 12.529964086141668");
		
		System.out.println("Test1:");
		boolean failed = checkProgramOutputForExpectedPrefixes(expectedPrefixes);
		
		System.out.println(failed ? "FAILED" : "PASSED");
	}

	private static void pythagoreanTheoremTest2() {
		PythagoreanTheorem testProgram = new PythagoreanTheorem();
		Program.printedStrings.clear();
		ArrayList<Integer> testInts = new ArrayList<Integer>();
		testInts.add(1234);
		testInts.add(4321);
		
		Program.intList = testInts;
		testProgram.run();
		
		ArrayList<String> expectedPrefixes = new ArrayList<String>();
		expectedPrefixes.add("Enter values to compute Pythagorean Theorem.");
		expectedPrefixes.add("a: ");
		expectedPrefixes.add("b: ");
		expectedPrefixes.add("c = 4493.750883");
		
		System.out.println("Test2:");
		boolean failed = checkProgramOutputForExpectedPrefixes(expectedPrefixes);
		
		System.out.println(failed ? "FAILED" : "PASSED");
	}
	
	private static void testPythagoreanTheorem() {
		System.out.println("Pythagorean Theorem test results:");
		pythagoreanTheoremTest1();
		pythagoreanTheoremTest2();
		System.out.println();
	}
	
	private static void findRangeTest1() {
		FindRange testProgram = new FindRange();
		Program.printedStrings.clear();
		ArrayList<Integer> testInts = new ArrayList<Integer>();
		testInts.add(11);
		testInts.add(17);
		testInts.add(42);
		testInts.add(9);
		testInts.add(-3);
		testInts.add(35);
		testInts.add(0);
		
		Program.intList = testInts;
		
		ArrayList<String> expectedPrefixes = new ArrayList<String>();
		expectedPrefixes.add("This program finds the largest and smallest numbers");
		for (int i = 0; i < testInts.size(); i++) {
			expectedPrefixes.add("? ");
		}
		expectedPrefixes.add("smallest: -3");
		expectedPrefixes.add("largest: 42");

		testProgram.run();
		
		System.out.println("Test1:");
		boolean failed = checkProgramOutputForExpectedPrefixes(expectedPrefixes);
		
		System.out.println(failed ? "FAILED" : "PASSED");
	}
	
	private static void findRangeTest2() {
		FindRange testProgram = new FindRange();
		Program.printedStrings.clear();
		ArrayList<Integer> testInts = new ArrayList<Integer>();
		testInts.add(0);
		
		Program.intList = testInts;
		
		ArrayList<String> expectedPrefixes = new ArrayList<String>();
		expectedPrefixes.add("This program finds the largest and smallest numbers");
		expectedPrefixes.add("? ");
		expectedPrefixes.add("please enter some numbers first");
		
		testProgram.run();
		
		System.out.println("Test2:");
		boolean failed = checkProgramOutputForExpectedPrefixes(expectedPrefixes);
		
		System.out.println(failed ? "FAILED" : "PASSED");
	}

	
	private static void testFindRange() {
		System.out.println("Find Range test results:");
		findRangeTest1();
		findRangeTest2();
		System.out.println();
	}
	
	private static void hailstoneTest1() {
		Hailstone testProgram = new Hailstone();
		Program.printedStrings.clear();
		ArrayList<Integer> testInts = new ArrayList<Integer>();
		testInts.add(17);
		
		Program.intList = testInts;
		
		ArrayList<String> expectedPrefixes = new ArrayList<String>();
		expectedPrefixes.add("Enter a number:");
		expectedPrefixes.add("17 is odd, so I make 3n + 1: 52");
		expectedPrefixes.add("52 is even so I take half: 26");
		expectedPrefixes.add("26 is even so I take half: 13");
		expectedPrefixes.add("13 is odd, so I make 3n + 1: 40");
		expectedPrefixes.add("40 is even so I take half: 20");
		expectedPrefixes.add("20 is even so I take half: 10");
		expectedPrefixes.add("10 is even so I take half: 5");
		expectedPrefixes.add("5 is odd, so I make 3n + 1: 16");
		expectedPrefixes.add("16 is even so I take half: 8");
		expectedPrefixes.add("8 is even so I take half: 4");
		expectedPrefixes.add("4 is even so I take half: 2");
		expectedPrefixes.add("2 is even so I take half: 1");
		expectedPrefixes.add("The process took 12 turns to reach 1");

		testProgram.run();
		
		System.out.println("Test1:");
		boolean failed = checkProgramOutputForExpectedPrefixes(expectedPrefixes);
		
		System.out.println(failed ? "FAILED" : "PASSED");
	}

	private static void hailstoneTest2() {
		Hailstone testProgram = new Hailstone();
		Program.printedStrings.clear();
		ArrayList<Integer> testInts = new ArrayList<Integer>();
		testInts.add(17);
		
		Program.intList = testInts;
		
		ArrayList<String> expectedPrefixes = new ArrayList<String>();
		expectedPrefixes.add("Enter a number:");
		expectedPrefixes.add("17 is odd, so I make 3n + 1: 52");
		expectedPrefixes.add("52 is even so I take half: 26");
		expectedPrefixes.add("26 is even so I take half: 13");
		expectedPrefixes.add("13 is odd, so I make 3n + 1: 40");
		expectedPrefixes.add("40 is even so I take half: 20");
		expectedPrefixes.add("20 is even so I take half: 10");
		expectedPrefixes.add("10 is even so I take half: 5");
		expectedPrefixes.add("5 is odd, so I make 3n + 1: 16");
		expectedPrefixes.add("16 is even so I take half: 8");
		expectedPrefixes.add("8 is even so I take half: 4");
		expectedPrefixes.add("4 is even so I take half: 2");
		expectedPrefixes.add("2 is even so I take half: 1");
		expectedPrefixes.add("The process took 12 turns to reach 1");

		testProgram.run();
		
		System.out.println("Test1:");
		boolean failed = checkProgramOutputForExpectedPrefixes(expectedPrefixes);
		
		System.out.println(failed ? "FAILED" : "PASSED");
	}
	
	private static void testHailstone() {
		System.out.println("Hailstone test results:");
		hailstoneTest1();
		hailstoneTest2();
		System.out.println();
	}
	
	private static void testPyramid() {
		GraphicsProgram.canvasObjects = new ArrayList<>();
		Pyramid testProgram = new Pyramid();
		Program.programWidth = 1200;
		Program.programHeight = 1200;
		testProgram.run();
		
		System.out.println("Testing Pyramid on 1200 x 1200 canvas");
		
		ArrayList<GPoint> expectedCoordinates = new ArrayList<>();
		
		int curHeight= Program.programHeight;
		for (int i = 14; i >= 1; i--) {
			int startingWidth = Program.programWidth/2 - (i * 30) /2;
			    curHeight -= 12;

			 for (int j = 0; j < i; j++) {
				 expectedCoordinates.add(new GPoint(startingWidth + j * 30, curHeight));
			 }
		}
		
		boolean failed = false;
		for (int i = GraphicsProgram.canvasObjects.size() - 1; i >= 0; i--) {
			GObject studentObject = GraphicsProgram.canvasObjects.get(i);
			if (!(studentObject instanceof GRect)) {
				failed = true;
				System.out.println("Found non-GRect object!");
				continue;
			}
			
			if (((GRect)studentObject).getWidth() != 30 ||
				((GRect)studentObject).getHeight() != 12) {
				failed = true;
				System.out.println("Found GRect with width and height of:\n"
						 			+ ((GRect)studentObject).getHeight() + " " +  + ((GRect)studentObject).getWidth() + "\n"
						 			+ "expected 30 and 12");
				
				continue;
			}
			
			boolean found = false;
			for (int j = 0; j < expectedCoordinates.size(); j++) {
				GPoint expectedLocation = expectedCoordinates.get(j);
				
				if (studentObject.getLocation().equals(expectedLocation)) {
					found = true;
					GraphicsProgram.canvasObjects.remove(i);
					expectedCoordinates.remove(j);
					break;
				}
			}
			
			if (!found) {
				failed = true;
				System.out.println("Found brick on location " + studentObject.getLocation() + " which should not be there");
			}
		}
		
		if (expectedCoordinates.size() > 0) {
			System.out.println("Could not find " + expectedCoordinates.size() + " expected GRects.");
			System.out.println("They are either not on the canvas or are added with wrong coordinates");
			
			failed = true;
		}
		
		System.out.println(failed ? "FAILED" : "PASSED");
		System.out.println();
	}
	
	
	private static void testTarget() {
		GraphicsProgram.canvasObjects = new ArrayList<>();
		Target testProgram = new Target();
		Program.programWidth = 1200;
		Program.programHeight = 1200;
		testProgram.run();
		
		System.out.println("Testing Target on 1200 x 1200 canvas");
		boolean failed = false;
		for (int j = 300; j <= 900; j++) {
			for (int i = 300; i <= 900; i++) {
				if (failed) break;
				
				double d1 = i - Program.programWidth / 2;
				double d2 = j - Program.programHeight / 2;
				double distance = Math.sqrt(d1*d1 + d2*d2);
				GObject obj = testProgram.getElementAt(j, i);
				
				if (distance > 72.5) {
					if (obj != null) {
						System.out.println("Found some object at coordinates (" + j + ", " + i + "). There should be nothing.\n");
						failed = true;
						break;
					}
				} else
				if (distance > 47 && distance < 71.5) {
					if (obj == null) {
						System.out.println("Point at coordinates (" + j + ", " + i + ") is not colored.");
						failed = true;
					} else
					if (obj.getColor().getRGB() != -65536) {
						System.out.println(distance + " Point at coordinates (" + j + ", " + i + ") should be red but is not.\n");
						failed = true;
						break;
					}
				} else
				if (distance > 22 && distance < 46) {
					if (obj == null) {
						System.out.println("Point at coordinates (" + j + ", " + i + ") is not colored.");
						failed = true;
					} else
					if (obj.getColor().getRGB() != -1) {
						System.out.println(distance + " Point at coordinates (" + j + ", " + i + ") should be white but is not.\n");
						System.out.println(">" + obj.getColor().getRGB() + "<");
						failed = true;
						break;
					}
				} else
				if (distance < 21) {
					if (obj == null) {
						System.out.println("Point at coordinates (" + j + ", " + i + ") is not colored.");
						failed = true;
					} else
					if (obj.getColor().getRGB() != -65536) {
						System.out.println(distance + " Point at coordinates (" + j + ", " + i + ") should be red but is not.\n");
						failed = true;
						break;
					}
				}
			}
		}
		
		System.out.println(failed ? "FAILED" : "PASSED");
		System.out.println();
	}
	
	public static void main(String[] args) {
		testPythagoreanTheorem();
		testFindRange();
		testHailstone();
		testPyramid();
		testTarget();
	}
		
}

