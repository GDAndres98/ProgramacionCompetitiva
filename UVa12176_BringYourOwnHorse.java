import java.util.*;
import java.io.*;

public class UVa12176_BringYourOwnHorse {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("file"));
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		String in=kb.readLine();
		int n = Integer.parseInt(in);
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			in = kb.readLine();
			st = new StringTokenizer(in);
			int nn = Integer.parseInt(st.nextToken());
			//			int[][] graph = new int[m+1][m+1];
			ArrayList<Integer>[] adj= new ArrayList[nn];
			for (int k = 0; k < nn; k++) {
				adj[k] = new ArrayList<Integer>();
			}
			int[][] w= new int[nn][nn];
			int m = Integer.parseInt(st.nextToken());
			for (int j = 0; j < m; j++) {
				in = kb.readLine();
				st = new StringTokenizer(in);;
				int q1=Integer.parseInt(st.nextToken())-1;
				int q2=Integer.parseInt(st.nextToken())-1;
				int q3=Integer.parseInt(st.nextToken());
				//				graph[q1][q2]=q3;
				adj[q1].add(q2);
				if(w[q1][q2]!=0) {
					w[q1][q2] = Math.min(w[q1][q2], q3);
					w[q1][q2] = w[q1][q2];
				}
				else {					
					w[q1][q2] = q3;
					w[q2][q1] = q3;
				}
			}
			ArrayList<int[]> result=kruskal(adj,w);
			HashMap<Integer,Node> mapIN = new HashMap<>();
			HashMap<Node,Integer> mapNI = new HashMap<>();
			for (int j = 0; j < nn; j++) {
				mapIN.put(j, new Node());
				mapNI.put(mapIN.get(j),j);
			}

//			for (int j = 0; j < result.size(); j+=2) {
//				int[] z =result.get(j);
//				for (int k = 0; k < z.length; k++) {
//					System.out.print(z[k]+1+" ");
//				}
//				System.out.println();
//			}


			makeTree(mapIN,result);
//			for (int j = 0; j < nn; j++) {
//				System.out.println(mapIN.get(j).h);
//			}

			System.out.println("Case "+(i+1));
			in=kb.readLine();
			int rutas = Integer.parseInt(in);
			for (int j = 0; j < rutas; j++) {
				in=kb.readLine();
				st = new StringTokenizer(in);
				int u = Integer.parseInt(st.nextToken())-1;
				int v = Integer.parseInt(st.nextToken())-1;
				int max = 0;
				while(mapIN.get(u).h!=mapIN.get(v).h) {
					if(mapIN.get(u).h>mapIN.get(v).h) {
						max = Math.max(max, w[mapNI.get(mapIN.get(u).an)][u]);
						u = mapNI.get(mapIN.get(u).an);
					}
					else {
						max = Math.max(max, w[mapNI.get(mapIN.get(v).an)][v]);
						v = mapNI.get(mapIN.get(v).an);
					}
				}
				while(mapIN.get(u)!=mapIN.get(v)) {
					max = Math.max(max, w[mapNI.get(mapIN.get(u).an)][u]);
					u = mapNI.get(mapIN.get(u).an);
					max = Math.max(max, w[mapNI.get(mapIN.get(v).an)][v]);
					v = mapNI.get(mapIN.get(v).an);
				}
				System.out.println(max);

			}
			System.out.println();


		}

	}


	static void makeTree(HashMap<Integer, Node> map, ArrayList<int[]> result) {
		{
			Node x =map.get(result.get(0)[0]);
			Node y =map.get(result.get(0)[1]);
			x.h=0;
			y.an=x;
			y.h=x.h+1;
		}

		for (int j = 2; j < result.size(); j+=2) {
			Node x =map.get(result.get(j)[0]);
			Node y =map.get(result.get(j)[1]);
			if(x.h!=-1) {
				if(y.h==-1)
					y.h++;
				y.an=x;
				y.h=x.h+y.h+1;
			}
			else if(y.h!=-1) {
				if(x.h==-1)
					x.h++;
				x.an=y;
				x.h=x.h+y.h+1;
			}
			else {
				result.add(new int[]{result.get(j)[0],result.get(j)[1]});
				result.add(new int[]{result.get(j)[0],result.get(j)[1]});
			}
		}

	}


	static class Node {
		int h;
		Node an;
		public Node() {
			h=-1;
		}
	}


	/*
1
4 4
1 2 100
1 4 200
2 3 100
3 4 100 

1
5 7
1 2 2
2 3 3
1 3 1
3 4 6
2 4 4
3 5 8
4 5 7

1
11 11
5 11 43
9 11 1
4 9 53
6 5 6
1 6 61
8 1 31
3 4 93
10 3 68
7 8 12
2 5 86
6 7 42
4
2 6
11 7
4 8
3 4




	 */





	static ArrayList<int[]> kruskal(ArrayList<Integer>[] lAdy, final int[][] mAdy){
		initSet(lAdy.length);
		int c = 0;
		for(ArrayList<Integer> l:lAdy)
			c+=l.size();
		int[][] aristas = new int[c][];
		c=0;
		for (int u = 0; u < lAdy.length; u++) 
			for(int v:lAdy[u])
				aristas[c++]=new int[] {u,v};
		Arrays.sort(aristas,new Comparator<int[]>(){
			@Override
			public int compare(int[] o1,int[] o2) {
				double val1=mAdy[o1[0]][o1[1]],val2=mAdy[o2[0]][o2[1]];
				return val1<val2?-1:(val1>val2?1:0);
			}
		});

		ArrayList<int[]> res=new ArrayList<int[]>();
		for(int i=0; i<aristas.length;i++)
			if(!isSameSet(aristas[i][0], aristas[i][1])) {
				unionSet(aristas[i][0], aristas[i][1]);
				res.add(aristas[i]);
				res.add(new int[] {aristas[i][1], aristas[i][0]});
			}

		return res;

	}
	public static int[]set;
	public static int _numDisjointSets;
	public static void initSet(int size) {
		set = new int[size];
		_numDisjointSets = size;
		for ( int i = 0; i < size; i++)
			set[i]=i;

	}
	public static int findSet(int i) {
		return set[i] == i ? i : findSet(set[i]);
	}
	public static boolean isSameSet(int i, int j) {
		return (findSet(i)== findSet(j));
	}
	public static void unionSet(int i, int j) {
		if(!isSameSet(i, j))
			_numDisjointSets--;
		set[findSet(i)] = findSet(j);
	}




}
