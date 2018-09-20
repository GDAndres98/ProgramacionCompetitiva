import java.io.*;
import java.util.*;

public class UVa10340_AllInAll {
	public static void main(String[] args) throws IOException {
		try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))){
			for (String in=kb.readLine();  in!=null; in=kb.readLine()) {
				StringTokenizer st = new StringTokenizer(in.trim());
				System.out.println(metodo(st.nextToken(),st.nextToken()));
			}
			
		}
	}

	private static String metodo(String o, String n) {
		if(n.contains(o))
			return "Yes";
		else if(n.length()==o.length())
			return "No";
		else{
			int idx=0;
			for (int i = 0; i < n.length(); i++) {
				if(o.charAt(idx)==n.charAt(i)) {
					idx++;
					if(idx==o.length()) 
						return "Yes";
				}
			}
			
		}
		
		
		return "No";
		
		
	}
}
