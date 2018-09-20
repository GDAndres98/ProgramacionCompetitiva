import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa11876_N_NOD_N {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		long[] nod=new long[64800];
		
		StringTokenizer st;
		nod[0]=1;
		long[] primos = getPrimos(500000);
		for (int i = 1; i <64800 ; i++) {
			nod[i]=( (nod[i-1]+NOD(nod[i-1],primos)));
		}
				
		int caso=1;
		
		while (n-- != 0) {
			
			st=new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int i1= Arrays.binarySearch(nod, a);
			int i2= Arrays.binarySearch(nod, b);
			if(i1<0)
				i1=-i1-1;
			if(i2<0)
				i2=-i2-2;
			out.println("Case "+(caso++)+": "+(i2-i1+1));
			
			
		}
		out.close();
	}

	static long[] getPrimos(int cota) {
		boolean[] arr = new boolean[cota + 1];
		long[] res = new long[cota];
		arr[0] = arr[1] = true;
		int k = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!arr[i]) {
				res[k++] = i;
				for (int j = 2 * i; j < arr.length; j += i) {
					arr[j] = true;
				}
			}
		}
		return Arrays.copyOfRange(res, 0, k);
	}

	static long NOD(long N, long[] primos) {
		long res = 0;
		long resAnt = 0;
		int sqrt = (int) Math.sqrt(N);
		for (int i = 0; N > 1; i++) {
			if (primos[i] > sqrt)
				N = 0;
			if (N % primos[i] == 0) {
				res += resAnt + 1;
				N /= primos[i--];
			} else
				resAnt = res;

		}
		return res + 1;

	}
}
