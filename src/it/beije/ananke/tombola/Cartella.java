package it.beije.ananke.tombola;

import java.util.Arrays;

public class Cartella{
	
	private int[][] cart;
	
	public Cartella(){
		
		this.cart = new int[9][2];
		
	}
	
	
	public static Cartella generaCartellaBase(int[][] tab, int... vColDaDue) {
		
		
		int num = 0; 				// indicherà se la colonna è da uno o due elementi
		int x=0; 					// metto il numero randomico genreato
		int col[] = new int[2];     // inserirò la colonna della cartella
		int temp;					// lo uso per scambiare i due numeri della colonna se sono "disordinati"
		Cartella c = new Cartella();
		
		//for(int i = 0; i < 3; i++) {System.out.println(vColDaDue[i]);}
		
		for(int i = 0; i < 9; i++) {
			
			if(Arrays.binarySearch(vColDaDue, i) >= 0 ) { num = 1; } else { num = 2; }
			
			for(int j = 0; j < num; j++) {
					
				do{
					x = Utility.rand(0, tab[i].length-1);
				}while(tab[i][x] == 0);
				
				col[j] = tab[i][x];
				tab[i][x] = 0;
				
			}
			
			if(num == 2)
				if(col[0] > col[1]){
					temp = col[1];
					col[1] = col[0];
					col[0] = temp;
				}
					
			c.insertCol(col, i, num);
			
		}
		
		return c;
		//c.visualizzaCartella();
		
	}
	
	public void visualizzaCartella() {
		
		System.out.println("--------*CARTELLA*--------");
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 9; j++) {
				if(this.cart[j][i] == 0) {System.out.print("[]" + " ");}
				else { if(this.cart[j][i] > 10)System.out.print(this.cart[j][i] + " ");
						else System.out.print(" " + this.cart[j][i] + " ");}
			}
			System.out.println();
		}
		
		System.out.println("--------------------------");
		
	}
	


	public void insertCol(int[] col, int numCol, int elem) {
		for(int j = 0; j < elem; j++) {
			this.cart[numCol][j] = col[j];
		}
		
	}


	public static Cartella generaCartella(int[] colDaDue) {
		
		int num = 0; 						// indicherà se la colonna è da ono o due elementi
		int x=0; 							// metto il numero randomico genreato
		int col[] = new int[] {0, 0};       // inserirò la colonna della cartella
		int min = 0; int max = 0;
		int temp = 0;
		Cartella c = new Cartella();
		
		//for(int i = 0; i < 3; i++) {System.out.println(vColDaDue[i]);}
		
		
		//Seleziona il range di numeri della colonna da generare
		for(int i = 0; i < 9; i++) {
			
			switch (i) {
			
			case 0:
				 min = 1; max = 9;
				break;
			case 1:
				min = 10; max = 19;
				break;
			case 2:
				min = 20; max = 29;
				break;
			case 3:
				min = 30; max = 39;
				break;
			case 4:
				min = 40; max = 49;
				break;
			case 5:
				min = 50; max = 59;
				break;
			case 6:
				min = 60; max = 69;
				break;
			case 7:
				min = 70; max = 79;
				break;
			case 8:
				min = 80; max = 90;
				break;
			}
			
			if(Arrays.binarySearch(colDaDue, i) >= 0 ) { num = 1; } else { num = 2; }
			
			
			if(num == 1) {
				
				col[0] = Utility.rand(min, max);;
				
			}else{
				
				col[0] = Utility.rand(min, max);
				
				do {
					col[1] = Utility.rand(min, max);
				}while(col[1] == col[0]);
			}
			
			/*for(int j = 0; j < num; j++) {
					
				do{
					x = Controllo.rand(min, max);
				}while(Controllo.search(col, x));
				
				col[j] = x;
				
				
			}*/
			
			if(num == 2)
				if(col[0] > col[1]){
					temp = col[1];
					col[1] = col[0];
					col[0] = temp;
				}
			
			c.insertCol(col, i, num);
			
		}
		
		return c; 
		
	}
	
	public boolean equals(Cartella contr) {
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 2; j++) {
				
				if(contr.cart[i][j] != 0) {
					if(contr.cart[i][j] != this.cart[i][0] && contr.cart[i][j] != this.cart[i][1]){return false;}
				}
			}
		}
		
		return true;
	}
	
	
	
	public void visualizzaCartella2() {
		
		
		int[][] vCard = new int[3][9];
		int[] contRig = {0,0,0};
		int rig = 0;
		
		for(int k = 0; k < 3; k++) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					do {
						rig = Utility.rand(0, 2);
					}while(contRig[rig] < 4 || vCard[i][rig] != 0 );
				}
				
			}
			
			
		}
		
		
		
		
		
	}
}
