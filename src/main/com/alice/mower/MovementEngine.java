/**
 * 
 */
package com.alice.mower;

import com.alice.mower.environment.Coordinate;
import com.alice.mower.environment.Orientation;
import com.alice.mower.environment.Position;

/**
 * @author alicebarsse
 *
 *This class is responsible for how the mower moves.
 */
public final class MovementEngine {

	/**
	 * This constructor should not be called.
	 */
	private MovementEngine() {
		
	}
	
	public static Position getPositionAfter(Position initialPosition, Movement movement) {
		
		if (movement == null || initialPosition == null) {
			return initialPosition;
		}
		
		switch (movement) {
		case FORWARD:
			return moveForward(initialPosition);
		case LEFT:
			return turnLeft(initialPosition);
		case RIGHT:
			return turnRight(initialPosition);
		default:
			System.err.println("Unhandled movement: " + movement);
		}
		
		return initialPosition;
		
	}

	/**
	 * Keep initial Coordinates, but change orientation to go right.
	 * @param initialPosition. Must not be null.
	 * @return position after turning right.
	 */
	private static Position turnRight(Position initialPosition) {
		return new Position(initialPosition.getCoordinate(), initialPosition.getOrientation().turnRight());
	}

	/**
	 * 
	 * @param initialPosition Must not be null.
	 * @return position after turning left.
	 */
	private static Position turnLeft(Position initialPosition) {
		return new Position(initialPosition.getCoordinate(), initialPosition.getOrientation().turnLeft());
	}

	/**
	 * @param initialPosition Must not be null.
	 * @return
	 */
	private static Position moveForward(Position initialPosition) {
		Coordinate initialCoordinate = initialPosition.getCoordinate();
		Coordinate newCoordinate = initialCoordinate;
		final Orientation initialOrientation = initialPosition.getOrientation();
		
		switch (initialOrientation) {
		case NORTH:
			// increment y
			newCoordinate = new Coordinate(initialCoordinate.getAbscisse(), initialCoordinate.getOrdonnée()+1);
			break;
		case WEST:
			// decrement x
			newCoordinate = new Coordinate(initialCoordinate.getAbscisse()-1, initialCoordinate.getOrdonnée());
			break;
		case SOUTH:
			// decrement y
			newCoordinate = new Coordinate(initialCoordinate.getAbscisse(), initialCoordinate.getOrdonnée()-1);
			break;
		case EAST:
			// increment x
			newCoordinate = new Coordinate(initialCoordinate.getAbscisse()+1, initialCoordinate.getOrdonnée());
			break;
		default:
			newCoordinate = initialCoordinate;
			System.err.println("Unexpected orientation");
			break;
		}
		return new Position(newCoordinate, initialOrientation);
	}
}
