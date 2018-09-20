import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class UVa280_Vertex {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		StringBuilder sb = new StringBuilder("");
		int n;
		boolean vis[];
		HashSet<Integer>[] g;
		while (true) {
			n = Integer.parseInt(in.readLine());
			if (n == 0)
				break;
			st = new StringTokenizer(in.readLine());
			g = new HashSet[n];
			vis = new boolean[n];
			for (int i = 0; i < g.length; i++)
				g[i] = new HashSet<>();

			while (st.countTokens() > 1) {
				int u = Integer.parseInt(st.nextToken()) - 1;
				int m = st.countTokens();
				for (int i = 1; i < m; i++)
					g[u].add(Integer.parseInt(st.nextToken()) - 1);
				st = new StringTokenizer(in.readLine());
			}
			st = new StringTokenizer(in.readLine());
			int m = Integer.parseInt(st.nextToken());
			
			
			
			
			
			
			
			for (int i = 0; i < m; i++) {
				sb.setLength(0);
				int cont = 0;
				Arrays.fill(vis, false);
				dfs(g, vis, Integer.parseInt(st.nextToken()) - 1);
				for (int j = 1; j <= vis.length; j++) {
					if (!vis[j - 1]) {
						cont++;
						sb.append(" " + j);
					}
				}
				sb.insert(0, cont);
				out.println(sb.toString());
			}
		}
		out.close();

	}

	private static void dfs(HashSet<Integer>[] g, boolean[] vis, int i) {
		for (int x: g[i]) {
			if(!vis[x]) {
				vis[x]=true;
				dfs(g, vis, x);
			}
		}

	}
}
