import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UVa160_FactorsAndFactorials {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n;
		long[] primos = getPrimos(100);
		long[][] res = new long[101][26];
		long[][] rxd;
		for (int i = 1; i <= 100; i++) {
			rxd = factoresPrimos(i, primos);
			for (int j = 0; j < rxd.length; j++) {
				res[i][j] = rxd[j][1];
				res[i][j] += res[i - 1][j];
			}
		}

		while (true) {
			n = Integer.parseInt(in.readLine());
			if (n == 0)
				break;
			out.printf("%3s! =", n);
			int i;
			for (i = 0; i < 15 && res[n][i] != 0; i++)
				out.printf("%3s", res[n][i]);
			if (res[n][15] != 0) {
				out.printf("\n%6s", "");
				for (; res[n][i] != 0; i++)
					out.printf("%3s", res[n][i]);
			}
			out.println();

		}

		out.close();

	}

	static long[][] factoresPrimos(long x, long[] primos) {
		List<long[]> res = new ArrayList<long[]>();
		double R = (long) (Math.sqrt(x) + 1 + 1e-5);
		long p, c;
		for (int i = 0, n = primos.length; i < n; i++) {
			p = primos[i];
			for (c = 0; x % p == 0; x /= p, c++)
				;
			res.add(new long[] { p, c });

		}
		if (x > 1)
			res.add(new long[] { x, 1 });
		return res.toArray(new long[0][]);
	}

	public static long[] getPrimos(int cota) {
		boolean[] arr = new boolean[cota + 1];
		long[] res = new long[cota];
		arr[0] = arr[1] = true;
		int K = 0;
		for (int i = 0; i < arr.length; i++)
			if (!arr[i]) {
				res[K++] = i;
				for (int j = 2 * i; j < arr.length; j += i)
					arr[j] = true;
			}
		return Arrays.copyOfRange(res, 0, K);
	}

}
