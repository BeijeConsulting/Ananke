package it.beije.ananke;

import java.util.ArrayList;
import java.util.List;

public class MyString {

	public static void main(String[] args) {
		System.out.println(MyString.append("hel", "lo"));
		System.out.println(MyString.insert("hello", 0, "-"));
		System.out.println(MyString.insert("hello", 5, "-"));
		System.out.println(MyString.insert("hello", 2, "-"));
		System.out.println(MyString.delete("hello", 1, 3));
		System.out.println(MyString.deleteCharAt("hello", 1));
		System.out.println(MyString.reverse("hello"));
		ArrayList<Integer> lista1 = new ArrayList<>();
		lista1.add(1);
		lista1.add(2);
		lista1.add(3);
		ArrayList<Integer> lista2 = new ArrayList<>();
		lista2.add(1);
		lista2.add(2);
		lista2.add(3);
		
		System.out.println(lista1.size());
		
		System.out.println(MyString.equals(lista1, lista2));
		System.out.println(lista1.size());
		System.out.println(lista1.toString());
		System.out.println(lista2.size());
		System.out.println(lista2.toString());

	}

	public static String append(String orig, String s) {
		return orig + s;
	}
	
	public static String insert(String orig, int offset, String str) {
		String s="";
		
		for(int i=0; i<offset; i++) {
			s += orig.charAt(i);
		}
		
		s += str;
		
		for(int i=offset; i<orig.length(); i++) {
			s += orig.charAt(i);
		}
		return s;
	}
	
	public static String delete(String orig, int start, int end) {
		String s="";
		
		for(int i=0; i<start; i++) {
			s += orig.charAt(i);
		}
				
		for(int i=end; i<orig.length(); i++) {
			s += orig.charAt(i);
		}
		return s;
	}
	
	public static String deleteCharAt(String orig, int index) {
		String s="";
		
		for(int i=0; i<index; i++) {
			s += orig.charAt(i);
		}
				
		for(int i=index+1; i<orig.length(); i++) {
			s += orig.charAt(i);
		}
		return s;
	}
	
	public static String reverse(String orig) {
		String s="";

		for(int i=orig.length()-1; i>=0; i--) {
			s += orig.charAt(i);
		}
		
		return s;
	}
	
	public static boolean equals(List list1, List list2) {
		if(list1.size() != list2.size()) {
			return false;
		}else {
			int i = 0;
			while(list1.size()>0) {
				Object obj1 = list1.remove(i);
				Object obj2 = list2.remove(i);
				if(obj2.equals(obj1)) {
					list1.remove(obj1);
				}else {
					return false;
				}
			}
		}return true;
	}
}
