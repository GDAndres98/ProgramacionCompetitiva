import java.io.*;
import java.util.*;
public class RE_UVa11729_CommandoWar {

	public static void main(String[] args) throws IOException {
		try(BufferedReader kb = new BufferedReader ( new InputStreamReader(System.in))){
			StringTokenizer st;
			int j = 1;
			for(String in = kb.readLine(); in!=null&&!in.equals("");in=kb.readLine()) {
				int n = Integer.parseInt(in);
				if(n==0) break;
				long [][] arr= new long[n][2];
				for (int i = 0; i < n; i++) {
					st = new StringTokenizer(kb.readLine());
					arr[i][0] = Long.parseLong(st.nextToken());
					arr[i][1] = Long.parseLong(st.nextToken());
				}
				Arrays.sort(arr, new Comparator<long[]>() {

					@Override
					public int compare(long[] o1, long[] o2) {
						if(o1[1]>o2[1])
							return -1;
						else if(o1[1]<o2[1])
							return 1;
						return 0;
					}
				});
				long sumb = 0;
				long sumj = 0;
				for (int i = 0; i < arr.length; i++) {
					sumb += arr[i][0];
					sumj -= arr[i][0];
					sumj = Math.max(sumj, arr[i][1]);


				}
				if(sumj<0)
					sumj=0;
				System.out.println("Case "+j+": "+(sumb+sumj));
				j++;


			}



		}
	}

}


/*
3
2 5
3 2
2 1
3
3 3
4 4
5 5
3
5 5
4 4
3 3
3
3 3
4 4
5 18
0
 */
