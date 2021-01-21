package it.beije.ananke;

public class MyString {

	public static void main(String... args){
		
		System.out.println(myAppend("Prova"," append"));
		System.out.println(myInsert("DoReMiSolLaSi",6,"Fa"));
		System.out.println(myInsert2("DoReMiSolLaSi",6,"Fa"));
		System.out.println(myDelete("0123456789",6,9));
		System.out.println(myDeleteCharAt("ABCDDEFGHI",3));
		System.out.println(myReverse("9876543210"));
		
		
		
	}
	
	public static String myAppend(String origin, String s){
		
		String res = new String();
		
		res = origin + s;
		
		return res;
	}
	
	// Overload
	public static String myAppend(String origin, char s){
		
		String res = new String();
		
		res = origin + s;
		
		return res;
	}
	
	public static String myInsert(String origin, int offset, String s){
		
		String res = new String();
		String dx = new String();
		String sx = new String();
		
		sx = origin.substring(0, offset);
		dx = origin.substring(offset);
		res = sx + s + dx ;
		
		return res;
	
	}
	
	//Senza subString
	public static String myInsert2(String origin, int offset, String s){
		
		String res = new String("");
		int i=0;
		
		
		for(char c: origin.toCharArray()){
			
			if(i==offset)
				res=myAppend(res,s);
			
			res=myAppend(res,c);
			i++;
		}
		
		
		return res;
	
	}
	
	public static String myDelete(String origin, int start, int end){
		
		String res = new String();
		String dx = new String();
		String sx = new String();
		
		sx = origin.substring(0, start);
		dx = origin.substring(end, origin.length());
		res = sx + dx ;
		
		
		return res;
	}
	
	public static String myDeleteCharAt(String origin, int index){
		
	
		String res = new String();
		String dx = new String();
		String sx = new String();
		
		sx = origin.substring(0, index);
		dx = origin.substring(index+1, origin.length());
		res = sx + dx ;
		
	
		return res;
	
	}
	
	public static String myReverse(String origin){
		
		String res = new String();
		
		for(int i = origin.length()-1 ; i>=0 ; i--) {
			res = myAppend(res, origin.charAt(i));
		}
		
		
		return res;
	}
	
}

