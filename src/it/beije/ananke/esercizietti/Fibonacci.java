package it.beije.ananke.esercizietti;

import java.util.Random;
public class Fibonacci{
	public static void main(String[] numRic){
		
		//int ricorrenze = Integer.parseInt(numRic[0]);
		int ricorrenze = 15;
		
		long penultimoNumero = 0;
		long ultimoNumero = 1;
		long prossimoNummero;
		
		/*
		//primo ciclo che stampa le ricorrenze in una singola riga.
		//int non basta per contenere i primi 50 elementi della serie di fibonacci.
		
		for(int i = 0; i < ricorrenze; i++){
			
			if(i == 0)
				System.out.print(penultimoNumero);
			if(i == 1)
				System.out.print(", " + ultimoNumero);
			
			prossimoNummero = penultimoNumero + ultimoNumero;
			
			System.out.print(", " + prossimoNummero);
			
			penultimoNumero = ultimoNumero;
			ultimoNumero = prossimoNummero;
			
		}
	
		*/
		
		long[] serie = new long[ricorrenze];
		
		////////////////////////////////////////////////////////////////////////////////////////////
		//secondo ciclo che stampa le ricorrenze in scala, salvando i numeri della serie in un array
		////////////////////////////////////////////////////////////////////////////////////////////
		
		/*
		
		
		//prima popolo l'array
		
		for(int i=0; i<ricorrenze; i++){
			if(i==0){
				serie[i]=0;
				continue;
			}
			if(i==1){
				serie[i]=1;
				continue;
			}
			serie[i]= serie[i-2] + serie[i-1];
		}
		
		//stampo
		
		for(int i=0; i<ricorrenze; i++){
			for(int j=0; j<i+1; j++){
				System.out.print(serie[j]+" ");
			}
			System.out.print("\n");
		}
		
		*/
		
		////////////////////////////////////////////////////////////////////////////////////////////
		//vorrei stampare in modo simpatico i numeri
		////////////////////////////////////////////////////////////////////////////////////////////
		
		Random rand = new Random();
		
		//prima popolo l'array
		
		for(int i=0; i<ricorrenze; i++){
			if(i==0){
				serie[i]=0;
				continue;
			}
			if(i==1){
				serie[i]=1;
				continue;
			}
			serie[i]= serie[i-2] + serie[i-1];
		}
		
		//stampo
		
		for(int i=0; i<ricorrenze; i++){
			int random = rand.nextInt(11);
			for(int j=0; j<random; j++)
				System.out.print("\t");
			System.out.println(serie[i]);
		}
		
	}
}