import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class UVa11748_RiggingElections {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n, m, c, cont1, cont2;
		StringTokenizer st;
		int[][] pueblo;
		boolean vis[], won;
		Set<Integer>[] candidatos;

		while (true) {
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			pueblo = new int[m][n	];
			vis = new boolean[n];
			candidatos = new HashSet[n];
			for (int i = 0; i < n; i++)
				candidatos[i] = new HashSet<>();

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < n; j++)
					pueblo[i][Integer.parseInt(st.nextToken()) - 1] = n - j;
			}

			for (int c1 = 0; c1 < n - 1; c1++) {
				for (int c2 = c1 + 1; c2 < n; c2++) {
					cont1 = cont2 = 0;
					for (int i = 0; i < m; i++)
						if (pueblo[i][c1] > pueblo[i][c2])
							cont1++;
						else
							cont2++;
					if (cont1 > cont2)
						candidatos[c1].add(c2);
					else
						candidatos[c2].add(c1);
				}
			}
			
			dfs(candidatos,vis,(c-1));
			
			
			won = true;
			for (int i = 0; i < vis.length; i++)
				if (!vis[i]) {
					won = false;
					break;
				}

			if (won)
				out.println("yes");
			else
				out.println("no");

		}
		out.close();

	}

	private static void dfs(Set<Integer>[] candidatos, boolean[] vis, int c) {
		vis[c]=true;
		for(int x: candidatos[c]) 
			if(!vis[x])
				dfs(candidatos,vis,x);
		
		
	}
}
