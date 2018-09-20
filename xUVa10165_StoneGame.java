import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class xUVa10165_StoneGame {
 
	public static void main(String[] args) throws NumberFormatException, IOException {		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n;
		int[] piles;
		StringTokenizer st;
		while((n=Integer.parseInt(in.readLine()))!=0) {
			piles = new int[n];
			st = new StringTokenizer(in.readLine());
			boolean o = false;
			for (int i = 0; i < piles.length; i++) {
				piles[i]=Integer.parseInt(st.nextToken());
				if(piles[i] != 1)
					o = true;
			}
			if(o)
				out.println("Yes");
			else
				if(n % 2 == 0)
					out.println("No");
				else 
					out.println("Yes");
		}
		out.close();
	}
 
}