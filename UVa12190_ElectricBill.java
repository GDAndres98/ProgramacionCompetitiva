import java.io.*;
import java.util.StringTokenizer;
public class UVa12190_ElectricBill {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		StringTokenizer st;
		while(true){
			st = new StringTokenizer(in.readLine());
			
			long A = Long.parseLong(st.nextToken());
			long B = Long.parseLong(st.nextToken());
			
			if(A + B == 0) break;
			
			long K = CostoToWats(A);
			
			
			long cost = bin(K, B);
			
			if(cost == -1)
				out.println(A);
			else
				out.println(WatsToCosto(cost));
		}
		
		
		out.close();
	}

	private static long bin(long k, long b) {
		
		long i = 0, d = k / 2;
		
		while(true) {
			long m = (d+i)/2;
			
			if( i > d)
				return -1;
			long cos = Math.abs(WatsToCosto(m) - WatsToCosto(k-m));
			
			if(cos == b)
				return m;
			if(cos > b)
				i = m + 1;
			else
				d = m - 1;
		}
		
	}

	public static long CostoToWats(long costo) {
		long result = 0;
		if (costo >= 200) {// 2
			costo -= 200;
			result += 100;

			if (costo >= (9900 * 3)) {// 3
				costo -= (9900 * 3);
				result += 9900;
				if (costo >= 990000 * 5) {
					costo -= (990000 * 5);
					result += 990000;
					result += costo / 7;
				} else
					result += costo / 5;

			} else
				result += costo / 3;

		} else
			result += costo / 2;

		return result;
	}

	public static long WatsToCosto(long wats) {
		long result = 0;
		if (wats >= 100) {
			result += 200;
			wats -= 100;
			if (wats >= 9900) {
				result += (3 * 9900);
				wats -= 9900;
				if (wats >= 990000) {
					result += (5 * 990000);
					wats -= 990000;
					result += wats * 7;
				} else
					result += wats * 5;
			} else
				result += wats * 3;

		} else
			result += wats * 2;
		return result;

	}

}
