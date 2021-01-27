package it.beije.ananke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Cartella {
	private int[][] cartella = new int[3][9];	
	private static ArrayList<Integer> numeri= new ArrayList<>();
	
	public Cartella() {
		riempiCartella();
	}
	
	public int[][] getCartella() {
		return cartella;
	}
	
	public boolean equals(Cartella c1) {
		if(Arrays.equals(c1.cartella, this.cartella)) {
			return true;
		}else {
			return false;
		}
	}
	
	public void stampaCartella() {
		System.out.println("-----------------------------------------------");
		for(int riga[] : cartella) {
			for(int casella : riga) {
				if(casella == 0) {
					System.out.print("|    ");
				}else {
					if(casella < 10){
						System.out.print("| " + casella + "  ");
					}else {
						System.out.print("| " + casella + " ");
					}
				}
			}System.out.println("|");
		}System.out.println("-----------------------------------------------");
	}
	
	public int creaRandom() {

		Random r = new Random();
		return 1 + (r.nextInt(90));

	}
	
	public int controlloColonna(int numero) {
		if (numero > 0 && numero < 10) {
			return 0;
		}else if(numero > 9 && numero < 20) {
			return 1;
		}else if(numero > 19 && numero < 30) {
			return 2;
		}else if(numero > 29 && numero < 40) {
			return 3;
		}else if(numero > 39 && numero < 50) {
			return 4;
		}else if(numero > 49 && numero < 60) {
			return 5;
		}else if(numero > 59 && numero < 70) {
			return 6;
		}else if(numero > 69 && numero < 80) {
			return 7;
		}else if(numero > 79 && numero < 91) {
			return 8;
		}else{
			return -1;
		}
	}
	
	public boolean controlloVuoto(int colonna, int riga) {
		if(cartella[riga][colonna] == 0) {
//			if(riga == 0) {
//				return true;
//			}else if(riga == 1){
//				if(numero > cartella[0][colonna]) {
//					return true;
//				}
			//}else 
			if(riga == 2) {
//				if(numero > cartella[1][colonna] && numero > cartella[0][colonna]) {
					if(cartella[0][colonna]==0 || cartella[1][colonna]==0) {
						return true;
					}else{
						return false;
					}
//				}
			}return true;
		}else {
			return false;
		}
	}
	
	public boolean scambio(int num1,int num2){
		if(num1>num2) {
			return true;
		}else {
			return false;
		}
	}
	
	public void ordina() {
		for(int colonna = 0; colonna < 9; colonna++) {
			if(scambio(cartella[0][colonna], cartella[1][colonna])){
				int temp = cartella[0][colonna];
				cartella[0][colonna] = cartella[1][colonna];
				cartella[1][colonna] = temp;
			}
			if(scambio(cartella[1][colonna], cartella[2][colonna])){
				int temp = cartella[1][colonna];
				cartella[1][colonna] = cartella[2][colonna];
				cartella[2][colonna] = temp;
			}
		}
	}
	
	public void riempiCartella() {
		for(int riga = 0; riga < 3; riga++) {
			for(int num = 0; num < 5; num++) {
				int numero = creaRandom();
				int colonna = this.controlloColonna(numero);
				if(controlloVuoto(colonna, riga)) {
					if(!numeri.contains(numero)){
						cartella[riga][colonna] = numero;
						numeri.add(numero);
					}else {
						num--;
					}
				}else {
					num--;
				}
			}
		}
		ordina();
		
	}
}
