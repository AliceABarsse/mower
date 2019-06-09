/**
 * 
 */
package com.alice.mower.params;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alice.mower.environment.Coordinate;

/**
 * @author alicebarsse
 *
 */
public class MowerParametersTest {

	private static final Coordinate DEFAULT_UPPER_RIGHT_CORNER = new Coordinate(5, 5);

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowerParameters#MowerParams(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testMowerParamsNominal() {
		String initialPositionString = "1 1 E";
		String movementSequenceString = "FFF";
		MowerParameters params = new MowerParameters(initialPositionString, movementSequenceString);
		assertNotNull(params);
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowerParameters#MowerParams(java.lang.String, java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMowerParamsNullValues() {
		new MowerParameters(null, null);
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowerParameters#validateMowerParams()}.
	 */
	@Test
	public void testValidateNominalMowerParams() {
		String initialPositionString = "1 1 E";
		String movementSequenceString = "FFF";
		MowerParameters params = new MowerParameters(initialPositionString, movementSequenceString);
		assertTrue(params.validateMowerParams(DEFAULT_UPPER_RIGHT_CORNER));
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowerParameters#validateMowerParams()}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testValidateMowerParamsBadPositionString() {
		String initialPositionString = "1 1";
		String movementSequenceString = "FFF";
		MowerParameters params = new MowerParameters(initialPositionString, movementSequenceString);
		params.validateMowerParams(DEFAULT_UPPER_RIGHT_CORNER);
	}

	/**
	 * Test method for
	 * {@link com.alice.mower.params.MowerParameters#validateMowerParams()}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testValidateMowerParamsPositionOutsiedeOfLawn() {
		String initialPositionString = "6 6 N";
		String movementSequenceString = "FFF";
		MowerParameters params = new MowerParameters(initialPositionString, movementSequenceString);
		params.validateMowerParams(DEFAULT_UPPER_RIGHT_CORNER);
	}

}
