
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import stanford.karel.KarelWorld;

public class CheckerboardKarelTest {
	@Test
	public void test1x1() {
		CheckerboardKarel checkerboardKarel = new CheckerboardKarel();
		checkerboardKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(1, 1);

		checkerboardKarel.setWorld(newWorld);
		checkerboardKarel.run();

		String msg = isCorrectlyFilled(newWorld);
		if (msg != null) {
			fail("Board 1x1 - " + msg);
		}
	}

	@Test
	public void test2x2() {
		CheckerboardKarel checkerboardKarel = new CheckerboardKarel();
		checkerboardKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(2, 2);

		checkerboardKarel.setWorld(newWorld);
		checkerboardKarel.run();

		String msg = isCorrectlyFilled(newWorld);
		if (msg != null) {
			fail("Board 2x2 - " + msg);
		}
	}

	@Test
	public void testnxnOdd() {
		CheckerboardKarel checkerboardKarel = new CheckerboardKarel();
		checkerboardKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(7, 7);

		checkerboardKarel.setWorld(newWorld);
		checkerboardKarel.run();

		String msg = isCorrectlyFilled(newWorld);
		if (msg != null) {
			fail("Board 7x7 - " + msg);
		}
	}

	@Test
	public void testnxnEven() {
		CheckerboardKarel checkerboardKarel = new CheckerboardKarel();
		checkerboardKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(8, 8);

		checkerboardKarel.setWorld(newWorld);
		checkerboardKarel.run();

		String msg = isCorrectlyFilled(newWorld);
		if (msg != null) {
			fail("Board 8x8 - " + msg);
		}
	}

	@Test
	public void test1xnOdd() {
		CheckerboardKarel checkerboardKarel = new CheckerboardKarel();
		checkerboardKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(1, 7);

		checkerboardKarel.setWorld(newWorld);
		checkerboardKarel.run();

		String msg = isCorrectlyFilled(newWorld);
		if (msg != null) {
			fail("Board 1x7 - " + msg);
		}
	}

	@Test
	public void test1xnEven() {
		CheckerboardKarel checkerboardKarel = new CheckerboardKarel();
		checkerboardKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(1, 8);

		checkerboardKarel.setWorld(newWorld);
		checkerboardKarel.run();

		String msg = isCorrectlyFilled(newWorld);
		if (msg != null) {
			fail("Board 1x8 - " + msg);
		}
	}

	@Test
	public void testnx1Odd() {
		CheckerboardKarel checkerboardKarel = new CheckerboardKarel();
		checkerboardKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(7, 1);

		checkerboardKarel.setWorld(newWorld);
		checkerboardKarel.run();

		String msg = isCorrectlyFilled(newWorld);
		if (msg != null) {
			fail("Board 7x1 - " + msg);
		}
	}

	@Test
	public void testnx1Even() {
		CheckerboardKarel checkerboardKarel = new CheckerboardKarel();
		checkerboardKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(8, 1);

		checkerboardKarel.setWorld(newWorld);
		checkerboardKarel.run();

		String msg = isCorrectlyFilled(newWorld);
		if (msg != null) {
			fail("Board 8x1 - " + msg);
		}
	}

	private String isCorrectlyFilled(KarelWorld world) {
		int numRows = world.getRows();
		int numCols = world.getColumns();
		for (int x = 1; x <= numCols; x++) {
			for (int y = 1; y <= numRows; y++) {
				if (y % 2 == 1) {
					if ((x % 2 == 1 && world.getBeepersOnCorner(x, y) == 0)) {
						return "(" + x + ", " + y + ")" + " should contain beeper";
					}
					if ((x % 2 == 0 && world.getBeepersOnCorner(x, y) == 1)) {
						return "(" + x + ", " + y + ")" + " should not contain beeper";
					}
				} else {
					if ((x % 2 == 0 && world.getBeepersOnCorner(x, y) == 0)) {
						return "(" + x + ", " + y + ")" + " should contain beeper";
					}
					if ((x % 2 == 1 && world.getBeepersOnCorner(x, y) == 1)) {
						return "(" + x + ", " + y + ")" + " should not contain beeper";
					}
				}
			}
		}
		return null;
	}
}
