/**
 * 
 */
package com.alice.mower.environment;

/**
 * @author alicebarsse
 *
 */
public final class Position {

	private final Coordinate coords;
	private final Orientation orientation;
	/**
	 * @param coords
	 * @param orientation
	 */
	public Position(Coordinate coords, Orientation orientation) {
		if (coords == null || orientation == null) {
			throw new IllegalArgumentException("Position parameters cannot be null");
		}
		this.coords = coords;
		this.orientation = orientation;
	}
	@Override
	public String toString() {
		return "Position [coords=" + coords + ", orientation=" + orientation + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coords == null) ? 0 : coords.hashCode());
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
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
		Position other = (Position) obj;
		if (coords == null) {
			if (other.coords != null)
				return false;
		} else if (!coords.equals(other.coords))
			return false;
		if (orientation != other.orientation)
			return false;
		return true;
	}
	public Coordinate getCoordinate() {
		return coords;
	}
	public Orientation getOrientation() {
		return orientation;
	}
	
	
}
