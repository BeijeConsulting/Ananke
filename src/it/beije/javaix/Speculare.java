package it.beije.javaix;
public class Speculare{
	
	public void speculare(){
			int [] mex=new int[10];
		
		for(int i=0;i<10;i++){
			mex[i]=i+1;
		}

   for(int i=0;i<10;i++)
	{
		for (int j=0; j<=i; j++){
			
		System.out.print(mex[j]+"      ");
		
		
		
		}
		for (int j=9; j>=i; j--){
			
			System.out.print("     "+ mex[j]);		
			}
	
		System.out.println("");
	}
		
	}
	public static void main(String[] argomenti){
Speculare s= new Speculare();
s.speculare();
	
		
	}
}