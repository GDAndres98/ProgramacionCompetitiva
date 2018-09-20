import java.util.*;
import java.io.*;

public class UVa11111_GeneralizedMatrioshkas {

	public static void main(String[] args) throws IOException {
		try(BufferedReader kb= new BufferedReader(new InputStreamReader(System.in))){
			StringTokenizer st;
			for (String in=kb.readLine(); in!=null&&!in.equals(""); in=kb.readLine() ) {
				st = new StringTokenizer(in.trim());
				Stack<Integer> num = new Stack<Integer>(); 
				int[] arr = new int[st.countTokens()];
				for (int i = 0; i < arr.length; i++) {
					num.add(Integer.parseInt(st.nextToken()));
				}
				
				System.out.println(isValid(num)?":-) Matrioshka!":":-( Try again.");
			}
		}
	}

	private static boolean isValid(Stack<Integer> num) {
		Stack<Integer> close = new Stack<Integer>();
		Stack<Integer> sumas = new Stack<Integer>();
		if(num.peek()<0)
			return false;
		while (!num.isEmpty()) {
			int n = num.pop();
			if(n>0) {
			close.add(n);
			sumas.add(n);
			}
			else {
				sumas.pop();
				if(n+close.pop()!=0) 
					return false;
				else {
					if(!sumas.isEmpty()) {
					int x = sumas.pop() + n;
					sumas.add(x);
					if(x<=0)
						return false;
					}
				}
			}
		}
		return (num.isEmpty()&&close.isEmpty());
	}


}

/*
-9 -5 5 9
-9 -7 7 -2 2 9

*/