package it.beije.ananke;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class Date {

	public static void main(String[] args) {
		LocalDateTime date = LocalDateTime.of(2018, 02, 13, 14,30, 25);
		System.out.println(LocalDate.now());
		System.out.println(LocalTime.now());
		System.out.println(LocalDateTime.now());
		System.out.println(LocalDate.of(2017,Month.AUGUST,17));
		System.out.println(LocalDateTime.of(2018, 02, 13, 14,30, 25));
		System.out.println(date.plusDays(5));
		System.out.println(date.minusHours(8));
		Period period = Period.ofMonths(2);
		System.out.println(date.minus(period));
		System.out.println(date.getMonth());
		System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm");
		System.out.println(date.format(f));
		f = DateTimeFormatter.ofPattern("MM d, yy, h:m");
		System.out.println(date.format(f));


	}
}
