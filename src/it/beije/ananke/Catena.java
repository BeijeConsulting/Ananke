package it.beije.ananke;
public class Catena{
	
	public void calcola(int max){
		String[] righeS = new String[max];
		String[] righeD = new String[max];

		righeS[0] = 1 + "";;
		righeD[max-1] = max + "";
		
		System.out.println(righeS[0]);
		
		for(int i = 1; i < max; i++){
			righeS[i] = righeS[i-1] + (i+1) + "";
			System.out.println(righeS[i]);
		}	
		
		System.out.println(righeD[max-1]);

		
		for(int i = max-2; i >=0; i--){
			righeD[i] = righeD[i+1] + (i+1) + "";
			System.out.println(righeD[i]);
		}	

				
	
	}
	
	public static void main (String[] args){
		Catena catena = new Catena();
		catena.calcola(9);
	}
}