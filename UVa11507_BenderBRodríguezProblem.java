import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UVa11507_BenderBRodríguezProblem {
	public static void main(String[] args) throws IOException {
		try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))){
			for (String in = kb.readLine(); in!=null; in=kb.readLine()) {
				int n =Integer.parseInt(in);
				if(n<2)
					break;
				else {
					StringTokenizer st = new StringTokenizer(kb.readLine().trim());
					System.out.println(process(st,n));
				}
			}
		}
	}

	private static String process(StringTokenizer st, int l) {
		String actual = "+x";	
		String x;
		for (int i=1; i<l; i++) {
			x = st.nextToken();
			if(!x.equals("No")) 
				if(actual.equals("+x"))
					actual = x.toString();

				else if(actual.equals("-x"))
					if(x.charAt(0)=='-')
						actual = "+"+x.charAt(1);
					else
						actual = "-"+x.charAt(1);

				else if(actual.charAt(1)=='y') {
					if(!(x.charAt(1)=='z'))
						if(actual.charAt(0) == '+' && x.charAt(0) == '+')
							actual = "-x";
						else if(actual.charAt(0) == '+' && x.charAt(0) == '-')
							actual = "+x";
						else if(actual.charAt(0) == '-' && x.charAt(0) == '+')
							actual = "+x";
						else 
							actual = "-x";
				}

				else
					if(!(x.charAt(1)=='y'))
						if(actual.charAt(0) == '+' && x.charAt(0) == '+')
							actual = "-x";
						else if(actual.charAt(0) == '+' && x.charAt(0) == '-')
							actual = "+x";
						else if(actual.charAt(0) == '-' && x.charAt(0) == '+')
							actual = "+x";
						else 
							actual = "-x";


		}
		return actual;
	}

}





















