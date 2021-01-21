package tombola;

import java.util.*;

public class Tombola {
private List<Cartella> d ;
public Tombola(int x) {
	d=new ArrayList<>();
	for(int i=0;i<x;i++) {
		Cartella c=new Cartella();
	while(d.contains(c))
		c=new Cartella();
		d.add(c);
	}
}
public void stampaCartelle() {
	StringBuilder s=new StringBuilder();
	s.append("=================================================================================\n");
	s.append("\t\t\t\t   TOMBOLA\n");
	s.append("=================================================================================\n\n");
	for(int i=0;i<d.size();i++) {
		s.append((d.get(i).toString()));
		if(i%2==0)
			s.append("");
	}
	System.out.println(s.toString());
}
	public static void main(String[] args) {
		
Tombola napoli=new Tombola(2000);
napoli.stampaCartelle();
	}

}
