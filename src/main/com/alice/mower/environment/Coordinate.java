/**
 * 
 */
package com.alice.mower.environment;

/**
 * @author alicebarsse
 * 
 *         Coordinate object for our mower needs. Also handles some comparison
 *         between two instances.
 *
 */
public final class Coordinate {

	private final int x;
	private final int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/**
	 * 
	 * @param other
	 *            coordinate, may be null
	 * @return true if this coordinate is greater than the coordinate given as
	 *         parameter, or if the given parameter is null.
	 */
	public boolean isGreaterThan(Coordinate other) {

		return other == null || getX() > other.getX() || getY() > other.getY();
	}

}
