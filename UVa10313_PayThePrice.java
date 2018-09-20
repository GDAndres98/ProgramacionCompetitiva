import java.io.*;
import java.util.StringTokenizer;

public class UVa10313_PayThePrice {
	static long[][] mem = new long[301][301];
	public static void main(String[] args) throws Exception {
		try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))){
			mem[0][0] = 1;
			for (int i = 0; i <= 300; ++i)      
				for (int j = 0; j + i <= 300; ++j) 
					for (int k = 1; k <= 300; ++k) 
						mem[k][i+j] += mem[k-1][j];
			StringTokenizer st;
			for( String in = kb.readLine(); in!=null&&!in.equals(""); in=kb.readLine()) {


				//
				//				for (int z = 0; z < 22; z++) {
				//					for (int q = 0; q < 22; q++) {
				//						System.out.print(mem[z][q]+" ");
				//					}
				//					System.out.println();
				//				}
				st = new StringTokenizer(in);
				switch(st.countTokens()) {
				case 1: System.out.println(f1(st));
				break;
				case 2: System.out.println(f2(st));
				break;
				case 3: System.out.println(f3(st));
				break;
				}


			}

		}
	}
	private static long f3(StringTokenizer st) {
		int n = Integer.parseInt(st.nextToken());
		int l1 = Integer.parseInt(st.nextToken());
		int l2 = Integer.parseInt(st.nextToken());
		if(l1>n)
			return 0;
		if(l1==n)
			return 1;
		if(l2>n)
			l2=n;
		if(l2<n&&l1<n&&l2==l1&&l1!=0)
			return mem[l2][n]-mem[l1-1][n];
		if(l1==l2||l1==0)
			return mem[l2][n];
		return mem[l2][n]-mem[l1-1][n];
	}
	private static long f2(StringTokenizer st) {
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		if(l>n)
			l=n;
		return mem[l][n];
	}
	private static long f1(StringTokenizer st) {
		int n = Integer.parseInt(st.nextToken());
		return mem[n][n];
	}
}
