import java.util.*;
import java.io.*;
public class xUVa437_TheTowerOfBabylon {
	public static void main(String[] args) throws IOException {
		try(BufferedReader kb = new BufferedReader (new InputStreamReader(System.in))){
			StringTokenizer st;
			for (String in = kb.readLine(); in!=null && !in.equals(""); in=kb.readLine()) {
				int n = Integer.parseInt(in);
				if(n==0) break;
				long[][] arr = new long[6*n+1][3];
				for (int i = 0; i < n; i++) {
					st = new StringTokenizer(kb.readLine().trim());
					long x = Integer.parseInt(st.nextToken());
					long y = Integer.parseInt(st.nextToken());
					long z = Integer.parseInt(st.nextToken());
					arr[i*6+0][0] = x; arr[i*6+0][1] = y; arr[i*6+0][2] = z;
					arr[i*6+1][0] = y; arr[i*6+1][1] = x; arr[i*6+1][2] = z;
					arr[i*6+2][0] = x; arr[i*6+2][1] = z; arr[i*6+2][2] = y;
					arr[i*6+4][0] = z; arr[i*6+4][1] = x; arr[i*6+4][2] = y;
					arr[i*6+3][0] = z; arr[i*6+3][1] = y; arr[i*6+3][2] = x;
					arr[i*6+5][0] = y; arr[i*6+5][1] = z; arr[i*6+5][2] = x;
				}
				
				Arrays.sort(arr, new Comparator<long[]>() {

					@Override
					public int compare(long[] o1, long[] o2) {
						if(o1[0]>o2[0])
							return 1;
						else if (o1[0]<o2[0])
							return -1;
						else if (o1[1]>o2[1])
							return 1;
						else if (o1[1]<o2[1])
							return -1;
						else if (o1[2]>o2[2])
							return 1;
						else if (o1[2]<o2[2])
							return -1;
						else return 0;
					}
				});
				for (int i = 0; i < n*6; i++) {
					System.out.println(arr[i][0]+" "+arr[i][1]+" "+arr[i][2]);
				}
				
				
			}
			
		}
	}
}
