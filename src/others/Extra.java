package others;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map.Entry;

import io.Input;
import io.InputReader;
import structures.Edge;
import structures.Vertex;

/**
 * Other methods not used during the execution of the program but were used to
 * pre-process the graph.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class Extra {
	public static int edges;
	public static int vertices;

	/**
	 * Removes from the graph the vertices that don't have any incoming
	 * vertices, and is not connected to any other vertices.
	 * 
	 * From the edges, the ones connecting vertices not in the vertices list
	 * were removed.
	 * 
	 * @throws IOException
	 *             If files containing either the vertices or the edges were not
	 *             found.
	 */
	public static void buildCleanGraph() throws IOException {
		Input.readVertices("vertices.txt");
		PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter(new File("input-files/vertices-clean.txt"))));
		InputReader in = new InputReader(new FileInputStream(new File("input-files/edges.txt")));
		HashSet<Long> validVertices = new HashSet<>();
		Misc.graph = new HashMap<Vertex, HashSet<Edge>>();
		while (in.hasNext()) {
			long id1 = in.nextLong();
			if (!Misc.vertices.containsKey(id1)) {
				in.remainingLine();
				continue;
			}
			validVertices.add(id1);
			Vertex source = Misc.vertices.get(id1);
			long id2 = in.nextLong();
			if (!Misc.vertices.containsKey(id2)) {
				in.remainingLine();
				continue;
			}
			validVertices.add(id2);
			Vertex destination = Misc.vertices.get(id2);
			double distance = in.nextDouble();
			in.remainingLine();
			if (!Misc.graph.containsKey(source))
				Misc.graph.put(source, new HashSet<Edge>());
			Misc.graph.get(source).add(new Edge(destination, distance));
		}
		HashSet<Vertex> total = new HashSet<>();
		vertices = 0;
		for (Entry<Long, Vertex> entry : Misc.vertices.entrySet()) {
			Vertex v = entry.getValue();
			if (validVertices.contains(v.getId())) {
				total.add(v);
				++vertices;
				out.printf(Locale.ROOT, "%d %.7f %.7f\n", v.getId(), v.getPoint().getX(), v.getPoint().getY());
			}
		}
		out.flush();
		out = new PrintWriter(new BufferedWriter(new FileWriter(new File("input-files/edges-clean.txt"))));
		for (HashMap.Entry<Vertex, HashSet<Edge>> entry : Misc.graph.entrySet()) {
			Long id1 = entry.getKey().getId();
			for (Edge e : entry.getValue()) {
				++edges;
				out.printf(Locale.ROOT, "%d %d %.7f\n", id1, e.getVertex().getId(), e.getCost());
			}
		}
		out.flush();
	}

}
