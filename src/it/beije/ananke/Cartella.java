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
		
		for(int i=0; i<COLS; i++)
		{
			for(int k=0; k<ROWS; k++)
			{
				if(spaces.contains(i))
				{
					card[i][k]=0;
					
				}			
				else
				{
					
				}
			}
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
			spaces.add(gen.nextInt(9));   //RISOLVERE BUG SPAZI RIPETUTI. provare shuffle su list per cambiare k 
											//ogni volta
		}
		
		return spaces;
	}
	
	public int getCount()
	{
		return count;
	}
	
	
	
@Override
	public String toString() {
		return "";
	}



//class fields
private static final int COLS=3, ROWS=9;
private int[][] card = new int[COLS][ROWS];
private Random gen = new Random();
private static int count=0;
}
