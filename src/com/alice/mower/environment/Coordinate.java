/**
 * 
 */
package com.alice.mower.environment;

/**
 * @author alicebarsse
 *
 */
public final class Coordinate {

	private final int abscisse;
	private final int ordonnée;
	
	public Coordinate(int abscisse, int ordonnée) {
		this.abscisse = abscisse;
		this.ordonnée = ordonnée;
	}

	public int getAbscisse() {
		return abscisse;
	}

	public int getOrdonnée() {
		return ordonnée;
	}

	@Override
	public String toString() {
		return "Coordinate [abscisse=" + abscisse + ", ordonnée=" + ordonnée + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + abscisse;
		result = prime * result + ordonnée;
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
		if (abscisse != other.abscisse)
			return false;
		if (ordonnée != other.ordonnée)
			return false;
		return true;
	}

	
	
}
