import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class xUVa12520_SquareGarden {

	private static long solve(int l, int n) {
		long x = 0, y = 0, n2 = n;
		int capes;
		int c;
		for (capes = l; capes > 0 && n > 0; capes -= 2) {
			if (capes == 1) {
				x += 4;
				n--;
			}
			if (capes == 2) {
				if (n == 1)
					x += 4;
				else {
					x += 8;
					n -= 2;
				}
			} else {
				c = capes % 2 == 0 ? capes / 2 : capes / 2 + 1;
				for (int i = 0; i < (c - 1) * 4 && n > 0; i++, n--) {
					if ((c - 1) * 4 >= n) {
						n -= (c - 1) * 4;
						x += (c - 1) * 4 * 4;
						break;
					}
					x += 4;
				}

			}
		}

		for (capes = l; capes > 1 && n2 > 0; capes -= 2) {
			if (capes == 2) {
				if (n2 == 1)
					y += 4;
				else {
					y += 8;
					n2 -= 2;
				}
			} else {
				c = capes / 2;
				for (int i = 0; i < (c) * 4 && n2 > 0; i++, n2--) {
					if ((c) * 4 >= n2) {
						n2 -= (c) * 4;
						y += (c) * 4 * 4;
						break;
					}
					y += 4;
				}
			}

		}

		System.out.println(x + ", " + y);
		if (n2 == 0 || n == 0)
			return Math.max(x, y);

		capes = l;
		while (n > 0) {
			if (capes == 2) {

				if (n == 1)
					x = l == 2 ? x : x - 4;
				else {
					x -= 8;
					n -= 2;
				}
			} else {
				c = capes / 2;
				for (int i = 0; i < (c - 1) * 4 && n > 0; i++, n--) {
					x -= 2;
					if (n <= 0)
						break;
				}

			}
			capes -= 2;
		}
		capes = l;
		while (n2 > 0) {
			if (capes == 1) {
				n2--;
				y -= 4;
			} else if (capes == 2) {
				if (n2 == 1) {
					y = l == 2 ? y : y - 4;
				} else {
					y -= 8;
					n2 -= 2;
				}

			} else {
				c = capes % 2 == 0 ? capes / 2 : capes / 2 + 1;
				for (int i = 0; i < (c -1) * 4 && n2 > 0; i++, n2--) {
					y -= 2;
					if (n2 <= 0)
						break;
				}

			}
			capes -= 2;
		}
		System.out.println("x: " + x + ", y: " + y);
		return Math.max(x, y);
	}

	

	
	
	/*
	 * 1 0 1 1 2 3 3 8 0 0
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		int n, l;
		while (true) {
			st = new StringTokenizer(in.readLine());
			l = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			if (l == 0 && n == l)
				break;
			out.println(solve(l, n));

		}
		out.close();
	}

}
