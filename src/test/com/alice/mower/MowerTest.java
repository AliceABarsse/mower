package com.alice.mower;

import static org.junit.Assert.*;
import static com.alice.mower.environment.Orientation.*;

import org.junit.Test;

import com.alice.mower.Controller;
import com.alice.mower.Mower;
import com.alice.mower.environment.Coordinate;
import com.alice.mower.environment.Lawn;
import com.alice.mower.environment.Position;

public class MowerTest {

	final static Position TEST_INITIAL_POSITION_POINT_NORTH = new Position(Lawn.LOWER_LEFT_COORDINATE, NORTH);
	final static Lawn TEST_LAWN = new Lawn(Controller.LAWN_UPPER_LEFT_COORDINATE);

	@Test(expected = IllegalArgumentException.class)
	public void nullParamsNewMower() {
		new Mower(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullInitialPositionNewMower() {
		new Mower(TEST_LAWN, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullLawnNewMower() {
		new Mower(null, TEST_INITIAL_POSITION_POINT_NORTH);
	}

	@Test
	public void getPositionInitialValue() {
		Mower testedMower = buildTestMower();
		assertEquals(Lawn.LOWER_LEFT_COORDINATE, testedMower.getPosition().getCoordinate());
	}

	private Mower buildTestMower() {
		return new Mower(TEST_LAWN, TEST_INITIAL_POSITION_POINT_NORTH);
	}

	@Test
	public void getPositionAfterOneMoveForward() {
		Mower testedMower = buildTestMower();

		// move mower to next square in grid
		boolean moveResult = testedMower.move(Movement.FORWARD);

		// expect move straight in initial direction
		assertTrue(moveResult);
		Position expectedPosition = new Position(new Coordinate(0, 1), NORTH);
		assertEquals(expectedPosition, testedMower.getPosition());
	}

	@Test
	public void getPositionAfterMaxMovesForward() {
		Mower testedMower = buildTestMower();
		// will move to last possible position
		int totalMoves = Controller.LAWN_UPPER_LEFT_COORDINATE.getY();
		boolean lastMoveSuccessful = false;

		for (int i = 0; i < totalMoves; i++) {
			lastMoveSuccessful = testedMower.move(Movement.FORWARD);
		}

		assertTrue(lastMoveSuccessful);
		// expect move straight in initial direction
		Position expectedPosition = new Position(new Coordinate(0, Controller.LAWN_UPPER_LEFT_COORDINATE.getY()),
				NORTH);
		assertEquals(expectedPosition, testedMower.getPosition());
	}

	@Test
	public void getPositionAfterExtraForwardMoves() {
		Mower testedMower = buildTestMower();
		// attempt to move to last position + 1
		int totalMoves = Controller.LAWN_UPPER_LEFT_COORDINATE.getY() + 1;

		boolean lastMoveSuccessful = false;
		for (int i = 0; i < totalMoves; i++) {
			lastMoveSuccessful = testedMower.move(Movement.FORWARD);
		}

		assertFalse(lastMoveSuccessful);
		// expect move straight in initial direction
		Position expectedPosition = new Position(new Coordinate(0, Controller.LAWN_UPPER_LEFT_COORDINATE.getY()),
				NORTH);
		assertEquals(expectedPosition, testedMower.getPosition());
	}

	@Test
	public void getPositionAfterExtraSouthernMoves() {

		Mower testedMower = buildTestMower();
		// 180 degrees to go south
		testedMower.move(Movement.LEFT);
		testedMower.move(Movement.LEFT);

		// attempt to move south past the origin line
		int totalMoves = Controller.LAWN_UPPER_LEFT_COORDINATE.getY() + 1;

		boolean lastMoveSuccessful = false;
		for (int i = 0; i < totalMoves; i++) {
			lastMoveSuccessful = testedMower.move(Movement.FORWARD);
		}
		assertFalse(lastMoveSuccessful);
		// expect stayed in initial position, pointing south
		Position expectedPosition = new Position(new Coordinate(0, 0), SOUTH);
		assertEquals(expectedPosition, testedMower.getPosition());
	}

}
