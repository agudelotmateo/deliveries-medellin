package algorithms;

import java.util.ArrayList;
import java.util.HashSet;

import others.Misc;
import structures.Vertex;

/**
 * Contains the method to compute the TSP route using nearest neighbor
 * heuristic: for every node, starting with the specified one, find the closest
 * and move to it. Do it until all the vertices have been visited. Based on
 * <a href=
 * "https://www.math.ku.edu/~jmartin/courses/math105-F11/Lectures/chapter6-part4.pdf">
 * this</a> Pgs. 1-11.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class NearestNeighbor {
	public static double distance;
	public static ArrayList<Vertex> path;

	/**
	 * Computes the TSP route using nearest neighbor method.
	 * 
	 * Total distance is stored in 'distance', and path in 'path'.
	 */
	public static void compute() {
		HashSet<Vertex> visited = new HashSet<>();
		path = new ArrayList<>();
		Vertex v = Misc.points.get(0);
		visited.add(v);
		path.add(v);
		int n = Misc.points.size();
		double total = 0d;
		int idx = -1;
		while (visited.size() < n) {
			int code = Misc.vertexCode.get(v);
			double min = Double.MAX_VALUE;
			for (int i = 0; i < n; ++i) {
				if (code >= Misc.subGraph.length || i >= Misc.subGraph.length) {
					System.out.printf("code: %d i: %d\n", code, i);
					System.exit(0);
				}
				double distance = Misc.subGraph[code][i];
				if (i != code && distance < min && !visited.contains(Misc.points.get(i))) {
					min = distance;
					idx = i;
				}
			}
			total += min;
			v = Misc.points.get(idx);
			visited.add(v);
			path.add(v);
		}
		path.add(path.get(0));
		total += Misc.subGraph[idx][Misc.vertexCode.get(path.get(0))];
		distance = total;
	}
}
