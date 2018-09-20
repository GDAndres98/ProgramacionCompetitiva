
/*
 #####    #               #    #                         #   #                      
 #                        #                              #   #                      
 #       ##    # ##    ## #   ##    # ##    ## #         ##  #   ###   ## #    ###  
 ####     #    ##  #  #  ##    #    ##  #  #  #          # # #  #   #  # # #  #   # 
 #        #    #   #  #   #    #    #   #   ##           #  ##  #####  # # #  #   # 
 #        #    #   #  #  ##    #    #   #  #             #   #  #      # # #  #   # 
 #       ###   #   #   ## #   ###   #   #   ###          #   #   ###   #   #   ###  
                                           #   #                                    
                                            ###                                     
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa1202_FindingNemo {
	static int[] limit = new int[2];
	static int[][] fill = new int[203][203];
	static int[][] cage = new int[203][203];

	// Primer dígito: puerta
	// Segundo dígito: pared
	// Dígito 0: nada
	// Dígito 1: derecha
	// Dígito 2: arriba
	// Dígito 3: ambas
	private static void addDoor(int x, int y, int H, int[][] cage) {
		if (H == 1) {
			x--;
			if ((cage[x][y] % 10) % 2 == 1)
				cage[x][y]--;

			if (cage[x][y] / 10 == 0)
				cage[x][y] = 10 + cage[x][y] % 10;

			else if (cage[x][y] / 10 == 2)
				cage[x][y] = 30 + cage[x][y] % 10;

		} else {
			y--;

			if ((cage[x][y] % 10) >= 2)
				cage[x][y] -= 2;
			if (cage[x][y] / 10 == 0)

				cage[x][y] = 20 + cage[x][y] % 10;

			else if (cage[x][y] / 10 == 1)
				cage[x][y] = 30 + cage[x][y] % 10;
		}
		limit[0] = Math.max(limit[0], x + 2);
		limit[1] = Math.max(limit[1], y + 2);

	}

	private static void addWall(int x, int y, int H, int l, int[][] cage) {
		int i;
		if (H == 1) {
			x--;
			for (i = y; i < y + l; i++) {
				if (cage[x][i] == 0)
					cage[x][i] = 1;
				else if (cage[x][i] == 2)
					cage[x][i] = 3;
			}
			limit[0] = Math.max(limit[0], x + 2);
			limit[1] = Math.max(limit[1], i + 2);
		} else {
			y--;
			for (i = x; i < x + l; i++) {
				if (cage[i][y] == 0)
					cage[i][y] = 2;
				else if (cage[i][y] == 1)
					cage[i][y] = 3;
			}
			limit[0] = Math.max(limit[0], i + 2);
			limit[1] = Math.max(limit[1], y + 2);
		}

	}

	private static void getNemo(int[] nemo, double x, double y) {
		nemo[0] = (int) x;
		nemo[1] = (int) y;
//		if(limit[0]<50&&limit[1]<50) {
//		limit[0] = Math.max(limit[0], nemo[0]+1);
//		limit[1] = Math.max(limit[1], nemo[1]+1);
//		}
	}

	private static int solve(int[] nemo, int[][] cage) {
		if (nemo[0] > limit[0] || nemo[1] > limit[1])
			return 0;

		int n = limit[0];
		int m = limit[1];
		for (int i = 0; i < 200; i++)
			Arrays.fill(fill[i], -1);

		AlvPMVP(0, 0, 0);

		return fill[nemo[0]][nemo[1]];
	}

	private static void AlvPMVP(int i, int j, int k) {
		
		if (fill[i][j] == k)
			return;
		if (fill[i][j] == -1 || fill[i][j] >= k)
			fill[i][j] = k;
		else
			return;
		if (j + 1 < limit[1]) {
			if (cage[i][j] % 10 < 2) {
				if (cage[i][j] / 10 < 2)
					AlvPMVP(i, j + 1, k);
				else
					AlvPMVP(i, j + 1, k + 1);
			}
		}
		if (i + 1 < limit[0]) {
			if ((cage[i][j] % 10) % 2 == 0) {
				if ((cage[i][j] / 10) % 2 == 0)
					AlvPMVP(i + 1, j, k);
				else
					AlvPMVP(i + 1, j, k + 1);

			}
		}
		if (j - 1 >= 0) {
			if (cage[i][j - 1] % 10 < 2) {
				if (cage[i][j - 1] / 10 < 2)
					AlvPMVP(i, j - 1, k);
				else
					AlvPMVP(i, j - 1, k + 1);
			}

		}
		if (i - 1 >= 0) {
			if ((cage[i - 1][j] % 10) % 2 == 0) {
				if ((cage[i - 1][j] / 10) % 2 == 0)
					AlvPMVP(i - 1, j, k);
				else
					AlvPMVP(i - 1, j, k + 1);

			}
		}

	}


	private static void print(int f, int nemo, int nemo2) {
		
		for (int j = f; j >= 0; j--) {
			for (int i = 0; i < f; i++) {
				if(cage[i][j]%10==2||cage[i][j]%10==3)
					System.out.print("_");
				else if(cage[i][j]/10==2||cage[i][j]/10==3)
					System.out.print("-");
				else 
					System.out.print(" ");
				System.out.print(" ");
					
			}
			System.out.println();
			for (int i = 0; i < f; i++) {
				if(i==nemo&&j==nemo2)
					System.out.print("Z");
				else if(fill[i][j]!=-1)
					System.out.print(fill[i][j]);
				else
					System.out.print("x");
				if(cage[i][j]%10==1||cage[i][j]%10==3)
					System.out.print("|");
				else if(cage[i][j]/10==1||cage[i][j]/10==3)
					System.out.print("/");
				else 
					System.out.print(" ");
			}
			System.out.println();
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int m, n;

		int[] nemo = new int[2];

		StringTokenizer st = new StringTokenizer(in.readLine());
		while ((m = Integer.parseInt(st.nextToken())) != -1) {
			n = Integer.parseInt(st.nextToken());
			for (int i = 0; i < cage.length; i++) {
				Arrays.fill(cage[i], 0);
			}
			limit[0] = 0;
			limit[1] = 0;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(in.readLine());
				addWall(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), cage);
			}
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());
				addDoor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), cage);
			}
			st = new StringTokenizer(in.readLine());
			double x = Double.parseDouble(st.nextToken()), y = Double.parseDouble(st.nextToken());
			getNemo(nemo, x, y);
			out.println(solve(nemo, cage));
			//print(50,nemo[0],nemo[1]);
			//System.out.println("****************");
			st = new StringTokenizer(in.readLine());
		}
		out.close();
	}

}













