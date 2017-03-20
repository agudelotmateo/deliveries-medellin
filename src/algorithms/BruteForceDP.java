package algorithms;

import java.util.ArrayList;

import others.Misc;
import structures.Vertex;

/**
 * Contains the method to compute the TSP route using brute force method with
 * top-down Dynamic Programming optimizations. Based on Competitive Programming,
 * 3rd Ed. by Steven and Felix Halim, Pgs. 110-111 and <a href=
 * "https://github.com/evandrix/SPOJ/blob/master/DP_Main112/Solving-Traveling-Salesman-Problem-by-Dynamic-Programming-Approach-in-Java.pdf">
 * this</a> to reconstruct the path.
 * 
 * @author Catalina Patino Forero
 * @author Mateo Agudelo Toro
 */
public class BruteForceDP {
	public static final int MAX = 20;
	public static double distance;
	public static ArrayList<Vertex> path;
	private static int[][] parent;
	private static double[][] memo;
	private static int n;

	/**
	 * Computes the TSP route using Dynamic Programming method.
	 * 
	 * Total distance is stored in 'distance', and path in 'path'.
	 */
	public static void compute() {
		n = Misc.subGraph.length;
		memo = new double[n][1 << n];
		parent = new int[n][1 << n];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < 1 << n; ++j) {
				memo[i][j] = Double.MIN_VALUE;
				parent[i][j] = -1;
			}
		distance = tsp(0, 1);
		path = new ArrayList<>();
		Vertex center = Misc.points.get(0);
		path.add(center);
		getPath(0, 1);
		path.add(center);
	}

	/**
	 * Computes the tour with minimum distance.
	 * 
	 * @param pos
	 *            Initial position.
	 * @param bitmask
	 *            Initial bitmask.
	 * @return Total distance.
	 */
	private static double tsp(int pos, int bitmask) {
		if (bitmask == (1 << n) - 1)
			return Misc.subGraph[pos][0];
		if (Double.compare(memo[pos][bitmask], Double.MIN_VALUE) != 0)
			return memo[pos][bitmask];
		double ans = Double.MAX_VALUE, temp;
		for (int nxt = 0; nxt < n; ++nxt)
			if (nxt != pos && (bitmask & (1 << nxt)) == 0) {
				temp = Misc.subGraph[pos][nxt] + tsp(nxt, bitmask | (1 << nxt));
				if (Double.compare(temp, ans) == -1) {
					ans = temp;
					parent[pos][bitmask] = nxt;
				}
			}
		memo[pos][bitmask] = ans;
		return ans;
	}

	/**
	 * Reconstructs the path.
	 * 
	 * @param pos
	 *            Initial position.
	 * @param bitmask
	 *            Initial bitmask.
	 */
	private static void getPath(int pos, int bitmask) {
		if (parent[pos][bitmask] == -1)
			return;
		int x = parent[pos][bitmask];
		path.add(Misc.points.get(x));
		getPath(x, bitmask | (1 << x));
	}
}
