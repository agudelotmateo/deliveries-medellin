package algorithms;

import java.util.HashMap;
import java.util.PriorityQueue;

import others.Misc;
import structures.Edge;
import structures.Pair;
import structures.Point;
import structures.Vertex;

/**
 * Contains two Single Source Shortest Path (SSSP) algorithms: A* and
 * Dijkstra's.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class ShortestPath {

	/**
	 * Manhattan distance.
	 * 
	 * @param a
	 *            First point.
	 * @param b
	 *            Second point.
	 * @return Manhattan distance between point a and point b.
	 */
	private static double heuristic(Point a, Point b) {
		return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
	}

	/**
	 * Finds the shortest path from the source vertex to the destination vertex
	 * on our graph. Based on <a href=
	 * "http://www.redblobgames.com/pathfinding/a-star/implementation.html">this
	 * </a>.
	 * 
	 * @param source
	 *            Vertex we start at.
	 * @param destination
	 *            Vertex we want to reach.
	 * @return Shortest path cost between the source and the destination
	 *         vertices. Returns -1 in case of no path.
	 */
	public static double aStar(Vertex source, Vertex destination) {
		HashMap<Vertex, Double> costSoFar = new HashMap<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(source, 0d));
		costSoFar.put(source, 0d);
		while (!pq.isEmpty()) {
			Vertex current = pq.poll().getVertex();
			if (current.equals(destination))
				break;
			if (!Misc.graph.containsKey(current))
				continue;
			for (Edge e : Misc.graph.get(current)) {
				Vertex next = e.getVertex();
				double newCost = costSoFar.get(current) + e.getCost();
				if (!costSoFar.containsKey(next) || newCost < costSoFar.get(next)) {
					costSoFar.put(next, newCost);
					pq.add(new Pair(next, newCost + heuristic(next.getPoint(), destination.getPoint())));
				}
			}
		}
		return costSoFar.get(destination) == null ? -1 : costSoFar.get(destination);
	}

	/**
	 * Finds shortest path between one vertex and all the others in the graph. A
	 * distance of null means no path between the specified pair of vertices.
	 * Based on Competitive Programming, 3rd Ed. by Steven and Felix Halim, Pg.
	 * 148.
	 * 
	 * @param source
	 *            Starting vertex.
	 * @return Distance from starting vertex to all the others.
	 */
	public static HashMap<Vertex, Double> dijkstra(Vertex source) {
		HashMap<Vertex, Double> costSoFar = new HashMap<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(source, 0d));
		costSoFar.put(source, 0d);
		while (!pq.isEmpty()) {
			Vertex current = pq.peek().getVertex();
			if (pq.poll().getValue() > costSoFar.get(current) || !Misc.graph.containsKey(current))
				continue;
			for (Edge e : Misc.graph.get(current)) {
				Vertex next = e.getVertex();
				double newCost = costSoFar.get(current) + e.getCost();
				if (!costSoFar.containsKey(next) || newCost < costSoFar.get(next)) {
					costSoFar.put(next, newCost);
					pq.add(new Pair(next, newCost));
				}
			}
		}
		return costSoFar;
	}
}
