/**
 * 
 */
package com.alice.mower;

import com.alice.mower.environment.Coordinate;
import com.alice.mower.environment.Lawn;
import com.alice.mower.environment.Orientation;
import com.alice.mower.environment.Position;

/**
 * @author alicebarsse
 *
 * Controller class is the entry point for the program.
 * It starts things up.
 */
public class Controller {

	public static final Coordinate LAWN_UPPER_LEFT_COORDINATE = new Coordinate(5, 5);
	private Lawn lawn;
	private Mower mower;
	
	private void initLawn() {
		
		if (lawn != null) {
			return;
		}
		// v1 fixed grid size for lawn
		Coordinate upperLeftCoords = LAWN_UPPER_LEFT_COORDINATE;
		lawn = new Lawn(upperLeftCoords);
	}
	
	private Mower initMower(Coordinate initialMowerCoordinate) {
		
		// v1 fixed initial mower position
		Position initialMowerPosition = new Position(initialMowerCoordinate, Orientation.N);
		return new Mower(lawn, initialMowerPosition);
	}
	
	public static void main(String[] args) {
		System.out.println("Start running.");
		
		Controller controller = new Controller();
		controller.doTheMowing();
		
	}
	
	private void doTheMowing() {
		
		initLawn();
		
		for (int abscisse = Lawn.LOWER_LEFT_COORDINATE.getAbscisse() ; abscisse < LAWN_UPPER_LEFT_COORDINATE.getAbscisse()+1 ; abscisse++) {

			// build one mower per row
			mower = initMower(new Coordinate(abscisse, 0));
			while (true) {
				if (!mower.move()) {
					break;
				}
			}
		}
		Position mowerPosition = mower.getPosition();
		System.out.println("Final mower position: " + mowerPosition);
	}
}
