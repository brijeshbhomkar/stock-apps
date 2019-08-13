package com.algo.trading;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test {

	final DateTimeFormatter outformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
	final DateTimeFormatter informatter = DateTimeFormatter.ofPattern("yyyy-MM-ddThh.mm.ssZ");

	public static void main(String[] args) {
		System.out.println(LocalDate.now().toString());
		
	}

}
