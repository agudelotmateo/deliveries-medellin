package structures;

import others.Misc;

/**
 * Representation of a 2D point (x, y).
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class Point implements Comparable<Point> {
	private final double x, y;

	/**
	 * Creates a new point.
	 * 
	 * @param x
	 *            Coordinate on the x axis.
	 * @param y
	 *            Coordinate on the y axis.
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return Position on the x axis.
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return Position on the y axis.
	 */
	public double getY() {
		return y;
	}

	@Override
	public int compareTo(Point p2) {
		return Misc.comparePoints(this, p2);
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	/**
	 * Based on <a href=
	 * "http://stackoverflow.com/questions/7316571/java-pairt-n-class-implementation">
	 * this</a>.
	 */
	@Override
	public int hashCode() {
		return new Double(x).hashCode() ^ new Double(y).hashCode();
	}

	/**
	 * Based on <a href=
	 * "http://stackoverflow.com/questions/21600344/java-hashmap-containskey-returns-false-for-existing-object">
	 * this</a>.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		return Misc.equalPoints(this, (Point) o);
	}
}
