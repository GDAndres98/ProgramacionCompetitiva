import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVa10505_MontescovsCapuleto {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine()), p;
		ArrayList<Integer>[] l;
		StringTokenizer st;
		while (t-- != 0) {
			in.readLine();
			p = Integer.parseInt(in.readLine());
			l = new ArrayList[p];
			for (int i = 0; i < l.length; i++)
				l[i] = new ArrayList<Integer>();

			for (int i = 0; i < p; i++) {
				st = new StringTokenizer(in.readLine());
				st.nextToken();
				while (st.hasMoreTokens()) {
					int a = Integer.parseInt(st.nextToken()) - 1;
					if (a < p) {
						l[a].add(i);
						l[i].add(a);
					}
				}
			}
			out.println(solve(l));
		}
		out.close();
	}

	private static int solve(ArrayList<Integer>[] l) {
		boolean done[] = new boolean[l.length];
		short color[] = new short[l.length];
		short it;
		boolean posible = true;
		int c1 = 0, c2 = 0, total = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < done.length; i++) {
			c1 = c2 = 0;
			if (!done[i]) {
				it = -1;
				posible = true;
				color[i] = it;
				q.add(i);
				while (!q.isEmpty()) {
					int x = q.poll();
					it = color[x];
					if (!done[x]) {
						done[x] = true;
						if (it == 1)
							c1++;
						else
							c2++;
						for (int j = 0; j < l[x].size(); j++) {
							if (!done[l[x].get(j)]) {
								q.add(l[x].get(j));
								color[l[x].get(j)] = (short) (it * -1);
							} else if (color[l[x].get(j)] == it)
								posible = false;
						}
					}
				}
			}
			if (posible)
				total += Math.max(c1, c2);
		}

		return total;
	}

}