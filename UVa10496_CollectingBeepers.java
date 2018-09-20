import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa10496_CollectingBeepers {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int t = Integer.parseInt(in.readLine().trim());
		
		for(int l = 0; l < t; l++){
			StringTokenizer st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(in.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			
			int k = Integer.parseInt(in.readLine().trim());
			
			double[][] mAdy = new double[k+1][k+1];
			
			int[][] nodes = new int[k+1][2];
			
			nodes[0][0] = x;
			nodes[0][1] = y;
			
			for(int i = 1; i <= k; i++){
				st = new StringTokenizer(in.readLine());
				nodes[i][0] = Integer.parseInt(st.nextToken());
				nodes[i][1] = Integer.parseInt(st.nextToken());
			}
			
			
			for(int i = 0; i < mAdy.length; i++)
				for(int j = 0; j < k+1; j++)
					mAdy[i][j] = Math.abs(nodes[i][0] - nodes[j][0]) + Math.abs(nodes[i][1] - nodes[j][1]); 

			
			long tsp = (long)travelingSalesman(mAdy, 0);
			out.printf("The shortest path has length %d\n", tsp);
		}
		
		
		out.close();
	}
	public static double travelingSalesman(double[][] mAdy, int v){
		int n = mAdy.length, t = 1<<n;
		double mem[][] = new double[t][n];
		for(double[] arr: mem)
			Arrays.fill(arr, -1d);
		return tsp(mAdy, n, v, v, 1 << v, mem);
	}

	private static double tsp(double[][] mAdy, int n, int v1, int v2, int visitados, double[][] mem) {
		if(mem[visitados][v1] >= 0d){
			return mem[visitados][v1];
		}
		if(visitados == (1 << n) - 1){
			return mem[visitados][v1] = mAdy[v1][v2];
		}
		double min = Double.POSITIVE_INFINITY, d;
		for(int e = visitados, j = 0; j < n; j++, e >>>=1){
			if((e&1)== 0 && (d=mAdy[v1][j]) < min){
				min = Math.min(min, d + tsp(mAdy, n, j, v2, visitados|(1 << j), mem));
			}
		}
		return mem[visitados][v1] = min;
	}
}