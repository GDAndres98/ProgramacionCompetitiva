import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class test {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder let;
		
		int n;
		while (true) {
			try {
				n = Integer.parseInt(in.readLine());

			} catch (Exception e) {
				break;
			}
			char[] word = in.readLine().toCharArray();
			int[] order = new int[n];
			char[] Slavko = new char[n / 2];
			char[] Mirko = new char[n/2];
			for (int i = 0; i < n; i++) {
				order[i] = (word.length - i - 1) + (((int) word[i]) * 10000000);
			}
			
			

			Arrays.sort(order);
			

			int c1 = word.length - 1;
			int cS = 0;
			int k1 = 0;
			int k2 =0;
			short it = 1;
			for (int i = 0; i <n; it*=-1,i++) {
				if(it==1) {
					while(word[c1]=='.')
						c1--;
					Mirko[k1++]=word[c1];
					word[c1]='.';
				}
				else {
					while(word[n-(order[cS]%1000000)-1]=='.') 
						cS++;
					Slavko[k2++]=word[n-(order[cS]%1000000)-1];
					word[n-(order[cS]%1000000)-1]='.';
					
					
					
				}
				
				
			}
			boolean gana=false;
			
			for (int i = 0; i < n/2; i++) {
				if(Slavko[i]<Mirko[i]) {
					gana=true;
					break;
				}
				else if(Slavko[i]>Mirko[i]) {
					gana=false;
					break;
				}
					
			}
			
			
			if (gana) {
				out.println("DA");
			}else {
				out.println("NE");
			}
			out.println(new String(Slavko));
			out.println(new String(Mirko));

		}
		out.close();
	}
}
