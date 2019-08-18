package stanford.karel;

import java.awt.Point;
import java.awt.event.ActionEvent;

import acm.program.Program;
import acm.util.ErrorException;

public class Karel extends Program {
	private static final int NORTH = KarelWorld.NORTH;
	private static final int EAST = KarelWorld.EAST;
	private static final int SOUTH = KarelWorld.SOUTH;
	private static final int WEST = KarelWorld.WEST;
	
	private int x;
	private int y;
	private int dir;
	private int beepers;
	private KarelWorld world;
	
	public Karel() {
		x = 1;
		y = 1;
		dir = EAST;
		world = null;
	}
	
	
	public void run() {
		/* Empty */
	}

	public void move() {
		checkWorld("move");
		if (world.checkWall(x, y, dir)) {
			throw new ErrorException("Karel is blocked");
		}
		setLocation(KarelWorld.adjacentPoint(x, y, dir));
	}
	
	public void turnLeft() {
		checkWorld("turnLeft");
		setDirection(KarelWorld.leftFrom(dir));
	}

	public void pickBeeper() {
		checkWorld("pickBeeper");
		int nb = world.getBeepersOnCorner(x, y);
		if (nb < 1) {
			throw new ErrorException("pickBeeper: No beepers on this corner");
		}
		world.setBeepersOnCorner(x, y, KarelWorld.adjustBeepers(nb, -1));
		setBeepersInBag(KarelWorld.adjustBeepers(getBeepersInBag(), +1));
	}

	public void putBeeper(){
		checkWorld("putBeeper");
		int nBag = getBeepersInBag();
		if (nBag < 1) {
			throw new ErrorException("putBeeper: No beepers in bag");
		}
		int nCorner = world.getBeepersOnCorner(x, y);
		world.setBeepersOnCorner(x, y, KarelWorld.adjustBeepers(nCorner, +1));
		setBeepersInBag(KarelWorld.adjustBeepers(nBag, -1));
	}

	public boolean frontIsClear() {
		checkWorld("frontIsClear");
		return !world.checkWall(x, y, dir);
	}

	public boolean frontIsBlocked() {
		checkWorld("frontIsBlocked");
		return world.checkWall(x, y, dir);
	}

	public boolean leftIsClear() {
		checkWorld("leftIsClear");
		return !world.checkWall(x, y, KarelWorld.leftFrom(dir));
	}

	public boolean leftIsBlocked() {
		checkWorld("leftIsBlocked");
		return world.checkWall(x, y, KarelWorld.leftFrom(dir));
	}

	public boolean rightIsClear() {
		checkWorld("rightIsClear");
		return !world.checkWall(x, y, KarelWorld.rightFrom(dir));
	}

	public boolean rightIsBlocked() {
		checkWorld("rightIsBlocked");
		return world.checkWall(x, y, KarelWorld.rightFrom(dir));
	}

	public boolean beepersPresent() {
		checkWorld("beepersPresent");
		return world.getBeepersOnCorner(x, y) > 0;
	}

	public boolean noBeepersPresent() {
		checkWorld("noBeepersPresent");
		return world.getBeepersOnCorner(x, y) == 0;
	}

	public boolean beepersInBag() {
		checkWorld("beepersInBag");
		return getBeepersInBag() > 0;
	}

	public boolean noBeepersInBag() {
		checkWorld("noBeepersInBag");
		return getBeepersInBag() == 0;
	}

	public boolean facingNorth() {
		checkWorld("facingNorth");
		return dir == NORTH;
	}

	public boolean facingEast() {
		checkWorld("facingEast");
		return dir == EAST;
	}

	public boolean facingSouth() {
		checkWorld("facingSouth");
		return dir == SOUTH;
	}

	public boolean facingWest() {
		checkWorld("facingWest");
		return dir == WEST;
	}

	public boolean notFacingNorth() {
		checkWorld("notFacingNorth");
		return dir != NORTH;
	}

	public boolean notFacingEast() {
		checkWorld("notFacingEast");
		return dir != EAST;
	}

	public boolean notFacingSouth() {
		checkWorld("notFacingSouth");
		return dir != SOUTH;
	}

	public boolean notFacingWest() {
		checkWorld("notFacingWest");
		return dir != WEST;
	}
	
	protected void checkWorld(String caller) {
		if (world == null) {
			throw new ErrorException(caller + ": Karel is not living in a world");
		}
	}
	
	public Point getLocation() {
		return new Point(x, y);
	}

	public void setLocation(Point pt) {
		setLocation(pt.x, pt.y);
	}
	
	public KarelWorld getWorld() {
		return world;
	}
	
	public void setWorld(KarelWorld world)  {
		this.world = world;
	}
	
	public void setBeepersInBag(int nBeepers) {
		beepers = nBeepers;
	}
	
	public void setDisplayOneFlag(boolean flag) {
		/* Empty */
	}
	
	public void setDirection(int dir) {
		this.dir = dir;
	}
	
	public void setLocation(int x, int y) {
		if (world != null) {
			if (world.outOfBounds(x, y)) {
				throw new ErrorException("setLocation: Out of bounds");
			}
		}
		this.x = x;
		this.y = y;
	}

	public int getDirection() {
		return dir;
	}

	public int getBeepersInBag() {
		return (beepers);
	}

	
	@Override
	public boolean menuAction(ActionEvent event) {
		System.out.println("DEBUGDEBUG: Karel.menuAction");
		return super.menuAction(event);
	}	
	
	public static void main(String[] args) {

	}

	
}
