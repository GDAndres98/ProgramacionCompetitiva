/*
  _                                             _                                                 
 |_|  |  |_   _  ,_   _       |_  °       _    |_|  ,_  |       ,_   _  ,_   _   _   _    _   _   
 | |  |  |_  (<  |   | |  (|  |_  |  \/  (<    | |  |   |)  ()  |   (<  _\  (_  (<  | |  (_  (<   

 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class UVa11307_AlternativeArborescence{
	public static int MAX;

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		StringTokenizer st;
		while(true){
			String s = in.readLine();
			while(s.length() == 0) 
				s = in.readLine();
			int n = Integer.parseInt(s.trim());
			if(n == 0) break;
			ArrayList<Integer>[] tree = new ArrayList[n];

			int x = 0;
			boolean[] vis = new boolean[n];
			for(int i = 0; i < n; i++){
				st = new StringTokenizer(in.readLine());
				st.nextToken();

				tree[i] = new ArrayList(st.countTokens());
				while(st.hasMoreTokens()) {
					x = Integer.parseInt(st.nextToken());
					vis[x] = true;
					tree[i].add(x);
				}
			}
		
			
			for(int i = 0; i < n; i++)
				if(!vis[i]) {
					x = i;
					break;
				}



			//comp(x,0,tree);

			MAX = 5;
			long[][] sol = new long[n][MAX + 4];

			rec(x,tree, sol);
			
			
			out.println(sol[x][MAX + 2]);
		}	


		out.close();
	}

	private static void rec(int x, ArrayList<Integer>[] tree, long[][] sol) {
		for(Integer num: tree[x]) {
			rec(num, tree, sol);
			
		}
		
		sol[x][MAX + 2] = Long.MAX_VALUE;
		sol[x][MAX + 3] = Long.MAX_VALUE;
		
		for(int i = 1; i <= MAX + 1; i++) {
			sol[x][i] = i;
			
			for(Integer num: tree[x]) {
				if(sol[num][MAX + 2] == sol[num][i])
					sol[x][i] += sol[num][MAX + 3];
				else
					sol[x][i] += sol[num][MAX + 2];
			}
			
			if(sol[x][i] < sol[x][MAX + 2])
				sol[x][MAX + 2] = sol[x][i];
			else if(sol[x][i] < sol[x][MAX + 3])
				sol[x][MAX + 3] = sol[x][i];
		}
	}

	public static void comp(int root, int level, ArrayList<Integer>[] tree){
		for(Integer i: tree[root])
			comp(i, level+1, tree);
		MAX = Math.max(MAX, level);
	}
}