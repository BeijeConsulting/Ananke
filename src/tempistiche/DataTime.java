package tempistiche;

import java.time.*;
import java.time.format.*;

public class DataTime {
	
	private static LocalDate date;
	private static LocalTime time;
	private static LocalDateTime both;
	private static DateTimeFormatter std;
	
	
	public static void main(String... args) {
		
		both = LocalDateTime.now();
		System.out.println(both);
		
		time = LocalTime.now();
		System.out.println(time);
		
		date = LocalDate.now();
		System.out.println(date);
		
		both = LocalDateTime.of(2021, 3, 5, 11, 30);
		System.out.println(both);
		both = LocalDateTime.of(date, time);
		System.out.println(both);
		
		std = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		System.out.println(std.format(both));
		DateTimeFormatter mine = DateTimeFormatter.ofPattern("MMMM dd, yyyy, h:d");
		both = LocalDateTime.of(2021, 1, 29, 9, 30);
		System.out.println(mine.format(both));
		
		System.out.println(both.plusMonths(1));
		System.out.println(both);
		
		Period uno = Period.of(5, 0, 0);
		System.out.println(both.plus(uno));
		
		std = DateTimeFormatter.ofPattern("MM dd yyyy");
		date = LocalDate.parse("03 28 2021", std);
		System.out.println(date);
		
		
	}
	
}
