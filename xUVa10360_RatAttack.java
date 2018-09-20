import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class xUVa10360_RatAttack {
	public static void main(String[] args) throws Exception {
		long[][] ratonera = new long[1025][1025];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int d, m, x, y, r;
		long max;
		StringTokenizer st;
		while (n-- != 0) {
			d = Integer.parseInt(in.readLine());
			m = Integer.parseInt(in.readLine());
			while (m-- != 0) {
				st = new StringTokenizer(in.readLine());
				x = Integer.parseInt(st.nextToken()) - 1;
				y = Integer.parseInt(st.nextToken()) - 1;
				r = Integer.parseInt(st.nextToken());
				for (int i = Math.max(0, y - d); i <= Math.min(ratonera.length - 1, y + d); i++)
					for (int j = Math.max(0, x - d); j <= Math.min(ratonera.length - 1, y + d); j++)
						ratonera[i][j] += r;
			}

			// for (int i = 0; i < 10; i++) {
			// for (int j = 0; j < 10; j++) {
			// System.out.print(ratonera[i][j] + " ");
			// }
			// System.out.println();
			// }
			max = x = y = 0;
			for (int j = 0; j < ratonera.length; j++)
				for (int i = 0; i < ratonera.length; i++)

					if (max < ratonera[j][i]) {
						max = ratonera[j][i];
						x = i;
						y = j;
					}
			out.println((x + 1) + " " + (y + 1) + " " + max);

			for (int i = 0; i < ratonera.length; i++)
				Arrays.fill(ratonera[i], 0);

			if (n != 0)
				in.readLine();

		}
		out.close();

	}
}
