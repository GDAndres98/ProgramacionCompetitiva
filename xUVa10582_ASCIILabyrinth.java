import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class xUVa10582_ASCIILabyrinth {

	public static int res[][], m, n;
	public static boolean vis[][];

	private static void solve(int[][] roads, int i, int j, int where) {
		if (i >= m || j >= n || j < 0 || i < 0)
			return;
		if (vis[i][j])
			return;
		if (i == m - 1 && j == n - 1) {
			res[i][j]++;
			return;
		}

		vis[i][j] = true;
		if (roads[i][j] == 1) {
			if (where < 3) {
				solve(roads, i + 1, j, 1);
				solve(roads, i - 1, j, 2);
			} else {
				solve(roads, i, j + 1, 3);
				solve(roads, i, j - 1, 4);
			}

		} else if (roads[i][j] == 2) {
			if (where > 2) {
				solve(roads, i + 1, j, 1);
				solve(roads, i - 1, j, 2);
			} else {
				solve(roads, i, j + 1, 3);
				solve(roads, i, j - 1, 4);
			}

		}

		vis[i][j] = false;
		return;
	}

	private static void solve(int[][] roads, int i, int j) {
		vis[i][j] = true;
		solve(roads, i + 1, j, 3);
		solve(roads, i, j + 1, 1);

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		int roads[][], sol = 0;
		StringTokenizer st;
		while (t-- != 0) {
			st = new StringTokenizer(in.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			roads = new int[m][n];
			res = new int[m][n];
			vis = new boolean[m][n];
			in.readLine();
			for (int i = 0; i < m; i++) {
				in.readLine();
				st = new StringTokenizer(in.readLine(), "|");
				for (int j = 0; j < n; j++) {
					switch (st.nextToken().trim().length()) {
					case 0:
						roads[i][j] = 0;
						break;
					case 1:
						roads[i][j] = 1;
						break;
					case 2:
						roads[i][j] = 2;
						break;
					case 3:
						roads[i][j] = 1;
						break;
					}
				}
				in.readLine();
				in.readLine();
			}

			if (m == n && n == 1)
				sol = 2;
			else if (roads[0][0] == 0 || roads[m - 1][n - 1] == 0)
				sol = 0;
			else {
				solve(roads, 0, 0);
				sol = res[m - 1][n - 1];
			}

			out.println("Number of solutions: " + sol);

		}

		out.close();
	}

}
/*
1
4 6
+---+---+---+---+---+---+
|   |   |   |   |   |   |
|***|***|** |** |** |***|
|   |   | * | * | * |   |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
|   |** |** |***|** |** |
|   | * | * |   | * | * |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
|***|** |***|***|***|** |
|   | * |   |   |   | * |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
|** |   |***|   |** |** |
| * |   |   |   | * | * |
+---+---+---+---+---+---+
*/