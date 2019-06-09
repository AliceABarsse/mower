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
 *         This class is responsible for how the mower moves.
 */
public final class MovementEngine {

	/**
	 * This constructor should not be called.
	 */
	private MovementEngine() {

	}

	/**
	 * 
	 * @param initialPosition
	 *            Initial position. Must not be null
	 * @param movement
	 *            Requested movement. Must not be null
	 * @return new position after performing the move.
	 */
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
	 * 
	 * @param initialPosition.
	 *            Must not be null.
	 * @return position after turning right.
	 */
	private static Position turnRight(Position initialPosition) {
		return new Position(initialPosition.getCoordinate(), initialPosition.getOrientation().turnRight());
	}

	/**
	 * 
	 * @param initialPosition
	 *            Must not be null.
	 * @return position after turning left.
	 */
	private static Position turnLeft(Position initialPosition) {
		return new Position(initialPosition.getCoordinate(), initialPosition.getOrientation().turnLeft());
	}

	/**
	 * @param initialPosition
	 *            Must not be null.
	 * @return New position after moving forward.
	 */
	private static Position moveForward(Position initialPosition) {
		Coordinate initialCoordinate = initialPosition.getCoordinate();
		Coordinate newCoordinate = initialCoordinate;
		final Orientation initialOrientation = initialPosition.getOrientation();

		switch (initialOrientation) {
		case NORTH:
			// increment y
			newCoordinate = new Coordinate(initialCoordinate.getX(), initialCoordinate.getY() + 1);
			break;
		case WEST:
			// decrement x
			newCoordinate = new Coordinate(initialCoordinate.getX() - 1, initialCoordinate.getY());
			break;
		case SOUTH:
			// decrement y
			newCoordinate = new Coordinate(initialCoordinate.getX(), initialCoordinate.getY() - 1);
			break;
		case EAST:
			// increment x
			newCoordinate = new Coordinate(initialCoordinate.getX() + 1, initialCoordinate.getY());
			break;
		default:
			newCoordinate = initialCoordinate;
			System.err.println("Unexpected orientation");
			break;
		}
		return new Position(newCoordinate, initialOrientation);
	}
}
