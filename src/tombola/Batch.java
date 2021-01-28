package tombola;

import java.util.*;

public class Batch {
	public final int RIGHE = 3;
	public final int COLONNE = 9;
	public final int OUT = 20;

	public ArrayList<Singola> tot = new ArrayList<>();
	public Random r = new Random();
	

	public Batch(ArrayList<Integer> set) {
		int prov, val;
		
		//inizializzo l'arraylist
		for(int i = 0; i < 6; i++) {
			tot.add(new Singola());
		}
		
		//inserisco per colonna, solo un numero
		for(int j = 0; j < COLONNE; j++) {
			
			//inserisco per cartella
			for(int i = 0; i < tot.size(); i++) {
				
				//estraggo a caso dove mettere l'elemento
				prov = r.nextInt(RIGHE);
				//prov = 0;
				while(prov < RIGHE) {
				
					//controllo se la casella è libera
					if(tot.get(i).isEmpty(prov, j)) {
						//check colonna
						if(tot.get(i).checkColonne(j) == 1) {
							//check colonna
							if(tot.get(i).checkRiga(prov) == 1) {
								val = extractNumber(j, set);
								tot.get(i).singular.get(j).set(prov, val);
								tot.get(i).checkr[prov] += 1;
								tot.get(i).checkc[j] += 1;
								set.set(set.indexOf(val), null);
								prov = OUT;
							}else {
								prov = r.nextInt(RIGHE);
							}
						}else {
							prov = OUT;
						}
					}else {
						prov = r.nextInt(RIGHE);
					}
				
				}			
			}
		}//fine inizializzazione 
		
		//stampa provv togliere
		for(Singola s : tot) {
			for(int i = 0; i < RIGHE; i++) {
				for(int j = 0; j < COLONNE; j++) {
					System.out.print(s.singular.get(j).get(i) + "\t");
				}
				System.out.print("\n");
			}
			System.out.print("\n\n");
		}

		System.out.println("Sono uscito dal while");
		
		
		
	}
				
	
	
	
	public int extractNumber(int decina, ArrayList<Integer> set) {
		int prov;

		do{
			if(decina == 8) {
				prov = r.nextInt(11) + 80;
			}else if(decina == 0){
				prov = r.nextInt(9) + 1;
			}else {
				prov = r.nextInt(10) + decina*10;
		
			}
		}while(!set.contains(prov));
		return prov;
	}
	
	public int secondExtraction(int decina, ArrayList<Integer> set) {
		ArrayList<Integer> prov = new ArrayList<>();
		int cont = 0;
		
		if(decina == 0) {
			while(cont < COLONNE) {
				if(set.get(cont) != null) {
					prov.add(set.get(cont));
				}
				cont++;
			}
		}else if(decina == 8) {
			cont = decina*10 - 1;
			while(cont < 90) {
				if(set.get(cont) != null) {
					prov.add(set.get(cont));
				}
				cont++;
			}
		}else {
			cont = decina*10 - 1;
			while(cont < decina*10 + 10) {
				if(set.get(cont) != null) {
					prov.add(set.get(cont));
				}
				cont++;
			}
		}
		
		cont = r.nextInt(prov.size());
		return prov.get(cont);
		
	}
	
	
	public boolean areThereAny(int decina, ArrayList<Integer> set) {
		int i = 0;
		if(decina == 0) {
			while(i < COLONNE - 1) {
				if(set.get(i) != null) {
					System.out.println("THERE ARE PRIMA COLONNA");
					return true;
				}
				i++;
			}
			return false;
		}else if(decina == 8) {
			i = 79;
			while(i < 90) {
				if(set.get(i) != null) { 
					System.out.println("THERE ARE ULTIMA COLONNA");
					return true;
				}
				i++;
			}
			return false;
		}else {
			i = decina*10 - 1;
			while(i < decina*10 + 10) {
				if(set.get(i) != null) { 
					System.out.println("THERE ARE COLONNA " + ++decina);
					return true;
				}
				i++;
			}
			return false;
		}
	
	}

}
