import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa11173_GreyCodes {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int N = Integer.parseInt(in.readLine().trim());
		StringTokenizer st;
		int n, k, r;
		boolean normal;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			normal = true;
			r = 0;
			for (int j = n; j > 0; j--) {
				if (k < ((1 << j) / 2)) {
					if (!normal) {
						r += 1 << (j-1);
						normal = !normal;
					}
				} else {
					k -= (1 << j) / 2;
					if (normal) {
						r += 1 << (j-1);
						normal = !normal;
					}
					

				}
			}
			out.println(r);

		}
		out.close();

	}
}
