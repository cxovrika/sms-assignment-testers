package stanford.karel;

import java.awt.Point;

public class KarelWorld {
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public static final int INFINITE = 99999999;
	public static final int PLUS1 = -1;
	public static final int MINUS1 = -2;
	
	private Corner[][] map;
	private int cols;
	private int rows;
	
	/* Constructor */
	public KarelWorld() {
	}

	public void init(int cols, int rows) {
		this.cols = cols;
		this.rows = rows;
		map = new Corner[cols + 2][rows + 2];
		for (int x = 1; x <= cols + 1; x++) {
			for (int y = 1; y <= rows + 1; y++) {
				map[x][y] = new Corner();
				map[x][y].wallSouth = (y == 1) || (y == rows + 1);
				map[x][y].wallWest = (x == 1) || (x == cols + 1);
			}
		}
	}
	
	public int getColumns() {
		return (cols);
	}

	public int getRows() {
		return (rows);
	}
	
	public boolean outOfBounds(Point pt) {
		return outOfBounds(pt.x, pt.y);
	}

	public boolean outOfBounds(int x, int y) {
		return (x < 1 || x > cols || y < 1 || y > rows);
	}
	
	public int getBeepersOnCorner(Point pt) {
		return getBeepersOnCorner(pt.x, pt.y);
	}

	public int getBeepersOnCorner(int x, int y) {
		return ((map[x][y]).nBeepers);
	}
	
	public void setBeepersOnCorner(Point pt, int nBeepers) {
		map[pt.x][pt.y].nBeepers = nBeepers;
	}

	public void setBeepersOnCorner(int x, int y, int nBeepers) {
		setBeepersOnCorner(new Point(x, y), nBeepers);
	}
	
	public static int adjustBeepers(int nBeepers, int delta) {
		if (nBeepers == INFINITE)
			return INFINITE;
		return nBeepers + delta;
	}

	public static int setBeepers(int nBeepers, int delta) {
		if (delta == INFINITE)
			return INFINITE;
		if (delta == PLUS1) {
			return (nBeepers == INFINITE) ? INFINITE : nBeepers + 1;
		}
		if (delta == MINUS1) {
			return (nBeepers == INFINITE) ? INFINITE : Math
					.max(0, nBeepers - 1);
		}
		return delta;
	}
	
	public boolean checkWall(Point pt, int dir) {
		return checkWall(pt.x, pt.y, dir);
	}

	public boolean checkWall(int x, int y, int dir) {
		switch (dir) {
		case SOUTH:
			return (map[x][y].wallSouth);
		case WEST:
			return (map[x][y].wallWest);
		case NORTH:
			return (map[x][y + 1].wallSouth);
		case EAST:
			return (map[x + 1][y].wallWest);
		}
		return (false);
	}

	public void setWall(Point pt, int dir) {
		switch (dir) {
		case SOUTH:
			map[pt.x][pt.y].wallSouth = true;
			break;
		case WEST:
			map[pt.x][pt.y].wallWest = true;
			break;
		case NORTH:
			map[pt.x][pt.y + 1].wallSouth = true;
			break;
		case EAST:
			map[pt.x + 1][pt.y].wallWest = true;
			break;
		}
	}

	public void setWall(int x, int y, int dir) {
		setWall(new Point(x, y), dir);
	}

	public void clearWall(Point pt, int dir) {
		switch (dir) {
		case SOUTH:
			map[pt.x][pt.y].wallSouth = false;
			break;
		case WEST:
			map[pt.x][pt.y].wallWest = false;
			break;
		case NORTH:
			map[pt.x][pt.y + 1].wallSouth = false;
			break;
		case EAST:
			map[pt.x + 1][pt.y].wallWest = false;
			break;
		}
	}

	public void clearWall(int x, int y, int dir) {
		clearWall(new Point(x, y), dir);
	}
	

	public static String directionName(int dir) {
		switch (dir) {
		case SOUTH:
			return "SOUTH";
		case WEST:
			return "WEST";
		case NORTH:
			return "NORTH";
		case EAST:
			return "EAST";
		}
		return null;
	}
	
	public static int leftFrom(int dir) {
		switch (dir) {
		case SOUTH:
			return EAST;
		case WEST:
			return SOUTH;
		case NORTH:
			return WEST;
		case EAST:
			return NORTH;
		}
		return -1;
	}

	public static int rightFrom(int dir) {
		switch (dir) {
		case SOUTH:
			return WEST;
		case WEST:
			return NORTH;
		case NORTH:
			return EAST;
		case EAST:
			return SOUTH;
		}
		return -1;
	}

	public static int oppositeDirection(int dir) {
		switch (dir) {
		case SOUTH:
			return NORTH;
		case WEST:
			return EAST;
		case NORTH:
			return SOUTH;
		case EAST:
			return WEST;
		}
		return -1;
	}
	
	public static Point adjacentPoint(Point pt, int dir) {
		return adjacentPoint(pt.x, pt.y, dir);
	}

	public static Point adjacentPoint(int x, int y, int dir) {
		switch (dir) {
		case SOUTH:
			return new Point(x, y - 1);
		case WEST:
			return new Point(x - 1, y);
		case NORTH:
			return new Point(x, y + 1);
		case EAST:
			return new Point(x + 1, y);
		}
		return null;
	}

	
	private static class Corner {
		public boolean wallSouth;
		public boolean wallWest;
		public int nBeepers;
	}

}
