package it.beije.ananke;

public class Piramide{
	public void stampaNumeri(int n){
		int x = 1;
		
		while(x<=n){
			int i = 0;
			int j = n-i;
			
			for(i = 1; i <= x; i++) {
				System.out.print(i);
			}
			for(int k=0; k<n; k++) {
				System.out.print(" ");
			}
			for(j = n-x+1; j > 0; j--) {
				System.out.print(j);
			}
			System.out.println();
			x++;
		}
	}
}