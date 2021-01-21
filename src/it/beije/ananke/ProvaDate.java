package it.beije.ananke;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ProvaDate {
	
	public static void main(String... args) {
		
		LocalDate temp1 = LocalDate.of(1974, Month.MARCH, 14);
		Period P = Period.ofYears(2);
		temp1.plus(P);
		System.out.println(temp1);
		temp1.plusDays(34).plusMonths(1).plusYears(7); // NON CAMBIA NULLA SERNZA RIASSEGNARE
		System.out.println(temp1);					   // PERCHE' è IMMUTABILE
		
		temp1 = temp1.plusDays(34).plusMonths(1).plusYears(7);
		System.out.println(temp1);	
		
		System.out.println("Giorno del mese: " + temp1.getDayOfMonth());
		System.out.println("Mese: " + temp1.getMonth());	
		System.out.println("Anno: " + temp1.getYear());
		
		DateTimeFormatter type = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		System.out.println(temp1.format(type));
		
		
	}
}
