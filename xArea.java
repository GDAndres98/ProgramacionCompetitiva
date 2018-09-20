import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class xArea {
//	public static double area(double[][] pts) {
//		double res = 0;
//		int n = pts.length;
//		for (int p1 = 0, jp2 = 1; p1 < n; p1++, jp2 = (jp2 + 1) % n) {
//			res += pts[p1][0] * pts[jp2][1] - pts[p1][1] * pts[jp2][0];
//		}
//		return Math.abs(res) / 2;
//	}
	
	private static void swapToNext(double[] p1, double[] p2, double i, double j) {
		p1[0]= p2[0];
		p1[1]= p2[1];
		p2[0]=i;
		p2[1]=j;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		double i = 0, j = 0, res = 0, p1[] = new double[2], p2[] = new double[2];
		while ((t--) != 0) {
			String coor = in.readLine();
			if (coor.length() == 1)
				out.println(0);
			else {
				i=j=res=p1[0]=p1[1]=p2[0]=p2[1]=0;
				for (int k = 1; k < coor.length(); k++) {
					switch (coor.charAt(k - 1)) {
					case '8':// norte
						i++;
						break;
					case '2':// sur
						i--;
						break;
					case '6':// este
						j++;
						break;
					case '4':// oeste
						j--;
						break;
					case '9':// noreste
						j++;
						i++;
						break;
					case '7':// noroeste
						j--;
						i++;
						break;
					case '3':// sureste
						j++;
						i--;
						break;
					case '1':// suroeste
						j--;
						i--;
						break;
					}					
					swapToNext(p1, p2, i, j);
					res += p1[0] * p2[1] - p1[1]* p2[0];
				}
				out.println(Math.abs(res)/2);
			}
		}

		out.close();

	}

	

}
