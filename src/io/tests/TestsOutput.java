package io.tests;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import io.Input;
import io.Output;
import others.Misc;
import structures.Point;
import structures.Vertex;

/**
 * Checks whether the methods in the Output class are working properly.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 *
 **/
public class TestsOutput {

	/**
	 * Test the method to open the vertices on Maps.
	 * 
	 * @throws FileNotFoundException
	 *             If the file containing the all vertices of the map or the one
	 *             containing the points to show on map is not found.
	 */
	@Test
	public void openPointsOnMaps() throws FileNotFoundException {
		Misc.originalVertex = new HashMap<Vertex, Point>();
		Input.readVertices("vertices-clean.txt");
		ArrayList<Vertex> vertices = Input.readPoints("points.txt", false);
		Misc.sortPoints(vertices);
		Output.openPointsOnMaps(vertices);
	}

}
