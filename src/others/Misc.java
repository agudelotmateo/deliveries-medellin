package others;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

import algorithms.BruteForceDP;
import algorithms.Combined;
import algorithms.NaturalApproximation;
import algorithms.NearestNeighbor;
import algorithms.ShortestPath;
import io.Input;
import io.InputReader;
import io.Output;
import structures.Edge;
import structures.Point;
import structures.Vertex;

/**
 * This class contains a variety of methods useful during different parts of the
 * execution of the program. Kind of a 'general helper'.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class Misc {
	// To store original vertex not in our graph
	public static HashMap<Vertex, Point> originalVertex;
	// From 2D coordinates to Vertex. Required when reading points
	public static HashMap<Point, Vertex> pointToVertex;
	// Adjacency List representation of the Graph of the city
	public static HashMap<Vertex, HashSet<Edge>> graph;
	// List of vertices. Required when building graph
	public static HashMap<Long, Vertex> vertices;
	// From vertex to its code. Required for Natural Approximation algorithm
	public static HashMap<Vertex, Integer> vertexCode;
	// List of points to be visited, being the first one the start and end of
	// the tour
	public static ArrayList<Vertex> points;
	// Complete subgraph whose vertices are only the points to be visited.
	// Required for all the algorithms but Natural Approximation
	// and used when counting the total distance of the tour
	public static double[][] subGraph;
	// Whether we're reading the points from a file
	private static final boolean DEV = false;
	// To stop the program
	private static boolean done = false;
	// To avoid recomputing subGraph when not necessary
	private static boolean newPoints = true;
	// To avoid recomputing subGraph when not necessary
	public static String lastURL = null, readURL = null;
	// Google Maps (ES) URL prefix
	public static final String MAPS_PREFIX = "https://www.google.es/maps/dir";
	// Extreme mode: no limit to use brute force method
	public static boolean extreme_mode = false;
	public static boolean new_mode = false, firstTime = false;

	/**
	 * Removes the @ on the URL and everything after it as it is useless for our
	 * purpose
	 * 
	 * @param s
	 *            string to be fixed
	 * @return fixed string (URL)
	 */
	public static String fixURL(String s) {
		StringBuilder sb = new StringBuilder();
		char t;
		for (int i = 0; i < s.length(); ++i) {
			t = s.charAt(i);
			if (t == '@')
				break;
			else
				sb.append(t);
		}
		return sb.toString();
	}

	/**
	 * If it finds a space in the string, this character and everything after it
	 * is removed.
	 * 
	 * @param s
	 *            String to be cleaned.
	 * @return The substring up to the first appearance of a space.
	 */
	public static String clean(String s) {
		int n = s.length();
		for (int i = 0; i < n; ++i)
			if (s.charAt(i) == ' ')
				return s.substring(0, i);
		return s;
	}

	/**
	 * Checks whether the user has entered a valid choice.
	 * 
	 * @param s
	 *            String entered by the used.
	 * @return Whether the input is valid.
	 */
	public static boolean validChoice(String s) {
		s = s.trim();
		return s.length() == 1 && (s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("x")
				|| (points.size() <= BruteForceDP.MAX || extreme_mode ? s.equals("5") : false)
				|| (!DEV ? s.equals("c") : false));
	}

	/**
	 * Sorts the points around the first one, which gives an approximation to
	 * the solution. Also adds the initial point to the end in order to complete
	 * the tour.
	 * 
	 * @param vertices
	 *            List of the points to be sorted.
	 */
	public static void sortPoints(ArrayList<Vertex> vertices) {
		vertices.sort(new CompareVertex(vertices.get(0)));
		vertices.add(vertices.get(0));
	}

	/**
	 * Compares two points in non descending order by x coordinate (and by y
	 * coordinate if tie).
	 * 
	 * @param p1
	 *            First point.
	 * @param p2
	 *            Second point.
	 * @return Order relationship between both points.
	 */
	public static int comparePoints(Point p1, Point p2) {
		if (Double.compare(p1.getX(), p2.getX()) == 0)
			return Double.compare(p1.getY(), p2.getY());
		return Double.compare(p1.getX(), p2.getX());
	}

	/**
	 * @param p1
	 *            First point.
	 * @param p2
	 *            Second point.
	 * @return Whether the two points (first and second) are equal.
	 */
	public static boolean equalPoints(Point p1, Point p2) {
		return Double.compare(p1.getX(), p2.getX()) == 0 && Double.compare(p1.getY(), p2.getY()) == 0;
	}

	/**
	 * Sums the distance between each pair of vertices.
	 * 
	 * @param path
	 *            List of vertices (tour).
	 * @return Total distance to traverse the given tour (path).
	 */
	public static double sumDistances(ArrayList<Vertex> path) {
		Vertex a = path.get(0);
		double total = 0d;
		for (int i = 1; i < path.size(); ++i) {
			Vertex b = path.get(i);
			total += Misc.subGraph[Misc.vertexCode.get(a)][Misc.vertexCode.get(b)];
			a = b;
		}
		return total;
	}

	/**
	 * Builds the adjacency matrix representation of the subgraph we will be
	 * working on (containing only the points of the tour). The graph must be
	 * complete to be created. Uses A* if we're working with 5 or less points,
	 * and Dijkstra's otherwise.
	 * 
	 * @return Whether the graph could be created or not.
	 */
	public static boolean buildSubGraph() {
		vertexCode = new HashMap<>();
		subGraph = new double[points.size()][points.size()];
		int n = points.size();
		if (n <= 5) {
			for (int i = 0; i < n; ++i) {
				Vertex source = points.get(i);
				vertexCode.put(source, i);
				for (int j = 0; j < n; ++j)
					if (i == j) {
						subGraph[i][j] = 0d;
					} else {
						Vertex destination = points.get(j);
						double distance = ShortestPath.aStar(source, destination);
						if (Double.compare(distance, -1.0) == 0) {
							subGraph = null;
							return false;
						}
						subGraph[i][j] = distance;
					}
			}
		} else {
			for (int i = 0; i < n; ++i) {
				Vertex source = points.get(i);
				vertexCode.put(source, i);
				HashMap<Vertex, Double> dij = ShortestPath.dijkstra(source);
				for (int j = 0; j < n; ++j)
					if (i == j) {
						subGraph[i][j] = 0;
					} else {
						Double distance = dij.get(points.get(j));
						if (distance == null) {
							subGraph = null;
							return false;
						}
						subGraph[i][j] = distance;
					}
			}
		}
		return true;
	}

	/**
	 * Prints warning that says that Google Maps input may not make sense and
	 * why.
	 */
	public static void printWarning() {
		System.out.println("WARNING: The distance is computed using our graph of the city, which might\n"
				+ "differ from the one used by Google Maps. This means that what for us is the\n"
				+ "shortest tour may not the same for them, also because they might\n"
				+ "have used a different way to complete the paths between every\n" + "pair of vertices");
	}

	/**
	 * Creates the graph and reads the points.
	 * 
	 * @throws FileNotFoundException
	 *             If any of the files containing either the vertices, the edges
	 *             or the points was not found.
	 */
	public static void initialize() throws FileNotFoundException {
		System.out.println("Initializing ...");
		long init = System.currentTimeMillis();
		Input.readVertices("vertices-clean.txt");
		Input.readEdges("edges-clean.txt");
		System.out.printf(Locale.ROOT, "Time required to build graph: %.3fs\n",
				(System.currentTimeMillis() - init) / 1000.0);
		originalVertex = new HashMap<>();
	}

	/**
	 * Reads the Google Maps' URL containing the points to be visited. Remember
	 * that the first one will be used to complete the tour as the final vertex
	 * too.
	 * 
	 * @throws FileNotFoundException
	 *             in case of developer mode, if the file containing the points
	 *             was not found.
	 */
	public static void askForPoints() throws FileNotFoundException {
		if (DEV)
			Input.readPoints("points.txt", false);
		else {
			System.out.println("Paste here the Google Maps URL containing the points you want to visit.\n"
					+ "Remember that the first point will be also the last one in the tour:");
			boolean invalidURL = false;
			firstTime = true;
			points = new ArrayList<>();
			while (points.size() < 2) {
				if (new_mode)
					new_mode = false;
				else if (!firstTime && !invalidURL)
					System.out.println("Not enough points! Try again:");
				else {
					firstTime = false;
					invalidURL = false;
				}
				try {
					points = Input.readPoints("", true);
				} catch (Exception e) {
					invalidURL = true;
					System.out.println("Invalid URL! Try again:");
				}
			}
		}
		if (lastURL == null || !lastURL.equals(readURL)) {
			lastURL = readURL;
			newPoints = true;
		}
	}

	/**
	 * User interface.
	 * 
	 * @throws FileNotFoundException
	 *             If either the file containing the vertices, the edges and/or
	 *             the points was not found.
	 */
	public static void UI() throws FileNotFoundException {
		askForPoints();
		printWarning();
		while (!done) {
			System.out.println("Choose (write the number and press enter):");
			System.out.println(
					"\t 1. Natural approximation fast mode -- won't show total distance\n" + "\t    (ALMOST INSTANT)");
			System.out.println("\t 2. Natural approximation normal mode -- might get a better tour than\n"
					+ "\t    option 1 (medium)");
			System.out.println("\t 3. Nearest Neighbor (medium)");
			System.out.println("\t 4. The best of both (options 2 and 3 combined) (medium)");
			if (points.size() <= BruteForceDP.MAX || extreme_mode)
				System.out.println(
						"\t 5. Exact -- potentially very slow, about 30 seconds for 20 points\n" + "\t    (SLOW)");
			if (!DEV)
				System.out.println("\t c. Change URL");
			System.out.println("\t x. Exit");
			InputReader in = new InputReader(System.in);
			String s = in.next().toLowerCase();
			while (!Misc.validChoice(s)) {
				System.out.println("Invalid choice! Pick again:");
				s = in.next().toLowerCase();
			}
			System.out.println("Selected: " + s);
			ArrayList<Vertex> path;
			if (s.equals("x")) {
				done = true;
			} else if (s.equals("c")) {
				askForPoints();
			} else if (s.equals("1")) {
				long init = System.currentTimeMillis();
				path = new ArrayList<>(Misc.points);
				Misc.sortPoints(path);
				System.out.printf(Locale.ROOT, "Time spent computing route: %.3fs\n",
						(System.currentTimeMillis() - init) / 1000.0);
				Output.openPointsOnMaps(path);
			} else {
				long init = System.currentTimeMillis();
				if (newPoints && !Misc.buildSubGraph()) {
					System.out.println("Error: selection ignored");
					System.out.println("Natural approximation method used since at least two of the given points are\n"
							+ "not connected between themselves");
					NaturalApproximation.compute();
					path = NaturalApproximation.path;
					System.out.printf(Locale.ROOT, "Time spent computing route: %.3fs\n",
							(System.currentTimeMillis() - init) / 1000.0);
					System.out.println("Total distance couldn't be calculated");
					Output.openPointsOnMaps(path);
					askForPoints();
					continue;
				}
				if (newPoints) {
					System.out.printf(Locale.ROOT, "Time spent building subgraph: %.3fs\n",
							(System.currentTimeMillis() - init) / 1000.0);
					newPoints = false;
				}
				init = System.currentTimeMillis();
				double distance;
				if (s.equals("2")) {
					NaturalApproximation.compute();
					path = NaturalApproximation.path;
					distance = NaturalApproximation.distance;
				} else if (s.equals("3")) {
					NearestNeighbor.compute();
					path = NearestNeighbor.path;
					distance = NearestNeighbor.distance;
				} else if (s.equals("4")) {
					Combined.compute();
					path = Combined.path;
					distance = Combined.distance;
				} else {
					BruteForceDP.compute();
					path = BruteForceDP.path;
					distance = BruteForceDP.distance;
				}
				System.out.printf(Locale.ROOT, "Time spent computing route: %.3fs\n",
						(System.currentTimeMillis() - init) / 1000.0);
				System.out.printf(Locale.ROOT, "Total distance: %.3f\n", distance);
				Output.openPointsOnMaps(path);
			}
			System.out.println();
		}
	}

}
