import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class UVa13258_RomeoAndJulietSecrets {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintStream out = new PrintStream(System.out);

		int caso = Integer.parseInt(in.readLine().trim());
		for (int k = 0; k < caso; k++) {
			StringBuilder cad = new StringBuilder(in.readLine().trim());
			StringBuilder find = new StringBuilder(in.readLine().trim());
			int e = Integer.parseInt(in.readLine().trim());
			int[] a = z(find.length()+1, (find.toString() + "$" + cad.toString()));
			int[] b = z(find.length()+1, (find.reverse().toString() + "$" + cad.reverse().toString()));
			int cont = 0;
			for (int i = 0, j = b.length-find.length(); i < a.length-find.length()+1; i++,j--){
				if(a[i]+b[j]+e>=find.length())
					cont++;
			}
			out.println(cont);

		}

		out.close();
		in.close();
	}

	static int[] z(int f, String cad) {
		int[] z = new int[cad.length()];
		int l = 0, r = 0, n = cad.length();
		for (int i = 1; i < n; i++) {
			if (i > r) {
				l = r = i;
				while (r < n && cad.charAt(r - l) == cad.charAt(r))
					r++;
				z[i] = r - l;
				r--;
			} else {
				int k = i - l;
				if (z[k] < r - i + 1) {
					z[i] = z[k];
				} else {
					l = i;
					while (r < n && cad.charAt(r - l) == cad.charAt(r))
						r++;
					z[i] = r - l;
					r--;
				}
			}
		}
		return Arrays.copyOfRange(z, f, z.length);
	}
}
