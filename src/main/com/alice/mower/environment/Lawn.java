/**
 * 
 */
package com.alice.mower.environment;

import java.util.function.Predicate;

/**
 * @author alicebarsse
 * 
 *         The lawn is responsible for knowing grid boundaries. Our lawn may be
 *         a square or a rectangle grid with lower left corner at coordinates
 *         X=0, Y=0.
 * 
 *         The lawn dimension is defined by giving the coordinates of the upper
 *         right corner.
 *
 */
public final class Lawn {

	/**
	 * Variable upper right corner coordinates.
	 */
	private final Coordinate upperRightCoords;

	/**
	 * Fixed lower left corner.
	 */
	public static final Coordinate LOWER_LEFT_COORDINATE = new Coordinate(0, 0);

	private final Predicate<Coordinate> filter;
	
	/**
	 * @param upperLeftCoords
	 *            Dimension-defining information.Must not be null.
	 * @throws IllegalArgumentException
	 *             if the parameter is null
	 */
	public Lawn(Coordinate upperLeftCoords) {
		if (upperLeftCoords == null) {
			throw new IllegalArgumentException("Lawn parameter cannot be null");
		}
		this.upperRightCoords = upperLeftCoords;
		
		// playing with lambda expression
		filter = (coord) -> !coord.isGreaterThan(upperLeftCoords) && !LOWER_LEFT_COORDINATE.isGreaterThan(coord);
	}

	/**
	 * 
	 * @param coord
	 *            A coordinate defining a spot on a plane
	 * @return True if the given coordinate is on the lawn, false if not.
	 */
	public boolean exists(Coordinate coord) {

		if (coord == null) {
			return false;
		}
		
		if (!filter.test(coord)) {
//		if (coord.isGreaterThan(upperRightCoords)) {
			System.err.println("Coordinate (" + coord
					+ ") is outside of lawn upper or right-side bounds (lawn upper-right coordinate is "
					+ upperRightCoords + ")");
			return false;
		}
//		if (LOWER_LEFT_COORDINATE.isGreaterThan(coord)) {
//			System.err.println("Coordinate (" + coord
//					+ ") is outside of lawn lower or left-side bounds (lawn lower-left coordinate is "
//					+ LOWER_LEFT_COORDINATE + ")");
//			return false;
//		}
		return true;
	}
}
