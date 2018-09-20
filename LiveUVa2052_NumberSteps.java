import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LiveUVa2052_NumberSteps {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st;
		while (n-- != 0) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (x == y || x - 2 == y) {
				if (x % 2 == 0)
					out.println(x + y);
				else
					out.println(x + y - 1);
			} else
				out.println("No Number");
		}
		out.close();

	}

}
