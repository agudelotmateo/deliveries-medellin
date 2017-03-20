package others.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import io.Input;
import others.Misc;
import structures.Point;
import structures.Vertex;

/**
 * Checks whether the methods in the Misc class are working properly.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class TestsMisc {

	/**
	 * Tests the string (URL) fixing method for fixing the URL and read points
	 * properly
	 */
	@Test
	public void fixURL() {
		String s;
		s = "This must stay@and all this must be gone!";
		assertEquals("This must stay", Misc.fixURL(s));
		s = " @";
		assertEquals(" ", Misc.fixURL(s));
		s = "https://www.google.es/maps/dir/41.5178319,-3.3975221/41.5897694,-2.9072572/41.4221339,-3.0802918/@41.4597097,-3.3425904,10z/data=!4m2!4m1!3e0";
		assertEquals(
				"https://www.google.es/maps/dir/41.5178319,-3.3975221/41.5897694,-2.9072572/41.4221339,-3.0802918/",
				Misc.fixURL(s));
	}

	/**
	 * Tests the string cleaning method for removing everything after the first
	 * space (including).
	 */
	@Test
	public void clean() {
		String s;
		s = "Hey! ---- this must be gone!!!";
		assertEquals(Misc.clean(s), "Hey!");
		s = "This_must_stay Not_this";
		assertEquals(Misc.clean(s), "This_must_stay");
	}

	/**
	 * Tests whether the method to check if the user has entered a valid choice
	 * is working as expected.
	 */
	@Test
	public void validChoice() {
		Misc.points = new ArrayList<>(10);
		String s;
		s = "   5   ";
		assertEquals(Misc.validChoice(s), true);
		s = "3   ";
		assertEquals(Misc.validChoice(s), true);
		s = "   3";
		assertEquals(Misc.validChoice(s), true);
		s = ", 3 ";
		assertEquals(Misc.validChoice(s), false);
	}

	// sortPoints already tested on structures.tests.TestCompareVertex.java

	// comparePoints already tested on structures.tests.TestPoint.java

	// equalPoints already tested on structures.tests.TestPoint.java

	// sumDistances already tested on algorithms.tests.TestsBruteForceDP.java

	/**
	 * Tests whether the buildSubGraph method to find build complete subgraph
	 * using only the given points is working properly.
	 * 
	 * @throws FileNotFoundException
	 *             If either the file containing the vertices, the edges and/or
	 *             the points was not found.
	 */
	@Test
	public void buildSubGraph() throws FileNotFoundException {
		Misc.originalVertex = new HashMap<Vertex, Point>();
		Input.readVertices("vertices-clean.txt");
		Input.readEdges("edges-clean.txt");
		Misc.points = new ArrayList<Vertex>();
		Misc.points.add(Misc.vertices.get(415669500L));
		Misc.points.add(Misc.vertices.get(330253050L));
		Misc.points.add(Misc.vertices.get(3671034775L));
		Misc.points.add(Misc.vertices.get(2430583047L));
		Misc.points.add(Misc.vertices.get(2668612917L));
		Misc.points.add(Misc.vertices.get(2597308571L));
		Misc.points.add(Misc.vertices.get(1841414820L));
		Misc.points.add(Misc.vertices.get(1841414821L));
		Misc.points.add(Misc.vertices.get(551128952L));
		Misc.points.add(Misc.vertices.get(1366942064L));
		Misc.points.add(Misc.vertices.get(1946462782L));
		Misc.points.add(Misc.vertices.get(1946462779L));
		Misc.points.add(Misc.vertices.get(4051971896L));
		Misc.points.add(Misc.vertices.get(538247012L));
		Misc.points.add(Misc.vertices.get(1670691882L));
		Misc.points.add(Misc.vertices.get(558882523L));
		Misc.points.add(Misc.vertices.get(3320789635L));
		Misc.points.add(Misc.vertices.get(3320789636L));
		Misc.points.add(Misc.vertices.get(3320789637L));
		Misc.points.add(Misc.vertices.get(429909643L));
		Misc.points.add(Misc.vertices.get(3745182715L));
		Misc.points.add(Misc.vertices.get(3745182718L));
		Misc.points.add(Misc.vertices.get(3745182719L));
		Misc.points.add(Misc.vertices.get(3745182706L));
		Misc.points.add(Misc.vertices.get(3745182704L));
		Misc.points.add(Misc.vertices.get(796626920L));
		Misc.points.add(Misc.vertices.get(796626918L));
		Misc.points.add(Misc.vertices.get(4050342386L));
		Misc.points.add(Misc.vertices.get(2145640257L));
		Misc.points.add(Misc.vertices.get(2145640267L));
		assertEquals(Misc.buildSubGraph(), false);
		Misc.points = Input.readPoints("points.txt", false);
		assertEquals(Misc.buildSubGraph(), true);
	}

}
