package it.beije.ananke;

public class FibonScala{
	
	
	public static void main(String[] args)
	{
		
		long fbn1=0, fbn2=1, fbnsum, fib_arr[] = new long[50];
	
		fib_arr[0]=fbn1;
		
		System.out.println("a[0]: "+ fbn1);
	
		for(int i=0;i<50;i++)
		{
			fib_arr[i] = fbnsum = fbn1 + fbn2;
			
			System.out.println("a["+ i + "]: " + fib_arr[i]);
			
			fbn1=fbn2;
			fbn2 = fbnsum;
			
		}
		
		for(int i = 0; i<50; i++)
		{
			for(int j=0; j<=i; j++)
			{
				System.out.print(fib_arr[j]+",");
			}
			System.out.println("");
		}
		
	}
	
	
	
	
	
	
}