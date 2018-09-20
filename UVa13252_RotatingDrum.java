import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVa13252_RotatingDrum {
	public static int cont;
	public static boolean done[];
	public static HashMap<String, Integer> map = new HashMap<>();
	public static HashMap<Integer, String> map2 = new HashMap<>();
	public static Queue<String> cola = new LinkedList();

	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		int k, n, g[][];
		while (true) {
			try {
				st = new StringTokenizer(in.readLine());
				k = Integer.parseInt(st.nextToken());
				n = Integer.parseInt(st.nextToken());
			} catch (Exception e) {
				break;
			}
			map.clear();
			map2.clear();
			cola.clear();
			int q = (int) Math.pow(k, n);
			g = new int[q][k];
			done = new boolean[q];
			cont = 0;
			if (k == 1) {
				for (int i = 0; i < n; i++)
					out.print('A');
			} else if (n == 1) {
				for (int i = 0; i < k; i++)
					out.print((char) (i + 65));

			} else {

				makeG(g, n, k);
				int x[] = euler(g, 0);
				// for (int i = 0; i < g.length; i++) {
				// System.out.print(map2.get(i) + ": ");
				// for (int j = 0; j < k; j++) {
				// System.out.print(map2.get(g[i][j]) + ", ");
				// }
				// System.out.println();
				// }
				// System.out.println(Arrays.toString(x));

				for (int i = 0; i < x.length - 1; i++)
					out.print(map2.get(x[i]).charAt(0));

			}
			out.println();

		}

		out.close();
	}

	private static void makeG(int[][] g, int n, int k) {
		StringBuilder a = new StringBuilder("");
		for (int i = 0; i < n - 1; i++)
			a.append('A');
		// System.out.println(a.toString());
		map.put(a.toString(), cont++);
		map2.put(cont - 1, a.toString());
		cola.add(a.toString());
		makeG(g, cola, n, k, 0);

	}

	private static void makeG(int[][] g, Queue<String> cola, int n, int k, int v) {
		
		while(!cola.isEmpty()) {
		StringBuilder a = new StringBuilder(cola.poll());
		v = map.get(a.toString());
		done[v] = true;
		a.delete(0, 1);
		for (int i = 0; i < k; i++) {
			a.append((char) (i + 65));
			// if (s.equals("AB"))
			// System.out.println(a.toString() + " " + i);
			if (!map.containsKey(a.toString())) {
				map.put(a.toString(), cont++);
				map2.put(cont - 1, a.toString());
			}
			g[v][i] = map.get(a.toString());
			if (!done[g[v][i]])
				cola.add(a.toString());
			a.setLength(a.length() - 1);
		}
		}

	}

	public static int[] euler(int[][] lady, int v) {
		ArrayList<Integer> r = new ArrayList<>();
		euler(lady, new int[lady.length], v, r);
		int t = r.size(), a[] = new int[t], i;
		for (i = 0; i < t; i++)
			a[i] = r.get(t - 1 - i);
		return a;

	}

	private static void euler(int[][] lady, int[] tams, int v, ArrayList<Integer> r) {
		while (tams[v] < lady[v].length)
			euler(lady, tams, lady[v][tams[v]++], r);
		r.add(v);

	}

}