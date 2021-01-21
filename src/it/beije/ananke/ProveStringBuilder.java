package it.beije.ananke;

import java.util.*;
public class ProveStringBuilder {
	public static void main(String[] args) {
		String mySurn ="";
		String myName = riempiStrBuilder();
		System.out.println("Stampiamo la stringa derivante dallo StringBuilder che rappresenta il nome: " + myName.trim());
		mySurn =provaStringhe(mySurn);
		System.out.println("Il cognome invece è: " + mySurn);
		boolean b = controlloStr(mySurn, myName);
		System.out.println("Il nome è uguale al cognome? " + b);
		op(mySurn, myName);
		String app = append("ciao ", "mondo");
		System.out.println(app);
		String rev = reverse(app);
		System.out.println(rev);
		String del = delete("Buongiorno",2,6);
		System.out.println(del);
		String delch = deleteCharAt("Buonanotte",5);
		System.out.println(delch);
		List<String> list1 = new ArrayList <String>();
		List<String> list2 = new ArrayList <String>();
		list1.add("Marco");
		list1.add("Fabio");
		list2.add("Fabio");
		list2.add("Marco");
		boolean b1 = equals(list1, list2);
		System.out.println("le due liste sono identiche? " + b1);
		String ins =insert("Buoorno", 3, "ngi");
		System.out.println(ins);

	}

	public static String riempiStrBuilder() {
		StringBuilder sb = new StringBuilder();
		sb.append("Marco");
		System.out.println("La lunghezza è " + sb.length());
		sb.insert(0, "Imperiale ");
		System.out.println("Lo SBuilder costrutito fin' ora è " + sb);
		System.out.println("Il primo carattere dell' SB è " + sb.charAt(0));
		String cognome = sb.substring(0, 9);
		String nome = sb.substring(10, 15);
		System.out.println("Il cognome : " + cognome);
		System.out.println("Il cognome : " + nome);
		sb.delete(0, 9);
		System.out.println("Dopo aver proceduto alla cancellazione del cognome mi rimane: " + sb);
		sb.deleteCharAt(0);
		sb.append(" ");
		String str = sb.toString();
		return str;
	}

	public static String provaStringhe(String str) {
		str = "   ImPe-RiAlE    ";
		str = str.trim();
		str = str.substring(0,4) + str.substring(5, str.length());
		str = str.toUpperCase();
		str = str.toLowerCase();
		str = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
		return str;
	}

	public static boolean controlloStr(String c, String n) {
		boolean b = false;
		if (c.equals(n)) b  = true;
		return b;
	}

	public static void op(String c,String n) {
		System.out.println("Il cognome inizia con m? " + c.startsWith("m"));
		System.out.println("Il cognome inizia con i? " + c.startsWith("I"));
		System.out.println("Il cognome finisce con e? " + c.endsWith("e"));
		System.out.println("Il cognome finisce con e? " + c.endsWith("E"));
		System.out.println("La lettera m nel nome si trova in posizione " + n.indexOf('m'));
		System.out.println("La lettera M nel nome si trova in posizione " + n.indexOf('M'));
	}

	public static String append(String origi, String s) {
		String z=origi+s;
		return z;
	}
	public static String reverse(String app) {
		String app1= "";
		for(int i=app.length()-1;i>=0;i--) {
			app1=app1+app.charAt(i);
		}
		return app1;
	}
	public static String deleteCharAt(String orig, int index) {
		String or ="";
		for(int i=0;i<orig.length();i++) {
			if(i>=index) {
			}
			else
				or=or+orig.charAt(i);
		}
		return or;
	}

	public static String delete(String orig, int start, int end) {
		String or ="";
		for(int i=0;i<orig.length();i++) {
			if(i>=start && i<= end) {
			}
			else
				or=or+orig.charAt(i);
		}
		return or;
	}
	public static boolean equals(List list1, List list2) {
		if(list1.size()!=list2.size())
			return false;
		for(int i=0;i<list1.size();i++) {
			if (!(list1.get(i).equals(list2.get(i))))
				return false;
		}
		return true;
	}
	public static String insert(String orig, int offset, String str) {
		String nuovaparola ="";
		for(int i=0;i<=orig.length();i++) {
			if (i==offset) {
				nuovaparola+=str;

			}
			else if (i>offset) {
				nuovaparola+=orig.charAt(i-1);
			}
			else {
				nuovaparola+=orig.charAt(i);
			}
		}
		return nuovaparola;
	}

}