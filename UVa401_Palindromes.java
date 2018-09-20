import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class UVa401_Palindromes {
	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st, stT;
		int aux;
		StringBuilder x = new StringBuilder("");
		byte isMirror;
		boolean pal;
		String letter;
		HashMap<Character, Character> map = new HashMap<>();
		map.put('A', 'A');
		map.put('E', '3');
		map.put('3', 'E');
		map.put('H', 'H');
		map.put('I', 'I');
		map.put('J', 'L');
		map.put('L', 'J');
		map.put('M', 'M');
		map.put('O', 'O');
		map.put('S', '2');
		map.put('T', 'T');
		map.put('U', 'U');
		map.put('V', 'V');
		map.put('W', 'W');
		map.put('X', 'X');
		map.put('Y', 'Y');
		map.put('Z', '5');
		map.put('5', 'Z');
		map.put('2', 'S');
		map.put('1', '1');
		map.put('8', '8');

		while (true) {
			try {
				letter = in.readLine();
				if (letter.equals("") || letter == null)
					break;
			} catch (Exception e) {
				break;
			}
			isMirror = 0;
			pal = true;
			int i = 0, j = letter.length() - 1;
			for (; i < j; i++, j--) {
				if (isMirror == 0) {
					if (!map.containsKey(letter.charAt(i))||map.get(letter.charAt(i)) != letter.charAt(j)) {
						isMirror = -1;
						if (letter.charAt(i) != letter.charAt(j)) {
							pal = false;
							break;
						}
					} else if (map.get(letter.charAt(i)) != letter.charAt(i)
							&& map.get(letter.charAt(i)) == letter.charAt(j)) {
						isMirror = 1;
						if (map.get(letter.charAt(i)) != letter.charAt(j)) {
							pal = false;
							break;
						}
					} else {
						if (letter.charAt(i) != letter.charAt(j)) {
							pal = false;
							break;
						}
					}

				} else if (isMirror == 1) {
					if (!map.containsKey(letter.charAt(i)) || map.get(letter.charAt(i)) != letter.charAt(j)) {
						pal = false;
						break;
					}
				} else if (isMirror == -1) {
					if (letter.charAt(i) != letter.charAt(j)) {
						pal = false;
						break;
					}

				}

			}
//			System.out.println(isMirror);
			if (j == i)
				if (isMirror == 1) {
					if (!map.containsKey(letter.charAt(i))||(map.get(letter.charAt(i))!=letter.charAt(i)))
						pal = false;
				} else if (isMirror == 0 && (!map.containsKey(letter.charAt(i))||(map.get(letter.charAt(i))!=letter.charAt(i))))
					isMirror = -1;

			if (pal)
				if (isMirror == -1)
					out.println(letter + " -- is a regular palindrome.");
				else if (isMirror == 0)
					out.println(letter + " -- is a mirrored palindrome.");
				else
					out.println(letter + " -- is a mirrored string.");
			else
				out.println(letter + " -- is not a palindrome.");
			out.println();
		}

		out.close();

	}
}
