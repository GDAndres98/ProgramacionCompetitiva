import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class xUVa1030_ImageIsEverything {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		char[][][] cube;
		char[][][] view = null;
		StringTokenizer st = null;
		int t = Integer.parseInt(in.readLine());
		while (t > 0) {
			cube = new char[t][t][t];
			view = new char[t][6][t];
			prepare(t, view, in, st);
			out.println(solve(cube, view));
			t = Integer.parseInt(in.readLine());
		}
		out.close();
	}

	private static String solve(char[][][] cube, char[][][] view) {
		byte b = 0;

		// front
		for (int x = 0; x < view.length; x++) {
			for (int y = 0; y < view.length; y++) {
				for (int i = 0; i < view.length; i++) {
					cube[y][x][i] = view[y][0][x];
				}
			}
		}

		print(cube);
		// left
		for (int z = 0; z < view.length; z++) {
			for (int y = 0; y < view.length; y++) {
				b = 0;
				for (int x = 0; x < view.length && (b <= 0); x++) {
					if (view[y][1][z] == '.')
						cube[y][x][view.length - z - 1] = '.';
					else if (b == -1)
						cube[y][x][view.length - z - 1] = 'x';
					else if (cube[y][x][view.length - z - 1] == view[y][1][z])
						b = 1;
					else if (cube[y][x][view.length - z - 1] != view[y][1][z]
							&& cube[y][x][view.length - z - 1] != '.') {
						cube[y][x][view.length - z - 1] = 'x';
						b = -1;
					}
				}
			}
		}
		print(cube);
		// back
		for (int x = 0; x < view.length; x++) {
			for (int y = 0; y < view.length; y++) {
				b = 0;
				for (int i = view.length - 1; i >= 0 && (b <= 0); i--) {
					if (view[y][2][x] == '.')
						cube[y][view.length - x - 1][i] = '.';
					else if (b == -1)
						cube[y][view.length - x - 1][i] = 'x';
					else if (cube[y][view.length - x - 1][i] == view[y][2][x])
						b = 1;
					else if (cube[y][view.length - x - 1][i] != view[y][2][x]
							&& cube[y][view.length - x - 1][i] != '.') {
						cube[y][view.length - x - 1][i] = 'x';
						b = -1;
					}
				}
			}
		}
		print(cube);
		// right
		for (int y = 0; y < view.length; y++) {
			for (int z = 0; z < view.length; z++) {
				b = 0;
				for (int x = view.length - 1; x >= 0 && (b <= 0); x--) {
					if (view[y][3][z] == '.')
						cube[y][x][z] = '.';
					else if (b == -1)
						cube[y][x][z] = 'x';
					else if (cube[y][x][z] == view[y][3][z])
						b = 1;
					else if (cube[y][x][z] != view[y][3][z] && cube[y][x][z] != '.') {
						cube[y][x][z] = 'x';
						b = -1;
					}

				}
			}
		}
		print(cube);
		// top
		for (int z = 0; z < view.length; z++) {
			for (int x = 0; x < view.length; x++) {
				b = 0;
				for (int y = 0; y < view.length && (b <= 0); y++) {

					if (view[z][4][x] == '.')
						cube[y][x][view.length - z - 1] = '.';
					else if (b == -1)
						cube[y][x][view.length - z - 1] = 'x';
					else if (cube[y][x][view.length - z - 1] == view[z][4][x])
						b = 1;
					else if (cube[y][x][view.length - z - 1] != view[z][4][x]
							&& cube[y][x][view.length - z - 1] != '.') {
						cube[y][x][view.length - z - 1] = 'x';
						b = -1;
					}
				}
			}
		}
		print(cube);
		// bottom
		for (int z = 0; z < view.length; z++) {
			for (int x = 0; x < view.length; x++) {
				b = 0;
				for (int y = view.length-1; y >= 0 && (b <= 0); y--) {
					if (view[z][5][x] == '.')
						cube[y][x][z] = '.';
					else if (b == -1)
						cube[y][x][z] = 'x';
					else if (cube[y][x][z] == view[z][5][x])
						b = 1;
					else if (cube[y][x][z] != view[z][5][x] && cube[y][x][z] != '.') {
						cube[y][x][z] = 'x';
						b = -1;
					}
				}
			}
		}
		print(cube);

		return "Maximum weight: " + sumAll(cube) + " gram(s)";
	}

	private static void print(char[][][] cube) {
		for (int z = 0; z < cube.length; z++) {
			for (int y = 0; y < cube.length; y++) {
				for (int x = 0; x < cube.length; x++) {
					System.out.print(cube[y][x][z] + " ");
				}
				System.out.println();
			}
			System.out.println("___");
		}
		System.out.println("***");

	}

	private static int sumAll(char[][][] cube) {
		int cont = 0;
		for (int x = 0; x < cube.length; x++) {
			for (int y = 0; y < cube.length; y++) {
				for (int z = 0; z < cube.length; z++) {
					if (cube[x][y][z] != '.' && cube[x][y][z] != 'x')
						cont++;
				}
			}
		}
		return cont;
	}

	private static void prepare(int t, char[][][] view, BufferedReader in, StringTokenizer st) throws Exception {
		for (int f = 0; f < t; f++) {
			st = new StringTokenizer(in.readLine());
			for (int cu = 0; cu < 6; cu++) {
				view[f][cu] = st.nextToken().toCharArray();
			}
		}
		return;
	}
}
