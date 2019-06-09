package com.alice.mower.environment;

/**
 * @author alicebarsse
 *
 * Cardinal directions that the mower uses.
 */
public enum Orientation {
	
	NORTH,
	EAST,
	WEST,
	SOUTH;

	
	public Orientation turnRight() {
		switch(this){
		case NORTH:
			return Orientation.EAST;
		case EAST:
			return Orientation.SOUTH;
		case SOUTH:
			return Orientation.WEST;
		case WEST:
			return NORTH;
		default:
			System.err.println("Unexpected Orientation");
			return Orientation.NORTH;
		}
	}
	public Orientation turnLeft() {
		switch(this){
		case NORTH:
			return Orientation.WEST;
		case WEST:
			return Orientation.SOUTH;
		case SOUTH:
			return Orientation.EAST;
		case EAST:
			return NORTH;
		default:
			System.err.println("Unexpected Orientation");
			return Orientation.NORTH;
		}
	}

}
