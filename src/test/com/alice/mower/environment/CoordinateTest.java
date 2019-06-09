/**
 * 
 */
package com.alice.mower.environment;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author alicebarsse
 *
 */
public class CoordinateTest {

	/**
	 * Test method for
	 * {@link com.alice.mower.environment.Coordinate#equals(java.lang.Object)}.
	 * 
	 * Different instances with the same X and Y should be equal.
	 */
	@Test
	public void testEqualsExpectTrue() {
		Coordinate c1 = new Coordinate(5, 5);
		Coordinate c2 = new Coordinate(5, 5);
		assertEquals(c1, c2);
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.environment.Coordinate#equals(java.lang.Object)}.
	 * 
	 * Different instances with the different X and Y should not be equal.
	 */
	@Test
	public void testHashExpectSame() {
		Coordinate c1 = new Coordinate(5, 4);
		Coordinate c2 = new Coordinate(5, 4);
		assertEquals(c1.hashCode(), c2.hashCode());
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.environment.Coordinate#isGreaterThan(com.alice.mower.environment.Coordinate)}.
	 */
	@Test
	public void testIsGreaterThanSameCoord() {
		Coordinate c1 = new Coordinate(5, 4);
		Coordinate c2 = new Coordinate(5, 4);
		assertFalse(c1.isGreaterThan(c2));
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.environment.Coordinate#isGreaterThan(com.alice.mower.environment.Coordinate)}.
	 */
	@Test
	public void testIsGreaterThanExpectTrue() {
		Coordinate c1 = new Coordinate(5, 4);
		Coordinate c2 = new Coordinate(4, 4);
		assertTrue(c1.isGreaterThan(c2));
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.environment.Coordinate#isGreaterThan(com.alice.mower.environment.Coordinate)}.
	 */
	@Test
	public void testIsGreaterThanExpectFalse() {
		Coordinate c1 = new Coordinate(4, 4);
		Coordinate c2 = new Coordinate(4, 5);
		assertFalse(c1.isGreaterThan(c2));
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.environment.Coordinate#isGreaterThan(com.alice.mower.environment.Coordinate)}.
	 */
	@Test
	public void testIsGreaterThanNullParameter() {
		Coordinate c1 = new Coordinate(5, 4);
		assertTrue(c1.isGreaterThan(null));
	}

}
