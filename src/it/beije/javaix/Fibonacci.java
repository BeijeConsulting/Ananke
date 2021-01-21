package it.beije.javaix;

public class Fibonacci{
	
	public long[] serieFibonacci(){
	
	long [] f = new long[50];
		f[0] = 0;       
		f[1] = 1;
		f[2] = 1;
        for (int i = 2; i<50; i++){
            f[i] = f[i - 1] + f[i - 2]; 
		}
		return f;
		}
	public void stampaSerie(){
		
		for(int i=0;i<50;i++)
			System.out.println(this.serieFibonacci()[i]);
		
	}
	public void stampaInScala(){
		
		for(int i=0;i<50;i++){
			for(int j=0; j<i;j++)
			System.out.print(this.serieFibonacci()[j]+" ");
		System.out.println(" ");
		}
	}
	

	
	public static void main(String [] args){
				Fibonacci f=new Fibonacci();
				f.stampaSerie();
				f.stampaInScala();
				
	}
}