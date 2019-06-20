package com.alice.mower.params;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.alice.mower.Movement;
import com.alice.mower.environment.Coordinate;
import com.alice.mower.environment.Orientation;
import com.alice.mower.environment.Position;

public final class MowerParameters {

	private final String initialPositionString;
	private final String movementSequenceString;

	private Position initialPositionParam;
	private final List<Movement> movementsList;
	
	private static final Pattern MOWER_POSITION_INPUT_FORMAT_PATTERN = Pattern.compile("(\\d+) (\\d+) ([NEWS])");

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
	 * Ignore space characters that may have slipped in.
	 * 
	 * @return list of movements read from string.
	 * @throws IllegalArgumentException
	 *             if the string does not have expected format.
	 */
	private List<Movement> parseMovementSequenceStringToList() {
		
		List<Movement> list = movementSequenceString.chars()
			.mapToObj(c -> (char) c)
			.filter(charAt -> !charAt.equals(' '))
			.map(charAt->Movement.fromValue(charAt)).collect(Collectors.toList());
		
		return list;
	}

	/**
	 * 
	 * @return parsed position from string
	 * @throws IllegalArgumentException
	 *             if the string does not have expected format
	 */
	private Position parsePositionString() {

		Matcher matcher = MOWER_POSITION_INPUT_FORMAT_PATTERN.matcher(initialPositionString);
		if (matcher.find()) {
			int xCoord = Integer.parseInt(matcher.group(1));
			int yCoord = Integer.parseInt(matcher.group(2));
			Coordinate coord = new Coordinate(xCoord, yCoord);
			Orientation orientation = Orientation.fromValue(matcher.group(3));
			return new Position(coord, orientation);
		}
		throw new IllegalArgumentException(
				"Mower initial position info cannot be parsed as with expected format (2 integers and an orientation letter separated by <<"
						+ MowingParameters.LINE_INNER_SEPARATOR + ">>)  <<" + initialPositionString + ">>");
	}
	public Position getInitialPositionParam() {
		return initialPositionParam;
	}

	public List<Movement> getMovementsList() {
		return movementsList;
	}
}
