import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVa12665_JokingWithFermatsLastTheorem {
 
	public static void main(String[] args) throws NumberFormatException, IOException {		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int[] a = new int[1200];
		for(int i = 0; i < a.length; i++)
			a[i] = i*i*i;
		
		
		int t = 0;
		while(true) {
			String s = in.readLine();
			if(s == null) break;
			t += 1;
			StringTokenizer st = new StringTokenizer(s);
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			int count = 0;
			for(int i = A; i < a.length && i <= B; i++) {
				for(int j = i + 1; j < a.length && j <= B; j +=2) {
					int sol = a[i] + a[j];
					if(sol/10 > B)
						break;
					if(sol % 10 == 3) {
						if(sol/10 >= A && sol/10 <= B)
							count++;
						if(sol/10 > B)
							break;
					}
				}
			}
			
			out.printf("Case %d: %d\n", t, count * 2);
		}

		out.close();
	}
 
}