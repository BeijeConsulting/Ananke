package it.beije.ananke.capitoli123;

import java.util.Locale;

public class UppercaseString {
    public static void main(String[] args) {
        String s = "java";
        s= s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
        System.out.println(s);
    }
}
