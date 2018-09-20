import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class UVa12324_PhilipJFryProblem {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		PriorityQueue<Integer> cola = new PriorityQueue<>((x, y) -> -Integer.compare(x, y));
		

		int n, sum;
		StringTokenizer st;
		while (true) {
			n = Integer.parseInt(in.readLine());
			if (n == 0)
				break;
			cola.clear();
			int[][] data = new int[n][2];
			for (int i = 0; i < data.length; i++) {
				st = new StringTokenizer(in.readLine());
				data[i][0] = Integer.parseInt(st.nextToken());
				data[i][1] = Integer.parseInt(st.nextToken());

			}
			sum = 0;
			for (int i = n - 1; i >= 0; i--) {
				if (!cola.isEmpty())
					for (int j = 0; j < data[i][1]; j++)
						if (!cola.isEmpty())
							sum -= (cola.poll() / 2);
				
				cola.add(data[i][0]);
				sum+=data[i][0];

			}
			out.println(sum);

		}

		out.close();
	}
}
