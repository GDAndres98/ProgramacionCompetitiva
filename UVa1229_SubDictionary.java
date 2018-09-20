
/*                                                                                                  
  ###          #             ####     #            #       #                                      
 #   #         #              #  #                 #                                              
 #      #   #  # ##           #  #   ##     ###   ####    ##     ###   # ##    ###   # ##   #   # 
  ###   #   #  ##  #  #####   #  #    #    #   #   #       #    #   #  ##  #      #  ##  #  #   # 
     #  #   #  #   #          #  #    #    #       #       #    #   #  #   #   ####  #      #  ## 
 #   #  #  ##  ##  #          #  #    #    #   #   #  #    #    #   #  #   #  #   #  #       ## # 
  ###    ## #  # ##          ####    ###    ###     ##    ###    ###   #   #   ####  #          # 
                                                                                            #   # 
                                                                                             ###  
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class UVa1229_SubDictionary{
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n;

		while ((n = Integer.parseInt(in.readLine())) != 0) {
			Map<String, Integer> word = new HashMap(n);
			String[] s = new String[n];
			StringTokenizer[] st = new StringTokenizer[n];

			for(int i = 0; i < n; i++){
				st[i] = new StringTokenizer(in.readLine());
				s[i] = st[i].nextToken();
				word.put(s[i], i);
			}

			ArrayList<Integer>[] g = new ArrayList[n]; 

			for(int i = 0; i < n; i++){
				g[i] = new ArrayList(st[i].countTokens());

				while(st[i].hasMoreTokens()) {
					int ind = word.get(st[i].nextToken());
					if(!g[i].contains(ind))
						g[i].add(ind);
				}

			}
		

			int[] comp = kosaraju(g);

			Set<Integer>[] sol = new HashSet[nComponente];
			
			for(int i = 0; i < sol.length; i++)
				sol[i] = new HashSet<>();
			
			for(int i = 0; i < n; i++)
				sol[comp[i]].add(i);
			
			
			int indexSol = -1;
			int min = 10000000;
			for(int i = 0; i < sol.length; i++) {
				boolean b = true;
				for (Integer num: sol[i]) {
					for (Integer num2: g[num]) {
						if(!sol[i].contains(num2)){
							b = false;
							break;
						}
					}
				}
				if(b) {
					if(sol[i].size() < min) {
						min = sol[i].size();
						indexSol = i;
					}
				}	
			}
			
			String[] ULTRASOL = new String[sol[indexSol].size()];
			
			int i = 0;
			for (Integer num: sol[indexSol])
				ULTRASOL[i++] = s[num];
			
			Arrays.sort(ULTRASOL);
			
			out.println(min);
			out.print(ULTRASOL[0]);
			for(i = 1; i < min; i++)
				out.print(" " + ULTRASOL[i]);

			out.println();
		}
		out.close();
	}
	
	static int nVisitados;
	static int nComponente;


	private static int[] kosaraju(ArrayList<Integer>[] adjList) {
		int n = adjList.length;
		int[] orden = new int[n];
		boolean[] visitado = new boolean[n];
		nVisitados = 0;
		for(int i = 0; i < orden.length; i++)
			if(!visitado[i])
				dfs(i, orden, visitado, adjList);
		adjList = transponerGrafo(adjList);
		int[] componente = new int[n];
		visitado = new boolean[n];
		nComponente = 0;
		for(int i = n-1; i >= 0; i--) {
			if(!visitado[orden[i]]) {
				dfsComp(orden[i], componente, visitado, nComponente, adjList);
				nComponente++;
			}
		}
		return componente;
	}

	private static ArrayList<Integer>[] transponerGrafo(ArrayList<Integer>[] adjList) {
		int n = adjList.length;
		ArrayList<Integer>[] transpose = new ArrayList[n];
		for(int i = 0; i < n; i++)
			transpose[i] = new ArrayList<Integer>();
		for(int i = 0; i < n; i++)
			for(int v: adjList[i])
				transpose[v].add(i);
		return transpose;
	}

	private static void dfsComp(int nodo, int[] componente, boolean[] visitado, int nComponente, ArrayList<Integer>[] adjList) {
		componente[nodo] = nComponente;
		visitado[nodo] = true;
		for(int v: adjList[nodo]) {
			if(!visitado[v]) {
				dfsComp(v, componente, visitado, nComponente, adjList);
			}
		}
	}

	private static void dfs(int nodo, int[] orden, boolean[] visitado, ArrayList<Integer>[] adjList) {
		visitado[nodo] = true;
		for(int v: adjList[nodo])
			if(!visitado[v])
				dfs(v, orden, visitado, adjList);
		orden[nVisitados] = nodo;
		nVisitados++;
	}


}