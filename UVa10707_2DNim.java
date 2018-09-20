/*
                                                                                      
      _/_/        _/_/_/                     _/      _/       _/                      
   _/    _/      _/    _/                   _/_/    _/                _/_/_/  _/_/    
      _/        _/    _/    _/_/_/_/_/     _/  _/  _/       _/       _/    _/    _/   
   _/          _/    _/                   _/    _/_/       _/       _/    _/    _/    
_/_/_/_/      _/_/_/                     _/      _/       _/       _/    _/    _/     
                                                                                      
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa10707_2DNim {
	/*
	 * public static void main(String[] args) throws NumberFormatException,
	 * IOException { BufferedReader in = new BufferedReader(new
	 * InputStreamReader(System.in)); PrintWriter out = new PrintWriter(System.out);
	 * StringTokenizer st; int ttt = Integer.parseInt(in.readLine()), n,j; int[][]
	 * f1, f2, c1, c2; while (ttt-- != 0) { st = new StringTokenizer(in.readLine());
	 * c1 = new int[Math.max(Integer.parseInt(st.nextToken()),
	 * Integer.parseInt(st.nextToken()))][2]; c2 = new int[c1.length][2]; n =
	 * Integer.parseInt(st.nextToken()); f1 = new int[n][2]; f2 = new int[n][2]; st
	 * = new StringTokenizer(in.readLine()); for (int i = 0; i < n; i++) { f1[i][0]
	 * = Integer.parseInt(st.nextToken()); f1[i][1] =
	 * Integer.parseInt(st.nextToken()); c1[f1[i][0]][0]++; c1[f1[i][1]][1]++; } st
	 * = new StringTokenizer(in.readLine()); for (int i = 0; i < n; i++) { f2[i][0]
	 * = Integer.parseInt(st.nextToken()); f2[i][1] =
	 * Integer.parseInt(st.nextToken()); c2[f2[i][0]][0]++; c2[f2[i][1]][1]++; } for
	 * (int i = 0; i < n; i++) { f1[i][0] = c1[f1[i][0]][0]; f1[i][1] =
	 * c1[f1[i][1]][1]; f2[i][0] = c2[f2[i][0]][0]; f2[i][1] = c2[f2[i][1]][1]; }
	 * 
	 * for (int i = 0; i < n; i++) { System.out.println(f2[i][0]+", "+f2[i][1]); }
	 * 
	 * Arrays.sort(f1, (a, b) -> { if (a[0] > b[0]) return 1; else if (a[0] < b[0])
	 * return -1; else return Integer.compare(a[1], b[1]); }); Arrays.sort(f2, (a,
	 * b) -> { if (a[0] > b[0]) return 1; else if (a[0] < b[0]) return -1; else
	 * return Integer.compare(a[1], b[1]); });
	 * 
	 * for (j = 0; j < n; j++) if(f1[j][0]!=f2[j][0]||f1[j][1]!=f2[j][1]) break;
	 * if(j==n) out.println("YES"); else { Arrays.sort(f2, (a, b) -> { if (a[1] >
	 * b[1]) return 1; else if (a[1] < b[1]) return -1; else return
	 * Integer.compare(a[0], b[0]); }); for (j = 0; j < n; j++)
	 * if(f1[j][0]!=f2[j][1]||f1[j][1]!=f2[j][0]) break; if(j==n)
	 * out.println("YES"); else out.println("NO");
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * } out.close(); }
	 */

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		int ttt = Integer.parseInt(in.readLine()), w, h, n, j, res[][], r1[], r2[];
		boolean[][] tab;
		while (ttt-- != 0) {
			st = new StringTokenizer(in.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			res = new int[w][h];
			tab = new boolean[w][h];
			r1 = new int[n];
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < n; i++)
				tab[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			Rain1(res, tab);
			Rain2(res, tab);
			Rain3(res, tab);
			Rain4(res, tab);
			getResult(res, r1);

			res = new int[w][h];
			tab = new boolean[w][h];
			r2 = new int[n];
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < n; i++)
				tab[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			Rain1(res, tab);
			Rain2(res, tab);
			Rain3(res, tab);
			Rain4(res, tab);
			getResult(res, r2);
			Arrays.sort(r1);
			Arrays.sort(r2);
			for (j = 0; j < n; j++)
				if (r1[j] != r2[j])
					break;

			if (j == n)
				out.println("YES");
			else
				out.println("NO");

		}
		out.close();
	}

	private static void getResult(int[][] res, int[] r) {
		int index = 0;
		for (int i = 0; i < res.length; i++)
			for (int j = 0; j < res[0].length; j++)
				if (res[i][j] != 0)
					r[index++] = res[i][j];

	}

	private static void Rain4(int[][] res, boolean[][] tab) {// derecha
		int x = 0;
		for (int i = 0; i < tab.length; i++) {
			x = 0;
			for (int j = tab[0].length - 1; j >= 0; j--)
				if (tab[i][j]) {
					res[i][j] += x++;
					x++;
				} else
					x = 0;

		}
	}

	private static void Rain3(int[][] res, boolean[][] tab) {// izquierda
		int x = 0;
		for (int i = 0; i < tab.length; i++) {
			x = 0;
			for (int j = 0; j < tab[0].length; j++)
				if (tab[i][j]) {
					res[i][j] += x++;
					x++;
				} else
					x = 0;

		}
	}

	private static void Rain2(int[][] res, boolean[][] tab) {// abajo
		int x = 0;
		for (int j = 0; j < tab[0].length; j++) {
			x = 0;
			for (int i = tab.length - 1; i >= 0; i--)
				if (tab[i][j]) {
					res[i][j] += x++;
					x++;
				} else
					x = 0;

		}
	}

	private static void Rain1(int[][] res, boolean[][] tab) {// arriba
		int x = 0;
		for (int j = 0; j < tab[0].length; j++) {
			x = 0;
			for (int i = 0; i < tab.length; i++)
				if (tab[i][j]) {
					res[i][j] += x++;
					x++;
				} else
					x = 0;

		}
	}

	class ficha {
		int Ver;
		int Hor;

		public ficha(int v, int h) {
			Ver = v;
			Hor = h;
		}
	}
}
