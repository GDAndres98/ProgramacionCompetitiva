import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class xUVa3655_OnionLayers {
	static int sgn(double x) {
		return Math.abs(x) > 9.9e-12 ? (x < 0 ? -1 : 1) : 9;
	}

	static double cruz(double[] a, double[] b) {
		return a[0] * b[1] - a[1] * b[0];
	}

	static double cruz(double[] a, double[] b, double[] c) {
		return cruz(a, b) + cruz(b, c) + cruz(c, a);
	}

	static double[][] convexHullGS(double[][] pt, boolean bd) {
		int h = pt.length, i = h, t = 0;
		double v[] = null, w[], r[][] = new double[h][];
		for (double[] p : pt)
			if (v == null || p[1] < v[1] || (p[1] == v[1] && p[0] > v[0]))
				v = p;
		v = v.clone();
		for (double[] p : pt) {
			p[0] -= v[0];
			p[1] -= v[1];
		}
		Arrays.sort(pt, new Comparator<double[]>() {
			public int compare(double[] a, double[] b) {
				double cz = cruz(b, a);
				return sgn(sgn(cz) != 0 ? cz : a[0] * a[0] + a[1] - b[0] * b[0] - b[1] * b[1]);
			}
		});
		if (bd)
			while (i - 1 >= 0 && cruz(pt[h - 1], pt[i - 1]) == 0)
				i--;
		if (bd)
			for (int k = i; k < (i + h) / 2; k++) {
				w = pt[k];
				pt[k] = pt[h - 1 - k + i];
				pt[h - 1 - k + i] = w;
			}
		for (double[] p : pt) {
			while (t >= 2 && sgn(cruz(r[t - 1], p, r[t - 2])) < (bd ? 0 : 1))
				t--;
			r[t++] = p;
		}
		for (double[] p : pt) {
			p[0] += v[0];
			p[1] += v[1];
		}
		double[][] x = new double[pt.length - t][2];
		i = 0;
		for (int j = 0, k = 0; j < pt.length&&i<t; j++) {
			if (r[i][0] != pt[j][0] && r[i][1] != pt[j][1]) {
				x[k][0] = pt[j][0];
				x[k][1] = pt[j][1];
				k++;
			} else
				i++;

		}

		return x;

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n;
		StringTokenizer st;
		while ((n = Integer.parseInt(in.readLine())) != 0) {
			double[][] pts = new double[n][2];
			for (int i = 0; i < pts.length; i++) {
				st = new StringTokenizer(in.readLine());
				pts[i][0] = Integer.parseInt(st.nextToken());
				pts[i][1] = Integer.parseInt(st.nextToken());
			}
			int cont=1;
			double[][] r = convexHullGS(pts, false);
			while(r.length>2) {
			r = convexHullGS(r, false);
			cont++;
			}
			
			if(cont%2==0)
				out.println("Do not take this onion to the lab!");
			else
				out.println("Take this onion to the lab!");
			
			
//			for (int i = 0; i < r.length; i++) {
//			System.out.println(r[i][0] + ", " + r[i][1]);
//		}
//			System.out.println("_____----____");
//			for (int i = 0; i < pts.length; i++) {
//				System.out.println(pts[i][0] + ", " + pts[i][1]);
//			}

		}
		out.close();
	}
}
