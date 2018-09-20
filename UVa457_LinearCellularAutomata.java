import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa457_LinearCellularAutomata {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		int n = Integer.parseInt(in.readLine()), DNA[] = new int[10], res[][] = new int[50][42];
		char[] xd = { ' ', '.', 'x', 'W' };
		for (int z = 0; z < n; z++) {
			in.readLine();
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < DNA.length; j++) {
				DNA[j] = Integer.parseInt(st.nextToken());
			}
			res[0][20] = 1;
			for (int i = 1; i < res.length; i++) {
				for (int j = 1; j < res[0].length - 1; j++) {
					res[i][j] = DNA[res[i - 1][j - 1] + res[i - 1][j] + res[i - 1][j + 1]];
				}

			}

			for (int i = 0; i < res.length; i++) {
				for (int j = 1; j < res[0].length - 1; j++) {
					out.print(xd[res[i][j]]);
				}
				out.println();
			}
			if (z != n - 1)
				out.println();
		}
		out.close();
	}
}
