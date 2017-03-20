package structures.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import io.Input;
import io.InputReader;
import others.Misc;
import structures.Edge;
import structures.Vertex;

/**
 * Checks whether the Edge class is working properly.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class TestsEdge {

	/**
	 * Tests if the Edge class (and thus the graph) is working properly.
	 * 
	 * @throws FileNotFoundException
	 *             If the file containing the vertices or the file containing
	 *             the edges was not found.
	 */
	@Test
	public void test() throws FileNotFoundException {
		Input.readVertices("vertices-clean.txt");
		Input.readEdges("edges-clean.txt");
		InputReader in = new InputReader(new FileInputStream(new File("input-files/edges-clean.txt")));
		boolean all = true;
		while (in.hasNext()) {
			Vertex source = Misc.vertices.get(in.nextLong());
			Vertex destination = Misc.vertices.get(in.nextLong());
			double cost = in.nextDouble();
			in.remainingLine();
			if (!Misc.graph.get(source).contains(new Edge(destination, cost))) {
				all = false;
				break;
			}
		}
		assertEquals(all, true);
	}

}
