/**
 * 
 */
package com.alice.mower.environment;

/**
 * @author alicebarsse
 * 
 * The lawn is responsible for knowing grid boundaries.
 * Our lawn is a square grid.
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
	public static final Coordinate LOWER_LEFT_COORDINATE = new Coordinate(0,0);

	/**
	 * @param upperLeftCoords Defining information
	 */
	public Lawn(Coordinate upperLeftCoords) {
		if (upperLeftCoords == null) {
			throw new IllegalArgumentException("Lawn parameter cannot be null");
		}
		this.upperRightCoords = upperLeftCoords;
	}
	
	public boolean exists(Coordinate coord) {
		
		if(coord == null) {
			return false;
		}
		//Is the Abscisse on the lawn? 
		if (coord.getAbscisse() < LOWER_LEFT_COORDINATE.getAbscisse() 
				|| coord.getAbscisse() > upperRightCoords.getAbscisse()) {
			System.err.println("Coordinate is outside of lawn: " + coord);
			return false;
		}
		//Is the Ordonnée on the lawn? 
		if (coord.getOrdonnée() < LOWER_LEFT_COORDINATE.getOrdonnée() 
				|| coord.getOrdonnée() > upperRightCoords.getOrdonnée()) {
			System.err.println("Coordinate is outside of lawn: " + coord);
			return false;
		}		
		return true;
	}
}
 