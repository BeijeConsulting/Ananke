package it.beije.ananke;

import java.util.List;

public class MyString {
	
	public static String append(String orig, String s) {
		orig += s;
		return orig;
	}
	
	public static String insert(String orig, int offset, String str) {
		String result = "";
		for(int i = 0; i < offset; i++) {
			result += orig.charAt(i);
		}
		result += str;
		for(int i = offset; i < orig.length() ; i++) {
			result += orig.charAt(i);
		}
		return result;
	}
	
	public static String delete(String orig, int start, int end) {
		String result = "";
		for (int i = 0; i < start; i++) {
			result += orig.charAt(i);
		}
		for (int i = end; i < orig.length(); i++) {
			result += orig.charAt(i);
		}
		return result;
	}
	
	public static String deleteCharAt(String orig, int index) {
		return delete(orig,index,index+1);
	}
	
	public static String reverse(String orig) {
		String result = "";
		for(int i = 0; i< orig.length(); i++) {
			char c = orig.charAt(orig.length()-1-i);
			result += c;
		}
		return result;
	}
	
	public static boolean equals(List list1, List list2) {
		if(list1.size() == list2.size()) {
			int count = 0;
			for (int i= 0; i< list1.size(); i++) {
				if(count == i) {
					if(list1.get(i).equals(list2.get(i))) {
						count++;
					}
				} else {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
