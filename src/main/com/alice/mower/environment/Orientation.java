package com.alice.mower.environment;

/**
 * @author alicebarsse
 *
 *         Cardinal directions that the mower uses. Also knows how to turn left
 *         and right (i.e relation between the orientation values).
 */
public enum Orientation {

	NORTH {
		@Override
		public Orientation turnRight() {
			return Orientation.EAST;
		}

		@Override
		public Orientation turnLeft() {
			return Orientation.WEST;
		}
	},
	EAST {
		@Override
		public Orientation turnRight() {
			return Orientation.SOUTH;
		}

		@Override
		public Orientation turnLeft() {
			return Orientation.NORTH;
		}
	},
	WEST {
		@Override
		public Orientation turnRight() {
			return Orientation.NORTH;
		}

		@Override
		public Orientation turnLeft() {
			return Orientation.SOUTH;
		}
	},
	SOUTH {
		@Override
		public Orientation turnRight() {
			return Orientation.WEST;
		}

		@Override
		public Orientation turnLeft() {
			return Orientation.EAST;
		}
	};

	public abstract Orientation turnLeft();

	public abstract Orientation turnRight();

	/**
	 * 
	 * @param orientationString,
	 *            may be just the first letter of a value of this enum
	 * @return matching orientation from String representation.
	 * @throws IllegalArgumentException
	 *             if the string does not have expected format.
	 */
	public static Orientation fromValue(String orientationString) {

		String firstLetter = orientationString == null ? null : orientationString.substring(0, 1);

		for (Orientation or : values()) {
			if (or.name().startsWith(firstLetter)) {
				return or;
			}
		}

		throw new IllegalArgumentException("Cannot match a value of enum to input <<" + orientationString + ">>");
	}

}
