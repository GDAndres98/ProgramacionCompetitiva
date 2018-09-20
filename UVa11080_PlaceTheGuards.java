import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVa11080_PlaceTheGuards {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			ArrayList<Integer>[] x = new ArrayList[v];
			ArrayList<HashSet<Integer>> set = new ArrayList<>();
			int color[] = new int[v];
			for (int j = 0; j < v; j++) {
				x[j] = new ArrayList<Integer>();
			}
			for (int j = 0; j < e; j++) {
				st = new StringTokenizer(in.readLine());
				int o = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				x[o].add(d);
				x[d].add(o);
			}

			out.println(solve(x));

		}

		out.close();
	}

	// 13 10
	// 0 5
	// 5 4
	// 5 3
	// 5 2
	// 5 1
	// 5 6
	// 7 8
	// 7 9
	// 7 10
	// 7 11

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
							} else if (color[l[x].get(j)] == it) {
								posible = false;
							return -1;	
							}
						}
					}
				}
			}
			if (posible) {
				if(c1==0)
					total +=c2;
				else if(c2==0)
					total +=c1;
				else
					total +=Math.min(c1, c2);
			}
		}

		return total;
	}

}
