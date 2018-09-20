import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class xUVa11770_LightingAway {

	static boolean[] arr=new boolean [1000000];

	public static void main(String[] args) throws IOException {
		try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))){
			int f = Integer.parseInt(kb.readLine());
			StringTokenizer st;
			for (int i = 0; i < f; i++) {
				st = new StringTokenizer(kb.readLine());
				int n = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());
				int arr[] = new int[n];
				for (int j = 0; j < arr.length; j++) {
					arr[j]=j;
				}
				for (int j = 0; j < m; j++) {
					st = new StringTokenizer(kb.readLine());
					int a = Integer.parseInt(st.nextToken())-1;
					int b = Integer.parseInt(st.nextToken())-1;
					int t = arr[b];
					if(t==b)
						for (int k = 0; k < arr.length; k++) {
							if(k!=b) {
								if(arr[k]==arr[b])
									arr[k]=arr[a];
							}
						}

					arr[b]=arr[a];
				}
				int cont=0;
				HashSet<Integer> c = new HashSet<Integer>();
				for (int j = 0; j < arr.length; j++) {
					c.add(arr[j]);
				}
				
				System.out.println("Case "+(i+1)+": "+c.size());
				kb.readLine();
			}
		}

	}
}



