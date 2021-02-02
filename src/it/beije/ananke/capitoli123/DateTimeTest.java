package it.beije.ananke.capitoli123;

import java.time.*;
import java.time.format.*;

public class DateTimeTest {
	public static void main(String[] args) {
		System.out.println(LocalDate.now());
		System.out.println(LocalTime.now());
		System.out.println(LocalDateTime.now());
		
		System.out.println("======================================");
		
		LocalDate date1 = LocalDate.of(1994,05,27);
		LocalDate date2 = LocalDate.of(1994, Month.MAY, 25);
		System.out.println(date1);
		System.out.println(date2);
		
		LocalTime time1 = LocalTime.of(15, 27, 2);
		System.out.println(time1);
		
		System.out.println("======================================");
		
		LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);
		System.out.println(dateTime1);
		
		LocalDate date3 = date1.plusYears(26).plusMonths(2).plusWeeks(3).minusDays(24);
		System.out.println(date3);

		Period period = Period.of(1, 0, 7);
		LocalDateTime date4 = dateTime1.plus(period);
		System.out.println(date4);
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MMMM YYYY");
		date4.format(f);
		System.out.println(date4.format(f));
		
	}

}
