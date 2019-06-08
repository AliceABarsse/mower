package com.alice.mower;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alice.mower.Controller;
import com.alice.mower.Mower;
import com.alice.mower.environment.Coordinate;
import com.alice.mower.environment.Lawn;
import com.alice.mower.environment.Orientation;
import com.alice.mower.environment.Position;

public class MowerTest {

	final static Orientation INITIAL_ORIENTATION = Orientation.N;
	final static Position TEST_INITIAL_POSITION = new Position(Lawn.LOWER_LEFT_COORDINATE, INITIAL_ORIENTATION);
	final static Lawn TEST_LAWN = new Lawn(Controller.LAWN_UPPER_LEFT_COORDINATE);

	@Test(expected=IllegalArgumentException.class)
	public void nullParamsNewMower() {
		new Mower(null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullInitialPositionNewMower() {
		new Mower(TEST_LAWN, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullLawnNewMower() {
		new Mower(null, TEST_INITIAL_POSITION);
	}
	
	@Test
	public void getPositionInitialValue() {
		Mower testedMower = buildTestMower();
		assertEquals(Lawn.LOWER_LEFT_COORDINATE, testedMower.getPosition().getCoordinate());
	}

	private Mower buildTestMower() {
		return new Mower(TEST_LAWN, TEST_INITIAL_POSITION);
	}
	
	@Test
	public void getPositionAfterOneMove() {
		Mower testedMower = buildTestMower();
		
		//move mower to next square in grid
		boolean moveResult = testedMower.move();
		
		// expect move straight in initial direction
		assertTrue(moveResult);
		Position expectedPosition = new Position(new Coordinate(0, 1), INITIAL_ORIENTATION);
		assertEquals(expectedPosition, testedMower.getPosition());
	}
	
	@Test
	public void getPositionAfterMaxMoves() {
		Mower testedMower = buildTestMower();
		// will move to last possible position
		int totalMoves = Controller.LAWN_UPPER_LEFT_COORDINATE.getOrdonnée();
		boolean lastMoveSuccessful = false;
		
		for (int i = 0; i < totalMoves; i++) {
			lastMoveSuccessful = testedMower.move();
		}
		
		assertTrue(lastMoveSuccessful);
		// expect move straight in initial direction
		Position expectedPosition = new Position(new Coordinate(0, Controller.LAWN_UPPER_LEFT_COORDINATE.getOrdonnée()), INITIAL_ORIENTATION);
		assertEquals(expectedPosition, testedMower.getPosition());
	}
	
	@Test
	public void getPositionAfterExtraMoves() {
		Mower testedMower = buildTestMower();
		// attempt to move to last position + 1
		int totalMoves = Controller.LAWN_UPPER_LEFT_COORDINATE.getOrdonnée() + 1;
		
		boolean lastMoveSuccessful = false;
		for (int i = 0; i < totalMoves; i++) {
			lastMoveSuccessful = testedMower.move();
		}
		
		assertFalse(lastMoveSuccessful);
		// expect move straight in initial direction
		Position expectedPosition = new Position(new Coordinate(0, Controller.LAWN_UPPER_LEFT_COORDINATE.getOrdonnée()), INITIAL_ORIENTATION);
		assertEquals(expectedPosition, testedMower.getPosition());
	}

}
