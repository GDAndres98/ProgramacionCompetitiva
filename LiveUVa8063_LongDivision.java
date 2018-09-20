import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LiveUVa8063_LongDivision {
	private static void solve(StringBuilder sb, int n, String d) {
		StringBuilder process = new StringBuilder(""), result = new StringBuilder(""), line = new StringBuilder("");
		int cont, act, space = 2, res = 0;

		process.append(n + "|" + d + "\n");

		for (int i = 0; i < d.length(); i++, space++) {
			if (i == 0) {
				act = Integer.parseInt(d.substring(i, i + 1));

				result.append(act / n);
				res = act % n;
				addChar(' ', space, line);
				line.append((act / n) * n + "\n");
				cont = getCont(act, n);
				addChar(' ', space, line);
				addChar('-', cont, line);
				if (d.length() == 1)
					addFinal(space, cont, res, line, result);

			} else if (i != d.length() - 1) {
				act = res * 10 + Integer.parseInt(d.substring(i, i + 1));
				if (act / 10 != 0) {
					space--;
				}
				addChar(' ', space, line);
				line.append(act + "\n");
				res = act % n;
				cont = getCont(act, n);
				if (cont == 1)
					space++;
				result.append(act / n);
				addChar(' ', space, line);
				line.append((act / n) * n + "\n");
				addChar(' ', space, line);
				addChar('-', cont, line);
				
			} else {

				act = res * 10 + Integer.parseInt(d.substring(i, i + 1));
				if (act / 10 == 0) 
					space = d.length() + 1;
				else
					space = d.length();
				addChar(' ', space, line);
				line.append(act + "\n");

				res = act % n;
				cont = getCont(act, n);
				if (cont == 1 && act / 10 != 0)
					space++;
				result.append(act / n);
				addChar(' ', space, line);
				line.append((act / n) * n + "\n");
				addChar(' ', space, line);
				addChar('-', cont, line);

				addFinal(space, cont, res, line, result);

			}

			line.append("\n");
			process.append(line.toString());
			line.setLength(0);

		}

		sb.append("  " + result.toString() + "\n +");
		addChar('-', result.length(), sb);
		sb.append("\n" + process.toString() + "-*-");

	}

	private static void addFinal(int space, int cont, int res, StringBuilder line, StringBuilder result) {
		line.append("\n");
		addChar(' ', space + cont - 1, line);
		line.append(res);
		if (res != 0)
			result.append("r" + res);
	}

	private static void addChar(char c, int n, StringBuilder sb) {
		for (int i = 0; i < n; i++)
			sb.append(c);

	}

	private static int getCont(int act, int n) {
		if (((act / n) * n) / 10 == 0)
			return 1;
		else
			return 2;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder("");
		int n;
		String d;

		while (st.countTokens() != 1) {
			d = st.nextToken();
			n = Integer.parseInt(st.nextToken());

			solve(sb, n, d);

			st = new StringTokenizer(in.readLine());
			sb.append("\n");
		}
		out.print(sb.toString());
		out.close();

	}

}
