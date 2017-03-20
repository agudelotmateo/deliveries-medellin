package io.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;

import io.Input;
import others.Misc;
import structures.Edge;
import structures.Point;
import structures.Vertex;

/**
 * Checks whether the methods in the Input class are working properly.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 *
 **/
public class TestsInput {

	/**
	 * Tests whether the vertices are being properly read and stored.
	 * 
	 * @throws FileNotFoundException
	 *             If the file containing the vertices was not found.
	 */
	@Test
	public void readVertices() throws FileNotFoundException {
		final int V = 179403;
		Input.readVertices("vertices-clean.txt");
		assertEquals(Misc.vertices.size(), V);
		assertEquals(Misc.pointToVertex.size(), V);
	}

	/**
	 * Tests whether the edges are being properly read and stored.
	 * 
	 * @throws FileNotFoundException
	 *             If either the file containing the vertices and or the one
	 *             containing the edges was not found.
	 */
	@Test
	public void readEdges() throws FileNotFoundException {
		Input.readVertices("vertices-clean.txt");
		Input.readEdges("edges-clean.txt");
		boolean valid = true;
		for (HashMap.Entry<Vertex, HashSet<Edge>> entry : Misc.graph.entrySet())
			if (entry.getValue() == null || entry.getValue().size() == 0)
				valid = false;
		assertEquals(valid, true);
	}

	/**
	 * Tests whether the points are being properly parsed from Maps URL, put
	 * into the ArrayList and "sorted" (at least no exceptions are thrown, but
	 * CompareVertex class has already been tested so it should work).
	 * 
	 * @throws FileNotFoundException
	 *             If any of the files containing vertices or points was not
	 *             found.
	 */
	@Test
	public void readPoints() throws FileNotFoundException {
		Misc.originalVertex = new HashMap<Vertex, Point>();
		Input.readVertices("vertices-clean.txt");
		ArrayList<Vertex> tour = Input.readPoints("points.txt", false);
		Misc.sortPoints(tour);
		assertEquals(tour.size(), 10);
	}

	/**
	 * Checks whether the findNearest function is finding points close enough.
	 * Uses random valid vertices (picked by hand).
	 * 
	 * @throws FileNotFoundException
	 *             If the file containing the vertices was not found.
	 */
	@Test
	public void findNearest() throws FileNotFoundException {
		Input.readVertices("vertices-clean.txt");
		assertEquals(closeEnough(new Point(6.2474335, -75.5620597)), true);
		assertEquals(closeEnough(new Point(6.2828405, -75.5847190)), true);
		assertEquals(closeEnough(new Point(6.2447886, -75.6125282)), true);
		assertEquals(closeEnough(new Point(6.2842056, -75.5545066)), true);
		assertEquals(closeEnough(new Point(6.2447886, -75.6125282)), true);
	}

	private boolean closeEnough(Point a) {
		final double EPS = 0.005;
		Point nearest = Input.findNearest(a).getPoint();
		Double dx = Math.abs(a.getX() - nearest.getX());
		Double dy = Math.abs(a.getY() - nearest.getY());
		return Double.compare(dx, EPS) == -1 && Double.compare(dy, EPS) == -1;
	}

}
