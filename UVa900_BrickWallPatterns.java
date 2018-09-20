import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UVa900_BrickWallPatterns {
	public static void main(String[] args) throws IOException {
		try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))){
			long[] xd = new long[50];
			xd[0]=1;xd[1]=2;
			for (int i = 2; i < xd.length; i++) {
			xd[i]=xd[i-1]+xd[i-2];
			}
			while(true) {
				int n = Integer.parseInt(kb.readLine());
				if(n==0)
					break;
				System.out.println(xd[n-1]);
				
			}
			
		}
	}
}
