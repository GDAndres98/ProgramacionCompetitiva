import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class xUVa13068_TheWeakestLink {
	
	public static int solve(char[] C) {
		int o1=0,o2=1,len=0;
		while(o1<C.length&&len!=C.length) {
			int f1=(o1+len)%C.length;
			int f2=(o2+len)%C.length;
			if(C[f1]>C[f2]) {
				len=0;
				o1=o2;
				o2=o1+1;
				if(o2==C.length)
					break;
			}
			else if(C[f1]==C[f2])
				len++;
			else {
				len=0;
				o2++;
				if(o2==C.length)
					break;
			}
		}
		
		
			return o1+1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		while ((t--) != 0) {
			out.println(solve(in.readLine().trim().toCharArray()));
		}
		out.close();
	}
}
