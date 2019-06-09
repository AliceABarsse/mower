/**
 * 
 */
package com.alice.mower.environment;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author alicebarsse
 * 
 *         Test class for Lawn {@link com.alice.mower.environment.Lawn}
 *
 */
public class LawnTest {

	private static final Coordinate DEFAULT_UPPER_RIGHT_CORNER = new Coordinate(5, 5);

	/**
	 * Test method for
	 * {@link com.alice.mower.environment.Lawn#Lawn(Coordinate)}.
	 * 
	 */
	@Test
	public void newLawn() {
		Lawn lawn = new Lawn(DEFAULT_UPPER_RIGHT_CORNER);

		assertNotNull(lawn);
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.environment.Lawn#Lawn(Coordinate)}.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void nullParamNewLawn() {
		new Lawn(null);
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.environment.Lawn#exists(Coordinate)}
	 */
	@Test
	public void existsNominal() {
		Lawn lawn = new Lawn(DEFAULT_UPPER_RIGHT_CORNER);

		assertTrue(lawn.exists(DEFAULT_UPPER_RIGHT_CORNER));
		assertTrue(lawn.exists(new Coordinate(0, 0)));
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.environment.Lawn#exists(Coordinate)}
	 */
	@Test
	public void existsNull() {
		Lawn lawn = new Lawn(DEFAULT_UPPER_RIGHT_CORNER);

		assertFalse(lawn.exists(null));
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.environment.Lawn#exists(Coordinate)}
	 */
	@Test
	public void existsOutOfBounds() {
		Lawn lawn = new Lawn(DEFAULT_UPPER_RIGHT_CORNER);

		assertFalse(lawn
				.exists(new Coordinate(DEFAULT_UPPER_RIGHT_CORNER.getX() + 1, DEFAULT_UPPER_RIGHT_CORNER.getY() + 1)));
		assertFalse(lawn.exists(new Coordinate(-1, -1)));
	}

}
