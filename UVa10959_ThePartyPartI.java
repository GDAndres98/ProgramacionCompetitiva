import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVa10959_ThePartyPartI {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine()), n, p, r[];
		HashSet<Integer>[] l;
		StringTokenizer st;
		while (t-- != 0) {
			in.readLine();
			st = new StringTokenizer(in.readLine());
			p = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			l = new HashSet[p];
			r = new int[p];
			for (int i = 0; i < l.length; i++) {
				l[i] = new HashSet<Integer>();
				r[i] = Integer.MAX_VALUE - 1;
			}
			r[0] = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				l[a].add(b);
				l[b].add(a);
			}
			solve(r, l);
			for (int i = 1; i < p; i++)
				out.println(r[i]);
			if (t != 0)
				out.println();
		}
		out.close();
	}

	private static void solve(int[] r, HashSet<Integer>[] l) {
		Queue<Integer> q = new LinkedList<Integer>();
		HashSet<Integer> done = new HashSet<Integer>();
		q.add(0);
		while (!q.isEmpty()) {
			int x = q.poll();
			done.add(x);
			for (int i : l[x]) {
				r[x]=Math.min(r[x], r[i]+1);
				if (!done.contains(i))
					q.add(i);
			}
				
		}
	}
}