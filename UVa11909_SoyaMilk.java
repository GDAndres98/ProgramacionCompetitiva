import java.io.*;
import java.util.*;

public class UVa11909_SoyaMilk {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		while (true) {
			String s = in.readLine();
			if (s == null || s.equals(""))
				break;
			StringTokenizer st = new StringTokenizer(s);
			double l = Double.parseDouble(st.nextToken());
			double w = Double.parseDouble(st.nextToken());
			double h = Double.parseDouble(st.nextToken());
			double t = Double.parseDouble(st.nextToken());
			if(t<Math.toDegrees(Math.atan(h/l))) {
			double x = (l * Math.sin(t*Math.PI/180)) / Math.sin((90 - t)*Math.PI/180);
			out.printf(Locale.US,"%.3f mL\n", (l * w * (h - (x / 2))));
			}
			else {
				double x = (h * Math.sin((90-t)*Math.PI/180)) / (Math.sin(t*(Math.PI/180)));
				out.printf(Locale.US,"%.3f mL\n", (w * ((x*h) / 2)));
			}
		}
//71 89 33 79
		out.close();
	}

}