package com.alice.mower.params;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alice.mower.environment.Coordinate;

/**
 * 
 * @author alicebarsse
 *
 */
public class MowingParamsTest {

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowingParameters#MowingParameters()}.
	 */
	@Test
	public void testMowingParameters() {
		MowingParameters params = new MowingParameters();
		assertEquals(0, params.mowerCount());
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowingParameters#init(java.lang.String)}.
	 */
	@Test
	public void testInitNoFile() {
		MowingParameters params = new MowingParameters();
		params.init(null);
		assertTrue(params.mowerCount() > 0);
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowingParameters#mowerCount()}.
	 * 
	 * Test depends on contents of default init file
	 */
	@Test
	public void testMowerCount() {
		MowingParameters params = new MowingParameters();
		params.init(null);
		assertEquals(5, params.mowerCount());
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowingParameters#validateLawnParam(java.lang.String)}.
	 * 
	 * Test depends on contents of default init file
	 */
	@Test
	public void testValidateLawnParamNominalSquare() {
		MowingParameters params = new MowingParameters();
		Coordinate validatedLawnCoordinate = params.validateLawnParam("4 4");
		assertEquals(new Coordinate(4, 4), validatedLawnCoordinate);
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowingParameters#validateLawnParam(java.lang.String)}.
	 * 
	 */
	@Test
	public void testValidateLawnParamNominalRectangle() {
		MowingParameters params = new MowingParameters();
		Coordinate validatedLawnCoordinate = params.validateLawnParam("4 6");
		assertEquals(new Coordinate(4, 6), validatedLawnCoordinate);
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowingParameters#validateLawnParam(java.lang.String)}.
	 * 
	 * Input contains bad format string, wrong separator
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testValidateLawnParamBadFormat() {
		MowingParameters params = new MowingParameters();
		params.validateLawnParam("4,4");
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowingParameters#validateLawnParam(java.lang.String)}.
	 * 
	 * Input contains bad format string, only one value instead of two
	 */
	@Test(expected = IllegalArgumentException.class)
	public void validateLawnParamMissingValue() {
		MowingParameters params = new MowingParameters();
		params.validateLawnParam("4");
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowingParameters#validateLawnParam(java.lang.String)}.
	 * 
	 * Input contains unexpected number format string
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testValidateLawnParamNumberFormatProblem() {
		MowingParameters params = new MowingParameters();
		params.validateLawnParam("4L 4L");
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowingParameters#validateLawnParam(java.lang.String)}.
	 * 
	 * Input contains out of bounds values
	 */
	@Test
	public void testValidateLawnParamOutOfBounds() {
		MowingParameters params = new MowingParameters();
		int testedValue = 10000;

		// check assumptions, so that test is easier to update in case constant
		// is changed in class
		assertTrue(
				"Tested value (" + testedValue + ") should be greater than maximum value ("
						+ MowingParameters.MAX_LAWN_SIZE + ") for this test to work",
				testedValue > MowingParameters.MAX_LAWN_SIZE);

		try {
			params.validateLawnParam(testedValue + " " + testedValue);
			fail("Expected an exception to be thrown.");
		} catch (IllegalArgumentException e) {

			// test can be used to check message contents
			System.out.println("Got expected exception: " + e.getClass().getSimpleName() + " " + e.getMessage());
		}
	}
}
