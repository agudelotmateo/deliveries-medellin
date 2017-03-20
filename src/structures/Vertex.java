package structures;

import others.Misc;

/**
 * Represents a vertex in the Adjacency List representation of a graph.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class Vertex {
	private long id;
	private Point point;

	/**
	 * Creates a new vertex.
	 * 
	 * @param id
	 *            Unique identifier of the vertex.
	 * @param x
	 *            Position on the x axis (latitude).
	 * @param y
	 *            Position on the y axis (longitude).
	 */
	public Vertex(long id, double x, double y) {
		this.id = id;
		point = new Point(x, y);
	}

	/**
	 * @return Vertex's id.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return Point containing it's x and y coordinates.
	 */
	public Point getPoint() {
		return point;
	}

	@Override
	public String toString() {
		return "[" + id + ", " + point.getX() + ", " + point.getY() + "]";
	}

	@Override
	public int hashCode() {
		return new Long(id).hashCode();
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
		Vertex v = ((Vertex) o);
		return id == v.id || Misc.equalPoints(this.getPoint(), v.getPoint());
	}
}
