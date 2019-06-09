/**
 * 
 */
package com.alice.mower;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alice.mower.environment.Coordinate;
import com.alice.mower.environment.Orientation;
import com.alice.mower.environment.Position;

/**
 * @author alicebarsse
 *
 *Unit tests for movement engine.
 */
public class MovementEngineTest {

	private static final Coordinate COORDS_INITIAL_4_4 = new Coordinate(4, 4);

	@Test
	public void nullParams() {
		assertNull(MovementEngine.getPositionAfter(null, null));
	}
	
	@Test
	public void nullMovementParam() {
		Position initialPosition = new Position(COORDS_INITIAL_4_4, Orientation.NORTH);
		assertEquals(initialPosition, MovementEngine.getPositionAfter(initialPosition, null));
	}
	
	@Test
	public void nullPositionParam() {
		assertNull(MovementEngine.getPositionAfter(null, Movement.FORWARD));
		assertNull(MovementEngine.getPositionAfter(null, Movement.LEFT));
		assertNull(MovementEngine.getPositionAfter(null, Movement.RIGHT));
	}

	@Test
	public void moveForwardFromNorthOrientation() {
		Position initialPosition = new Position(COORDS_INITIAL_4_4, Orientation.NORTH);
		Position expectedPosition = new Position(new Coordinate(4, 5), Orientation.NORTH);
		assertEquals(expectedPosition, MovementEngine.getPositionAfter(initialPosition, Movement.FORWARD));
	}
	
	@Test
	public void moveLeftFromNorthOrientation() {
		Position initialPosition = new Position(COORDS_INITIAL_4_4, Orientation.NORTH);
		Position expectedPosition = new Position(COORDS_INITIAL_4_4, Orientation.WEST);
		assertEquals(expectedPosition, MovementEngine.getPositionAfter(initialPosition, Movement.LEFT));
	}
	
	@Test
	public void moveRightFromNorthOrientation() {
		Position initialPosition = new Position(COORDS_INITIAL_4_4, Orientation.NORTH);
		Position expectedPosition = new Position(COORDS_INITIAL_4_4, Orientation.EAST);
		assertEquals(expectedPosition, MovementEngine.getPositionAfter(initialPosition, Movement.RIGHT));
	}
	
	@Test
	public void moveForwardFromSouthOrientation() {
		Position initialPosition = new Position(COORDS_INITIAL_4_4, Orientation.SOUTH);
		Position expectedPosition = new Position(new Coordinate(4, 3), Orientation.SOUTH);
		assertEquals(expectedPosition, MovementEngine.getPositionAfter(initialPosition, Movement.FORWARD));
	}
	
	@Test
	public void moveLeftFromSouthOrientation() {
		Position initialPosition = new Position(COORDS_INITIAL_4_4, Orientation.SOUTH);
		Position expectedPosition = new Position(COORDS_INITIAL_4_4, Orientation.EAST);
		assertEquals(expectedPosition, MovementEngine.getPositionAfter(initialPosition, Movement.LEFT));
	}
	
	@Test
	public void moveRightFromSouthOrientation() {
		Position initialPosition = new Position(COORDS_INITIAL_4_4, Orientation.SOUTH);
		Position expectedPosition = new Position(COORDS_INITIAL_4_4, Orientation.WEST);
		assertEquals(expectedPosition, MovementEngine.getPositionAfter(initialPosition, Movement.RIGHT));
	}
	
	@Test
	public void moveForwardFromWestOrientation() {
		Position initialPosition = new Position(COORDS_INITIAL_4_4, Orientation.WEST);
		Position expectedPosition = new Position(new Coordinate(3, 4), Orientation.WEST);
		assertEquals(expectedPosition, MovementEngine.getPositionAfter(initialPosition, Movement.FORWARD));
	}
	
	@Test
	public void moveLeftFromWestOrientation() {
		Position initialPosition = new Position(COORDS_INITIAL_4_4, Orientation.WEST);
		Position expectedPosition = new Position(COORDS_INITIAL_4_4, Orientation.SOUTH);
		assertEquals(expectedPosition, MovementEngine.getPositionAfter(initialPosition, Movement.LEFT));
	}
	
	@Test
	public void moveRightFromWestOrientation() {
		Position initialPosition = new Position(COORDS_INITIAL_4_4, Orientation.WEST);
		Position expectedPosition = new Position(COORDS_INITIAL_4_4, Orientation.NORTH);
		assertEquals(expectedPosition, MovementEngine.getPositionAfter(initialPosition, Movement.RIGHT));
	}
	
	@Test
	public void moveForwardFromEastOrientation() {
		Position initialPosition = new Position(COORDS_INITIAL_4_4, Orientation.EAST);
		Position expectedPosition = new Position(new Coordinate(5, 4), Orientation.EAST);
		assertEquals(expectedPosition, MovementEngine.getPositionAfter(initialPosition, Movement.FORWARD));
	}
	
	@Test
	public void moveLeftFromEastOrientation() {
		Position initialPosition = new Position(COORDS_INITIAL_4_4, Orientation.EAST);
		Position expectedPosition = new Position(COORDS_INITIAL_4_4, Orientation.NORTH);
		assertEquals(expectedPosition, MovementEngine.getPositionAfter(initialPosition, Movement.LEFT));
	}
	
	@Test
	public void moveRightFromEastOrientation() {
		Position initialPosition = new Position(COORDS_INITIAL_4_4, Orientation.EAST);
		Position expectedPosition = new Position(COORDS_INITIAL_4_4, Orientation.SOUTH);
		assertEquals(expectedPosition, MovementEngine.getPositionAfter(initialPosition, Movement.RIGHT));
	}
}
