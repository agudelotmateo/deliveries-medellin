package main;

import java.io.IOException;

import others.Misc;

/**
 * Runs the program.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class Main {

	/**
	 * Programs entry point.
	 * 
	 * @param args
	 *            Command line arguments (not used).
	 * @throws IOException
	 *             If any of the files containing the vertices, the edges or the
	 *             points was not found.
	 */
	public static void main(String[] args) throws IOException {
		Misc.initialize();
		Misc.UI();
	}

}
