package structures.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Map.Entry;

import org.junit.Test;

import io.Input;
import others.Misc;
import structures.Point;
import structures.Vertex;

/**
 * Checks whether the Point class is working properly.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class TestsPoint {

	/**
	 * Tests whether the hashing function and equals function of the Point's
	 * class are working fine. If it does, it means that the class can be
	 * properly used as key from HashMaps and inserted onto HashSets.
	 * 
	 * @throws FileNotFoundException
	 *             If the file containing the vertices is not found.
	 */
	@Test
	public void hashingAndEquals() throws FileNotFoundException {
		Input.readVertices("vertices-clean.txt");
		boolean all = true;
		for (Entry<Long, Vertex> entry : Misc.vertices.entrySet())
			if (!Misc.pointToVertex.containsKey(entry.getValue().getPoint())) {
				all = false;
				break;
			}
		assertEquals(all, true);
	}

	/**
	 * Tests whether the the points are being sorted in non-descending order,
	 * first by x coordinate, and if tie, by y coordinate.
	 */
	@Test
	public void compareTo() {
		Point[] points = new Point[5];
		int i = 0;
		points[i++] = new Point(10, 4);
		points[i++] = new Point(1, 2);
		points[i++] = new Point(1, -10);
		points[i++] = new Point(1, 0);
		points[i++] = new Point(-1000, -1000);
		Arrays.sort(points);
		Point[] answer = new Point[5];
		i = 0;
		answer[i++] = new Point(-1000, -1000);
		answer[i++] = new Point(1, -10);
		answer[i++] = new Point(1, 0);
		answer[i++] = new Point(1, 2);
		answer[i++] = new Point(10, 4);
		assertArrayEquals(points, answer);
	}

}
