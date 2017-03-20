package others;

import java.util.Comparator;

import structures.Point;
import structures.Vertex;

/**
 * Comparator to sort the vertices based on its 2D coordinates anti-clockwise
 * around a given vertex (center). Based on <a href=
 * "https://github.com/mvpossum/eldiego/blob/master/geometria/orden.radial.cpp">
 * this</a>.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class CompareVertex implements Comparator<Vertex> {
	private Point center;

	/**
	 * Creates a new vertex comparator around the given vertex.
	 * 
	 * @param center
	 *            Vertex to sort with respect to.
	 */
	public CompareVertex(Vertex center) {
		this.center = center.getPoint();
	}

	private int quadrand(Point a) {
		if (a.getX() > 0 && a.getY() >= 0)
			return 0;
		if (a.getX() <= 0 && a.getY() > 0)
			return 1;
		if (a.getX() < 0 && a.getY() <= 0)
			return 2;
		if (a.getX() >= 0 && a.getY() < 0)
			return 3;
		return -1;
	}

	private boolean comp(Point p1, Point p2) {
		int c1 = quadrand(p1), c2 = quadrand(p2);
		if (c1 == c2)
			return Double.compare(p1.getY() * p2.getX(), p1.getX() * p2.getY()) == -1;
		else
			return c1 < c2;
	}

	@Override
	public int compare(Vertex v1, Vertex v2) {
		Point p1 = v1.getPoint();
		Point p2 = v2.getPoint();
		Point t1 = new Point(p1.getX() - center.getX(), p1.getY() - center.getY());
		Point t2 = new Point(p2.getX() - center.getX(), p2.getY() - center.getY());
		return comp(t1, t2) ? -1 : 1;
	}

}
