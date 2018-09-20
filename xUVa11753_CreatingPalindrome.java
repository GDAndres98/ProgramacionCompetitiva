import java.util.*;
import java.io.*;

public class xUVa11753_CreatingPalindrome {
	static int[][] dp;
	static int cota;
	public static void main(String[] args) throws IOException {
		BufferedReader kb= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String in=kb.readLine(); 
		int n = Integer.parseInt(in);
		for (int i = 0; i < n; i++) {
			in=kb.readLine();
			st = new StringTokenizer(in);
			int m = Integer.parseInt(st.nextToken());
			dp = new int[m+1][m+1];
			for (int j = 0; j < dp.length; j++) {
				for (int j2 = 0; j2 < dp.length; j2++) {
					dp[j][j2]=-1;
				}
			}
			cota = Integer.parseInt(st.nextToken());
			int[] pal = new int[m];
			in = kb.readLine();
			st = new StringTokenizer(in);
			for (int j = 0; j < m; j++) {
				pal[j] = Integer.parseInt(st.nextToken());
			}


			System.out.println("Case "+(i+1)+": "+result(pal));
		}
	}

	private static String result(int[] pal) {
		int x = solve(pal,0,pal.length-1,0);
		if(x==0)
			return "Too easy";
		if(x>cota)
			return "Too difficult";
		return x+"";
	}

	private static int solve(int[] pal, int i, int j, int k) {
		if(dp[i][j] == -1) {
			if(i >= j)
				dp[i][j] = 0;
			else if(pal[i] != pal[j]) {
				if ((k+1)>cota) {
					dp[i][j] = k+1;
				}
				else {
					int x = Math.min(solve(pal,i+1,j,k+1)+1, solve(pal,i,j-1,k+1)+1);
					dp[i][j] = x; 
				}
//				if(dp[i][j]>cota)
//					return -666;
			}
			else
				dp[i][j] = solve(pal,i+1,j-1,k);
		}
		return dp[i][j];

	}


}
