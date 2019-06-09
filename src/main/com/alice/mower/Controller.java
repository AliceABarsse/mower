/**
 * 
 */
package com.alice.mower;

import java.util.List;

import com.alice.mower.environment.Coordinate;
import com.alice.mower.environment.Lawn;
import com.alice.mower.environment.Position;
import com.alice.mower.params.MowerParams;
import com.alice.mower.params.MowingParameters;

/**
 * @author alicebarsse
 *
 *         Controller class is the entry point for the program. It starts things
 *         up.
 */
public class Controller {

	public static final Coordinate LAWN_UPPER_LEFT_COORDINATE = new Coordinate(5, 5);
	private Lawn lawn;
	private List<MowerParams> mowerParameters;

	private void initLawn(Coordinate upperLeftCoords) {

		if (lawn != null) {
			return;
		}

		lawn = new Lawn(upperLeftCoords);
	}

	public static void main(String[] args) {
		// System.out.println("Start running.");

		Controller controller = new Controller();
		MowingParameters params = new MowingParameters();
		String inputFile = null;
		if (args != null && args.length != 0) {
			if (args.length == 1) {
				inputFile = args[0];
			} else {
				System.err.println("Usage: Controller [input file path]");
				return;
			}
		}
		params.init(inputFile);
		controller.init(params);
		controller.doTheMowing();

	}

	private void init(MowingParameters params) {

		// initLawn(LAWN_UPPER_LEFT_COORDINATE);
		initLawn(params.getLawnUpperRightCorner());
		mowerParameters = params.getMowerParameters();
		// MowerParams fixedParams1 = new MowerParams("1 2 N", "FFLRFFLR");
		// MowerParams fixedParams2 = new MowerParams("3 3 E", "FFLRFFLR");

	}

	private void doTheMowing() {
		for (MowerParams mowerParams : mowerParameters) {
			Mower mower = new Mower(lawn, mowerParams.getInitialPositionParam());
			// feed the movements to the mower
			mower.move(mowerParams.getMovementsList());
			Position mowerPosition = mower.getPosition();
			// System.out.println("Final mower position: ");
			System.out.println(mowerPosition.displayString());
		}
	}
}
