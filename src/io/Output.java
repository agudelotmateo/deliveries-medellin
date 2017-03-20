package io;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import others.Misc;
import structures.Point;
import structures.Vertex;

/**
 * Class containing the methods to show the user the results.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class Output {
	/**
	 * Given a list of vertices, it shows them on Google Maps. The gaps are
	 * completed using Maps's algorithms and may differ from the solution
	 * presented here. Based on
	 * <a href="http://www.programcreek.com/java-api-examples/java.awt.Desktop">
	 * this</a>.
	 * 
	 * @param vertices
	 *            List of vertices to be visited.
	 */
	public static void openPointsOnMaps(ArrayList<Vertex> vertices) {
		StringBuilder sb = new StringBuilder();
		sb.append(Misc.MAPS_PREFIX);
		int n = vertices.size();
		Point t;
		for (int i = 0; i < n; ++i) {
			if (Misc.originalVertex.containsKey(vertices.get(i)))
				t = Misc.originalVertex.get(vertices.get(i));
			else
				t = vertices.get(i).getPoint();
			sb.append("/" + t.getX() + "," + t.getY());
		}
		String url = sb.toString();
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.BROWSE)) {
				try {
					desktop.browse(new URI(url));
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} catch (URISyntaxException use) {
					use.printStackTrace();
				}
			}
		}
		System.out.println("Opening: " + url);
	}
}
