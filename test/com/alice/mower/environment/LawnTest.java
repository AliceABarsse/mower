/**
 * 
 */
package com.alice.mower.environment;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author alicebarsse
 *
 */
public class LawnTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void newLawn() {
		Coordinate upperLeftSquare = new Coordinate(5,5);
		Lawn lawn = new Lawn(upperLeftSquare);
		
		assertNotNull(lawn);
		assertTrue(lawn.exists(upperLeftSquare));
		assertTrue(lawn.exists(new Coordinate(0, 0)));
		assertFalse(lawn.exists(new Coordinate(upperLeftSquare.getAbscisse()+1, upperLeftSquare.getOrdonnée()+1)));
		assertFalse(lawn.exists(new Coordinate(-1, -1)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullParamNewLawn() {
		new Lawn(null);
	}
}
