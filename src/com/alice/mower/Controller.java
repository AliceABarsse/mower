/**
 * 
 */
package com.alice.mower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alice.mower.environment.Coordinate;
import com.alice.mower.environment.Lawn;
import static com.alice.mower.environment.Orientation.*;
import static com.alice.mower.Movement.*;
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
	
	private Mower initMower() {
		
		// v1 fixed initial mower position
		//Coordinate initialCoordinate1 = new Coordinate(1,2);
		//Position initialMowerPosition1 = new Position(initialMowerCoordinate1, NORTH);
		Coordinate initialCoordinate2 = new Coordinate(3,3);
		Position initialMowerPosition2 = new Position(initialCoordinate2, EAST);
		return new Mower(lawn, initialMowerPosition2);
	}
	
	private List<Movement> getMovementSequence() {
		List<Movement> list = new ArrayList<>();
		//Movement[] list1 = new Movement[]{LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD};
				Movement[] list2 = new Movement[]{FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, RIGHT, FORWARD};
		list.addAll(Arrays.asList(list2));
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println("Start running.");
		
		Controller controller = new Controller();
		controller.doTheMowing();
		
	}
	
	private void doTheMowing() {
		initLawn();

		// build one mower at a fixed position
		mower = initMower();

		// get the movement sequence
		List<Movement> moves = getMovementSequence();
		
		// feed the movements to the mower
		mower.move(moves);
		Position mowerPosition = mower.getPosition();
		System.out.println("Final mower position: " + mowerPosition);
	}
}
