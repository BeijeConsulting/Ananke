package it.beije.ananke;

import java.util.ArrayList;
import java.util.List;

public class MyStringTest {
	
	public static void main(String[] args) {
		String s = "Ciao";
		
		String s2 = MyString.append(s, "Cavallo");
		System.out.println(s2);
		
		String s3 = MyString.insert(s2, 5, "Vuoto");
		System.out.println(s3);
		
		String s4 = MyString.delete(s3, 3, 5);
		System.out.println("s4 :" + s4);
		
		String s5 = MyString.deleteCharAt(s4, 3);
		System.out.println("s5: " + s5);
		
		String s6 = MyString.reverse(s5);
		System.out.println("s6: " +s6);
		
		String str = "Ciao";
		System.out.println("Ciao"==str);
		StringBuilder sb = new StringBuilder(str);
		System.out.println("Ciao".equals(str));
		
		
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		
		list1.remove(0);

		list1.add(s);
		list1.add(s2);
		list1.add(s3);
		list1.add(s4);
		list1.add(s5);
		
		list2.add(s);
		list2.add(s2);
		list2.add(s3);
		list2.add(s4);
		list2.add(s5);
		
//		System.out.println(MyString.equals(list1, list2));
//		list2.remove(2);
//
//		System.out.println(MyString.equals(list1, list2));
	}

	
}
