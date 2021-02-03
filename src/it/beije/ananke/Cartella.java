package it.beije.ananke;

import java.util.*;

public class Cartella {
	
	
	//CONSTRUCTOR
	public Cartella()
	{
		
		count++;
	}
	
	
	//METHODS
	
	public int[][] populate(ArrayList<Integer> pool)
	{
		ArrayList<Integer> spaces = this.setSpaces();
		
		int limit = 0;
		
		for(int i=0; i<ROWS; i++)
		{
			for(int k=0; k<COLS; k++)
			{
				if(spaces.contains(k))
				{
					card[i][k]=0;
					
				}			
				else
				{
					if(limit<=2)
					{
						card[i][k]=decine(k,pool);
						limit++;
						if(i!=0)
						{
						if(card[i][k]<card[i-1][k])
						{
							int temp = card[i-1][k];
							card[i-1][k]= card[i][k];
							card[i][k] = temp;
						}
					else {
						card[i][k]=0;
					}
					}
				}
			}
		}
			spaces.clear();
			this.setSpaces();
		}	
		
		return card;
	}
	
	
	public int decine(int num, ArrayList<Integer> pooly)
	{
		int generated;
		
		switch(num)
		{
		case 0: generated = gen.nextInt(9)+1;
		do {
			 generated = gen.nextInt(9)+1;
		}while(!pooly.contains(generated));
			return generated;
		case 1: generated = gen.nextInt(10)+10;
				do {
					 generated = gen.nextInt(10)+10;
				}while(!pooly.contains(generated));
				return generated;
		case 2: generated = gen.nextInt(10)+20;
		do {
			 generated = gen.nextInt(10)+20;
		}while(!pooly.contains(generated));
		return generated;
		case 3: generated = gen.nextInt(10)+30;
		do {
			 generated = gen.nextInt(10)+30;
		}while(!pooly.contains(generated));
		return generated;
		case 4: generated = gen.nextInt(10)+40;
		do {
			 generated = gen.nextInt(10)+40;
		}while(!pooly.contains(generated));
		return generated;
		case 5:generated = gen.nextInt(10)+50;
		do {
			 generated = gen.nextInt(10)+50;
		}while(!pooly.contains(generated));
		return generated;
		case 6: generated = gen.nextInt(10)+60;
		do {
			 generated = gen.nextInt(10)+60;
		}while(!pooly.contains(generated));
		case 7: generated = gen.nextInt(10)+70;
		do {
			 generated = gen.nextInt(10)+70;
		}while(!pooly.contains(generated));
		case 8: generated = gen.nextInt(11)+81;
		do {
			 generated = gen.nextInt(11)+81;
		}while(!pooly.contains(generated));
		default : break;
		}
		
		return 0;
	}
	
	
	//Array contains random spaces for support card populating
	public ArrayList<Integer> setSpaces()
	{
		ArrayList<Integer> spaces = new ArrayList<>();
		
		for(int i=0; i<4;i++)
		{
			spaces.add(gen.nextInt(9));   
		}
		
		return spaces;
	}
	
	public int getCount()
	{
		return count;
	}
	
	
	public void  print()
	{
		for(int i = 0; i<ROWS; i++)
		{
			for(int k=0; k<COLS ; k++)
			{
				if(card[i][k]==0)
				{
					System.out.print(" ");
				}
				else System.out.println(card[i][k]);
			}
		}
	}
	
	
	



//class fields
private static final int COLS=9, ROWS=3;
private int[][] card = new int[ROWS][COLS];
private Random gen = new Random();
private static int count=0;
}
