package algorithms;

import java.util.ArrayList;

import structures.Vertex;

/**
 * Contains the method to compute the TSP route using both 'Natural
 * approximation' and 'Nearest neighbor' methods and picking the best tour.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class Combined {
	public static double distance;
	public static ArrayList<Vertex> path;

	/**
	 * Computes the TSP route, using 'Natural approximation' or 'Nearest
	 * neighbor' depending of which gives the shortest path.
	 * 
	 * Total distance is stored in 'distance', and path in 'path'.
	 */
	public static void compute() {
		NearestNeighbor.compute();
		NaturalApproximation.compute();
		double nn = NearestNeighbor.distance;
		double na = NaturalApproximation.distance;
		if (Double.compare(nn, na) == -1) {
			distance = nn;
			path = NearestNeighbor.path;
		} else {
			distance = na;
			path = NaturalApproximation.path;
		}
	}

}
