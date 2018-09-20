import java.util.Scanner;
public class U11935 {
	static Scanner kb=new Scanner(System.in);
	static double menorConsumo=0;
	static int kma=0;
	static int kmb=0;
	static int cleak=0;

	public static void main(String[] args) {
		int gas=-1;
		double consume=0;
		kma=kb.nextInt();
		while (gas!=0){
			String accion=kb.next();
			if(accion.equals("Fuel")){
				accion=kb.next();
				if(kma==0&&gas!=-1)System.out.println();
				gas=kb.nextInt();
				if (gas==0);
				else{
					consume+=sumadorConsumo(gas);
				}
			}
			else if(accion.equals("Leak")){
				cleak++;
				consume+=sumadorConsumo(gas);
			}
			else if(accion.equals("Gas")){
				accion=kb.next();
				menorConsumo=Math.max(consume,menorConsumo);
				consume=0;
				consume+=sumadorConsumo(gas);
			}
			else if(accion.equals("Mechanic")){
				cleak=0;
				kmb=kma;
				kma=kb.nextInt();
				consume+=(double)(gas*(kma-kmb))/100;
			}
			else if(accion.equals("Goal")){
				consume=Math.max(consume,menorConsumo);
				System.out.printf("%.3f",consume);
				consume=0;
				menorConsumo=0;
				kma=kb.nextInt();;
				kmb=0;
				cleak=0;


			}
		}
	}
	public static double sumadorConsumo(int gas) {
		kmb=kma;
		kma=kb.nextInt();
		return (cleak*(kma-kmb)+(double)(gas*(kma-kmb))/100);

	}
}


