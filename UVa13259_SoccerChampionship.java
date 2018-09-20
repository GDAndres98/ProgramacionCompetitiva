import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class UVa13259_SoccerChampionship {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st, stT;
		int aux;

		int n;
		while (true) {
			try {
				n = Integer.parseInt(in.readLine());
			} catch (Exception e) {
				break;
			}

			HashMap<String, Team> all = new HashMap<String, Team>();
			ArrayList<Team[]> matches = new ArrayList<>();

			for (int i = 0; i < n; i++) {

				String s = in.readLine();

				String n1 = s.substring(0, s.indexOf("vs.")).trim();
				String n2 = s.substring(s.indexOf("vs.") + 3, s.length()).trim();

				int index1 = n1.lastIndexOf(' ');
				int index2 = n2.indexOf(' ');

				String s1 = n1.substring(0, index1);
				String s2 = n2.substring(index2 + 1);

				int a = Integer.parseInt(n1.substring(index1).trim());
				int b = Integer.parseInt(n2.substring(0, index2).trim());

				
				Team A, B;

				if (!all.containsKey(s1))
					all.put(s1, new Team(s1));
				A = all.get(s1);

				if (!all.containsKey(s2))
					all.put(s2, new Team(s2));
				B = all.get(s2);

				A.addMatch(a, b, false);
				B.addMatch(b, a, true);

				if (a > b) {
					matches.add(new Team[] { A, B });
				} else if (b > a) {
					matches.add(new Team[] { B, A });
				}
			}
			ArrayList<Team> equipos = new ArrayList<>();
			equipos.addAll(all.values());
			equipos.sort(new Comparator<Team>() {

				@Override
				public int compare(Team o1, Team o2) {
					return o1.compareTo(o2);
				}
			});
			int cont = 0;

			for (int i = 0; i < matches.size(); i++) {
				// System.out.println(matches.get(i)[0].name+" vs "+matches.get(i)[1].name);
				// System.out.println(matches.get(i)[0].compareTo(matches.get(i)[1]));
				if (matches.get(i)[0].compareTo(matches.get(i)[1]) < 0) {
					cont++;

				}

			}
			out.println("The paradox occurs " + cont + " time(s).");
			for (int i = equipos.size() - 1, j = 1; i >= 0; i--, j++) {
				out.println(j + ". " + equipos.get(i).name);
			}

			// for (int i = 0; i < equipos.size(); i++) {
			// System.out.println(equipos.get(i).name+": "+equipos.get(i).points);
			// }

			/*
			 * 
			 * System.out.println(); System.out.println(equipos.get(0).name);
			 * System.out.println(equipos.get(0).points);
			 * System.out.println(equipos.get(0).difGoal);
			 * System.out.println(equipos.get(0).goals); System.out.println();
			 * System.out.println(equipos.get(1).name);
			 * System.out.println(equipos.get(1).points);
			 * System.out.println(equipos.get(1).difGoal);
			 * System.out.println(equipos.get(1).goals); System.out.println();
			 * System.out.println(equipos.get(2).name);
			 * System.out.println(equipos.get(2).points);
			 * System.out.println(equipos.get(2).difGoal);
			 * System.out.println(equipos.get(2).goals);
			 * 
			 * 
			 * System.out.println();
			 */

		}
		out.close();

	}

	static class Team {
		String name;
		int points;
		int difGoal;
		int goals;
		int goalsVis;
		int pos;

		public Team(String a) {
			name = a;
			points = 0;
			difGoal = 0;
			goals = 0;
			goalsVis = 0;
		}

		public void addMatch(int me, int he, boolean vis) {
			if (me > he)
				points += 3;
			else if (me == he)
				points++;
			difGoal += me - he;
			goals += me;
			if (vis)
				goalsVis += me;

		}

		public int compareTo(Team b) {
			if (points != b.points)
				return Integer.compare(points, b.points);
			if (difGoal != b.difGoal)
				return Integer.compare(difGoal, b.difGoal);
			if (goals != b.goals)
				return Integer.compare(goals, b.goals);
			if (goalsVis != b.goalsVis)
				return Integer.compare(goalsVis, b.goalsVis);
			return -name.compareTo(b.name);
		}

	}
}