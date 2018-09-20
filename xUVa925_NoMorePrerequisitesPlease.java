import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class xUVa925_NoMorePrerequisitesPlease{
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);


		int t = Integer.parseInt(in.readLine());
		StringTokenizer st;
		for(int l = 0; l < t; l++){
			int n = Integer.parseInt(in.readLine());

			Map<String, Integer> map = new HashMap<>(n);
			String[][] mapOut = new String[n][2];
			String[] mapOut2 = new String[n];

			for(int i = 0; i < n; i++){
				mapOut[i][0] = in.readLine();
				mapOut2[i] = mapOut[i][0];
				mapOut[i][1] = i+"";
				map.put(mapOut[i][0],i);
			}

			int nn = Integer.parseInt(in.readLine());
			ArrayList<Integer>[] g = new ArrayList[n];

			for(int m = 0; m < nn; m++){
				st = new StringTokenizer(in.readLine());
				int s = map.get(st.nextToken());
				g[s] = new ArrayList<>();
				int num = Integer.parseInt(st.nextToken());
				for(int i = 0; i < num; i++)
					g[s].add(map.get(st.nextToken()));  
			}


			ArrayList	<Integer> [] 	   sol = new ArrayList 	[n]				;
			Set		    <Integer> [] 	   mem = new HashSet   	[n]				;


			for(int i = 0; i < n; i++){
				if(sol[i] == null) continue;
				sol[i] = new ArrayList();
				mem[i] = new HashSet();
				for(int j = 0; j < g[i].size(); j++){

					if(!mem[i].contains(g[i].get(j))){
						alvpmp(g[i].get(j), g, sol, mem);

						for(int k = 0; k < sol[i].size(); k++)
							if(mem[j].contains(sol[i].get(k))){
								sol[i].remove(k);
								k--;
							}


						sol[i].add(g[i].get(j));
						mem[i].addAll(mem[g[i].get(j)]);
					}
				}
			}

			String[] ord = new String[n];

			Arrays.sort(mapOut, (x, y) ->{return x[0].compareTo(y[0]);});

			for(int i = 0; i < n; i++){
				int ind = Integer.parseInt(mapOut[i][1]);
				if(sol[ind] != null && sol[ind].size() > 0){
					out.print(mapOut[i] + " " + sol[ind].size());
					for(int j = 0; j < sol[ind].size(); j++){
						ord[j] = mapOut2[sol[ind].get(j)];
					}
					Arrays.sort(ord);
					for(int j = 0; j < sol[ind].size(); j++)
						out.print(" " + ord[j]);
					out.println();
				}
			}

		}

		out.close();
	}




	public static void alvpmp(int i, ArrayList<Integer>[] g, ArrayList<Integer>[] sol,Set<Integer>[] mem){
		if(sol[i] != null) return;
		sol[i] = new ArrayList();
		mem[i] = new HashSet();
		for(int j = 0; j < g[i].size(); j++){

			if(!mem[i].contains(g[i].get(j))){
				alvpmp(g[i].get(j), g, sol, mem);

				for(int k = 0; k < sol[i].size(); k++)
					if(mem[j].contains(sol[i].get(k))){
						sol[i].remove(k);
						k--;
					}


				sol[i].add(g[i].get(j));
				mem[i].addAll(mem[g[i].get(j)]);
			}
		}

	}}
