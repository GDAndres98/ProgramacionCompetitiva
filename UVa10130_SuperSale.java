import java.util.*;
import java.io.*;
public class UVa10130_SuperSale {
	static int[][] dp;
	static int[][] pp;
	public static void main (String[] args) throws Exception{
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		String in = kb.readLine();
		int n = Integer.parseInt(in);
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			in=kb.readLine();
			int m = Integer.parseInt(in);
			pp = new int[m+1][2];
			for (int j = 1; j <= m; j++) {
				in = kb.readLine();
				st = new StringTokenizer(in);
				pp[j][0]=Integer.parseInt(st.nextToken());
				pp[j][1]=Integer.parseInt(st.nextToken());
			}
			in=kb.readLine();
			m = Integer.parseInt(in);
			int[] cap = new int[m];
			int max = 0;
			for (int j = 0; j < m; j++) {
				cap[j] = Integer.parseInt(kb.readLine());
				max=Math.max(cap[j], max);
			}
			max++;
			dp = new int[pp.length][max];
			for (int j = 0; j < pp.length; j++) {
				for (int j2 = 0; j2 < max; j2++) {
					dp[j][j2]=-1;
				}
			}
			System.out.println(getSuma(cap));


		}


	}

	 static int getSuma(int[] cap) {
		int suma=0;
		for (int i = 0; i < cap.length; i++) {
			suma+=getMaxKnapsack(pp.length-1,cap[i]);
		}
		return suma;
	}

	 static int getMaxKnapsack(int i, int cap) {
		 if (dp[i][cap]==-1) {
			 if(i==0||cap==0)
				 dp[i][cap]=0;
			 else if(pp[i][1]>cap)
				 dp[i][cap]=getMaxKnapsack(i-1, cap);
			 else
				 dp[i][cap]=Math.max(pp[i][0]+getMaxKnapsack(i-1, cap-pp[i][1]),getMaxKnapsack(i-1, cap));
			 
			 
		 }
		return dp[i][cap];
	}

}

