import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class UVa11115_UncleJack {
	static long mem[][] = new long[11][26];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
		while (n != 0) {
			out.println(BigInteger.valueOf(n).pow(d));
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
		}

		out.close();
	}
}
