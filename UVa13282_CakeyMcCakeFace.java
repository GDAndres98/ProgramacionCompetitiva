import java.util.*;
import java.io.*;

public class UVa13282_CakeyMcCakeFace {

	public static void main(String args[]) throws IOException {

		try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))){
			int a;
			int b;
			long[] entry;
			long[] exit;
			long[] arr;
			StringTokenizer st;
			for(String in=kb.readLine();in!=null&&!in.equals("");in=kb.readLine()){
				a = Integer.parseInt(in.trim());
				b = Integer.parseInt(kb.readLine().trim());
				entry = new long[a];
				st = new StringTokenizer(kb.readLine());
				for (int i = 0; i < a; i++) {
					entry[i] = Long.parseLong(st.nextToken());
				}
				exit = new long[b];
				st = new StringTokenizer(kb.readLine());
				for (int i = 0; i < b; i++) {
					exit[i] = Long.parseLong(st.nextToken());
				}
				arr = new long[a*b];
				arr=cooking_time(entry,exit,arr);
				Arrays.sort(arr);
				long x=Moda(arr);
				if(x==-1)
					x=0;
				System.out.println(x);

			}
		}

	}

	private static long Moda(long[] arr) {
		long moda = -1;
		long maux = 0;
		long rep = 0;
		long raux = 0;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]!=maux) {
				if(raux>rep) {
					moda=maux;
					rep=raux;
				}
				maux=arr[i];
				raux=0;
			}
			raux++;

		}
		return moda;
	}

	private static long[] cooking_time(long[] entry, long[] exit, long[] arr) {
		int nless=0;
		int k = 0;
		for (int i = 0; i < entry.length; i++) {
			while(exit[nless]-entry[i]<0) {
				nless++;
				if(nless>=exit.length) {
					i=entry.length;
					break;
				}
			}
			for (int j = nless; j < exit.length; j++) {
				arr[k++]=exit[j]-entry[i];

			}
		}
		return Arrays.copyOf(arr, k);

	}
}

/*				
5
5
0 10 12 20 30
1 5 17 27 50
5
5
0 17 20 27 30
1 4 20 30 50
5
5
1 2 3 4 5
6 7 8 9 10
3
6
1 2 3
123456789 999999996 999999997 999999998 999999999 1000000000
6
5
0 17 20 27 30 60
1 4 20 30 50
6
6
0 11 13 20 30 53
1 5 17 27 53 61
5
5
6 7 8 9 10
1 2 3 4 5
 */