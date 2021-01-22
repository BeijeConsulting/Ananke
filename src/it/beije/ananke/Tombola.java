package it.beije.ananke;

import java.util.*;

public class Tombola {

	public Tombola(int num)
	{
		this.fill();
		for(int i=0;i<num;i++)
		{
			cards.add(new Cartella());
			if(cards.get(i).getCount()%6==0)
			{
				this.fill();
				cards.get(i).populate(pool);
			}
			else {
				cards.get(i).populate(pool);
				
			}
			
		}
			
	}

	
	
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
		
		Tombola tomb = new Tombola(6);
		tomb.allCards();
		
	}
	

private ArrayList<Cartella> cards = new ArrayList<>();	
private ArrayList<Integer> pool = new ArrayList<>();	
}

