/*
 
                                                         
  ###     #    #   #          ###            #    #   # 
 #   #   # #   #   #           #            # #   #   # 
 #      #   #  ## ##           #           #   #  ## ## 
  ###   #   #  # # #           #           #   #  # # # 
     #  #####  #   #           #           #####  #   # 
 #   #  #   #  #   #           #           #   #  #   # 
  ###   #   #  #   #          ###          #   #  #   # 
                                                        
*/


import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class xUVa11419_SAMIAM{
  public static void main(String[] args)throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    
    StringTokenizer st;
    String s;
    while(true){
    	s = in.readLine();
    	while(s.length() == 0)
    		s = in.readLine();
      st = new StringTokenizer(s);
      int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());

      if(r + c + n == 0) break;
      
      int[] x = new int[r];
      
      ArrayList<int[]>[] y = new ArrayList[c];
      
      for(int i = 0; i < c; i++)
    	  y[i] = new ArrayList<>();
      int[] g = new int[c];
      int count = 0;
      
      
      for(int i = 0; i < n; i++){
    
        st = new StringTokenizer(in.readLine());
        int[] pt = new int[2];
        pt[0] = Integer.parseInt(st.nextToken())-1;
        pt[1] = Integer.parseInt(st.nextToken())-1;
        if(x[pt[0]] == 0)
          count += 1;
        
        x[pt[0]] += 1;
        y[pt[1]].add(pt);
      }
      
      for(int i = 0 ; i < c; i++){
        boolean t = false;
        for(int[] pt: y[i]){
          if(x[pt[0]] == 1){
            t = true;
            count -= 1;
          }
          g[i] += 1;
        }
        if(t){
          for(int[] pt: y[i])
          	x[pt[0]] -= 1;
          	count += 1;
        }
        else
          g[i] = 0;
      }
      
      
      out.print(count);
      
      for(int i = 0; i < r; i++){
        if(x[i] > 0)
          out.print(" r" + (i+1));
      }

      for(int i = 0; i < c; i++){
        if(g[i] > 0)
          out.print(" c" + (i+1));
      }
      out.println();
      
    }

    
    out.close();
  }
}

/*
4 4 3
1 1
1 4
3 2

4 4 2
1 1
2 2

0 0 0
*/