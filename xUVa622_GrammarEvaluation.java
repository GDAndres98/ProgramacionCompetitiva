import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class xUVa622_GrammarEvaluation {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		StringBuilder sb;
		String g;
		Stack<String> pila = new Stack<String>();
		int n = Integer.parseInt(in.readLine());
		for (int t = 0; t < n; t++) {
			g = in.readLine().trim();
			for (int i = 0; i < g.length(); i++) {
				sb = new StringBuilder("");
				if (Character.isDigit(g.charAt(i))) {
					for (; i < g.length() && Character.isDigit(g.charAt(i)); i++) 
						sb.append(g.charAt(i));
					
					pila.add(sb.toString());
					i--;
				}
				else if(g.charAt(i)=='('||g.charAt(i)=='1') {
					
				}
				

			}

		}
		out.close();
	}
}
