package it.beije.ananke.vettori;

import java.awt.List;
import java.util.*;
import java.awt.*;


public class MyMethods {
	
	String append(String orig, String s) {
		String nuova = orig + s;
		return nuova;
	}
	
	
	String insert(String orig, int offset, String str) {
		String parte1 = null, parte2 = null;
		int i = 0;
		while(i < offset) {
			parte1 = parte1 + orig.charAt(i);
			i++;
		}
		
		while(i < orig.length()) {
			parte2 = parte2 + orig.charAt(i);
			i++;
		}
		
		return parte1 + str + parte2;
	}
	
	
	String delete(String orig, int start, int end) {
		String parte1 = null, parte2 = null;
		int i = 0;
		while(i < start) {
			parte1 = parte1 + orig.charAt(i);
			i++;
		}
		
		i = end;
		while(i < orig.length()) {
			parte2 = parte2 + orig.charAt(i);
			i++;
		}
		
		return parte1 + parte2;
	}
	
	
	String deleteCharAt(String orig, int index) {
		String parte1 = null, parte2 = null;
		int i = 0;
		while(i < index) {
			parte1 = parte1 + orig.charAt(i);
			i++;
		}
		
		i += 1;
		
		while(i < orig.length()) {
			parte2 = parte2 + orig.charAt(i);
			i++;
		}
		
		return parte1 + parte2;
	}

	
	String reverse(String orig) {
		String inversa = null;
		int i = 1;
		
		while(i <= orig.length()) {
			inversa = inversa + orig.charAt(orig.length() - i);
			i++;
		}
		
		return inversa;
	}
	
	boolean myEquals(List list1, List list2) {
		
		return true;
	}
}
