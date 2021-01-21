package it.beije.ananke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.time.*;

public class ProvaArrayList {

	public static void main(String[] args) {
		int [] numeriprimi = {1,3,5,7,11,13,17,23,29,31,37,41,47,53,59,97,89,83,79,73,71,67};
		Arrays.sort(numeriprimi);
		for(int  i=0;i<numeriprimi.length;i++) {
			System.out.println(numeriprimi[i]);
		}
		System.out.println("Il numero primo 41 è in posizione: "+Arrays.binarySearch(numeriprimi, 41));
		int x = Arrays.binarySearch(numeriprimi, 42);
		if (x<=0) {
			System.out.println("Il numero primo 42 non è un numero primo !");
		}
		else {
			System.out.println("Il numero primo 42 è in posizione: " + x);
		}
		int[] numeripari = new int[5];
		for(int i=0;i<numeripari.length;i++) {
			numeripari[i]= i*2;
			System.out.println(numeripari[i]);
		}
		System.out.println(numeripari.length);
		System.out.println("Se dovessi aggiungereil numero 3 alla lista dei numeri pari, questo andrebbe in posizione: " + ((Arrays.binarySearch(numeripari, 3)*-1) + -1));
		String[] people[] = new String [4][2];
		people[0][0] = "Karol";
		people[0][1] = "K";
		people[1][0] = "Stefano";
		people[1][1] = "B";
		people[2][0] = "Alessandro";
		people[2][1] = "S";
		people[3][0] = "Marco";
		people[3][1] = "Imp";
		for(int i=0;i<4;i++) {
			for (int j=0;j<people[i].length;j++) {
				if(j==0)
					System.out.println("Il nome è: " + people[i][0]);
				else
					System.out.println("Il cognome è: " + people[i][1]);	
			}
		}

		ArrayList<String> personaggiGreci = new ArrayList<>();
		personaggiGreci.add("Omero");
		personaggiGreci.add("Calliope");
		personaggiGreci.add("Aliseo");
		personaggiGreci.add("Apollo");
		personaggiGreci.add("Achille");
		personaggiGreci.add(0, "Cicerone"); // aggiunto al primo elemento
		System.out.println(personaggiGreci);
		personaggiGreci.remove(2); // rimuovo calliope
		System.out.println(personaggiGreci);
		personaggiGreci.remove(0); // rimuovo il primo elemento dell ArrayList
		System.out.println(personaggiGreci);
		personaggiGreci.set(1, "Cicerone"); // Sostituisco al secondo elemento, quello in pozione 1, cicerone
		System.out.println(personaggiGreci);
		System.out.println("L? arrayList contiene il perosnaggio omero? " + personaggiGreci.contains("Omero"));
		ArrayList<String> personaggiGreci2 = personaggiGreci;
		System.out.println("Provo a fare un altro AL" + personaggiGreci2);
		System.out.println("I due AL sono identici? " + personaggiGreci.equals(personaggiGreci2));
		Collections.sort(personaggiGreci);
		System.out.println("Eccola lista ordinata: " + personaggiGreci);
		System.out.println("Dopo l' ordinamento sono ancora identici? " + personaggiGreci.equals(personaggiGreci2) + "Perchè puntano allo stesso oggetto");
		personaggiGreci2.add("aaaaaaaaa");

		System.out.println("L? arrayList dei personaggi greci è vuoto? " + personaggiGreci.isEmpty());
		System.out.println("No ha " + personaggiGreci.size() + " Elementi, ma ora procedo a svuotarlo");
		personaggiGreci.clear();
		System.out.println("Infatti adesso ha " + personaggiGreci.size() + " Elementi");
		System.out.println("L? arrayList contiene il perosnaggio omero? " + personaggiGreci.contains("omero"));
		System.out.println("---------------------------------------------------------------------------------");
		//QUalche prova con le classi wrapper
		ArrayList<Integer> anniNascita = new ArrayList<Integer>();
		anniNascita.add(22);
		anniNascita.add(11);
		anniNascita.add(44);
		anniNascita.add(55);
		Collections.sort(anniNascita);
		System.out.println("Ordino in modo crescente gli anni appena inseriti " + anniNascita);
		x = anniNascita.get(0);
		System.out.println(x);
		int y = 77;
		anniNascita.add(y);
		System.out.println(anniNascita);
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Che giorno e' oggi? " + LocalDate.now());
		System.out.println("Che ore sono in questo istante? " + LocalTime.now());
		System.out.println("Posso scrivere tutto assieme: " + LocalDateTime.now());
		LocalDate compleanno = LocalDate.of(1997, Month.JULY, 22);
		LocalDate onomastico = LocalDate.of(1997, 07, 22);
		System.out.println("sono nato il " + compleanno + " e San Marco e' il " + onomastico);
		System.out.println("Aggiungiamo 10 giorni al mio compleanno ");
		compleanno = compleanno.plusDays(10);
		System.out.println(compleanno + " il 32 luglio non esiste");
		compleanno.plusMonths(5);
		System.out.println("Dall' uno agosto, se aggiungiamo 5 mesi finiamo al ..." + compleanno + " NON E' CAMBIATO NULLA PERCHè NON ABBIAMO ASSEGNATO NIENTE... RIFACCIAMO");
		compleanno = compleanno.plusMonths(5);
		System.out.println(compleanno);
		compleanno = compleanno.plusWeeks(4);
		System.out.println(compleanno);
		compleanno= compleanno.plusYears(23).plusMonths(1);
		System.out.println(compleanno);
		System.out.println("Grazie a questo calcolo ci aggiungiamo che il calendario non arriva fino al 29 febbraio il che significa che questo sarà un anno bisestile.");
		LocalDate nascita = LocalDate.of(1997, 07, 22);
		LocalDate oggi = LocalDate.now();
		Period per = Period.ofYears(1);
		int eta= calcolaAnni(nascita, oggi, per);
		System.out.println("Alla fine di quest anno io avro' " + eta + " anni");	}
	public static int calcolaAnni( LocalDate nascita, LocalDate oggi, Period per) {
		int count =0;
		while (nascita.getYear() < oggi.getYear()){
			count++;
			nascita=nascita.plus(per);
		}
		
		return count;

	}
}