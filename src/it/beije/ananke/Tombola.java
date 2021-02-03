package it.beije.ananke;

import java.util.*;

public class Tombola {

		
	public void allCards() {
		
		for(Cartella car : cards)
			car.print();
			System.out.println("\n\n");
	}


	private void fill()
	{
		for(int i=1;i<=90;i++)
		{
			pool.add(i);
		}
	}
	
	public static void main(String[] args) {
		
		Tombola tomb = new Tombola();
		tomb.fill();
		
		for(int i=0;i<6;i++)
		{
			tomb.cards.add(new Cartella());
			if(tomb.cards.get(i).getCount()%6==0)
			{
				tomb.fill();
				tomb.cards.get(i).populate(tomb.pool);
			}
			else {
				tomb.cards.get(i).populate(tomb.pool);
				
			}
			
		}
		tomb.allCards();
		
	}
	

private ArrayList<Cartella> cards = new ArrayList<>();	
private ArrayList<Integer> pool = new ArrayList<>();	
}

