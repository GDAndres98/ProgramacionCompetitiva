import java.io.*;
import java.util.*;

public class UVa11900_BoiledEggs {

	public static void main(String[] args) throws IOException {
		try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))){
			kb.readLine();
			int j = 1;
			for (String in; (in = kb.readLine())!=null&&!in.equals("");) {
				StringTokenizer st = new StringTokenizer(in);
				int n = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				int q = Integer.parseInt(st.nextToken());
				int[] eggs = new int[n];
				st = new StringTokenizer(kb.readLine());
				for (int i = 0; i < eggs.length; i++) {
					eggs[i] = Integer.parseInt(st.nextToken());
				}
				Arrays.sort(eggs);
				if(eggs.length > p) {
					System.out.println("Case "+ j + ": " + solve(Arrays.copyOf(eggs, p),q));
				}
				else { 
					System.out.println("Case "+ j + ": " + solve(eggs,q));
				}
				j++;
			}
		}
	}

	public static int solve(int[] copyOf, int q) {
		int cont = 0;
		int suma = 0;
		for (int i = 0; i < copyOf.length; i++) {
			suma += copyOf[i];
			if(suma <= q) cont++;
			else return cont;
		}
		return cont;
	}

}
