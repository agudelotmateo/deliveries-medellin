package others.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import others.CompareVertex;
import structures.Vertex;

/**
 * Checks whether the CompareVertex class is working properly.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class TestsCompareVertex {

	/**
	 * Tests whether the vertices are being properly sorted in clockwise-order
	 * around a given vertex (the first one).
	 */
	@Test
	public void testCompare() {
		Vertex[] vertices = new Vertex[10];
		Vertex center = new Vertex(-1, 1, 0);
		int i = 0;
		vertices[i++] = center;
		vertices[i++] = new Vertex(i, -6, 5);
		vertices[i++] = new Vertex(i, 0, -3);
		vertices[i++] = new Vertex(i, -1, 4);
		vertices[i++] = new Vertex(i, -1, -3);
		vertices[i++] = new Vertex(i, 0, 10);
		vertices[i++] = new Vertex(i, 2, 1);
		vertices[i++] = new Vertex(i, 2, -1);
		vertices[i++] = new Vertex(i, 3, 0);
		vertices[i++] = new Vertex(i, 3, 3);
		Arrays.sort(vertices, new CompareVertex(center));
		Vertex[] answer = new Vertex[10];
		i = 0;
		answer[i++] = center;
		answer[i++] = new Vertex(i, 3, 0);
		answer[i++] = new Vertex(i, 2, 1);
		answer[i++] = new Vertex(i, 3, 3);
		answer[i++] = new Vertex(i, 0, 10);
		answer[i++] = new Vertex(i, -1, 4);
		answer[i++] = new Vertex(i, -6, 5);
		answer[i++] = new Vertex(i, -1, -3);
		answer[i++] = new Vertex(i, 0, -3);
		answer[i++] = new Vertex(i, 2, -1);
		assertArrayEquals(vertices, answer);
	}

}
