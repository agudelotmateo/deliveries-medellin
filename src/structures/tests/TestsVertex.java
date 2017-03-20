package structures.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map.Entry;

import org.junit.Test;

import io.Input;
import others.Misc;
import structures.Vertex;

/**
 * Checks whether the Vertex class is working properly.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class TestsVertex {

	/**
	 * Tests whether the hashing function and equals function of the Vertex's
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
		for (Entry<Long, Vertex> entry : Misc.vertices.entrySet()) {
			Vertex v = entry.getValue();
			if (!Misc.pointToVertex.containsKey(v.getPoint()) || !Misc.vertices.containsKey(v.getId())) {
				all = false;
				break;
			}

		}
		assertEquals(all, true);
	}

}
