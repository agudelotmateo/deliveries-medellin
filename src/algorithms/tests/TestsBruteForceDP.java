package algorithms.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.junit.Test;

import algorithms.BruteForceDP;
import io.Input;
import others.Misc;
import structures.Point;
import structures.Vertex;

/**
 * Checks whether the methods in the BruteForceDP class are working properly.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class TestsBruteForceDP {

	/**
	 * Tests whether the BruteForceDP method to find a tour is finding and
	 * storing it properly. This is a "long" test (about 30s on my machine) that
	 * uses 20 random valid points (picked by hand) whose URL is stored on file
	 * "20-points.txt".
	 * 
	 * @throws FileNotFoundException
	 *             if either the file containing the vertices, the edges and/or
	 *             the points was not found.
	 */
	@Test
	public void test() throws FileNotFoundException {
		Input.readVertices("vertices-clean.txt");
		Input.readEdges("edges-clean.txt");
		Misc.originalVertex = new HashMap<Vertex, Point>();
		Misc.points = Input.readPoints("20-points.txt", false);
		Misc.buildSubGraph();
		BruteForceDP.compute();
		assertEquals(Double.compare(Math.abs(Misc.sumDistances(BruteForceDP.path) - BruteForceDP.distance), 1e-9), -1);
	}

}
