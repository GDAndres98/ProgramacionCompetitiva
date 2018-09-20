import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Stack;

public class UVa1062_Containers {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String act = in.readLine();
		int cont = 1;
		boolean fin;
		LinkedList<Integer> stacks = new LinkedList<Integer>();
		while (!act.equals("end")) {
			for (int i = 0; i < act.length(); i++) {
				if (stacks.isEmpty())
					stacks.add((int) act.charAt(i));
				else {
					fin = false;
					for (int j = 0; j < stacks.size(); j++) {

						if (((int) act.charAt(i)) <= stacks.get(j)) {
							stacks.set(j, (int) act.charAt(i));
							break;
						} else if (j == stacks.size() - 1)
							fin = true;
					}
					if (fin)
						stacks.add((int) act.charAt(i));
				}	
			}

			out.println("Case " + cont + ": " + stacks.size());
			act = in.readLine();
			stacks.clear();
			cont++;
		}
		out.close();

	}
}
