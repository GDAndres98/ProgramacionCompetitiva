import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa907_WinterimBackpackingTrip {
	static long[] arr;
	static long[][] mem;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		for (String z = in.readLine(); z != null && !z.equals(""); z = in.readLine()) {
			StringTokenizer st = new StringTokenizer(z);
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			mem = new long[N+1][K+1];
			arr = new long[N + 1];
			for (int i = 0; i < N + 1; i++) {
				if (i != 0)
					arr[i] = Integer.parseInt(in.readLine().trim()) + arr[i - 1];
				else
					arr[i] = Integer.parseInt(in.readLine().trim());
			}

			out.println(f(N, K));
		}
		out.close();

	}

	private static long f(int n, int K) {
		if (K == 0)
			return arr[n];
		if (n == 0)
			return arr[0];
		if (mem[n][K] != 0)
			return mem[n][K];

		long max = Integer.MAX_VALUE;
		for (int i = n - 1; i >= 0; i--) {
			max = Math.min(max, Math.max(arr[n] - arr[i], f(i, K - 1)));
		}
		mem[n][K] = max;
		return max;
	}

}
