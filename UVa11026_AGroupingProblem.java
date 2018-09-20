/*

                _____                                  _                     _____                   _       _                    
     /\        / ____|                                (_)                   |  __ \                 | |     | |                   
    /  \      | |  __   _ __    ___    _   _   _ __    _   _ __     __ _    | |__) |  _ __    ___   | |__   | |   ___   _ __ ___  
   / /\ \     | | |_ | | '__|  / _ \  | | | | | '_ \  | | | '_ \   / _` |   |  ___/  | '__|  / _ \  | '_ \  | |  / _ \ | '_ ` _ \ 
  / ____ \    | |__| | | |    | (_) | | |_| | | |_) | | | | | | | | (_| |   | |      | |    | (_) | | |_) | | | |  __/ | | | | | |
 /_/    \_\    \_____| |_|     \___/   \__,_| | .__/  |_| |_| |_|  \__, |   |_|      |_|     \___/  |_.__/  |_|  \___| |_| |_| |_|
                                              | |                   __/ |                                                         
                                              |_|                  |___/                                                          
 */

import java.io.*;
import java.util.*;

public class UVa11026_AGroupingProblem{
	public static void main(String[] args)throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		StringTokenizer st;

		while(true){
			st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken());
			long m = Integer.parseInt(st.nextToken());

			if(n + m == 0) break;

			long[] sol = new long[n];

			long[] values = new long[n];
			long[] reallySol = new long[n];

			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < n; i++)
				values[i]+=Integer.parseInt(st.nextToken());     


			for(int i = n-1; i >= 0; i--) { //i == letra
				for(int j = n-i-1; j >= 1; j--) // j == nivel
					sol[j] = (sol[j] + (values[i] * sol[j-1]) % m) % m;
				sol[0] = (sol[0] + values[i]) % m;
				
			}


			long r = 0;
			for(int i = 0; i < n; i++){
				r = Math.max(r,sol[i]%m);
			}

			out.println(r);

		}



		out.close();
	}

}