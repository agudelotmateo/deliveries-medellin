package io;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import others.Misc;
import structures.Edge;
import structures.Point;
import structures.Vertex;

/**
 * Contains the methods to read the graph and user input (points).
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class Input {
	/**
	 * Reads all vertices from a text-file.
	 * 
	 * @param file
	 *            Name of the file containing all the vertices of the graph.
	 * @throws FileNotFoundException
	 *             If the file containing the vertices' description is not
	 *             found.
	 */
	public static void readVertices(String file) throws FileNotFoundException {
		InputReader in = new InputReader(Input.class.getResourceAsStream("/" + file));
		Misc.pointToVertex = new HashMap<>();
		Misc.vertices = new HashMap<>();
		while (in.hasNext()) {
			long id = in.nextLong();
			double x = in.nextDouble();
			double y = Double.parseDouble(Misc.clean(in.remainingLine()));
			Point p = new Point(x, y);
			if (!Misc.pointToVertex.containsKey(p)) {
				Vertex temp = new Vertex(id, x, y);
				Misc.vertices.put(id, temp);
				Misc.pointToVertex.put(p, temp);
			}
		}
	}

	/**
	 * Builds the graph.
	 * 
	 * @param file
	 *            Name of the file containing the edges.
	 * @throws FileNotFoundException
	 *             If the file containing the edges was not found.
	 */
	public static void readEdges(String file) throws FileNotFoundException {
		InputReader in = new InputReader(Input.class.getResourceAsStream("/" + file));
		Misc.graph = new HashMap<>();
		while (in.hasNext()) {
			Vertex source = Misc.vertices.get(in.nextLong());
			Vertex destination = Misc.vertices.get(in.nextLong());
			double distance = in.nextDouble();
			in.remainingLine();
			if (!Misc.graph.containsKey(source))
				Misc.graph.put(source, new HashSet<Edge>());
			Misc.graph.get(source).add(new Edge(destination, distance));
		}
	}

	/**
	 * Reads points to be visited (Google Map's URL). The points not found on
	 * our graph will be associated to the nearest one we have. If an x, either
	 * lower-case or upper-case, with or without leading and/or trailing spaces
	 * is entered, the program will exit.
	 * 
	 * @param file
	 *            Name of the file containing the Google Maps URL of the points
	 *            to be visited.
	 * @param userMode
	 *            If we're reading from keyboard instead of from a file.
	 * @return The vertices to be visited, being the first the one that closes
	 *         the tour.
	 * @throws FileNotFoundException
	 *             If the file containing the Google Maps URL is not found or
	 *             the URL doesn't contain the correct Google Maps Spain URL
	 */
	public static ArrayList<Vertex> readPoints(String file, boolean userMode) throws FileNotFoundException {
		InputReader in = new InputReader(userMode ? System.in : Input.class.getResourceAsStream("/" + file));
		String s = Misc.fixURL(in.next().trim());
		String t = s.toLowerCase();
		if (t.equals("x"))
			System.exit(0);
		if (t.equals("extreme-mode")) {
			if (Misc.extreme_mode) {
				System.out.println("Extreme mode already enabled");
				Misc.firstTime = true;
			} else {
				Misc.extreme_mode = Misc.new_mode = true;
				System.out.println("Extreme mode enabled. Brute force method can now be used for any amount\n"
						+ "of points. Be careful!");
			}
			System.out.println("Enter the URL: ");
			return new ArrayList<Vertex>();
		}
		if (!s.startsWith(Misc.MAPS_PREFIX))
			throw new FileNotFoundException();

		String[] points = s.substring(31, s.length()).split("/");
		int n = points.length;
		ArrayList<Vertex> vertices = new ArrayList<>();
		for (int i = 0; i < n; ++i)

		{
			String[] parts = points[i].split(",");
			Point pt = new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
			if (Misc.pointToVertex.containsKey(pt))
				vertices.add(i, Misc.pointToVertex.get(pt));
			else {
				Vertex nearest = findNearest(pt);
				vertices.add(i, nearest);
				Misc.originalVertex.put(nearest, pt);
			}
		}
		Misc.readURL = s;
		return vertices;

	}

	/**
	 * Given a point not in out list of vertices, finds the vertex on our list
	 * closest to it.
	 * 
	 * @param p
	 *            Point we want to stay close to.
	 * @return Nearest vertex to point p.
	 */
	public static Vertex findNearest(Point p) {
		double x = p.getX(), y = p.getY();
		double bestDX = Double.MAX_VALUE, bestDY = Double.MAX_VALUE;
		Vertex closesVertex = null;
		for (Entry<Long, Vertex> entry : Misc.vertices.entrySet()) {
			Vertex v = entry.getValue();
			Point tempP = v.getPoint();
			double tempDX = Math.abs(x - tempP.getX());
			double tempDY = Math.abs(y - tempP.getY());
			if (Double.compare(tempDX, bestDX) == -1 && Double.compare(tempDY, bestDY) == -1) {
				closesVertex = v;
				bestDX = tempDX;
				bestDY = tempDY;
			}
		}
		return closesVertex;
	}

}
