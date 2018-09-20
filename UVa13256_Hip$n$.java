import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class UVa13256_Hip$n${

	private static boolean theres(int q0, int q1, Set<Integer> tab) {
		return tab.contains(q0+q1*400);
	}

	private static boolean loses(Set<Integer> tab, int x, int y) {
		if (tab.size() < 3)
			return false;
		int xp, yp;
		for (int i : tab) {
			int yi = i / 400;
			int xi = i - yi * 400;
			xp = (xi + x) / 2;
			yp = (yi + y) / 2;

			int p0 = xi - x;
			int p1 = yi - y;

			int qa0 = -p1;
			int qa1 = p0;

			if (qa1 % 2 != 0 || qa0 % 2 != 0)
				continue;
			qa0 = qa0 / 2 + xp;
			qa1 = qa1 / 2 + yp;
			
			int qb0 = p1;
			int qb1 = -p0;
			
			if (qb1 % 2 != 0 || qb0 % 2 != 0)
				continue;
			qb0 = qb0 / 2 + xp;
			qb1 = qb1 / 2 + yp;
			
		
//			System.out.println(tab.get(i)[0]+" "+tab.get(i)[1]);
			/*
			 * if (x == 1 && y == 2 && tab.get(i)[0]==1&& tab.get(i)[1]==0) { if (theresP(Q,
			 * tab) && theresP(Q2, tab)) return true; } else
			 */
			if (theres(qa0,qa1, tab) && theres(qb0,qb1, tab))
				return true;

		}

		return false;
	}



	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));
		PrintWriter out = new PrintWriter(System.out);
		int n, x, y, i, it;
		Set<Integer> tab1;
		Set<Integer> tab2;

		StringTokenizer st;

		while (true) {
			try {
				n = Integer.parseInt(in.readLine());
			} catch (Exception e) {
				break;
			}

			tab1 = new HashSet<Integer>();
			tab2 = new HashSet<Integer>();

			st = new StringTokenizer(in.readLine());
			n = n * n;

			for (i = 0, it = 1; i < n; i++, it *= -1) {
				x = Integer.parseInt(st.nextToken()) * 2;
				y = Integer.parseInt(st.nextToken()) * 2;
				if (it > 0) {
					if (loses(tab1, x, y)) {
						break;
					}
					tab1.add(x + y * 400);
				} else {
					if (loses(tab2, x, y))
						break;
					tab2.add(x + y * 400);
				}

			}
			if (i == n)
				out.println(0);
			else if (it > 0)
				out.println(1);
			else
				out.println(2);
		}

		out.close();
	}

}