package structures.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.junit.Test;

import io.Input;
import others.Misc;
import structures.Pair;
import structures.Point;
import structures.Vertex;

/**
 * Checks whether the Pair class is working properly.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class TestsPair {

	/**
	 * Tests whether the pairs are being extracted from the priority queue by
	 * the smallest value of the Pair's vertex.
	 * 
	 * The file contains the Maps URL containing the points.
	 * 
	 * @throws FileNotFoundException
	 *             If the file containing the points is not found.
	 */
	@Test
	public void compareTo() throws FileNotFoundException {
		Input.readVertices("vertices-clean.txt");
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		Misc.originalVertex = new HashMap<Vertex, Point>();
		ArrayList<Vertex> temp = Input.readPoints("points.txt", false);
		Misc.sortPoints(temp);
		int i = 0;
		for (Vertex v : temp)
			pq.add(new Pair(v, i++));
		double[] values = new double[i];
		i = 0;
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			values[i++] = p.getValue();
		}
		boolean valid = true;
		// Assuming at least two points
		for (int k = 1; k < i; ++k)
			if (Double.compare(values[k - 1], values[k]) != -1) {
				valid = false;
				break;
			}
		assertEquals(valid, true);
	}

}
