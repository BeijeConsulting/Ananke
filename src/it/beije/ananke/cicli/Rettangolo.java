package it.beije.ananke.cicli;

public class Rettangolo{
	
	public static void main(String[] args){
		
		for(int i = 1; i < 10; i++){
			
			int j = 1, z = 10 - i;
		
			while(j <= i){
				System.out.print(j);
				j++;
			}

			System.out.print("    ");
			
			while(z > 0){
				System.out.print(z);
				z--;
			}

			System.out.print("\n");
		}
	}
}