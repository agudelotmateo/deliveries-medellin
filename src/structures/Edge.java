package structures;

/**
 * Class to represent an Edge in the Adjacency List representation of a graph
 * where the nodes are objects of the Vertex class.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class Edge {
	private Vertex vertex;
	private double cost;

	/**
	 * Creates a new edge.
	 * 
	 * @param vertex
	 *            Destination vertex.
	 * @param cost
	 *            Cost of reaching the destination vertex.
	 */
	public Edge(Vertex vertex, double cost) {
		this.vertex = vertex;
		this.cost = cost;
	}

	/**
	 * @return Destination vertex.
	 */
	public Vertex getVertex() {
		return vertex;
	}

	/**
	 * @return Cost of reaching the destination vertex.
	 */
	public double getCost() {
		return cost;
	}

	@Override
	public int hashCode() {
		return vertex.hashCode();
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
		Edge e = ((Edge) o);
		return vertex.equals(e.getVertex()) && Double.compare(cost, e.getCost()) == 0;
	}
}
