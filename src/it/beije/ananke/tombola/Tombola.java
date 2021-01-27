package it.beije.ananke.tombola;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Tombola {

	public static void main(String[] args) {
		ArrayList<Cartella> cartelle = new ArrayList<Cartella>();
		ArrayList<Integer> numeriDisponibili = new ArrayList<Integer>();
		int contatorecartella=0;
		int contanumericolonna=0;
		int numerolimitecolonna =0;
		int numeripercartella=0;
		int x=0;
		int [] numeriEstratti = new int [15];
		int contaNumeriEstr =0;
		boolean b = false;
		for(int i=1;i<=90;i++) {
			numeriDisponibili.add(i);
		}
		while(numeriDisponibili.size()!=0) {

			cartelle.add(new Cartella(contatorecartella));
			System.out.println("Ho creato la cartella numero " + contatorecartella);
			numeripercartella=0;
			numerolimitecolonna=0;
			for(int i=0;i<9;i++) {
				//	System.out.println("sono nella colonna "+ i);
				if (numeripercartella == 15) {
					break;	
				}
				if((i==3 && numeripercartella<=3) || (i==4 && numeripercartella<=5)||(i==5 && numeripercartella<=7)||(i==6 && numeripercartella<=9) ||(i==7 && numeripercartella<=11)||(i==8 && numeripercartella<=13) ) {
					contanumericolonna = 2;
				}
				else if ((i==8 && numeripercartella==14)) {
					contanumericolonna = 1;
				}
				else {
					contanumericolonna = (int) ((Math.random()*3));
				}
				//	System.out.println("Per questa colonna estrarremo numeri : "+ contanumericolonna);
				if(i==0)
					numerolimitecolonna += 9;
				else if (i>=1 && i<=7)
					numerolimitecolonna += 10;
				else
					numerolimitecolonna += 11;
				//	System.out.println(numerolimitecolonna);
				for(int j=0;j<3;j++) {
					if (contanumericolonna==0) {
						break;
					}
					//	System.out.println("sono nella riga "+ j);
					while(b==false) {
						//		System.out.println("Sono nel while");
						x =	(int) ((Math.random()* numerolimitecolonna) + 1);
						if(x<(numerolimitecolonna-10)) {
							//System.out.println("il numero seguente è troppo basso: " + x);
						}
						else {
							b=true;	
							//System.out.print(x + " ");
							//	numeriEstratti[contaNumeriEstr]= x;
							contaNumeriEstr++;
							cartelle.get(contatorecartella).setNumeri(x, j, i);
						}
					}
					b=false;
					if(numeriDisponibili.contains(x)) {
						numeriDisponibili.remove(Integer.valueOf(x));
					}
					x=0;
					numeripercartella++;
					contanumericolonna--;
					if (contanumericolonna==0) {
						//						System.out.println("sono nel break");
						break;
					}
				}
				System.out.println("");
			}
			//faccio i 2 for che mi stampino col getter? la cartella
			contatorecartella++;
		}
		for(int i=0;i<cartelle.size();i++)
			cartelle.get(i).getNumeri();
	}
}