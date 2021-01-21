package tombola;

import java.util.Arrays;
import java.util.Random;

public class Cartella {
	private static EstrazioneCartella estrazione = new EstrazioneCartella();
	private static int nCartella;
	private int nC;
	private int numeri[][] = new int[3][9];

	public Cartella() {
		++nCartella;
		this.nC=nCartella;
		generaCartella();
		if (nCartella % 6 == 0)
			estrazione.ripristinaCartella();

	}

	public void generaCartella() {
		
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
}
