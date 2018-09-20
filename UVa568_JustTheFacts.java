import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class UVa568_JustTheFacts {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		long mem[] = new long[10001];
		mem[0] = mem[1] = 1;
		long x;
		for (int i = 2; i < mem.length; i++) {
			x = mem[i - 1] * i;
			while (x % 10 == 0)
				x /= 10;
			mem[i] = (int) (x % 100000000);
		}

		for (String z = in.readLine(); z != null && !z.equals(""); z = in.readLine()) {

			out.printf("%5s -> %s\n", z, mem[Integer.parseInt(z)] % 10);

		}

		out.close();
	}
}
