package structures;

import others.Misc;

/**
 * Pair of Vertex and it's value during the execution of either the A* or
 * Dijkstra's algorithm.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class Pair implements Comparable<Pair> {
	private final Vertex vertex;
	private final double value;

	/**
	 * Creates a new Pair.
	 * 
	 * @param vertex
	 *            Vertex of the graph.
	 * @param value
	 *            It's value.
	 */
	public Pair(Vertex vertex, double value) {
		this.vertex = vertex;
		this.value = value;
	}

	/**
	 * @return Vertex contained in the pair.
	 */
	public Vertex getVertex() {
		return vertex;
	}

	/**
	 * @return Value of the vertex contained in the pair.
	 */
	public double getValue() {
		return value;
	}

	@Override
	public int compareTo(Pair p2) {
		if (Double.compare(value, p2.getValue()) == 0)
			return Misc.comparePoints(vertex.getPoint(), p2.getVertex().getPoint());
		return Double.compare(value, p2.getValue());
	}

}