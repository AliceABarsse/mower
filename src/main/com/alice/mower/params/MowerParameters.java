package com.alice.mower.params;

import java.util.ArrayList;
import java.util.List;

import com.alice.mower.Movement;
import com.alice.mower.environment.Coordinate;
import com.alice.mower.environment.Orientation;
import com.alice.mower.environment.Position;

public final class MowerParameters {

	private final String initialPositionString;
	private final String movementSequenceString;

	private Position initialPositionParam;
	private final List<Movement> movementsList;

	/**
	 * 
	 * @param initialPositionString
	 *            Must not be null
	 * @param movementSequenceString
	 *            Must not be null
	 * @throws IllegalArgumentException
	 *             if either parameter is null
	 */
	public MowerParameters(String initialPositionString, String movementSequenceString) {
		if (initialPositionString == null || movementSequenceString == null) {
			throw new IllegalArgumentException("MowerParameters constructor parameters must not be null.");
		}
		movementsList = new ArrayList<>();
		this.initialPositionString = initialPositionString;
		this.movementSequenceString = movementSequenceString;
	}

	@Override
	public String toString() {
		return "MowerParameters [initialPositionString=" + initialPositionString + ", movementSequenceString="
				+ movementSequenceString + "]";
	}

	/**
	 * 
	 * @param lawnUpperRightCorner
	 * @return true if the parameter is valid (never returns false, invalid
	 *         parameters will result in an exception)
	 * @throws IllegalArgumentException
	 *             if the coordinate is outside of lawn bounds
	 */
	public boolean validateMowerParams(Coordinate lawnUpperRightCorner) {
		initialPositionParam = parsePositionString();
		if (initialPositionParam.getCoordinate().isGreaterThan(lawnUpperRightCorner)) {
			throw new IllegalArgumentException(
					"Mower initial position (" + initialPositionParam + ") is outside of lawn.");
		}
		movementsList.clear();
		movementsList.addAll(parseMovementSequenceStringToList());
		return initialPositionParam != null && !movementsList.isEmpty();
	}

	/**
	 * parse the string and match each character to a movement.
	 * 
	 * @return list of movements read from string.
	 * @throws IllegalArgumentException
	 *             if the string does not have expected format.
	 */
	private List<Movement> parseMovementSequenceStringToList() {
		List<Movement> list = new ArrayList<>();

		for (int index = 0; index < movementSequenceString.length(); index++) {
			char charAt = movementSequenceString.charAt(index);
			// stop at first non-alphabetic character
			if (!Character.isAlphabetic(charAt)) {
				break;
			}
			Movement movement = Movement.fromValue(charAt);
			list.add(movement);
		}
		return list;
	}

	/**
	 * 
	 * @return parsed position from string
	 * @throws IllegalArgumentException
	 *             if the string does not have expected format
	 */
	private Position parsePositionString() {

		String[] split = initialPositionString.split(MowingParameters.LINE_INNER_SEPARATOR);
		if (split.length < 3) {
			throw new IllegalArgumentException(
					"MowerParameters position string parameter cannot be parsed <<" + initialPositionString + ">>.");
		}
		try {
			int xCoord = Integer.parseInt(split[0]);
			int yCoord = Integer.parseInt(split[1]);
			Coordinate coord = new Coordinate(xCoord, yCoord);
			Orientation orientation = Orientation.fromValue(split[2]);

			return new Position(coord, orientation);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"MowerParameters position string parameter cannot be parsed <<" + initialPositionString + ">>.");
		}
	}

	public Position getInitialPositionParam() {
		return initialPositionParam;
	}

	public List<Movement> getMovementsList() {
		return movementsList;
	}
}
