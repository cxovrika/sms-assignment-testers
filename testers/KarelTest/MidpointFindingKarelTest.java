

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import stanford.karel.KarelWorld;

public class MidpointFindingKarelTest {

	@Test
	public void test1x1() {
		MidpointFindingKarel midpointFindingKarel = new MidpointFindingKarel();
		midpointFindingKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(1, 1);
		midpointFindingKarel.setWorld(newWorld);
		midpointFindingKarel.run();
		assertTrue("Board 1x1 - Only midpoint should be filled", isOnlyMidpointFilled(newWorld));
		assertTrue("Board 1x1 - Karel finally should be positioned on midpoint", isKarelCorrectlyPositioned(midpointFindingKarel));
	}
	
	@Test
	public void test2x2() {
		MidpointFindingKarel midpointFindingKarel = new MidpointFindingKarel();
		midpointFindingKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(2, 2);
		midpointFindingKarel.setWorld(newWorld);
		midpointFindingKarel.run();
		assertTrue("Board 2x2 - Only midpoint should be filled", isOnlyMidpointFilled(newWorld));
		assertTrue("Board 2x2 - Karel finally should be positioned on midpoint", isKarelCorrectlyPositioned(midpointFindingKarel));
	}
	
	@Test
	public void testnxnOdd() {
		MidpointFindingKarel midpointFindingKarel = new MidpointFindingKarel();
		midpointFindingKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(5, 5);
		midpointFindingKarel.setWorld(newWorld);
		midpointFindingKarel.run();
		assertTrue("Board 5x5 - Only midpoint should be filled", isOnlyMidpointFilled(newWorld));
		assertTrue("Board 5x5 - Karel finally should be positioned on midpoint", isKarelCorrectlyPositioned(midpointFindingKarel));
	}
	
	@Test
	public void testnxnEven() {
		MidpointFindingKarel midpointFindingKarel = new MidpointFindingKarel();
		midpointFindingKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(20, 20);
		midpointFindingKarel.setWorld(newWorld);
		midpointFindingKarel.run();
		assertTrue("Board 20x20 - Only midpoint should be filled", isOnlyMidpointFilled(newWorld));
		assertTrue("Board 20x20 - Karel finally should be positioned on midpoint", isKarelCorrectlyPositioned(midpointFindingKarel));
	}
	
	@Test
	public void testnxmOdd() {
		MidpointFindingKarel midpointFindingKarel = new MidpointFindingKarel();
		midpointFindingKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(7, 9);
		midpointFindingKarel.setWorld(newWorld);
		midpointFindingKarel.run();
		assertTrue("Board 7x9 - Only midpoint should be filled", isOnlyMidpointFilled(newWorld));
		assertTrue("Board 7x9 - Karel finally should be positioned on midpoint", isKarelCorrectlyPositioned(midpointFindingKarel));
	}
	
	@Test
	public void testnxmEven() {
		MidpointFindingKarel midpointFindingKarel = new MidpointFindingKarel();
		midpointFindingKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(8, 12);
		midpointFindingKarel.setWorld(newWorld);
		midpointFindingKarel.run();
		assertTrue("Board 8x12 - Only midpoint should be filled", isOnlyMidpointFilled(newWorld));
		assertTrue("Board 8x12 - Karel finally should be positioned on midpoint", isKarelCorrectlyPositioned(midpointFindingKarel));
	}
	
	boolean isOnlyMidpointFilled(KarelWorld world) {
		int numRows = world.getRows();
		int numCols = world.getColumns();
		for (int x = 1; x <= numCols; x++) {
			for (int y = 2; y <= numRows; y++) {
				if (world.getBeepersOnCorner(x, y) != 0) {
					return false;
				}
			}
		}
		return isFirstRowCorrectlyFilled(world);
	}
	
	boolean isKarelCorrectlyPositioned(MidpointFindingKarel karel) {
		KarelWorld world = karel.getWorld();
		int numCols = world.getColumns();
		if (numCols % 2 == 1) {
			return karel.getLocation().x == numCols/2 + 1;
		} else {
			return karel.getLocation().x == numCols/2 || karel.getLocation().x == numCols/2 + 1;
		}	
	}
	
	private boolean isFirstRowCorrectlyFilled(KarelWorld world) {
		int numCols = world.getColumns();
		for (int x = 1; x <= numCols; x++) {
			if (x == numCols/2 + 1 || (numCols % 2 == 0 && x == numCols/2)) {
				continue;
			}
			if (world.getBeepersOnCorner(x, 1) != 0) {
				return false;
			}
		}
		if (numCols % 2 == 1) {
			return world.getBeepersOnCorner(numCols/2 + 1, 1) == 1;
		} else {
			return (world.getBeepersOnCorner(numCols/2, 1) == 1 && world.getBeepersOnCorner(numCols/2 + 1, 1) == 0) || 
					(world.getBeepersOnCorner(numCols/2, 1) == 0 && world.getBeepersOnCorner(numCols/2 + 1, 1) == 1);
		}		
	}

}
