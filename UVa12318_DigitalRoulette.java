import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class UVa12318_DigitalRoulette {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int m, k,pot, arr[] = new int[11];
		long n, index = 0;
		StringTokenizer st;
		HashSet<Long> set = new HashSet<Long>();

		
		
		
		while (true) {
			st = new StringTokenizer(in.readLine());
			n = Long.parseLong(st.nextToken()) + 1;
			if (n == 1)
				break;
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine());
			set.clear();
			for (int i = 0; i <= k; i++)
				arr[i] = Integer.parseInt(st.nextToken());
//			System.out.println(Arrays.toString(arr));
			for (int i = 0; i <= m; i++) {
				index=getIndex(arr,m,n,k,i);
				set.add(index);
				
			}
			
			
			out.println(set.size());
//			out.println(set.toString());


		}

		out.close();
	}

	private static long getIndex(int[] arr, int m, long n, int k, int i) {
		long rec=1,index = 0;
		for (int j = 0; j <= k; j++) {
			if (arr[j] != 0)
				index = (index + arr[j] * rec) % (n);
			rec *= i ;
			rec%= n;
		}
		return index;
	
	}
}




