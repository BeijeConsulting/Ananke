package it.beije.ananke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.util.converter.LocalDateStringConverter;

public class Inverti{
	public static void main(String[] args){
		for(int i=1;i<10;i++){
			int k = 1;
			while(k<=i){
				System.out.print(k);
				k++;
			}
			if(i<8)
				System.out.print("		");
			else
				System.out.print("	");
			for(int j=10-i;j>0;j--){
				System.out.print(j);
			}
			System.out.println("");
			//		LocalDateTime d = LocalDateTime.of(2015, 5, 10, 11, 22, 33);
			//		Period p = Period.ofDays(1).ofYears(2);
			//		d = d.minus(p);
			//		DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
			//		System.out.println(f.format(d));
		}
	}
}