import java.io.*;
import java.util.*;
public class UVa10394_TwinPrimes {
	static long[] criba;
	static int[][] result;
	static int ci=0;
	static int cr=-1;
	public static void main(String[] args) throws IOException {
		criba= eratostenes(20000000);
		result= new int[2][1000000+1];
		try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))){
			for (String in = kb.readLine() ; in != null; in = kb.readLine()) {
				int n = Integer.parseInt(in.trim());
				solve(n);
				System.out.println("("+result[0][n-1]+", "+result[1][n-1]+")");
			}

		}
	}
	private static long[] eratostenes(int n) {
		boolean[] arr = new boolean[n+1];
		long[] res = new long[n];
		arr[0] = arr[1] = true;
		int k= 0; for (int i=0; i<arr.length; i++)
			if(!arr[i]) {
				res[k++]=i;
				for(int j = 2*i; j<arr.length; j+=i)arr[j] = true;
			}
		return Arrays.copyOfRange(res, 0, k);
	}
	static void solve(int n) {
		if(result[0][n-1]!=0)
			return;
		for (; cr<=n-1; ci++) {
			if(criba[ci+1]-criba[ci]==2) {
				cr++;
				result[0][cr]=(int) criba[ci];
				result[1][cr]=(int) criba[ci+1];
			}
		}
		return;
	}

}
