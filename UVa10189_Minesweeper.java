import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa10189_Minesweeper {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int f, c, cont = 1;
		char[][] mines;
		int[][] num;
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(in.readLine());
			f = Integer.parseInt(st.nextToken());
			if (f == 0)
				break;
			c = Integer.parseInt(st.nextToken());
			num = new int[f][c];
			mines = new char[f][];
			if (cont != 1)
				out.println();
			for (int i = 0; i < f; i++)
				mines[i] = in.readLine().toCharArray();

			getNUM(mines, num);
			// for (int i = 0; i < num.length; i++)
			// System.out.println(Arrays.toString(num[i]));
			out.println("Field #" + (cont++) + ":");
			for (int i = 0; i < f; i++) {
				for (int j = 0; j < c; j++)
					out.print(mines[i][j] != '*' ? num[i][j] : "" + '*');
				out.println();
			}

		}

		out.close();
	}

	private static void getNUM(char[][] mines, int[][] num) {
		for (int i = 0; i < num.length; i++)
			for (int j = 0; j < num[0].length; j++)
				for (int a = i - 1; a <= i + 1; a++)
					for (int b = j - 1; b <= j + 1; b++)
						try {
							if (mines[i][j] == '*')
								num[a][b]++;
						} catch (Exception e) {

						}

	}
}
