package it.beije.ananke;

import java.time.*;

public class DateAndTime {

	public static void main(String[] args) {
		
		
		//now method
		System.out.println("Local Date now: " + LocalDate.now());
		System.out.println("Local Time now: " + LocalTime.now());
		System.out.println("Local DateTime now: " + LocalDateTime.now());

		//of method
		System.out.println("Local Time hours min sec nanosec: " + LocalTime.of(23,23,23,230));
		System.out.println("Local Time hours min: " + LocalTime.of(22,59));
		System.out.println("Date and time with 'of' method: " + LocalDateTime.of(2021,02,23,8,40,35));
		
		
		//plusDays,plusWeeks,plusMonths,plusYears(pag 142)
		LocalDate date =  LocalDate.of(2021,Month.FEBRUARY,23);
		System.out.println("a date: " + date);
		System.out.println("date plus 3 days: " + date.plusDays(3));
		System.out.println("date plus 3 months: " + date.plusMonths(3));
		System.out.println("date plus 3 years: " + date.plusYears(3));
		
		//getMonth,getDay,getYear,getDayOf...
		LocalDate another = LocalDate.of(1994,06,20);
		System.out.println("another date: " + another);
		System.out.println("just day: " + another.getDayOfWeek());
		System.out.println("just month: " + another.getMonth());
		System.out.println("just year: " + another.getYear());
		System.out.println("n° day in the year: " + another.getDayOfYear());
	
	}

}
