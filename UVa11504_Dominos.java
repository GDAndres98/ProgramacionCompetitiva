import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVa11504_Dominos {
	static int nodos, aristas;
	static boolean[] visited;
	static ArrayList<Integer>[] ady;
	static Stack<Integer> pila;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintStream out = new PrintStream(System.out);

		int caso = Integer.parseInt(in.readLine());
		for (int c = 0; c < caso; c++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			nodos = Integer.parseInt(st.nextToken());
			aristas = Integer.parseInt(st.nextToken());
			ady = new ArrayList[nodos];
			visited = new boolean[nodos];
			for (int i = 0; i < ady.length; ady[i] = new ArrayList<>(), i++);
			for (int i = 0; i < aristas; i++) {
				st = new StringTokenizer(in.readLine());
				int nodo1 = Integer.parseInt(st.nextToken())-1;
				int nodo2 = Integer.parseInt(st.nextToken())-1;
				ady[nodo1].add(nodo2);
			}
			out.println(tarjan());
		}

		out.close();
		in.close();
	}

	static int tarjan() {
		pila = new Stack<>();
		for (int i = nodos - 1; i > -1; i--) {
			if (!visited[i]) {
				dfs(i);
			}
		}
		visited = new boolean[nodos];
		int numComp = 0;
		while (!pila.isEmpty()) {
			int x = pila.pop();
			if (!visited[x]) {
				numComp++;
				dfs2(x);
			}
		}
		return numComp;
	}

	static void dfs(int u) {
		visited[u] = true;
		for (int v : ady[u]) {
			if (!visited[v]) {
				dfs(v);
			}
		}
		pila.push(u);

	}

	static void dfs2(int u) {
		visited[u] = true;
		for (int v : ady[u]) {
			if (!visited[v]) {
				dfs2(v);
			}
		}
	}
}
