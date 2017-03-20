package others.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import others.Extra;

/**
 * Tests whether the methods in the Extra class are working properly.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class TestsExtra {

	/**
	 * Tests whether the graph cleaning algorithm is doing its job. Note: this
	 * is a long test! About 20 seconds on my machine.
	 * 
	 * @throws IOException
	 *             If any of the files containing the original vertices and/or
	 *             edges was not found.
	 */
	@Test
	public void buildClean() throws IOException {
		Extra.buildCleanGraph();
		assertEquals(Extra.edges, 335719);
		assertEquals(Extra.vertices, 179403);
	}

}
