import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


 class xUVa11626_ConvexHull {
	public static double[] min = new double[2];
	public static void main(String[] args) throws IOException {
	System.setIn(new FileInputStream("file"));
		BufferedReader kb= new BufferedReader(new InputStreamReader(System.in));
		 PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		String in=kb.readLine().trim(); 
		int n = Integer.parseInt(in);
		for (int i = 0; i < n; i++) {
			int z = Integer.parseInt(kb.readLine().trim());
			double[][] arr= new double[z][3];
			
			int j = 0;
			boolean noEn=true;
			while(noEn) {
				in=kb.readLine().trim();
				st = new StringTokenizer(in);
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				if(st.nextToken().equals("Y")) {
					noEn=false;
					min[0] = x;
					min[1] = y;
					arr[0][0] = x;
					arr[0][1] = y;
				}
				j++;
			}
			int j2=1;
			for (; j < z; j++) {
				in=kb.readLine();
				st = new StringTokenizer(in);
				double x = Integer.parseInt(st.nextToken());
				double y = Integer.parseInt(st.nextToken());
				if(st.nextToken().equals("Y")) {
					arr[j2][0]=x;
					arr[j2][1]=y;
					min=getNewMin(x,y);
					
					j2++;
				}
			}
			for (j = 0 ; j < j2; j++) {
					arr[j][2]=setAng(arr[j]);
			}
			Arrays.sort(arr,0,j2,new Comparator<double[]>(){
				@Override
				public int compare(double[] o1, double[] o2) {
					if(Math.hypot(o1[0]-min[0], o1[1]-min[1])==0)
						return -1;
					if(Math.hypot(o2[0]-min[0], o2[1]-min[1])==0)
						return 1;
					if(o1[2]>o2[2]) {
						return 1;
					}
					if(o1[2]==o2[2]) {
						double act=Math.hypot(o1[0]-min[0], o1[1]-min[1]);
						double otr=Math.hypot(o2[0]-min[0], o2[1]-min[1]);
						if(o1[2]>0)
							return Double.compare(otr, act);
						else
							return Double.compare(act, otr);
					}
					return -1;
				}
			});
			out.println(j2);
			int x=(int) min[0];
			int y=(int) min[1];
			for (int k2 = 0; k2 < arr.length; k2++) {
				 x=(int) arr[k2][0];
				 y=(int) arr[k2][1];
				out.println(x+" "+y);
			}
		}
		out.flush();
		out.close();
	}

	public static double setAng( double[] ds) {
		return Math.atan2(ds[1]-min[1], ds[0]-min[0]);
	}

	public static double[] getNewMin(double x, double y) {
		double[] p = {x,y};
		if(min[0]>=x) {
			if(min[0]>x) {
				return p;
			}
			else {
				if(min[1]>y) {
					return p;
				}
			}
		}
		return min;
	}

}

/* 
1
5
1 1 Y
1 -1 Y
0 0 N
-1 -1 Y
-1 1 Y

1
5
1 1 Y
1 -1 Y
0 0 N
-1 1 Y
-1 -2 Y

3
12
0 0 Y
2 0 Y
1 0 Y
3 0 Y
3 3 Y
1 3 Y
2 3 Y
3 1 Y
3 2 Y
0 1 Y
0 2 Y
0 3 Y
3
0 0 Y
1000000000 1000000000 Y
1000000000 0 Y
8
0 -2 Y
1 -1 Y
2 0 Y
1 1 Y
0 2 Y
-1 1 Y
-2 0 Y
-1 -1 Y
 * 
 */