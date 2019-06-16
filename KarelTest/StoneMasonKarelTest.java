import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import stanford.karel.KarelWorld;

public class StoneMasonKarelTest {
	@Test
	public void testSimple() {
		StoneMasonKarel stoneMasonKarel = new StoneMasonKarel();
		stoneMasonKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(13, 8);
		
		for(int x = 1; x < 13; x+=4) {
			newWorld.setWall(x, 6, KarelWorld.SOUTH);
			newWorld.setWall(x+1, 7, KarelWorld.SOUTH);
			newWorld.setWall(x+2, 8, KarelWorld.SOUTH);
			newWorld.setWall(x+3, 7, KarelWorld.SOUTH);
		}
		newWorld.setWall(13, 6, KarelWorld.SOUTH);
		
		for(int x = 1; x < 13; x+=4) {
			newWorld.setWall(x, 6, KarelWorld.EAST);
			newWorld.setWall(x+1, 7, KarelWorld.EAST);
			newWorld.setWall(x+2, 7, KarelWorld.EAST);
			newWorld.setWall(x+3, 6, KarelWorld.EAST);
		}
		
		newWorld.setBeepersOnCorner(1, 4, 1);
		newWorld.setBeepersOnCorner(1, 5, 1);
		newWorld.setBeepersOnCorner(5, 1, 1);
		newWorld.setBeepersOnCorner(5, 2, 1);
		newWorld.setBeepersOnCorner(5, 4, 1);
		newWorld.setBeepersOnCorner(9, 5, 1);
		newWorld.setBeepersOnCorner(13, 1, 1);
		newWorld.setBeepersOnCorner(13, 3, 1);
		newWorld.setBeepersOnCorner(13, 5, 1);
		
		stoneMasonKarel.setWorld(newWorld);
		stoneMasonKarel.run();
		for (int x = 1; x <= 13; x++) {
			for (int y = 1; y <= 8; y++) {
				if ((x - 1) % 4 == 0 && y <= 5) {
					if (newWorld.getBeepersOnCorner(x, y) != 1) {
						fail("Beeper was not found");
					} 
				} else {
					if (newWorld.getBeepersOnCorner(x, y) != 0) {
						fail("Extra beeper was found");
					}
				}
			}
		}
	}
	
	@Test
	public void testDifferentHeights() {
		StoneMasonKarel stoneMasonKarel = new StoneMasonKarel();
		stoneMasonKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(13, 12);
		
		newWorld.setWall(1, 6, KarelWorld.SOUTH);
		newWorld.setWall(5, 8, KarelWorld.SOUTH);
		newWorld.setWall(9, 2, KarelWorld.SOUTH);
		
		stoneMasonKarel.setWorld(newWorld);
		stoneMasonKarel.run();
		for (int x = 1; x <= 13; x++) {
			for (int y = 1; y <= 10; y++) {
				int limit = 10;
				if (x == 1) {
					limit = 5;
				} else if(x == 5) {
					limit = 7;
				} else if(x == 9) {
					limit = 1;
				} 
				if ((x - 1) % 4 == 0 && y <= limit) {
					if (newWorld.getBeepersOnCorner(x, y) != 1) {
						fail("Beeper was not found");
					} 
				} else {
					if (newWorld.getBeepersOnCorner(x, y) != 0) {
						fail("Extra beeper was found");
					}
				}
			}
		}
	}
	
	@Test
	public void testFull() {
		StoneMasonKarel stoneMasonKarel = new StoneMasonKarel();
		stoneMasonKarel.setBeepersInBag(KarelWorld.INFINITE);
		KarelWorld newWorld = new KarelWorld();
		newWorld.init(13, 10);
		stoneMasonKarel.setWorld(newWorld);
		stoneMasonKarel.run();
		for (int x = 1; x <= 13; x++) {
			for (int y = 1; y <= 10; y++) {
				if ((x - 1) % 4 == 0) {
					if (newWorld.getBeepersOnCorner(x, y) != 1) {
						fail("Beeper was not found");
					} 
				} else {
					if (newWorld.getBeepersOnCorner(x, y) != 0) {
						fail("Extra beeper was found");
					}
				}
			}
		}
	}
}
