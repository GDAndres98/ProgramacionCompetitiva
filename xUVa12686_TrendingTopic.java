import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

public class xUVa12686_TrendingTopic {
	static int cont = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		HashMap<String, Integer> map[] = new HashMap[7];
		HashMap<String, Integer> index = new HashMap<String, Integer>();
		String all[][] = new String[20000][2];
		for (int i = 0; i < map.length; i++) {
			map[i] = new HashMap<String, Integer>();
		}
		for (int i = 0; i < all.length; i++) {
			all[i][1] = "/";
			all[i][0] = "á";
		}
		StringTokenizer st;
		String s = "";
		int dia = 0;
		while (true) {
			// try {
			// s = st.nextToken();
			// } catch (Exception e) {
			// break;
			// }
			while (s.equals("")) {
				s = in.readLine();
				if (s == null)
					break;
			}
			
			if (s == null)
				break;
			st = new StringTokenizer(s);
			s = st.nextToken();
			if (s.equals("<text>")) {
				dia = (dia + 1) % 7;
				resetDia(index, dia, map, all);
				map[dia].clear();
				st = new StringTokenizer(in.readLine());

				while (!(s = st.nextToken()).equals("</text>")) {
					add(s, map, dia, index, all);
					while (st.hasMoreTokens())
						add(st.nextToken(), map, dia, index, all);
					st = new StringTokenizer(in.readLine());
				}

			} else if (s.equals("<top")) {
				int top = Integer.parseInt(st.nextToken());
				out.println("<top " + top + ">");
				int t = printTop(index, top, all);
				for (int j = 0; j < t; j++) {
					out.println(all[j][0] + " " + all[j][1]);
				}

				out.println("</top>");
			}

		}

		out.close();
	}

	private static int printTop(HashMap<String, Integer> index, int top, String[][] all) {
		int n = 0;
		Arrays.sort(all, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				
				if (o1[1].compareTo(o2[1]) == 0)
					return o1[0].compareTo(o2[0]);
				if(o1[1].equals("/"))
					return -1;
				if(o2[1].equals("/"))
					return 1;
				return Integer.compare(Integer.parseInt(o2[1]), Integer.parseInt(o1[1]));
			}
		});
		for (int i = 0; i < cont; i++)
			index.replace(all[i][0], i);

		int t = 1;
		for (n = 1; n < cont && t < top; n++)
			t++;

		for (; n < cont; n++)
			if (!all[n][1].equals(all[n - 1][1]))
				break;

		return n;
	}

	private static void add(String s, HashMap<String, Integer>[] map, int dia, HashMap<String, Integer> index,
			String[][] all) {
		if (s.length() < 4)
			return;
		int i;
		// System.out.println(index.toString());

		if (index.containsKey(s)) {
			i = index.get(s);
			all[i][1] = "" + (Integer.parseInt(all[i][1]) + 1);
		} else {
			i = cont;
			all[i][0] = s;
			all[i][1] = "1";
			index.put(s, i);
			cont++;
		}
		if (map[dia].containsKey(s))
			map[dia].replace(s, map[dia].get(s) + 1);

		else
			map[dia].put(s, 1);

	}

	private static void resetDia(HashMap<String, Integer> index, int dia, HashMap<String, Integer>[] map,
			String[][] all) {
		Set<Entry<String, Integer>> sett = map[dia].entrySet();
		for (Entry<String, Integer> x : sett) {
			int q = Integer.parseInt(all[index.get(x.getKey())][1]) - x.getValue();
			// if(x.getKey().equals("text"))
			// System.out.println("Q----------> "+ q);
			all[index.get(x.getKey())][1] = q + "";
		}

	}

}