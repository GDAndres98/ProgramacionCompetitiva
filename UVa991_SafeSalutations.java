import java.util.*;
import java.io.*;

public class UVa991_SafeSalutations {

	static int arr[]=new int[10];
	public static void main(String args[]) throws IOException {

		try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))){
			boolean z=false;
			for(String in=kb.readLine();in!=null;in=kb.readLine()){
				if(z)
					System.out.println();
				arr[0]=1;
				System.out.println(shakeHands(Integer.parseInt(in)));
				z=true;

				kb.readLine();
			}
		}

	}

	public static long shakeHands(int in){
		if(arr[in-1]!=0){
			return arr[in-1];
		}
		if(in<=0)
			return 1;
		if(in==1)
			return 1;
		int x=0;

		x+=2*shakeHands(in-1);
		for (int i = 1; (2*i)<=(in-1) ;i++ ){
			if(i==in-i-1)
				x+=shakeHands(i)*shakeHands(in-i-1);
			else
				x+=2*shakeHands(i)*shakeHands(in-i-1);

		}

		arr[in-1]=x;
		return x;

	}






}
