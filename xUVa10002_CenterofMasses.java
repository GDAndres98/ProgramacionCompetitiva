import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class xUVa10002_CenterofMasses {
	
	public static void main(String[] args) throws Exception{
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		String in = kb.readLine();
		for (int i = 0; in!=null ; i++) {
			
			
			
		}
		
		
		
	}
	
	static int sgn(double x) {return Math.abs(x)>9.9e-12?(x<0?-1:1):0;}
	static double cruz(double[] a, double[] b) {return a[0]*b[1]-a[1]*b[0];}
	static double cruz(double[] a, double[] b, double[] c) { return cruz(a,b) + cruz(b, c) + cruz(c,a);}
	static double[][] convexHullGS(double[][] pt, boolean bd){
		int h=pt.length,i=h,t=0; double v[]=null,w[],r[][]=new double[h][];
		for (double[] p:pt) if(v==null||p[1]<v[1]||(p[1]==v[1]&&p[0]>v[0])) v=p; v = v.clone();
		for (double[] p:pt) {p[0]-=v[0]; p[1]-=v[1];}
		Arrays.sort(pt,new Comparator<double[]>() {public int compare(double[] a, double[] b)
		{double cz=cruz(b,a); return sgn(sgn(cz)!=0?cz:a[0]*a[0]+a[1]*a[1]-b[0]*b[0]-b[1]*b[1]);}});
		if(bd) while(i-1>=0&&cruz(pt[h-1],pt[i-1])==0)i--;
		if(bd) for(int k=i; k<(i+h)/2;k++) {w=pt[k]; pt[k]=pt[h-1-k+i]; pt[h-1-k+i]=w;}
		for(double[] p:pt) {while(t>=2&&sgn(cruz(r[t-1],p,r[t-2]))<(bd?0:1))t--; r[t++]=p;}
		for(double[] p:pt) {p[0]+=v[0]; p[1]+=v[1];}
		return Arrays.copyOfRange(r,0,t);
	}
	
	static double area(double[][] pts){
		double res=0;int n=pts.length;
		for(int i=0,j=1;i<n;i++,j=(j+1)%n)
			res+=pts[i][0]*pts[j][1]-pts[i][1]*pts[j][0];
		return Math.abs(res)/2;
	}
	
	static double[] centroide(double[][] pt){
		double p[] ={0d,0d}, d= area(pt)*6;
		for(int i=0,j=1,t=pt.length;i<t;i++,j=(j+1)%t)
			for(int k=0;k<2;k++)
				p[k]+=(pt[i][k]+pt[j][k])*(pt[i][0]*pt[j][1]-pt[j][0]*pt[i][1]);
		return new double[] {p[0]/d, p[1]/d};
	}


}
