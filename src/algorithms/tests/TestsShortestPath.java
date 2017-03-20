package algorithms.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.junit.Test;

import algorithms.ShortestPath;
import io.Input;
import others.Misc;
import structures.Vertex;

/**
 * Checks whether the methods in the ShortestPath class are working properly.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class TestsShortestPath {

	/**
	 * Tests whether the Dijkstra's and A* algorithm are working properly. Uses
	 * random valid vertices (picked by hand).
	 * 
	 * @throws FileNotFoundException
	 *             if the file containing the vertices of the graph or the one
	 *             containing the edges was not found.
	 */
	@Test
	public void dijkstraAndAStar() throws FileNotFoundException {
		Input.readVertices("vertices-clean.txt");
		Input.readEdges("edges-clean.txt");
		Vertex source = Misc.vertices.get(3513307784L);
		ArrayList<Vertex> destinations = new ArrayList<>();
		destinations.add(Misc.vertices.get(415669500L));
		destinations.add(Misc.vertices.get(330253050L));
		destinations.add(Misc.vertices.get(3671034775L));
		destinations.add(Misc.vertices.get(2430583047L));
		destinations.add(Misc.vertices.get(2668612917L));
		destinations.add(Misc.vertices.get(2597308571L));
		destinations.add(Misc.vertices.get(1841414820L));
		destinations.add(Misc.vertices.get(1841414821L));
		destinations.add(Misc.vertices.get(551128952L));
		destinations.add(Misc.vertices.get(1366942064L));
		destinations.add(Misc.vertices.get(1946462782L));
		destinations.add(Misc.vertices.get(1946462779L));
		destinations.add(Misc.vertices.get(4051971896L));
		destinations.add(Misc.vertices.get(538247012L));
		destinations.add(Misc.vertices.get(1670691882L));
		destinations.add(Misc.vertices.get(558882523L));
		destinations.add(Misc.vertices.get(3320789635L));
		destinations.add(Misc.vertices.get(3320789636L));
		destinations.add(Misc.vertices.get(3320789637L));
		destinations.add(Misc.vertices.get(429909643L));
		destinations.add(Misc.vertices.get(3745182715L));
		destinations.add(Misc.vertices.get(3745182718L));
		destinations.add(Misc.vertices.get(3745182719L));
		destinations.add(Misc.vertices.get(3745182706L));
		destinations.add(Misc.vertices.get(3745182704L));
		destinations.add(Misc.vertices.get(796626920L));
		destinations.add(Misc.vertices.get(796626918L));
		destinations.add(Misc.vertices.get(4050342386L));
		destinations.add(Misc.vertices.get(2145640257L));
		destinations.add(Misc.vertices.get(2145640267L));
		System.out.printf("Total vertices: %d\n", destinations.size());
		long init = System.currentTimeMillis();
		HashMap<Vertex, Double> dij = ShortestPath.dijkstra(source);
		double temp = System.currentTimeMillis() - init;
		System.out.printf(Locale.ROOT, "Dijkstra: %.3fs\n", temp / 1000.0);
		boolean valid = true;
		double aStar;
		long aStarAvg = 0;
		for (Vertex v : destinations) {
			init = System.currentTimeMillis();
			aStar = ShortestPath.aStar(source, v);
			temp = System.currentTimeMillis() - init;
			aStarAvg += temp;
			if (dij.get(v) == null || aStar == -1) {
				if (dij.get(v) == null && aStar == -1) {
					System.out.printf("%s not connected to %s\n", source, v);
					continue;
				}
				if (dij.get(v) == null && aStar != -1)
					System.out.println("dij not working");
				if (aStar == -1 && dij.get(v) != null)
					System.out.println("A* not working");
				valid = false;
				break;
			}
			if (Double.compare(dij.get(v), aStar) != 0)
				valid = false;
		}
		aStarAvg /= destinations.size();
		System.out.printf(Locale.ROOT, "A*: %.3fs\n", aStarAvg / 1000.0);
		assertEquals(valid, true);
	}

}
