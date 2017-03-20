package algorithms;

import java.util.ArrayList;

import others.Misc;
import structures.Vertex;

/**
 * Contains the method to compute the TSP route by sorting the points in both
 * clockwise and anti-clockwise order around the first and picking the shortest
 * one.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class NaturalApproximation {
	public static double distance;
	public static ArrayList<Vertex> path;

	/**
	 * Computes the TSP route using natural approximation method.
	 * 
	 * Total distance is stored in 'distance', and path in 'path'.
	 */
	public static void compute() {
		path = new ArrayList<>(Misc.points);
		Misc.sortPoints(path);
		if (Misc.subGraph != null) {
			double total1 = Misc.sumDistances(path);
			ArrayList<Vertex> backwards = new ArrayList<>();
			for (int i = path.size() - 1; i >= 0; --i)
				backwards.add(path.get(i));
			double total2 = Misc.sumDistances(backwards);
			if (Double.compare(total2, total1) == -1) {
				path = backwards;
				total1 = total2;
			}
			distance = total1;
		}
	}
}
