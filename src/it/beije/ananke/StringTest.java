package it.beije.ananke;

public class StringTest {

	public static void main(String[] args) {
		
	StringBuilder sb = new StringBuilder("Fruits");
	StringBuilder sb1 = new StringBuilder(sb.append(" are good"));
	
	
	System.out.println(sb.insert(2,"animals"));
	System.out.println(sb1);
	
	
	//Testing custom methods
	System.out.println(StringTest.append("Cavallo","cane"));
	System.out.println(StringTest.insert("Cavallo", 2, "cane"));
	System.out.println(StringTest.delete("Cavallo",1,"Cavallo".length()));
	System.out.println(StringTest.deleteCharAt("Cavallo", 5));
	System.out.println(StringTest.reverse("Cavallo"));
	
	}
	
	
	
	//<-----------Implement StringBuilder methods for String------------>
	
	
	//append method
	public static String append(String orig, String s)
	{
		return orig + s;
	}
	
	//insert method
	public static String insert(String orig, int offset, String s)
	{			
		String out = "";
		
		for(int i = 0; i<offset; i++)
		{
			out += orig.charAt(i);
		}
		
		out+=s;
		
		for(int j = offset; j<orig.length(); j++)
		{
			out += orig.charAt(j);
		}
		
		return out;
	}
	
	//delete method
	public static String delete(String orig, int start, int end)
	{
		String head = "", tail="", result="";
		
		for(int i=0; i<start;i++)
		{
			head+= orig.charAt(i);
		}
		
		for(int j=end; j<orig.length(); j++)
		{
			tail += orig.charAt(j);
		}
		
		result = head+tail;
		
		if(result.length() == 0)
		{
			System.out.println("Hai cancellato tutta la stringa");
			return result;
		}
		
		return result;
	}
	
	//deleteCharAt method
	public static String deleteCharAt(String orig, int index)
	{
	  String result = StringTest.delete(orig,index,index+1);
	
	  return result;
	}
	
	//reverse method
	public static String reverse(String orig)
	{
		String result = "";
		
		for(int i = orig.length()-1 ; i>=0; i--)
		{
			result += orig.charAt(i);
		}
		
		return result;
	}
	
}
