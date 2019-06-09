/**
 * 
 */
package com.alice.mower;

import java.util.List;

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

	/**
	 * 
	 * @param movement
	 * @return true if the move was possible, false if the mower could not move in the requested way.
	 */
	public boolean move(Movement movement) {
		System.out.println("Moving " + movement);
		Position nextPosition = MovementEngine.getPositionAfter(currentPosition, movement);
		
		if (lawn.exists(nextPosition.getCoordinate())) {
			currentPosition = nextPosition;
			System.out.println("Mower moved to " + currentPosition );
			return true;
		}
		return false;
	}

	public Position getPosition() {
		return currentPosition;
	}

	/**
	 * Give the mower a sequence of moves to perform.
	 * @param moves
	 */
	public void move(List<Movement> moves) {
		for (Movement m: moves) {
			move(m);
		}
	}

}
