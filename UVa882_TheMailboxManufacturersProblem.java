import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa882_TheMailboxManufacturersProblem {
	static long mem[][][] = new long[101][101][11];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		int n = Integer.parseInt(in.readLine()), k, m;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			k = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			out.println(f(1, m, k));

		}

		out.close();
	}

	static long f(int a, int b, int x) {
		if(b<a)
			return 0;
		if (mem[a][b][x] != 0)
			return mem[a][b][x];
		if (x == 1) {
			mem[a][b][x] = (b * (b + 1)) / 2 - (a * (a - 1)) / 2;
			return mem[a][b][x];
		}

		long min = Long.MAX_VALUE;
		for (int i = a; i <= b; i++)
			min = Math.min(min, i + Math.max((f(a, i - 1, x - 1)), f(i+1, b, x)));
		mem[a][b][x] = min;
		return mem[a][b][x];

	}

}
