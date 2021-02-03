package tombola;

import java.util.Arrays;
import java.util.Random;

public class Cartella {
	private static EstrazioneCartella estrazione = new EstrazioneCartella();
	private static int nCartella;
	private int nC;
	private int numeri[][] = new int[3][9];
private static  int emergenza;
	public Cartella() throws ImpossibileGenerareCartella {
		++nCartella;
		this.nC=nCartella;
		generaCartella();
		if (nCartella % 6 == 0)
			estrazione.ripristinaCartella();
		
	}
public void resetCartelle() {
	estrazione.ripristinaCartella();
	nCartella=0;
	nC=0;
}
	public void generaCartella() throws ImpossibileGenerareCartella {
		
		Random r = new Random();
		if(nC>6) {
		for (int i = 0; i < 3; i++) {
			int estratti = 0;
			while (estratti != 5) {
				for (int j = 0; j < 9; j++) {
					if (r.nextBoolean()&&numeri[i][j]==0) {
						int z=estrazione.getNumero(j + 1);
						while(this.controlloNumero(z)) 
							z=estrazione.getNumero(j + 1);
						numeri[i][j] = z;
						estratti++;
						if (estratti == 5)
							break;
						
					}
				}

			}
			}
		}else {
		int numero;
		
		for(int i=0;i<3;i++) {
			int estr = 0;
			emergenza=0;
		while(estr!=5) {
			boolean ripetizione=true;
			do {
			numero=estrazione.getNumeroSR();
			int colonna;
			if(numero==90)
				colonna=8;
			else
			 colonna=numero/10;
		
			if(	numeri[i][colonna]==0&& (controllodoppioColonna(colonna)||nC>=5)) {
			
				
						numeri[i][colonna]=numero;
							ripetizione=false;
							estr++;

			}else
			{
				estrazione.aggiungiNumero(numero);
				emergenza++;
				if(emergenza==100)
					throw new  ImpossibileGenerareCartella();
			}
			}while(ripetizione);
			
			
			}
			
		}
		
	}

			
		}
				
				
			public void swappynumber(int numero) {	 
				int colonna;
				if(numero==90)
					colonna=8;
				else
				 colonna=numero/10;
			if(numeri[0][colonna]==0) {
				numeri[0][colonna]=numero;
			}else {
				if(numeri[1][colonna]==0) {
					numeri[1][colonna]=numero;
				}else {
					if(numeri[2][colonna]==0)
						numeri[2][colonna]=0;
			}
			}
			
			}
	

	public int getnCartella() {
		return nCartella;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Numero cartella: " + this.nC + "\n---------------------------------------------------------------------------------\n");

	for (int j = 0; j < 9; j++) {
int zeri=0;
		for(int i=0;i<3;i++) {
	if(numeri[i][j]==0)
		zeri++;
		}
		if(zeri==0) {
			int [] vet= new int[] {numeri[0][j],numeri[1][j],numeri[2][j]};
			Arrays.sort(vet);
			numeri[0][j]=vet[0];
			numeri[1][j]=vet[1];
			numeri[2][j]=vet[2];
			
		}
		if(zeri==1) {
			int indice=0;
			for(int i=0;i<3;i++) {
				if(numeri[i][j]==0)
					indice=i;	
			}
			if(indice==0) {
				int [] vet= new int[] {numeri[1][j],numeri[2][j]};
				Arrays.sort(vet);
				numeri[1][j]=vet[0];
				numeri[2][j]=vet[1];
			}
			if(indice==1) {
				int [] vet= new int[] {numeri[0][j],numeri[2][j]};
				Arrays.sort(vet);
				numeri[0][j]=vet[0];
				numeri[2][j]=vet[1];
			}
			if(indice==2) {
				int [] vet= new int[] {numeri[0][j],numeri[1][j]};
				Arrays.sort(vet);
				numeri[0][j]=vet[0];
				numeri[1][j]=vet[1];
			}
			
		}
			
	}
	
	

		for (int i = 0; i < 3; i++) {
			s.append("|\t");
			for (int j = 0; j < 9; j++) {
				
				if (numeri[i][j] == 0)
					s.append("\t");
				else {
				
					s.append(numeri[i][j] + "\t");
				}
			}
			s.append("|\n");
		}
		s.append("\n---------------------------------------------------------------------------------\n");
		return s.toString();
	
	}

	


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartella other = (Cartella) obj;
		if (!Arrays.deepEquals(numeri, other.numeri))
			return false;
		return true;
	}

	private  boolean controlloNumero(int x) {
		
		for(int i=0;i<3;i++)
			for(int j=0;j<9;j++) 
				if(numeri[i][j]==x)
					return true;
			
		return false;
	
}
private  boolean controllodoppioColonna(int indice) {
		
		int conta=0;
		if(numeri[0][indice]!=0)
			conta++;
		if(numeri[1][indice]!=0)
			conta++;
		if(numeri[2][indice]!=0)
			conta++;
			
		if(conta<2)
		return true;
		else 
		return false;
	
}
}
