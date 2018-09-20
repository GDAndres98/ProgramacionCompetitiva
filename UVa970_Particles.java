  import java.io.*;
  public class UVa970_Particles {

  static String cadena;
  static byte mem[][][];
  static char[][] arr = {{'Y','X','Z'},
                    {'X','Y','Y'},
                    {'Z','Y','X'}};

    public static void main(String args[]) throws IOException{
        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))){
          int q = Integer.parseInt(kb.readLine().trim());
          for(int i = 0; i < q; i++ ){
            cadena= kb.readLine();
            int n = cadena.length();
            mem = new byte[n][n][3];
            if(f(0,n-1,'Z')==2)
              System.out.println('Z');
            else if(f(0,n-1,'Y')==2)
              System.out.println('Y');
            else if(f(0,n-1,'X')==2)
              System.out.println('X');
            else
              System.out.println("IDIOTA");
          }

        }

    }

  public static byte f(int i, int j, char l){
    int p=0;
    switch (l) {
            case 'X':  p=0;
                     break;
            case 'Y':  p=1;
                     break;
            case 'Z':  p=2;
                     break;
    }
    if(i==j){
      if(l==cadena.charAt(i)){
        mem[i][j][p]=2;
        return 2;
      }
      mem[i][j][p]=1;
        return 1;
    }
    if(mem[i][j][p]!=0) return mem[i][j][p];
    
    else{
    for(int k=i+1 ; k<=j; k++){
      if(p==0){
        if(f(i,k-1,'Y')==2&&f(k,j,'X')==2){
          mem[i][j][p]=2;
          return 2;}
        if(f(i,k-1,'X')==2&&f(k,j,'Y')==2){
          mem[i][j][p]=2;
          return 2;}
        if(f(i,k-1,'Z')==2&&f(k,j,'Z')==2){
          mem[i][j][p]=2;
          return 2;}
      }
      if(p==1){
        if(f(i,k-1,'Y')==2&&f(k,j,'Y')==2){
          mem[i][j][p]=2;
          return 2;}
        if(f(i,k-1,'X')==2&&f(k,j,'X')==2){
          mem[i][j][p]=2;
          return 2;}
        if(f(i,k-1,'Y')==2&&f(k,j,'Z')==2){
          mem[i][j][p]=2;
          return 2;}
         if(f(i,k-1,'Z')==2&&f(k,j,'Y')==2){
           mem[i][j][p]=2;
          return 2;}
      }
      if(p==2){
        if(f(i,k-1,'X')==2&&f(k,j,'Z')==2){
          mem[i][j][p]=2;
          return 2;}
        if(f(i,k-1,'Z')==2&&f(k,j,'X')==2){
          mem[i][j][p]=2;
          return 2;}
      }


    }
    mem[i][j][p]=1;
    return 1;
  }
 }
}
