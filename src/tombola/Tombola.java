package tombola;

import java.util.*;

public class Tombola {
private List<Cartella> d ;
public Tombola(int x) {
	d=new ArrayList<>();
	Cartella c=null;
	for(int i=0;i<x;i++) {
		
		try {
			c = new Cartella();
			while(c.getnCartella()>6&& d.contains(c))
				try {
					c=new Cartella();
				} catch (ImpossibileGenerareCartella e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				d.add(c);
		} catch (ImpossibileGenerareCartella e) {
			// TODO Auto-generated catch block
			c.resetCartelle();
			d.clear();
			i=-1;
		}
		
	
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
		
Tombola napoli=new Tombola(30);
napoli.stampaCartelle();
	}

}
