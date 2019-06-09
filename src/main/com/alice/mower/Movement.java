/**
 * 
 */
package com.alice.mower;

/**
 * @author alicebarsse
 *
 *         Types of movement the mower knows.
 */
public enum Movement {

	LEFT, RIGHT, FORWARD;

	/**
	 * 
	 * @param movementFirstLetter,
	 *            the first letter of a value of this enum
	 * @return matching Movement from String representation.
	 * @throws IllegalArgumentException
	 *             if failed to find a corresponding value
	 */
	public static Movement fromValue(char movementFirstLetter) {
		final String firstLetter = String.valueOf(movementFirstLetter);
		for (Movement mvmt : values()) {
			if (mvmt.name().startsWith(firstLetter)) {
				return mvmt;
			}
		}

		throw new IllegalArgumentException("Cannot match a value of enum to input <<" + movementFirstLetter + ">>");
	}
}
