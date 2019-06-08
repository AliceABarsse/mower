/**
 * 
 */
package com.alice.mower;

import com.alice.mower.environment.Coordinate;
import com.alice.mower.environment.Lawn;
import com.alice.mower.environment.Position;

/**
 * @author alicebarsse
 * 
 * The mower is responsible for knowing how to move across a lawn.
 */
public final class Mower {
	
	private final Lawn lawn;
	
	private Position currentPosition;
	
	/**
	 * Build a new mower on a given lawn and a given position.
	 * @param lawn the terrain the mower will be moving on. Must not be null.
	 * @param initialPosition initial position. Must not be null.
	 */
	public Mower(Lawn lawn, Position initialPosition) {
		if (lawn == null || initialPosition == null) {
			throw new IllegalArgumentException("Mower parameters cannot be null");
		}
		this.lawn = lawn;
		this.currentPosition = initialPosition;
		System.out.println("Mower built at " + currentPosition);
	}

	public boolean move() {
		//v1 just move forward in current direction
		Coordinate nextCoordinate = new Coordinate(currentPosition.getCoordinate().getAbscisse(), currentPosition.getCoordinate().getOrdonnée() +1);
		if (lawn.exists(nextCoordinate)) {
			currentPosition = new Position(nextCoordinate, currentPosition.getOrientation());
			System.out.println("Mower moved to " + currentPosition );
			return true;
		}
		return false;
	}

	public Position getPosition() {
		return currentPosition;
	}

}
