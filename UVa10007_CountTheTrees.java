import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class UVa10007_CountTheTrees {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		BigInteger[] f=new BigInteger[301];
		BigInteger[] c=new BigInteger[301];
		f[0]=c[0]= BigInteger.ONE;
		
		for (int i = 1; i < 301; i++) {
			f[i] = f[i-1].multiply(BigInteger.valueOf(i));
			c[i] = BigInteger.valueOf((2*(2*i-1))).multiply(c[i-1]).divide(BigInteger.valueOf(i+1));
		}
		
		int n = Integer.parseInt(in.readLine());
		while (n!=0) {
			out.println(c[n].multiply(f[n]));
			n = Integer.parseInt(in.readLine());
		}
		
		out.close();
		
		
	}
}
