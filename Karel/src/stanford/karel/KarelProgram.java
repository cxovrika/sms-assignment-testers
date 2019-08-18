package stanford.karel;

import acm.program.Program;

public class KarelProgram extends Program {

	public static final int NORTH = KarelWorld.NORTH;
	public static final int EAST = KarelWorld.EAST;
	public static final int SOUTH = KarelWorld.SOUTH;
	public static final int WEST = KarelWorld.WEST;
	
	public static final int INFINITE = KarelWorld.INFINITE;
	
	private KarelWorld world;
	
	public KarelProgram() {
		world = createWorld();
		world.init(10, 10);
	}
	
	public void main() {
		/* Empty */
	}
	
	public KarelWorld getWorld() {
		return world;
	}
	
	public void setWorld(KarelWorld world) {
		this.world = world;
	}
	
	protected KarelWorld createWorld() {
		return new KarelWorld();
	}
}
