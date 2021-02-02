package it.beije.ananke;

public class WrapperTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = Integer.parseInt("123");
		System.out.println(i);
		Integer j = Integer.valueOf("125"); //funziona con interi e stringhe sicuramente
		System.out.println(j);
		
		Integer k = Integer.parseInt("12"); //credo che qui faccia un autoboxing da solo
		System.out.println(k);

		Integer s = new Integer(23); // autoboxing
		System.out.println(s);
	}

}
