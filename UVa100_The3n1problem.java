import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa100_The3n1problem {
	static int mem[];

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		mem = new int[1000000];
		mem[1] = 1;
		for (int i = 2; i < mem.length; i++)
			mem[i] = f(i);

		for (String i = in.readLine(); i != null && !i.equals(""); i = in.readLine()) {
			st = new StringTokenizer(i);
			int ao = Integer.parseInt(st.nextToken());
			int bo = Integer.parseInt(st.nextToken());
			int a = ao;
			int b = bo;
			if (b < a) {
				int t = a;
				a = b;
				b = t;
			}

			int x = 0;
			for (int j = a; j <= b; j++)
				x = Math.max(mem[j], x);

			out.println(ao + " " + bo + " " + x);
		}
		out.close();

	}

	public static int f(long x) {
		int o = (int) x;
		int cont = 0;

		while (x != 1) {
			cont++;
			if (x % 2 == 0)
				x /= 2;
			else
				x = 3 * x + 1;

			if (mem.length > x)
				if (mem[(int) x] != 0) {
					// cont++;
					mem[o] = cont + mem[(int) x];
					return mem[o];
				}
		}
		cont++;
		mem[o] = cont;
		return mem[o];

	}
}
