import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import stanford.karel.KarelWorld;

public class CollectNewspaperKarelTest {

	@Test
	public void test() {
		CollectNewspaperKarel collectNewspaperKarel = new CollectNewspaperKarel();
		collectNewspaperKarel.setBeepersInBag(0);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(7, 5);
	
		newWorld.setBeepersOnCorner(6, 3, 1);
		newWorld.setWall(3, 5, KarelWorld.SOUTH);
		newWorld.setWall(4, 5, KarelWorld.SOUTH);
		newWorld.setWall(5, 5, KarelWorld.SOUTH);

		newWorld.setWall(3, 4, KarelWorld.WEST);
		newWorld.setWall(3, 3, KarelWorld.WEST);
		newWorld.setWall(3, 2, KarelWorld.WEST);

		newWorld.setWall(3, 2, KarelWorld.SOUTH);
		newWorld.setWall(4, 2, KarelWorld.SOUTH);
		newWorld.setWall(5, 2, KarelWorld.SOUTH);

		newWorld.setWall(6, 4, KarelWorld.WEST);
		newWorld.setWall(6, 2, KarelWorld.WEST);

		collectNewspaperKarel.setWorld(newWorld);
		collectNewspaperKarel.setLocation(3, 4);
		collectNewspaperKarel.run();

		int numRows = newWorld.getRows();
		int numCols = newWorld.getColumns();
		for (int x = 1; x < numCols; x++) {
			for (int y = 1; y < numRows; y++) {
				assertTrue("(" + x + ", " + y + ")" + " should not contain beeper", 0 == newWorld.getBeepersOnCorner(x, y));
			}
		}
		assertTrue("Karel's final destination should be (3,4), facing East", 3 == collectNewspaperKarel.getLocation().x);
		assertTrue("Karel's final destination should be (3,4), facing East", 4 == collectNewspaperKarel.getLocation().y);
		assertTrue("Karel's final destination should be (3,4), facing East", KarelWorld.EAST == collectNewspaperKarel.getDirection());
	}

}
