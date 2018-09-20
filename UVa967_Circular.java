import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UVa967_Circular {

	static boolean[] arr=new boolean [1000000];

	public static void main(String[] args) throws IOException {
		try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))){
			int[] z = getPrimos(99984);
			long[] xd = new long[1000001];
			for (int i = 0; i < z.length; i++) {
				if(isCircular(z[i])) 
					xd[z[i]]=1;
			}

			SegmentTree stree = new SegmentTree(xd);
			StringTokenizer st;
			while(true) {
				String in =kb.readLine();
				st = new StringTokenizer(in);
				int a = Integer.parseInt(st.nextToken());
				if(a==-1)
					break;
				if(a>999984)
					a=999984;
				int b = Integer.parseInt(st.nextToken());
				if(b>999984)
					b=999984;
				
				long cont=stree.queryOfRange(a, b);

				if(cont==0)
					System.out.println("No Circular Primes.");
				else if(cont==1)
					System.out.println("1 Circular Prime.");
				else
					System.out.println(cont+" Circular Primes.");
			}
		}

	}

	static boolean isCircular(int n) {
		int k = 1000000;
		int i = 6;
		while(n/k == 0) {
			k /= 10;
			i--;
		}
		for(int j = 0; j < i; j++) {
			int z = n/k;
			n%=k;
			n*=10;
			n+=z;
			if(arr[n])
				return false;
		}
		return true;
	}

	static int[] getPrimos(int cota) {
		int[]res = new int[cota];
		arr[0] = arr[1] = true;
		int k = 0; 
		for(int i = 0; i<arr.length;i++) {
			String x = Integer.toString(i);
			if(!arr[i]) {
				res[k++]=i;
				for(int j = 2*i; j<arr.length; j+=i) 
					arr[j]=true;
			}

			for (int j = 0; j < x.length(); j++) {
				if(x.charAt(j)=='5'||x.charAt(j)=='2'||x.charAt(j)=='4'||x.charAt(j)=='6'||x.charAt(j)=='8'||x.charAt(j)=='0') {
					arr[i]=true;
					break;
				}
			}
		}
		return Arrays.copyOfRange(res, 0, k);
	}
}


class SegmentTree {
	private long[] segmentTree;
	private long[] arr;
	private int n;
	
	public SegmentTree(long[] arr) {
		int x = (int) (Math.ceil(Math.log(arr.length) / Math.log(2)));
        int m = 2 * (int) Math.pow(2, x) - 1;
        n = arr.length;
		segmentTree = new long[m];
		this.arr = arr;
		constructionSegmentTree(0, n-1, 0);
	}

	private void constructionSegmentTree(int i,int j, int p) {
		if(i == j) {
			segmentTree[p] = arr[i];
			return;
		}
		int m = (i + j)/2;
		constructionSegmentTree(i, m, 2*p + 1); 
		constructionSegmentTree(m + 1, j, 2*p + 2);
		segmentTree[p] = segmentTree[2*p+1] + segmentTree[2*p+2];
	}
	
	public long queryOfRange(int i, int j) {
		return queryOfRange(i,j,0,n-1,0);
	}

	private long queryOfRange(int i, int j, int k, int l, int p) {
		if(k >= i && l <= j) return segmentTree[p];
		if(j < k || i > l) return 0;
		return queryOfRange(i, j, k ,(k+l)/2, p * 2 + 1) + queryOfRange(i, j, (k+l)/2 + 1,l, p * 2 + 2);  
	}
	
	
	public void updateValue(int i, long value) {
		update(i, 0, n-1, 0, value);
	}

	private void update(int i, int k, int l, int p, long value) {
		if(k == l) {
			if(k == i)
				segmentTree[p] = value;
			return;
		}
		if(k <= i && i <= l) {
			update(i, k, (k+l)/2, p*2+1, value);
			update(i, (k+l)/2+1, l, p*2+2, value);
			segmentTree[p] = segmentTree[p*2 + 1] + segmentTree[p*2 + 2];
			return;
		}
	}
}
