import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class UVa10672_MarblesOnATree {
	static int n, j, m, f, marbles[], father[], cont, needed[], roadToRuin[];
	static StringTokenizer st;
	static HashSet<Integer>[] sons;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		while ((n = Integer.parseInt(in.readLine())) != 0) {
			marbles = new int[n];
			father = new int[n];
			sons = new HashSet[n];
			roadToRuin = new int[n];
			needed = new int[n];
			cont = 0;
			for (int i = 0; i < n; i++) {
				sons[i] = new HashSet<Integer>();
				father[i] = -1;
				needed[i] = 1;
			}
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());
				f = Integer.parseInt(st.nextToken()) - 1;
				marbles[f] = Integer.parseInt(st.nextToken());
				m = Integer.parseInt(st.nextToken());
				for (int k = 0; k < m; k++) {
					j = Integer.parseInt(st.nextToken()) - 1;
					father[j] = f;
					sons[f].add(j);
				}
			}

			for (int i = 0; i < n; i++)
				if (father[i] == -1) {
					f = i;
					break;
				}
			preord(f);

//			System.out.println(Arrays.toString(marbles));
//			System.out.println(Arrays.toString(roadToRuin));
			cont=0;
			solve();
			out.println(cont);
		}

		out.close();
	}

	private static void preord(int x) {
		for (int y : sons[x]) {
			preord(y);
		}
		roadToRuin[cont++] = x;
	}

	private static void solve() {
		for (int k = 0; k < n - 1; k++) {
			int x = roadToRuin[k];
			if (marbles[x] >= needed[x]) {
				marbles[x] -= needed[x];
				cont+= marbles[x];
				marbles[father[x]] += marbles[x];
			} else {
				needed[father[x]] += needed[x]-marbles[x];
				cont += needed[x]-marbles[x];
			}
//			System.out.println(x+" "+cont);
//			System.out.println("nodo 1 -> "+needed[1]);
		}
	}

}
